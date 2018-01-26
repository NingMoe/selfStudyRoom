package com.human.utils.mailUtils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.star.io.ConnectException;

public class FileToImgUtil {
	
	 public static final String OpenOffice_IP="127.0.0.1";
	 public static final int OpenOffice_Port=8100;
	 public static final String OpenOffice_HOME="C:\\Program Files (x86)\\OpenOffice 4\\program\\soffice.exe";
	 /**
	  * 常见图片格式
	  */
	 public static String[]imgFile=new String[]{"gif","jpg","bmp","png"};
	 
	 /**
	  * 常见文档格式
	  */
	 public static String[]docFile=new String[]{"txt","doc","docx","xls","xlsx","ppt","pptx","html"};
	 
	
	private static OpenOfficeConnection startOpenOffice(){
        //OpenOffice的安装目录，linux环境下需要手动启动openoffice服务
		//String OpenOffice_HOME = PropertiesUtil.getOpenOfficeParam("openOfficePath");
		//String OpenOffice_IP = PropertiesUtil.getOpenOfficeParam("ip");
		//int OpenOffice_Port = Integer.parseInt(PropertiesUtil.getOpenOfficeParam("port"));
        // 启动OpenOffice的服务
        String command = OpenOffice_HOME+ " -headless -accept=\"socket,host="+OpenOffice_IP+",port="+OpenOffice_Port+";urp;\"";
        try {
            Process pro = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建连接
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(OpenOffice_IP, OpenOffice_Port); 
        return connection;
    }

    private static void doc2Pdf(String docPath, String pdfPath) throws ConnectException, java.net.ConnectException {
        File inputFile = new File(docPath);
        File outputFile = new File(pdfPath);
        OpenOfficeConnection connection =startOpenOffice();
        connection.connect();
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();   
        DocumentFormat txt = formatReg.getFormatByFileExtension("odt") ;
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf") ;
        converter.convert(inputFile, txt, outputFile, pdf);
        connection.disconnect();
    }

       
    /**
     * 获取附件类型
     * @param docPath文件路径
     * @return
     */
    public static String getFileType(String docPath){  	
    	String fileType="";
    	String fileExtension=FilenameUtils.getExtension(docPath);
    	//判断是否是图片类型
    	if(Arrays.asList(imgFile).contains(fileExtension)){
    		fileType="0";  		
    	}else if(Arrays.asList(docFile).contains(fileExtension)){
    		fileType="1";   		
    	}else if("pdf".equalsIgnoreCase(fileExtension)){//判断是否是PDF的类型
    		fileType="2";
    	}else{
    		fileType="3";//其他类型 ：比如rar、jar、zip等
    	}
       return fileType;
    }
    
        
   /**
    *  根据附件的类型不同做不同的处理
    * @param docPath
    * @param imgDirPath
    */
    public static void dealFileByType(String docPath, String imgDirPath){
    	String fileType=getFileType(docPath);
    	if("0".equals(fileType)||"3".equals(fileType)){
    		return;
    	}else if("1".equals(fileType)){
    		doc2Imags(docPath,imgDirPath);
    	}else if("2".equals(fileType)){//第二步：判断附件是否是PDF
    		String fileName=FilenameUtils.getBaseName(docPath);
    		try {
				pdf2Imgs(docPath, imgDirPath,fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}    	
    }

    /**
     * 把ppt word excel等不是PDF文件生成图片文件
     * @param docPath 文件路径
     * @param imgDirPath 图片保存文件夹
     */
    public static void doc2Imags(String docPath, String imgDirPath){
        String pdfPath =String.format("%s%s.pdf",  FilenameUtils.getFullPath(docPath), FilenameUtils.getBaseName(docPath));
        try {
        	String fileName=FilenameUtils.getBaseName(docPath);
            doc2Pdf(docPath, pdfPath);
            pdf2Imgs(pdfPath, imgDirPath,fileName);
            File pdf =  new File(pdfPath);
            if(pdf.isFile()){
                pdf.delete();
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将pdf转换成图片
     * 
     * @param pdfPath
     * @param imagePath
     * @return 返回转换后图片的名字
     * @throws Exception
     */
    private static List<String> pdf2Imgs(String pdfPath, String imgDirPath,String fileName) throws Exception {
        Document document = new Document();
        document.setFile(pdfPath);
        float scale = 1f;//放大倍数
        float rotation = 0f;//旋转角度
        List<String> imgNames = new ArrayList<String>();
        int pageNum = document.getNumberOfPages();
        File imgDir = new File(imgDirPath);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        for (int i = 0; i < pageNum; i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String filePath = imgDirPath + File.separator +fileName+(i+1)+ ".jpg";
                File file = new File(filePath);
                ImageIO.write(rendImage, "jpg", file);
                imgNames.add(FilenameUtils.getName(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            image.flush();
        }
        document.dispose();
        return imgNames;
    }
    
	public static void main(String[] args) {
		String docPath = "E:/doc/resume.html";
        String imgDirPath = "E:/imgDirPath/";
        dealFileByType(docPath,imgDirPath);

	}

}
