package com.ls.spt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;

@Component
public class OSSUtil {

	/**
	 * 日志
	 */
	private final static Logger logger = LogManager.getLogger(OSSUtil.class);
	
	/**
	 * 阿里云路径
	 */
	@Value("${oss.endpoint}")
	private  String endpoint;
	
	@Value("${oss.accessId}")
	private  String accessId;
	
	@Value("${oss.accessKey}")
	private  String accessKey;
	
	/**
	 * 文件访问URL
	 */
	@Value("${oss.fileurl}")
	private  String fileurl;
	
	@Value("${oss.bucket}")
    private  String bucketName;
	

	
	public OSSClient getClient(){
		return new OSSClient(endpoint, accessId, accessKey);
	}
	
	/**
     * 创建Bucket
     *
     * @param client  OSSClient对象
     * @param bucketName  BUCKET名
     * @throws OSSException
     * @throws ClientException
     */
    public void ensureBucket(OSSClient client, String bucketName)throws OSSException, ClientException{
    	logger.info("创建bucket");
        try{
            client.createBucket(bucketName);
            logger.info("创建bucket结束");
        }catch(ServiceException e){
        	logger.info("bucket"+bucketName+"已存在");
            if(!OSSErrorCode.BUCKET_ALREADY_EXISTS.equals(e.getErrorCode())){
                throw e;
            }
        }
    }
    
    /**
     * 删除一个Bucket和其中的Objects
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @throws OSSException
     * @throws ClientException
     */
    private void deleteBucket(OSSClient client, String bucketName)throws OSSException, ClientException{
        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
        for(int i = 0; i < listDeletes.size(); i++){
            String objectName = listDeletes.get(i).getKey();
            System.out.println("objectName = " + objectName);
            //如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }
    
    /**
     * 把Bucket设置成所有人可读
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @throws OSSException
     * @throws ClientException
     */
    public void setBucketPublicReadable(OSSClient client, String bucketName)throws OSSException, ClientException{
        //创建bucket
       // client.createBucket(bucketName);
         
        //设置bucket的访问权限， public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }
    
    /**
     * 上传文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename  本地文件名
     * @throws OSSException
     * @throws ClientException
     * @throws FileNotFoundException
     */
   /* public static void uploadFile(OSSClient client, String bucketName, String Objectkey, String filename)
            throws OSSException, ClientException, FileNotFoundException{
        File file = new File(filename);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        //判断上传类型，多的可根据自己需求来判定
        if (filename.endsWith("xml")) {
            objectMeta.setContentType("text/xml");
        }
        else if (filename.endsWith("jpg")) {
            objectMeta.setContentType("image/jpeg");
        }
        else if (filename.endsWith("png")) {
            objectMeta.setContentType("image/png");
        }
         
        InputStream input = new FileInputStream(file);
        client.putObject(bucketName, Objectkey, input, objectMeta);
    }*/
    
    /**
     * 上传文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename  本地文件名
     * @throws OSSException
     * @throws ClientException
     * @throws IOException 
     */
    public Map<String,Object> uploadFile(OSSClient client, String bucketName, String objectkey, MultipartFile  multipartFile){
    	logger.info("上传文件开始");
    	Map<String,Object> result = new HashMap<String,Object> ();
    	try{
    		ObjectMetadata objectMeta = new ObjectMetadata();
    		objectMeta.setContentLength(multipartFile.getSize());
    		objectMeta.setContentType(multipartFile.getContentType());
    		client.putObject(bucketName, objectkey,multipartFile.getInputStream(), objectMeta);
    		result.put("flag", true);
			result.put("message", "上传成功");
			result.put("objectkey", objectkey);
    	}catch(Exception e){
    		logger.info("上传文件出错");
    		e.printStackTrace();
    		result.put("flag", false);
			result.put("message", "上传文件出错");
    	}
        return result;
    }
    
    /**
     * 异步上传文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename  本地文件名
     * @throws OSSException
     * @throws ClientException
     * @throws IOException 
     */
    @Async
    public Map<String,Object> asyncUploadFile(OSSClient client, String bucketName, String objectkey, MultipartFile  multipartFile){
        logger.info("上传文件开始");
        Map<String,Object> result = new HashMap<String,Object> ();
        try{
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(multipartFile.getSize());
            objectMeta.setContentType(multipartFile.getContentType());
            client.putObject(bucketName, objectkey,multipartFile.getInputStream(), objectMeta);
            result.put("flag", true);
            result.put("message", "上传成功");
            result.put("objectkey", objectkey);
        }catch(Exception e){
            logger.info("上传文件出错");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "上传文件出错");
        }
        return result;
    }
    
    /**
     *判断对象是否存在
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param key OSS文件名
     * @throws OSSException
     * @throws ClientException
     */
    public boolean isObjectExist(OSSClient client, String bucketName,String key)throws OSSException, ClientException{
        return client.doesObjectExist(bucketName, key);
    }
    
    /**
     * 删除一个Object
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param key OSS文件名
     * @throws OSSException
     * @throws ClientException
     */
    public void deleteObject(OSSClient client, String bucketName,String key)throws OSSException, ClientException{
    	logger.info("删除文件");
        client.deleteObject(bucketName,key);
    }
    
    /**
     *  下载文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename 文件下载到本地保存的路径
     * @throws OSSException
     * @throws ClientException
     */
    public void downloadFile(OSSClient client, String bucketName, String Objectkey, String filename)
            throws OSSException, ClientException {
        client.getObject(new GetObjectRequest(bucketName, Objectkey),
                new File(filename));
    }
    
    /**
     * 下载文件
     * @param key
     * @return 
     * @ReturnType:String
    */
    public InputStream  getObjectInputStream(OSSClient client, String bucketName, String Objectkey){
        OSSObject object = client.getObject(bucketName, Objectkey);
        object.getObjectMetadata().getContentType();
        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();
        return objectContent;
    }
    
    /**
     * 上传文件流
     * @param client
     * @param bucketName
     * @param objectkey
     * @param file
     * @return
     */
    public Map<String,Object> uploadFile(OSSClient client, String bucketName, String objectkey,InputStream stream){
        logger.info("上传文件开始");
        Map<String,Object> result = new HashMap<String,Object> ();
        try{
            client.putObject(bucketName, objectkey,stream);
            result.put("flag", true);
            result.put("message", "上传成功");
            result.put("objectkey", objectkey);
        }catch(Exception e){
            logger.info("上传文件出错");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "上传文件出错");
        }
        return result;
    }
    
    public Map<String,Object> uploadFile(OSSClient client, String bucketName, String objectkey, File  file){
    	logger.info("上传文件开始");
    	Map<String,Object> result = new HashMap<String,Object> ();
    	try{
    		ObjectMetadata objectMeta = new ObjectMetadata();
    		objectMeta.setContentLength(file.length());
    		objectMeta.setContentType( new MimetypesFileTypeMap().getContentType(file));
    		client.putObject(bucketName, objectkey,new FileInputStream(file), objectMeta);
    		result.put("flag", true);
			result.put("message", "上传成功");
			result.put("objectkey", objectkey);
    	}catch(Exception e){
    		logger.info("上传文件出错");
    		e.printStackTrace();
    		result.put("flag", false);
			result.put("message", "上传文件出错");
    	}
        return result;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    
    
}
