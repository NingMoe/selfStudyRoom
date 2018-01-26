package com.human.utils.verificationCode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 /**
 * ClassName: ImageTool <br/>
 * Description: get BufferedImage creat image<br/>
 * Date: 2015-3-10 下午05:05:38 <br/>
 * <br/>
 * 
  * @author yptian@aliyun.com
  * 
  * first made
  * @version 1.0.0<br/>
  *
  */
public class ImageTool {
    //LOG
    private static final Logger LOG =LogManager.getLogger(VerificationCodeTool.class);
    //image
    private BufferedImage image;   
    
    /**
     * createImage : out dest path for image
     * @param fileLocation dest path
     */
    private void createImage(String fileLocation) {        
        try {            
            FileOutputStream fos = new FileOutputStream(fileLocation);            
            BufferedOutputStream bos = new BufferedOutputStream(fos);  
            ImageIO.write(image, "png", bos);
//            com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(bos);            
//            encoder.encode(image);            
            bos.flush();
            bos.close(); 
            LOG.info("@2"+fileLocation+"图片生成输出成功");   
       } catch (Exception e) {            
            LOG.info("@2"+fileLocation+"图片生成输出失败",e);    
       }
    }
    
   /**
    * graphicsGeneration : create image
    * @param imgurl display picture url . eg:F:/imagetool/7.jpg<br/>
    * @param imageOutPathName image out path+naem .eg:F:\\imagetool\\drawimage.jpg<br/>
    * @param variableParmeterLength ; int, third parameter length.<br/>
    * @param drawString variableParmeterLength ;String [] .<br/>
    */
    public void graphicsGeneration(String imgurl,String imageOutPathName,int variableParmeterLength,String...drawString) {
        //The width of the picture
        int imageWidth = 500;   
        //The height of the picture
        int imageHeight = 400;   
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);        
        Graphics graphics = image.getGraphics();        
        graphics.setColor(Color.WHITE);    
        //drawing image
        graphics.fillRect(0, 0, imageWidth, imageHeight);        
        graphics.setColor(Color.BLACK);        
        //draw string  string , left margin,top margin
        for(int i = 0,j = variableParmeterLength;i < j;i++){
            graphics.drawString(drawString[i], 50, 10+15*i);
        }
        //draw image url
        ImageIcon imageIcon = new ImageIcon(imgurl); 
        //draw image , left margin,top margin
        //The coordinates of the top left corner as the center(x,y)[left top angle]
        //Image observer :If img is null, this method does not perform any action
        graphics.drawImage(imageIcon.getImage(), 200, 0, null); 
        createImage(imageOutPathName);    
    }

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
