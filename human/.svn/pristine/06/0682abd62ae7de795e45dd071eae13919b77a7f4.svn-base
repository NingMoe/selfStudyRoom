package com.human.questionnaire.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.questionnaire.dao.FormCollectDao;
import com.human.questionnaire.dao.QformDao;
import com.human.questionnaire.entity.DisableIP;
import com.human.questionnaire.entity.FormCollect;
import com.human.questionnaire.entity.FormParam;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.entity.Qform;
import com.human.questionnaire.service.QuestionCollectService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.sun.star.uno.RuntimeException;

@Service
public class QuestionCollectServiceImpl implements QuestionCollectService {
    
    private final Logger logger = LogManager.getLogger(QuestionCollectServiceImpl.class);
    
    @Resource
    private QformDao qfDao;

    @Resource
    private FormCollectDao fcDao;
    
    @Override
    public PageView query(PageView pageView, Qform bean) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", bean);
        List<ParamBean> list = qfDao.query(map);

        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveForm(Qform bean) {
        bean.setCreateUser(Common.getMyUser().getUserid());
        qfDao.insert(bean);
        for(FormParam fp:bean.getParamList()){
            if(null!=fp.getParamId()){
                fp.setFormId(bean.getId());
                qfDao.insertFormParam(fp);
            }
        }
    }

    @Override
    public List<Qform> queryNoPage(Qform bean) {
        return qfDao.queryNoPage(bean);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateParam(Qform bean) {
        bean.setCreateUser(Common.getMyUser().getUserid());
        qfDao.updateForm(bean);
        qfDao.deleteFormParam(bean);
        for(FormParam fp:bean.getParamList()){
            if(null!=fp.getParamId()){
                fp.setFormId(bean.getId());
                qfDao.insertFormParam(fp);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCollect(Qform qfrom, List<ParamBean> pbList,HttpServletRequest request) {
        Enumeration<String> enu=request.getParameterNames();  
        List<ParamBean> requestList=new ArrayList<ParamBean>();
        while(enu.hasMoreElements()){  
            String paraName=(String)enu.nextElement();  
            ParamBean pb=new ParamBean();
            pb.setKey(paraName);
            pb.setName(request.getParameter(paraName));
            requestList.add(pb);
    }
        String uid=UUID.randomUUID().toString().replace("-", "");
        FormCollect fc=new FormCollect();
        fc.setUuid(uid);
        fc.setIpAddr(Common.toIpAddr(request));
        //format的格式可以任意   
        Timestamp ts = new Timestamp(System.currentTimeMillis());   
        fc.setSubTime(ts);
        for (ParamBean pb : pbList) {
            boolean isRe=true;
            if(pb.getRequired()){
                isRe=false;
            }
            for (int i=requestList.size()-1;i>=0;i--) {
                ParamBean rePB=requestList.get(i);
                if(pb.getKey().equals(rePB.getKey())){
                    fc.setFormId(qfrom.getId());
                    fc.setParamId(pb.getId());
                    fc.setContent(rePB.getName());
                    if(!isRe&&(null==rePB.getName()|| rePB.getName().trim().length()==0)){
                       throw new RuntimeException();
                    }
                    fcDao.saveFormCollect(fc);
                    isRe=true;
                    requestList.remove(i);
                    break;
                }
            }
            
            if(!isRe){
                throw new RuntimeException();
            }
            
        }
    }

    @Override
    public PageView queryResult(PageView pageView, Long id) {
        List<Map<String,Object>> list = qfDao.queryResult(id);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        list= list.subList(i, j);
        pageView.setRecords(list);
        
        return pageView;
    }

    @Override
    public void delCollect(String uid) {
        String[] ids=uid.split(",");
        qfDao.delCollect(ids);
    }

    @Override
    public List<DisableIP> queryDisableIP(DisableIP ip) {
        return qfDao.queryDisableIP(ip);
    }

    @Override
    public PageView queryDisableIpList(PageView pageView, DisableIP bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", bean);
        List<DisableIP> list = qfDao.queryDisableIpList(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void delIp(String deleteIds) {
        String[] ids=deleteIds.split(",");
        Map<String,Object> paraMap=new HashMap<String,Object>();
        paraMap.put("ids", ids);
        qfDao.delIpById(paraMap);
    }

    @Override
    public void addDisableIp(DisableIP bean) {
        bean.setOpUser(Common.getMyUser().getUserid());
        qfDao.addDisableIp(bean);
    }
    
    @Override
    public void exportResult(HttpServletRequest request, HttpServletResponse response) {
        String idStr=request.getParameter("id");
        if(null==idStr||idStr.trim().length()==0){
            logger.error("参数错误:ID信息不存在");
            return;
        }
        Long id=Long.valueOf(request.getParameter("id"));
        List<Map<String,Object>> list = qfDao.queryResult(id);
        // 第一步，创建一个webbook，对应一个Excel文件    
        HSSFWorkbook wb = new HSSFWorkbook(); 
        HSSFSheet sheet = wb.createSheet();   
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式    
        HSSFCellStyle boldStyle = wb.createCellStyle();   
        HSSFFont font= wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        boldStyle.setFont(font);
        boldStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        if(list.size()>0){
            HSSFRow row = sheet.createRow((int) 0);    
            HSSFCell cell = row.createCell(0);   
            cell.setCellValue("IP地址");    
            cell.setCellStyle(boldStyle);
            cell = row.createCell(1);    
            cell.setCellValue("提交时间");    
            cell.setCellStyle(boldStyle);
            int m=2;
            Map<String,Object> keyMap=list.get(0);
            Set<String> keys = keyMap.keySet();
            for(String key :keys){
                if(key.equals("uuid")||key.equals("subTime")||key.equals("ipAddr")){
                    break;
                }
                cell = row.createCell(m);    
                cell.setCellValue(key);    
                cell.setCellStyle(boldStyle);
                m++;
            }
            int s=0;
           for(int i=0;i<list.size();i++){
               Map<String,Object> map=list.get(i);
                Iterator<String> iter=map.keySet().iterator();
                row = sheet.createRow((int) i + 1); 
                int j=2;
                while(iter.hasNext()){ 
                    String key=iter.next(); 
                    if(key.equals("ipAddr")){
                        String value = String.valueOf(map.get(key)); 
                        cell = row.createCell(0);
                        cell.setCellValue(value);
                        cell.setCellStyle(style);
                    }else   if(key.equals("subTime")){
                        String value = String.valueOf(map.get(key)); 
                        cell = row.createCell(1);
                        cell.setCellValue(value.substring(0,value.length()-2));
                        cell.setCellStyle(style);
                    }else  if(key.equals("uuid")){
                        
                    }else{
                        String value = String.valueOf(map.get(key)); 
                        cell = row.createCell(j);
                        cell.setCellValue(value);
                        cell.setCellStyle(style);
                    }
                    j++;
                }
                s=j;
            }
           for (int a=0;a<s;a++){
               sheet.autoSizeColumn(a);
           }
        }
        OutputStream out = null;    
        try {        
            out = response.getOutputStream();    
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName=Common.getMyUser().getName()+"_"+request.getParameter("formName")+"_"+sdf.format(new Date())+".xls";
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1")); 
            wb.write(out);    
        } catch (Exception e) {  
            logger.error("导出异常",e);
            e.printStackTrace();    
        } finally {      
            try {       
                out.close();      
            } catch (IOException e) { 
                logger.error("导出异常",e);
                e.printStackTrace();    
            }      
        }     
    }

    @Override
    public void deleteForm(String deleteIds) {
        String[] ids=deleteIds.split(",");
        qfDao.deleteFormById(ids);
    }
}
