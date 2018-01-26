package com.human.utils.execl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtil {
    /************************************ XSSF *********************************************/

    /**
     * 取得指定单元格行和列
     * 
     * @param keyMap
     *            所有单元格行、列集合
     * @param key
     *            单元格标识
     * @return 0：列 1：行（列表型数据不记行，即1无值）
     */
    public static int[] getPos(HashMap keyMap, String key) {
        int[] ret = new int[0];

        String val = (String) keyMap.get(key);

        if (val == null || val.length() == 0)
            return ret;

        String pos[] = val.split(",");

        if (pos.length == 1 || pos.length == 2) {
            ret = new int[pos.length];
            for (int i0 = 0; i0 < pos.length; i0++) {
                if (pos[i0] != null && pos[i0].trim().length() > 0) {
                    ret[i0] = Integer.parseInt(pos[i0].trim());
                } else {
                    ret[i0] = 0;
                }
            }
        }
        return ret;
    }

    /**
     * 取对应格子的值
     * 
     * @param sheet
     * @param rowNo
     *            行
     * @param cellNo
     *            列
     * @return
     * @throws IOException
     */
    public static String getCellValue(XSSFSheet sheet, int rowNo, int cellNo) {
        String cellValue = null;
        XSSFRow row = sheet.getRow(rowNo);
        XSSFCell cell = row.getCell(cellNo);
        if (cell != null) {
            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                cellValue = getCutDotStr(Double.toString(cell
                        .getNumericCellValue()));
            } else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue();
            }
            if (cellValue != null) {
                cellValue = cellValue.trim();
            }
        } else {
            cellValue = null;
        }
        return cellValue;
    }

    /**
     * 取整数
     * 
     * @param srcString
     * @return
     */
    private static String getCutDotStr(String srcString) {
        String newString = "";
        if (srcString != null && srcString.endsWith(".0")) {
            newString = srcString.substring(0, srcString.length() - 2);
        } else {
            newString = srcString;
        }
        return newString;
    }

    /**
     * 读数据模板
     * 
     * @param 模板地址
     * @throws IOException
     */
    public static HashMap[] getTemplateFile(String templateFileName)
        throws IOException {
        FileInputStream fis = new FileInputStream(templateFileName);
        File file = new File(templateFileName);
        XSSFWorkbook wbPartModule = new XSSFWorkbook(file.getAbsolutePath());
        int numOfSheet = wbPartModule.getNumberOfSheets();
        HashMap[] templateMap = new HashMap[numOfSheet];
        for (int i = 0; i < numOfSheet; i++) {
            XSSFSheet sheet = wbPartModule.getSheetAt(i);
            templateMap[i] = new HashMap();
            readSheet(templateMap[i], sheet);
        }
        fis.close();
        return templateMap;
    }

    /**
     * 读模板数据的样式值置等信息
     * 
     * @param keyMap
     * @param sheet
     */
    private static void readSheet(HashMap keyMap, XSSFSheet sheet) {
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        for (int j = firstRowNum; j <= lastRowNum; j++) {
            XSSFRow rowIn = sheet.getRow(j);
            if (rowIn == null) {
                continue;
            }
            int firstCellNum = rowIn.getFirstCellNum();
            int lastCellNum = rowIn.getLastCellNum();
            for (int k = firstCellNum; k <= lastCellNum; k++) {
                // XSSFCell cellIn = rowIn.getCell((short) k);
                XSSFCell cellIn = rowIn.getCell(k);
                if (cellIn == null) {
                    continue;
                }

                int cellType = cellIn.getCellType();
                if (XSSFCell.CELL_TYPE_STRING != cellType) {
                    continue;
                }
                String cellValue = cellIn.getStringCellValue();
                if (cellValue == null) {
                    continue;
                }
                cellValue = cellValue.trim();
                if (cellValue.length() > 2
                        && cellValue.substring(0, 2).equals("<%")) {
                    String key = cellValue.substring(2, cellValue.length());
                    String keyPos = Integer.toString(k) + ","
                            + Integer.toString(j);
                    keyMap.put(key, keyPos);
                    keyMap.put(key + "CellStyle", cellIn.getCellStyle());
                } else if (cellValue.length() > 3
                        && cellValue.substring(0, 3).equals("<!%")) {
                    String key = cellValue.substring(3, cellValue.length());
                    keyMap.put("STARTCELL", Integer.toString(j));
                    keyMap.put(key, Integer.toString(k));
                    keyMap.put(key + "CellStyle", cellIn.getCellStyle());
                }
            }
        }
    }

    /**
     * 获取格式，不适于循环方法中使用，wb.createCellStyle()次数超过4000将抛异常
     * 
     * @param keyMap
     * @param key
     * @return
     */
    public static CellStyle getStyle(HashMap keyMap, String key, XSSFWorkbook wb) {
        CellStyle cellStyle = null;

        cellStyle = (CellStyle) keyMap.get(key + "CellStyle");
        // 当字符超出时换行
        cellStyle.setWrapText(true);
        CellStyle newStyle = wb.createCellStyle();
        newStyle.cloneStyleFrom(cellStyle);
        return newStyle;
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
            Object value, CellStyle cellStyle) {
        XSSFRow rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        XSSFCell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            // 修复产生多超过4000 cellStyle 异常
            // CellStyle newStyle = wb.createCellStyle();
            // newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        // 对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        }/* else if (CalcTotalUtil.isCanParseDouble(value)) {
            String str = value.toString();
            double tempDouble = 0d;
            tempDouble = Double.parseDouble(str);
            cellIn.setCellValue(tempDouble);
        }*/ else {
            if (isCellDateFormatted(cellStyle)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(value.toString());
                } catch (ParseException e) {
                    cellIn.setCellValue(new XSSFRichTextString(value.toString()));
                }
                cellIn.setCellValue(date);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    private static int rowFlag = 1;
    private static int i = 4;

    public static void setMegraValue(XSSFSheet sheet, int row, int cell,
            Object value, CellStyle cellStyle) {
        XSSFRow rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        XSSFCell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            // 修复产生多超过4000 cellStyle 异常
            // CellStyle newStyle = wb.createCellStyle();
            // newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        if (row != rowFlag) {
            if (i % 4 == 0) {
                sheet.addMergedRegion(new CellRangeAddress(row, row + 3, 0, 0));
                i = 0;
            }
            i++;
        }
        rowFlag = row;
        // 对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        } else if (CalcTotalUtil.isCanParseDouble(value)) {
            String str = value.toString();
            double tempDouble = 0d;
            tempDouble = Double.parseDouble(str);
            cellIn.setCellValue(tempDouble);
        } else {
            if (isCellDateFormatted(cellStyle)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(value.toString());
                } catch (ParseException e) {
                    cellIn.setCellValue(new XSSFRichTextString(value.toString()));
                }
                cellIn.setCellValue(date);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    public static void setMegraValueCustom(XSSFSheet sheet, int row, int cell,
            Object value, CellStyle cellStyle, Map<String, String> keyMap,
            String key) {
        XSSFRow rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        XSSFCell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            // 修复产生多超过4000 cellStyle 异常
            // CellStyle newStyle = wb.createCellStyle();
            // newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        if (keyMap.get(key) != null && !"".equals(keyMap.get(key))) {
            int size = Integer.valueOf(keyMap.get(key));
            if (cell == 0 || cell == 3 || cell == 4) {
                sheet.addMergedRegion(new CellRangeAddress(row, row + size - 1,
                        cell, cell));
            }
        }
        // 对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        } else if (CalcTotalUtil.isCanParseDouble(value)) {
            String str = value.toString();
            double tempDouble = 0d;
            tempDouble = Double.parseDouble(str);
            cellIn.setCellValue(tempDouble);
        } else {
            if (isCellDateFormatted(cellStyle)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(value.toString());
                } catch (ParseException e) {
                    cellIn.setCellValue(new XSSFRichTextString(value.toString()));
                }
                cellIn.setCellValue(date);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    public static int colspan = 0;

    public static void setPmisBudgetMegraValue(XSSFSheet sheet, int row,
            int cell, Object value, CellStyle cellStyle) {
        XSSFRow rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        XSSFCell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            cellIn.setCellStyle(cellStyle);
        }

        if (cell == 1 && "分类小计".equals(value)) {
            sheet.addMergedRegion(new CellRangeAddress(row - (colspan / 7),
                    row - 1, 1, 1));
            colspan = 0;
        } else {
            colspan++;
        }

        // 对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        } else if (CalcTotalUtil.isCanParseDouble(value)) {
            String str = value.toString();
            double tempDouble = 0d;
            tempDouble = Double.parseDouble(str);
            cellIn.setCellValue(tempDouble);
        } else {
            if (isCellDateFormatted(cellStyle)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(value.toString());
                } catch (ParseException e) {
                    cellIn.setCellValue(new XSSFRichTextString(value.toString()));
                }
                cellIn.setCellValue(date);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    public static void setMegraValueHistory(XSSFSheet sheet, int row, int cell,
            Object value, CellStyle cellStyle,
            Map<String, Integer> idListkeyMap, String key, int columnOperate) {
        XSSFRow rowIn = sheet.getRow(row);
        if (rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        XSSFCell cellIn = rowIn.getCell(cell);
        if (cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if (cellStyle != null) {
            cellIn.setCellStyle(cellStyle);
        }
        if (idListkeyMap.get(key) != null && !"".equals(idListkeyMap.get(key))) {
            int size = Integer.valueOf(idListkeyMap.get(key));
            // 合并columnOperate之前所有的列
            if (columnOperate > cell) {
                sheet.addMergedRegion(new CellRangeAddress(row, row + size - 1,
                        cell, cell));
            }
        }
        cellIn.removeCellComment();
        // 对时间格式进行单独处理
        if (value == null) {
            cellIn.setCellValue("");
        } else if (CalcTotalUtil.isCanParseDouble(value)) {
            String str = value.toString();
            double tempDouble = 0d;
            tempDouble = Double.parseDouble(str);
            cellIn.setCellValue(tempDouble);
        } else {
            if (isCellDateFormatted(cellStyle)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(value.toString());
                } catch (ParseException e) {
                    cellIn.setCellValue(new XSSFRichTextString(value.toString()));
                }
                cellIn.setCellValue(date);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }

    /**
     * 根据表格样式判断是否为日期格式
     * 
     * @param cellStyle
     * @return
     */
    public static boolean isCellDateFormatted(CellStyle cellStyle) {
        if (cellStyle == null) {
            return false;
        }
        int i = cellStyle.getDataFormat();
        String f = cellStyle.getDataFormatString();

        return org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
    }

    /**
     * 适用于导出的数据Excel格式样式重复性较少 不适用于循环方法中使用
     * 
     * @param wbModule
     * @param sheet
     * @param pos
     *            模板文件信息
     * @param startCell
     *            开始的行
     * @param value
     *            要填充的数据
     * @param cellStyle
     *            表格样式
     */
    public static void createCell(XSSFWorkbook wbModule, XSSFSheet sheet,
            HashMap pos, int startCell, Object value, String cellStyle) {
        int[] excelPos = getPos(pos, cellStyle);
        setValue(sheet, startCell, excelPos[0], value,
                getStyle(pos, cellStyle, wbModule));
    }
    /************************************ XSSF *******************************************/
}
