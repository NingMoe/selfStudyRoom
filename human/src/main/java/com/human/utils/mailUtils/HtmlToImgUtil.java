package com.human.utils.mailUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.OSSClient;
import com.human.resume.dao.ResumeSnapshotDao;
import com.human.resume.entity.ResumeSnapshot;
import com.human.utils.OSSUtil;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeComponent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;

public class HtmlToImgUtil extends JPanel {
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
    // 行分隔符  
    final static public String LS = System.getProperty("line.separator", "/n");  
    // 文件分割符  
    final static public String FS = System.getProperty("file.separator", "//");  
    //以javascript脚本获得网页全屏后大小  
    final static StringBuffer jsDimension;        
    static {  
        jsDimension = new StringBuffer();  
        jsDimension.append("var width = 0;").append(LS);  
        jsDimension.append("var height = 0;").append(LS);  
        jsDimension.append("if(document.documentElement) {").append(LS);  
        jsDimension.append(  
                        "  width = Math.max(width, document.documentElement.scrollWidth);")  
                .append(LS);  
        jsDimension.append(  
                        "  height = Math.max(height, document.documentElement.scrollHeight);")  
                .append(LS);  
        jsDimension.append("}").append(LS);  
        jsDimension.append("if(self.innerWidth) {").append(LS);  
        jsDimension.append("  width = Math.max(width, self.innerWidth);")  
                .append(LS);  
        jsDimension.append("  height = Math.max(height, self.innerHeight);")  
                .append(LS);  
        jsDimension.append("}").append(LS);  
        jsDimension.append("if(document.body.scrollWidth) {").append(LS);  
        jsDimension.append(  
                "  width = Math.max(width, document.body.scrollWidth);")  
                .append(LS);  
        jsDimension.append(  
                "  height = Math.max(height, document.body.scrollHeight);")  
                .append(LS);  
        jsDimension.append("}").append(LS);  
        jsDimension.append("return width + ':' + height;");  
    }  
    
    private static String snapshotPath;
    
    private static String bucketName;
    
    private static OSSUtil oSSUtil;
    
    private static ResumeSnapshotDao resumeSnapshotDao;
    
    
    public HtmlToImgUtil(String snapshotPath,String bucketName,OSSUtil oSSUtil,ResumeSnapshotDao resumeSnapshotDao){
        this.snapshotPath=snapshotPath;
        this.bucketName=bucketName;
        this.oSSUtil=oSSUtil;
        this.resumeSnapshotDao=resumeSnapshotDao;
    }
    
    
    /**
     * 将html转为图片
     * @param url
     * @param maxWidth
     * @param maxHeight
     */
    public HtmlToImgUtil(String url,int maxWidth,int maxHeight,String path,long resumeId,String hrCompanyId) {  
        super(new BorderLayout());
        long startTime=System.currentTimeMillis();
        String fileName1 = path + "image1" + startTime + ".jpg";
        String fileName2 = path + "image2" + startTime + ".jpg";
        File imageFile1=new File(fileName1);
        File imageFile2=new File(fileName2); 
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setBarsVisible(false);
        webBrowser.navigate(url);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
            // 监听加载进度
            public void loadingProgressChanged(WebBrowserEvent e) {
                // 当加载完毕时
                if (e.getWebBrowser().getLoadingProgress() == 100) {
                    String result = (String) webBrowser.executeJavascriptWithResult(jsDimension.toString());
                    int index = result == null ? -1 : result.indexOf(":");
                    NativeComponent nativeComponent = webBrowser.getNativeComponent();
                    Dimension originalSize = nativeComponent.getSize();
                    Dimension imageSize = new Dimension(Integer.parseInt(result.substring(0, index)), Integer.parseInt(result.substring(index + 1)));
                    imageSize.width = Math.max(originalSize.width, imageSize.width + 50);
                    imageSize.height = Math.max(originalSize.height, imageSize.height + 50);
                    nativeComponent.setSize(imageSize);
                    BufferedImage image = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB);
                    nativeComponent.paintComponent(image);
                    nativeComponent.setSize(originalSize);
                    BufferedImage image1 = null;
                    BufferedImage image2 = null;                    
                    //判断截图的宽度,默认为页面的宽度
                    int screenshotWidth=imageSize.width;
                    if(imageSize.width > maxWidth){
                        screenshotWidth=maxWidth;                                            
                    }
                    // 当网页高度超出目标高度时，快照分2张图保存
                    if (imageSize.height > maxHeight) {                       
                        image1 = image.getSubimage(0, 0, screenshotWidth, maxHeight);
                        image2 = image.getSubimage(0, maxHeight, screenshotWidth, imageSize.height - maxHeight);
                    }else{// 当网页没有超出目标大小时，只需一张图保存快照
                        image1 = image.getSubimage(0, 0, screenshotWidth,imageSize.height); 
                    }
                    try {
                        // 输出图像
                        ImageIO.write(image1, "jpg", imageFile1);
                        //saveResumeSnapshot(imageFile1,resumeId,hrCompanyId);
                        if(image2!=null){
                            ImageIO.write(image2, "jpg",imageFile2); 
                            //saveResumeSnapshot(imageFile2,resumeId,hrCompanyId);
                        }  
                        Thread.sleep(1000);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }finally{
                        //imageFile1.delete();
                        //imageFile2.delete();                       
                    }
                   
                }
            }
        });
        add(panel, BorderLayout.SOUTH);        
    }
    
    
    public static void saveHtmlAsImage(String url,int maxWidth, int maxHeight,String path,long resumeId,String hrCompanyId){
        new Thread(){
           public void run() { 
               UIUtils.setPreferredLookAndFeel(); 
               NativeInterface.open();  
               SwingUtilities.invokeLater(new Runnable(){  
                   public void run() {  
                       // SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser  
                       JFrame frame = new JFrame();  
                       // 加载指定页面，最大保存为640x480的截图  
                       frame.getContentPane().add(  
                               new HtmlToImgUtil(url, maxWidth, maxHeight,path,resumeId,hrCompanyId),  
                               BorderLayout.CENTER);  
                       frame.setSize(800, 1200);  
                       // 仅初始化，但不显示  
                       frame.invalidate();  
                       frame.pack();  
                       frame.setVisible(true);                
                   }  
               });  
               NativeInterface.runEventPump();   
           }
        }.start();  
    }
    
    
    /**
     * 保存邮件快照
     * @param imageFile1
     * @param imageFile2
     * @param resumeId
     * @param hrCompanyId
     */
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeSnapshot(File imageFile,long resumeId,String hrCompanyId){
        ResumeSnapshot rsh=new ResumeSnapshot();
        String snapshotDir=snapshotPath+hrCompanyId+"/";//邮件快照保存路径
        OSSClient ossClient = oSSUtil.getClient();           
        String newFileName = snapshotDir+System.currentTimeMillis()+".jpg"; 
        System.out.println("邮件快照保存路径: " + newFileName);
        Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,imageFile);
        boolean uploadflag = (boolean)uploadResult.get("flag");
        String savePath="";
        if(uploadflag){
            savePath=(String)uploadResult.get("objectkey");
            rsh.setPathName(savePath);
            rsh.setResumeId(resumeId);
            resumeSnapshotDao.insertSelective(rsh);
        } 
    }
        
    public static void main(String[] args) {  
        String url="http://hrms-img.oss-cn-shanghai.aliyuncs.com/htmlEmail/128/1490152774346.html";
        String path = "E:/";
        long resumeId=227;
        String hrCompanyId="128";
        saveHtmlAsImage(url,640, 750,path,resumeId,hrCompanyId);
    }  

}
