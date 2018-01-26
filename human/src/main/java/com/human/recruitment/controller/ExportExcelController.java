package com.human.recruitment.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.human.utils.Common;
import com.human.utils.ExportConfigure;
import com.human.utils.execl.ExcelHandle;

/**
 * 
 * @author liwuei
 * 
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/recruit/exportExcel/")
public class ExportExcelController{
	
    private final  Logger logger = LogManager.getLogger(ExportExcelController.class); 
    
    /**
     * 导出安排面试信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportInterviewList")
    @ResponseBody
    public Object exportStudentList(HttpServletRequest request,HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadInterview.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTINTERVIEW,
                    ExportConfigure.exportInterview, response, "安排面试信息(本页)", account);
            map.put("flag", true);
			map.put("message", "导出安排面试信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出安排面试信息数据失败!", e.getMessage());
        	map.put("flag", false);
			map.put("message", "导出安排面试信息数据失败!");            
        }
        return map;
    }

    
    /**
     * 导出人才库信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportTalentList")
    @ResponseBody
    public Object exportTalentList(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadTalent.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTTALENT,
                    ExportConfigure.exportTalent, response, "人才库信息(本页)", account);
            map.put("flag", true);
            map.put("message", "导出人才库信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出人才库信息数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出人才库信息数据失败!");            
        }
        return map;
    }
    
    
    /**
     * 导出面试观察员信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportWatcherList")
    @ResponseBody
    public Object exportWatcherList(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadWatcher.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTWATCHER,
                    ExportConfigure.exportWatcher, response, "简历(面试观察员)信息(本页)", account);
            map.put("flag", true);
            map.put("message", "导出简历(面试观察员)信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出简历(面试观察员)信息数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出简历(面试观察员)信息数据失败!");            
        }
        return map;
    }
    
    /**
     * 导出我的内推信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportMyInsideRecommendList")
    @ResponseBody
    public Object exportMyInsideRecommendList(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadMyInsideRecommend.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTMYINSIDERECOMMEND,
                    ExportConfigure.exportMyInsideRecommend, response, "我的内推信息(本页)", account);
            map.put("flag", true);
            map.put("message", "导出我的内推信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出我的内推信息数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出我的内推信息数据失败!");            
        }
        return map;
    }
    
    
    /**
     * 导出内推管理信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportRecommendManagerList")
    @ResponseBody
    public Object exportRecommendManagerList(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadRecommendManager.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTINSIDERECOMMENDMANAGER,
                    ExportConfigure.exportInsideRecommendManager, response, "内推管理信息(本页)", account);
            map.put("flag", true);
            map.put("message", "导出内推管理信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出内推管理信息数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出内推管理信息数据失败!");            
        }
        return map;
    }
    
    /**
     * 导出校招管理信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="exportSchoolRtManagerList")
    @ResponseBody
    public Object exportSchoolRtManagerList(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String tempFilePath = request.getSession().getServletContext().getRealPath("/static/temp/downloadSchoolRtManager.xlsx");
        try {
            String account =Common.getAuthenticatedUsername();
            writeExcel(tempFilePath, ExportConfigure.EXPORTSCHOOLRECRUITMENTMANAGER,
                    ExportConfigure.exportSchoolRecruitmentManager, response, "校招管理信息(本页)", account);
            map.put("flag", true);
            map.put("message", "导出校招管理信息数据成功!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导出校招管理信息数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出校招管理信息数据失败!");            
        }
        return map;
    }
    
    
    
    @SuppressWarnings("unused")
    private void writeMegraExcel(String tempFilePath,List<String> dataListCell,Map<String, List<Map<String, Object>>> dataList,HttpServletResponse response, String fileName, String account)throws IOException {
        ExcelHandle handle = new ExcelHandle();
        handle.writeMegraData(tempFilePath, dataListCell, dataList, 0, account);
        // 写到输出流并关闭资源
        OutputStream output = response.getOutputStream();
        response.reset();
        fileName = fileName + ".xlsx";
        fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
        response.setHeader("Content-Disposition",
                "attachment;filename=".concat(fileName));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        handle.writeAndClose(tempFilePath, output);
        handle.readClose(tempFilePath);
    }

    /**
     * 
     * @description 进行excel整合导出
     * @author ybpan
     * @create 2015年5月27日下午6:44:36
     * @version 1.0
     * @param tempFilePath
     *            模版路径
     * @param dataListCell
     *            列标识
     * @param dataList
     *            待导出数据列表
     * @param response
     *            http响应
     * @param fileName
     *            文件名
     * @throws IOException
     *             读写异常
     */
    private void writeExcel(String tempFilePath, List<String> dataListCell,Map<String, List<Map<String, Object>>> dataList,HttpServletResponse response, String fileName, String account)throws IOException {   	
        ExcelHandle handle = new ExcelHandle();
        handle.writeListData(tempFilePath, dataListCell, dataList, 0, account);
        // 写到输出流并关闭资源
        OutputStream output = response.getOutputStream();
        response.reset();
        fileName = fileName + ".xlsx";
        fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
        response.setHeader("Content-Disposition",
                "attachment;filename=".concat(fileName));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        handle.writeAndClose(tempFilePath, output);
        handle.readClose(tempFilePath);
    }


  }
   


