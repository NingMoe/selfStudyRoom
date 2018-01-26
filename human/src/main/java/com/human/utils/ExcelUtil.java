package com.human.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class ExcelUtil <T> {
	
	private static final  Logger logger = LogManager.getLogger(ExcelUtil.class);
	
	/** 时间格式数组（年月日 时分秒） */
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	
	private static final String OFFICE_EXCEL_XLS = ".xls";
	
	private static final String OFFICE_EXCEL_XLSX = ".xlsx";
	
	/**
	 * 从第startX行开始读
	 */
	private  int startX=0;
	
	/**
	 * 从第startY列开始读
	 */
	private  int startY=0;
	
	/**
	 * 默认读取第0个sheet页
	 */
	private Integer sheetnum=0;
	
	/**
	 * 
	 * @param startX 开始页
	 * @param startY 开始
	 */
	public ExcelUtil(int startX, int startY){
		this.startX = startX;
		this.startY = startY;
	}
	public ExcelUtil(){
    }
	
	/**
	 * 
	 * @param startX 开始行数
	 * @param startY 开始列数
	 * @param sheetnum 第几个sheet页
	 */
	public ExcelUtil(int startX, int startY,int sheetnum){
		this.startX = startX;
		this.startY = startY;
		this.sheetnum=sheetnum;
	}
	
	/**
	 * 
	 * @param file excel文件
	 * @param sheetnum 第几个sheet，第一个写0
	 * @return
	 * @throws Exception 文件为空、文件不已.xls或 .xlsx结尾。
	 */
	public  Map<String,Object> checkAccount(MultipartFile file,Class<T> clazz) throws Exception{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try{
    		if(file == null || file.isEmpty()){
    			resultMap.put("flag", false);
    			resultMap.put("errorMessage", "文件不存在,或文件内容为空!");
    			return resultMap;
    		}
    		Map<String,Object> map=getHeaderList(clazz);
    		if(file.getOriginalFilename().endsWith(OFFICE_EXCEL_XLS)){
    			return getHSSFWorkbook(file,sheetnum,startX,startY,map,clazz);
    		}else if(file.getOriginalFilename().endsWith(OFFICE_EXCEL_XLSX)){
    			return getXSSFWorkbook(file,sheetnum,startX,startY,map,clazz);
    		}else{
    		    resultMap.put("flag", false);
                resultMap.put("errorMessage", "文件格式不对,请上传Execl文件!");
    			logger.error(new IllegalArgumentException("file does not support:"+file.getName()));
    		}
		}catch(Exception e){
			resultMap.put("flag", false);
			resultMap.put("errorMessage", "文件导入出错,请稍后再试!");
		}
		return resultMap;
	}
	
	/**
	 * 获取导入的注解bean
	 * @param model
	 * @return
	 */
	public static Map<String,Object> getHeaderList(Class<?> model) {
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		// 通过反射机制，能访问model对象表示的类的所有方法
		Field[] fieIds = model.getDeclaredFields();
		for (Field m : fieIds) {
				// 通过反射机制，判断注释存在于方法上，筛选出指定的数据集
				if (m.isAnnotationPresent(ExcelAnnotation.class)) {
					ExcelAnnotation ea = m.getAnnotation(ExcelAnnotation.class);
					map.put(m.getName(),ea.exportName());
			}
		}
		return map;
	}
	
	/**
	 * 获取导出的注解属性
	 * @param model
	 * @return
	 */
	public static List<String> getExportHeaderList(Class<?> model) {
	    // 通过反射机制，能访问model对象表示的类的所有方法
	    Field[] fieIds = model.getDeclaredFields();
	    List<String> list=new ArrayList<String>();
	    for (Field m : fieIds) {
	            if (m.isAnnotationPresent(ExportTitleAnnotation.class)) {
	                list.add(m.getName());
	        }
	    }
	    return list;
	}
	
	/**
	 * 往模版中写入数据，用作导出
	 * @param tempFilePath 模版路径
	 * @param model 导出数据的bean
	 * @param dataList 导出数据的集合
	 * @param response 
	 * @param fileName 导出文件名称
	 * @param sheet 写入的sheet页，0：第一页
	 * @param row 开始写的行数，0，第一行
	 * @throws IOException
	 */
	public void writeExcel(String tempFilePath, Class<?> model, List<Map<String,Object>> dataList,HttpServletResponse response, String fileName,int sheet,int row)
	        throws IOException {
	        List<String> headList=getExportHeaderList(model);
	        FileInputStream  inputStream=new FileInputStream(tempFilePath);
	        XSSFWorkbook work=writeListData(inputStream, headList, dataList, sheet,row);
	        inputStream.close();
	        // 写到输出流并关闭资源
	        OutputStream output = response.getOutputStream();
	        fileName = fileName + ".xlsx";
	        fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
	        response.setHeader("Content-Disposition",
	                "attachment;filename=".concat(fileName));
	        response.setHeader("Content-Type", "application/vnd.ms-excel");
	        work.write(output);
	        work.close();
	        output.close();
	    }
	
		
	@SuppressWarnings("all")
	public Map<String, Object> getXSSFWorkbook(MultipartFile file, Integer sheetnum, int startX, int startY,
			Map<String, Object> map, Class<T> clazz) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", true);
		int errorRow = -1;
		int errorCell = -1;
		int columnNumber = 0;
		try {
			InputStream ins = file.getInputStream();
			XSSFWorkbook work = new XSSFWorkbook(ins);
			XSSFSheet sheet = work.getSheetAt(sheetnum);
			List<String> keys = Common.getMapKeys(map);
			List<T> resultList = new ArrayList<>();
			columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = startX; i <= sheet.getLastRowNum(); i++) {
				errorRow = i;
				XSSFRow row = sheet.getRow(i);
				if(row==null){
					continue;
				}
				int cellLength = columnNumber - startY;
				if (cellLength != keys.size()) {
					result.put("flag", false);
					result.put("errorMessage", "请检查上传EXCEL文件的列数是否与模板一致");
					logger.error("bean is fieIds is not equals cells");
					return result;
				}
				T o = clazz.newInstance();
				for (int j = startY; j < columnNumber; j++) {
					errorCell = j;
					int init = j - startY;
					XSSFCell cell = row.getCell(j);
					String val = getCellValue(cell);
					AssignValueForAttributeUtil.setAttrributeValue(o, keys.get(init), val);
				}
				resultList.add(o);
			}
			result.put("list", resultList);
		} catch (Exception e) {
			logger.error(e);
			result.put("flag", false);
			result.put("errorMessage", " 第" + (errorRow + 1) + "行，第" + (errorCell + 1) + "列文本有误，请核实后再导入!");
		}
		return result;
	}
	
	
	@SuppressWarnings("all")
	public Map<String, Object> getHSSFWorkbook(MultipartFile file, Integer sheetnum, int startX, int startY,
			Map<String, Object> map, Class<T> clazz) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", true);
		int errorRow = -1;
		int errorCell = -1;
		int columnNumber = 0;
		try {
			InputStream ins = file.getInputStream();
			HSSFWorkbook work = new HSSFWorkbook(ins);
			HSSFSheet sheet = work.getSheetAt(sheetnum);
			List<String> keys = Common.getMapKeys(map);
			List<T> resultList = new ArrayList<>();
			columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = startX; i <= sheet.getLastRowNum(); i++) {
				errorRow = i;
				HSSFRow row = sheet.getRow(i);
				if(row==null){
					continue;
				}
				int cellLength = columnNumber - startY;
				if (cellLength != keys.size()) {
					result.put("flag", false);
					result.put("errorMessage", "请检查上传EXCEL文件的列数是否与模板一致");
					logger.error("bean is fieIds is not equals cells");
					return result;
				}
				T o = clazz.newInstance();
				for (int j = startY; j < columnNumber; j++) {
					errorCell = j;
					int init = j - startY;
					HSSFCell cell = row.getCell(j);
					String val = getCellValue(cell);
					AssignValueForAttributeUtil.setAttrributeValue(o, keys.get(init), val);
				}
				resultList.add(o);
			}
			result.put("list", resultList);
		} catch (Exception e) {
			logger.error(e);
			result.put("flag", false);
			result.put("errorMessage", " 第" + (errorRow + 1) + "行，第" + (errorCell + 1) + "列文本有误，请核实后再导入!");
		}
		return result;
	}

	/** 
     * 获取当前单元格内容 
     * */  
    private static String getCellValue(Cell cell){  
        String value = "";  
        if(cell!=null){  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:  
                if(HSSFDateUtil.isCellDateFormatted(cell)){ //日期类型  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
                    value = sdf.format(date);  
                }else{  
                	BigDecimal bd=new BigDecimal(cell.getNumericCellValue());
                    value= bd.toPlainString();
                    //下面这种方式也可以解决科学计数法
                   // int cellValue_int = (int)cell.getNumericCellValue();
                    //value=String.valueOf(cellValue_int);
                }  
                break;  
            case Cell.CELL_TYPE_STRING:  
                value = cell.getStringCellValue();  
                break;  
            case Cell.CELL_TYPE_BOOLEAN:  
                Boolean data = cell.getBooleanCellValue();  
                value = data.toString();   
                break;  
            case Cell.CELL_TYPE_ERROR:  
                System.out.println("单元格内容出现错误");  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                value = String.valueOf(cell.getNumericCellValue());    
                if (value.equals("NaN")) {// 如果获取的数据值非法,就将其装换为对应的字符串  
                    value = cell.getStringCellValue().toString();    
                }             
                break;            
            case Cell.CELL_TYPE_BLANK:  
                System.out.println("单元格内容为空值 ");  
                break;            
            default :  
                value = cell.getStringCellValue().toString();  
                break;  
            }  
        }  
        return value;  
    }  

    /**
     * 获取导入的团体报名学生EXECL文件的行数
     * @param file
     * @param sheetnum
     * @return
     */
    public static long countExeclRow(MultipartFile file,Integer sheetnum){
    	long rowCount=0L;
		String fileName=file.getOriginalFilename();
		try{
			InputStream ins = file.getInputStream();
			if(fileName.endsWith(OFFICE_EXCEL_XLS)){
					HSSFWorkbook work = new HSSFWorkbook(ins);
					HSSFSheet sheet = work.getSheetAt(sheetnum);
					rowCount=sheet.getLastRowNum();
			}
			if(fileName.endsWith(OFFICE_EXCEL_XLSX)){
					XSSFWorkbook work = new XSSFWorkbook(ins);
					XSSFSheet sheet = work.getSheetAt(sheetnum);
					rowCount=sheet.getLastRowNum();
			}	
		}catch(Exception e ){
			logger.error("获取EXECL文件的行数出错!",e);
		}	
		return rowCount;
   }
    

public XSSFWorkbook writeListData(FileInputStream inputStream, List<String> cellList,
        List<Map<String,Object>> mapDataList, int sheet,int startRow) throws IOException {
       // 按模板为写入板
       XSSFWorkbook temWorkbook =  new XSSFWorkbook(inputStream);
       // 数据填充的sheet
       XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
           for (int i=0,k=mapDataList.size();i<k;i++) {
               for (int j=0,t=cellList.size();j<t;j++) {
                   // 写入数据
                   Object s=mapDataList.get(i).get(cellList.get(j));
                   if(s==null){
                       s="";
                   }
                   setValue(wsheet, startRow, j,String.valueOf(s));
               }
               startRow++;
           }
       return temWorkbook;
   }


/**
 * Excel单元格输出
 * 
 * @param sheet
 * @param row
 *            行
 * @param cell
 *            列
 * @param value
 *            值
 * @param cellStyle
 *            样式
 */
public static void setValue(XSSFSheet sheet, int row, int cell,
        String value) {
    XSSFRow rowIn = sheet.getRow(row);
    if (rowIn == null) {
        rowIn = sheet.createRow(row);
    }
    XSSFCell cellIn = rowIn.getCell(cell);
    if (cellIn == null) {
        cellIn = rowIn.createCell(cell);
    }
     cellIn.setCellValue(new XSSFRichTextString(value));
}

public static void setValue(XSSFSheet sheet, int row, int cell,
        String value,XSSFCellStyle style) {
    XSSFRow rowIn = sheet.getRow(row);
    if (rowIn == null) {
        rowIn = sheet.createRow(row);
    }
    XSSFCell cellIn = rowIn.getCell(cell);
    if (cellIn == null) {
        cellIn = rowIn.createCell(cell);
    }
     cellIn.setCellValue(new XSSFRichTextString(value));
     cellIn.setCellStyle(style);
}
}
