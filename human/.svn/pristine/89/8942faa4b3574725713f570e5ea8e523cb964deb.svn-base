package com.human.continuedClass.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import com.human.utils.QRCodeUtil;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TestCardPdf {
    
    public static void main(String[] args) {
        createPdf();
    }
    public static void createPdf(){
        //创建一个A4纸大小的文档，上下左右边距分别为10,10,10,10
        Document  document= new Document (PageSize.A4,10,10,10,10); 
         try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:/image.pdf"));
            Image image = Image.getInstance(new URL("http://hrms-img.oss-cn-shanghai.aliyuncs.com/ruleBackPhoto/1498702479079.jpg"));
            image.scaleAbsolute(575,410);
            /* 设置图片的位置 */
            image.setAbsolutePosition(10, 10);
            image.setAlignment(Image.UNDERLYING);
            document.open();
            document.add(image);
            //加入原班表格
            /* 使用中文字体 */
            BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //标题字体
            Font titleFont = new Font(bf, 7, Font.NORMAL);
            //正文字体
            Font contextFont = new Font(bf,10, Font.NORMAL);
            Font contextFont1 = new Font(bf,10, Font.BOLD);
            // 第一行（标题）
            PdfPTable table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(380);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            //设置每一列所占的长度            
            addCommonCell(table,"贵潜校区 周鑫鑫老师 CE181201189 张雨学员 HF1811088  9/10-12/31 周六10:20-12:20",titleFont);           
            table.writeSelectedRows(0, -1,15,820, writer.getDirectContent());
            //加入续班表格上面的文字
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(380);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            addCommonCell(table,"刘威您好!根据您的学习情况我们为您推荐了以下课程",contextFont);
            table.writeSelectedRows(0, -1,180,600, writer.getDirectContent());
            //加入续班表格
            table = new PdfPTable(4);
            //设置每一列所占的长度
            table.setWidths(new int[]{15,30,15,40});
            //设置表格具体宽度
            table.setTotalWidth(400);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            addCommonCell2(table,"班号",contextFont1);
            addCommonCell2(table,"课程名称",contextFont1);
            addCommonCell2(table,"教师名称",contextFont1);
            addCommonCell2(table,"上课时间",contextFont1);            
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            table.writeSelectedRows(0, -1,180,580, writer.getDirectContent());
            //加入二维码图片
            String code_url="http://testwx.staff.xdf.cn/wechat/firstenrolltwostudent/insertstudentByQrcode.do?student_code=HF169086&school_id=25&wx_ext=xbk";//二维码链接;
            BufferedImage bufferedImage=QRCodeUtil.createImage(code_url);
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImage, "jpg", out);  
            byte[] b = out.toByteArray();  
            Image codeImg=Image.getInstance(b);
            image.scaleAbsolute(100,100);
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(120);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            table.addCell(codeImg);
            table.writeSelectedRows(0, -1,25,620, writer.getDirectContent());   
            
            //加入分割线
            Paragraph splitLine = new Paragraph();
            for(int i=0; i<150; i++){
                Chunk chunk = new Chunk("-",contextFont);  
                splitLine.add(chunk);  
              }  
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(570);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            table.addCell(splitLine);
            table.writeSelectedRows(0, -1,10,440, writer.getDirectContent()); 
            

            Image image1 =image;
            image1.scaleAbsolute(575,410);
            /* 设置图片的位置 */
            image1.setAbsolutePosition(10, 412);
            image1.setAlignment(Image.UNDERLYING);
            document.add(image1);
            // 第一行（标题）
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(380);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            //设置每一列所占的长度            
            addCommonCell(table,"贵潜校区 周鑫鑫老师 CE181201189 张雨学员 HF1811088  9/10-12/31 周六10:20-12:20",titleFont);           
            table.writeSelectedRows(0, -1,15,410, writer.getDirectContent());
            //加入续班表格上面的文字
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(380);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            addCommonCell(table,"刘威您好!根据您的学习情况我们为您推荐了以下课程",contextFont);
            table.writeSelectedRows(0, -1,180,195, writer.getDirectContent());
            //加入续班表格
            table = new PdfPTable(4);
            //设置每一列所占的长度
            table.setWidths(new int[]{15,30,15,40});
            //设置表格具体宽度
            table.setTotalWidth(400);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            addCommonCell2(table,"班号",contextFont1);
            addCommonCell2(table,"课程名称",contextFont1);
            addCommonCell2(table,"教师名称",contextFont1);
            addCommonCell2(table,"上课时间",contextFont1);            
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            addCommonCell2(table,"CE172218",contextFont);
            addCommonCell2(table,"初二尖子英语秋季班",contextFont);
            addCommonCell2(table,"周鑫鑫",contextFont);
            addCommonCell2(table,"9/10-12/31 周六10:20-12:20",contextFont);
            table.writeSelectedRows(0, -1,180,175, writer.getDirectContent());
            //加入二维码图片
            table = new PdfPTable(1);
            //设置表格具体宽度
            table.setTotalWidth(120);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            table.addCell(codeImg);
            table.writeSelectedRows(0, -1,25,218, writer.getDirectContent());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * 添加单元格公用方法
     * @param table
     * @param name
     * @param contextFont
     */
    public static void addCommonCell(PdfPTable table,String name,Font contextFont){
        PdfPCell cell = new PdfPCell();
        Paragraph para = new Paragraph(name,contextFont);
        //设置该段落为居中显示
        para.setAlignment(Cell.ALIGN_CENTER);
        cell.setPhrase(para);
        cell.setBorderWidth(0);
        table.addCell(cell);
    }
    
    /**
     * 添加单元格公用方法
     * @param table
     * @param name
     * @param contextFont
     */
    public static void addCommonCell2(PdfPTable table,String name,Font contextFont){
        PdfPCell cell = new PdfPCell();
        Paragraph para = new Paragraph(name,contextFont);
        //设置该段落为居中显示
        para.setAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(para);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20f);
        table.addCell(cell);
    }
}
