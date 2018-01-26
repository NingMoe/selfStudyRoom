package com.human.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * FTP文件上传、下载工具类
 * @author HF-121093-01
 *
 */
@Component
public class FtpUtil_bak {
	/*
	private static  Logger logger = LogManager.getLogger(FtpUtil_bak.class);
	
	@Value("${ftpServer}")
	private  String ftpHost;
	
	@Value("${ftpServcer.userName}")
	private  String userName;
	
	@Value("${ftpServcer.passWord}")
	private   String password;
	
	@Value("${ftpServcer.port}")
	private  int port;
	
	@Value("${ftpServcer.initPath}")
	private  String path;
	
	private static String ftpHost;
	
	private static int port;
	
	private static String userName;
	
	private static String password;
	
	private static String path;
	
	@PostConstruct
	 public void init() {
		ftpHost = serverAddr;
		port=serverPort;
		userName=serverUser;
		password=serverPassword;
		path=serverPath;
	}

	
	*//** 
     * 获取FTPClient对象 
     * @param ftpHost FTP主机服务器 
     * @param ftpPassword FTP 登录密码 
     * @param ftpUserName FTP登录用户名 
     * @param ftpPort FTP端口 默认为21 
     * @return 
     *//*  
    public   FTPClient getFTPClient() {  
        FTPClient ftpClient = null;  
        try {  
            ftpClient = new FTPClient();  
            ftpClient.connect(ftpHost,port);// 连接FTP服务器  
            ftpClient.login(userName,password);// 登陆FTP服务器  
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                logger.info("未连接到FTP，用户名或密码错误。");  
                closeFtp(ftpClient);
            } else {  
                logger.info("FTP连接成功。");  
            }  
        } catch (SocketException e) {  
            logger.error("FTP的IP地址可能错误，请正确配置。",e);  
        } catch (IOException e) {  
            logger.error("FTP的端口错误,请正确配置。",e);  
        }  
        return ftpClient;  
    }
    
    *//**
     * 关闭连接
     * @param ftpClient
     * @return
     *//*
    public   boolean closeFtp(FTPClient ftpClient) {  
    	boolean result=false;
        if(ftpClient!=null&&ftpClient.isConnected()){
        		try {
					ftpClient.logout();
					ftpClient.disconnect();
					 logger.info("FTP连接已关闭");  
					 result= true;
				} catch (IOException e) {
					 logger.error("FTP关闭异常。",e);  
				}
        }
        return result;
    }

   
	*//**
	 * ftp单个文件上传
	 * @param updateFile 上传的文件路径
	 * @param filePath 文件目录
	 * @param fileName ftp存时的文件名
	 * @return
	 *//*
    public  boolean upload( FTPClient ftpClient, InputStream fis,String filePath,String fileName) {
        try {
            //设置上传目录
            String updatePath=path;
            if(filePath!=null&&filePath.trim().length()!=0){
            	updatePath+=filePath;
            }
            if (!ftpClient.changeWorkingDirectory(updatePath)) {// 如果不能进入dir下，说明此目录不存在！  
                if (!CreateDirecroty(ftpClient,updatePath+"/"+fileName)) {  
                    return false;
                }  
            }  
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(updatePath);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName, fis);
            return true;
        } catch (IOException e) {
        	logger.error("FTP客户端出错！",e);
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
        	closeFtp(ftpClient);
            IOUtils.closeQuietly(fis);
        }
    }
    

    *//** *//*
      *//**
      * 递归创建远程服务器目录
      * 
      * @param remote
      *            远程服务器文件绝对路径
      * 
      * @return 目录创建是否成功
      * @throws IOException
      *//*
      public boolean CreateDirecroty(FTPClient ftpClient,String updatePath) throws IOException {
          boolean success = true;
          String directory = updatePath.substring(0, updatePath.lastIndexOf("/") + 1);
          // 如果远程目录不存在，则递归创建远程服务器目录
          if (!directory.equalsIgnoreCase("/")&& !ftpClient.changeWorkingDirectory(new String(directory))) {
              int start = 0;
              int end = 0;
              if (directory.startsWith("/")) {
                  start = 1;
              } else {
                  start = 0;
              }
              end = directory.indexOf("/", start);
              while (true) {
                  String subDirectory = new String(updatePath.substring(start, end));
                  if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                      if (ftpClient.makeDirectory(subDirectory)) {
                    	  ftpClient.changeWorkingDirectory(subDirectory);
                      } else {
                    	  logger.error("创建文件目录【"+updatePath+"】 失败！"); 
                          success = false;
                          return success;
                      }
                  }
                  start = end + 1;
                  end = directory.indexOf("/", start);
                  // 检查所有目录是否创建完毕
                  if (end <= start) {
                      break;
                  }
              }
          }
          return success;
      }

    *//**
     * FTP下载单个文件
     *//*
    public  void download(FTPClient ftpClient,String remoteFile,String localFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(localFile);
            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFile, fos);
        } catch (IOException e) {
        	logger.error("FTP客户端出错！",e);
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }
    
    *//** 
     * 判断是否有重名文件 
     * @param fileName 
     * @param fs 
     * @return 
     *//*  
	public boolean isFileExist(FTPClient ftp, String filePath) {
		boolean re = false;
		try {
			int lastIndex = filePath.lastIndexOf("\\") + 1;
			String fileName = filePath.substring(lastIndex);
			String remotePath = path + filePath.substring(0, lastIndex);
			FTPFile[] fs = ftp.listFiles(remotePath);
			for (int i = 0; i < fs.length; i++) {
				FTPFile ff = fs[i];
				if (ff.getName().equals(fileName)) {
					re = true; // 如果存在返回 正确信号
				}
			}
		} catch (IOException e) {
			logger.error("ftp判断文件重名异常！", e);
		}
		return re; // 如果不存在返回错误信号
	}
    
    *//** 
     * 删除文件-FTP方式 
     * @param ftp FTPClient对象 
     * @param path FTP服务器上传地址 
     * @param filename FTP服务器上要删除的文件名 
     * @return 
     *//*  
    public boolean deleteFile(FTPClient ftp,String filePath) {  
        boolean success = false;  
        try {  
        	int lastIndex = filePath.lastIndexOf("\\") + 1;
			String fileName = filePath.substring(lastIndex);
			String movePath = path + filePath.substring(0, lastIndex);
            ftp.changeWorkingDirectory(movePath);//转移到指定FTP服务器目录  
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");  
            ftp.deleteFile(fileName);  
            success = true;  
        } catch (Exception e) {  
        	logger.error("FTP客户端出错！",e);
        }  
        return success;  
    }  */
}
