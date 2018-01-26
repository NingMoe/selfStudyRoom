package com.ls.spt.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author xdwang
 * 
 * @create 2015年9月8日 下午9:09:40
 * 
 * @email:xdwangiflytek@gmail.com
 * 
 * @description 文件操作
 * 
 */
public class FileUtil {

	/**
	 * 日志
	 */
	private final static Logger logger = LogManager.getLogger(FileUtil.class);

	/**
	 * @description 获取后缀名
	 * @author xdwang
	 * @create 2015年11月21日下午1:57:58
	 * @version 1.0
	 * @param filename
	 *            文件名
	 * @return 后缀名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * @description 读取文件文本
	 * @author xdwang
	 * @create 2015年11月21日下午1:58:35
	 * @version 1.0
	 * @param filePath
	 *            文件路径
	 * @return 文本内容
	 * @throws IOException
	 *             IO异常
	 */
	public static String readTxt(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		// 构造一个BufferedReader类来读取文件
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String s = null;
		try {
			// 使用readLine方法，一次读一行
			while ((s = br.readLine()) != null) {
				sb.append("\n" + s);
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}
    
    /**
     * 创建目录
     * 
     * @param dir 目录
     */
    public static void mkdir(String dir) {
        try {
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        } catch (Exception e) {
            logger.error("创建目录操作出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @description 写入txt
     * @author Yubin
     * @create 2015年12月7日上午11:20:53
     * @version 1.0
     * @param path
     * @param content
     */
    public static void writeTxt(String path, String fileName, String content) {
        try {
            //文件夹
            mkdir(path);
            //文件路径
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 如果追加方式用true
            FileOutputStream out = new FileOutputStream(file, true); 
            StringBuffer sb = new StringBuffer();
            sb.append("-----------" + sdf.format(new Date()) + "------------\r\n");
            sb.append(content + "\r\n");
            // 注意需要转换对应的字符集
            out.write(sb.toString().getBytes("utf-8"));
            out.close();
        } catch (IOException ex) {
            logger.error("创建文件操作出错: " + ex.getMessage());
            ex.getStackTrace();
        }
    }
    
    /** 
     * 删除某个文件夹下的所有文件夹和文件 
     * 
     * @param delpath 
     *            String 
     * @throws FileNotFoundException 
     * @throws IOException 
     * @return boolean 
     */  
    public static boolean deletefile(String delpath)  {
        try {
            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            }
            else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        logger.info(delfile.getAbsolutePath() + "删除文件成功");
                    }
                    else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                logger.info(file.getAbsolutePath() + "删除成功");
                file.delete();
            }

        }
        catch (Exception e) {
            logger.error("创建文件操作出错: " + e.getMessage());
            return false;
        }
        return true;
    } 
    
    public static void fileToZip(String sourcePath, String zipPath) {  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        try {  
            fos = new FileOutputStream(zipPath);  
            zos = new ZipOutputStream(fos);  
            writeZip(new File(sourcePath), "", zos);  
        } catch (FileNotFoundException e) {  
            logger.error("创建ZIP文件失败",e);  
        } finally {  
            try {  
                if (zos != null) {  
                    zos.close();  
                }  
            } catch (IOException e) {  
                logger.error("创建ZIP文件失败",e);  
            }  
  
        }  
    }  
  
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {  
        if(file.exists()){  
            if(file.isDirectory()){//处理文件夹  
                parentPath+=file.getName()+File.separator;  
                File [] files=file.listFiles();  
                if(files.length != 0)  
                {  
                    for(File f:files){  
                        writeZip(f, parentPath, zos);  
                    }  
                }  
                else  
                {       //空目录则创建当前目录  
                        try {  
                            zos.putNextEntry(new ZipEntry(parentPath));  
                        } catch (IOException e) {  
                            logger.error(e);
                        }  
                }  
            }else{  
                FileInputStream fis=null;  
                try {  
                    fis=new FileInputStream(file);  
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());  
                    zos.putNextEntry(ze);  
                    byte [] content=new byte[1024];  
                    int len;  
                    while((len=fis.read(content))!=-1){  
                        zos.write(content,0,len);  
                        zos.flush();  
                    }  
  
                } catch (FileNotFoundException e) {  
                    logger.error("创建ZIP文件失败",e);  
                } catch (IOException e) {  
                    logger.error("创建ZIP文件失败",e);  
                }finally{  
                    try {  
                        if(fis!=null){  
                            fis.close();  
                        }  
                    }catch(IOException e){  
                        logger.error("创建ZIP文件失败",e);  
                    }  
                }  
            }  
        }  
    }
    
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    public static File saveFile(InputStream is,String fileName)throws FileNotFoundException, IOException{
        File file=new File(fileName);        
        BufferedInputStream bis = new BufferedInputStream(is);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));  
        int len = -1;  
        while ((len = bis.read()) != -1) {  
            bos.write(len);     
        }  
        bos.flush(); 
        bos.close();  
        bis.close();       
        return file;       
    } 
    
    public static InputStream getImageStream(BufferedImage bufferedImage){ 
        InputStream is = null; 
        ByteArrayOutputStream bs = new ByteArrayOutputStream();           
        ImageOutputStream imOut=null; 
        try { 
            imOut = ImageIO.createImageOutputStream(bs); 
            ImageIO.write(bufferedImage, "png",imOut); 
            is= new ByteArrayInputStream(bs.toByteArray()); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }finally{
            try {
                imOut.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }  
        return is; 
    } 
}
