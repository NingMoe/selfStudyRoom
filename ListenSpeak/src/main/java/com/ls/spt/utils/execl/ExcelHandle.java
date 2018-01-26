package com.ls.spt.utils.execl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 对excel进行操作工具类
 * 
 * @author xiliang.xiao
 * @date 2015年1月8日 下午1:46:36
 * 
 **/
@SuppressWarnings("rawtypes")
public class ExcelHandle {

    private Map<String, HashMap[]> tempFileMap = new HashMap<String, HashMap[]>();
    private Map<String, Map<String, Cell>> cellMap = new HashMap<String, Map<String, Cell>>();
    private Map<String, FileInputStream> tempStream = new HashMap<String, FileInputStream>();
    private Map<String, XSSFWorkbook> tempWorkbook = new HashMap<String, XSSFWorkbook>();
    private Map<String, XSSFWorkbook> dataWorkbook = new HashMap<String, XSSFWorkbook>();

    /**
     * 单无格类
     * 
     * @author xiliang.xiao
     * 
     */
    class Cell {
        private int column;// 列
        private int line;// 行
        private CellStyle cellStyle;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public CellStyle getCellStyle() {
            return cellStyle;
        }

        public void setCellStyle(CellStyle cellStyle) {
            this.cellStyle = cellStyle;
        }
    }

    public void writePmisBudgetData(String tempFilePath, List<String> cellList,
            Map<String, List<Map<String, Object>>> mapDataList, int sheet,
            String account, String fileName) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));

        XSSFRow firstRow = wsheet.getRow(0);
        XSSFCell firstCell = firstRow.getCell(0);
        firstCell.setCellValue(new XSSFRichTextString(fileName));

        List<Map<String, Object>> dataList = mapDataList.get(account);
        ExcelUtil.colspan = 0;
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> map : dataList) {
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    // 写入数据
                    ExcelUtil.setPmisBudgetMegraValue(wsheet, startCell,
                            c.getColumn(), map.get(cell), c.getCellStyle());
                }
                startCell++;
            }
        }
    }

    public void writeMegraData(String tempFilePath, List<String> cellList,
            Map<String, List<Map<String, Object>>> mapDataList, int sheet,
            String account) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));
        List<Map<String, Object>> dataList = mapDataList.get(account);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> map : dataList) {
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    // 写入数据
                    ExcelUtil.setMegraValue(wsheet, startCell, c.getColumn(),
                            map.get(cell), c.getCellStyle());
                }
                startCell++;
            }
        }
    }

    public void writeMegraDataCustom(String tempFilePath,
            List<String> cellList,
            Map<String, List<Map<String, Object>>> mapDataList, int sheet,
            String account, Map<String, String> keyMap) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));
        List<Map<String, Object>> dataList = mapDataList.get(account);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> map : dataList) {
                String key = "";
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    if ("processName".equals(cell)) {
                        key = map.get(cell).toString();
                    }
                    // 写入数据
                    ExcelUtil.setMegraValueCustom(wsheet, startCell,
                            c.getColumn(), map.get(cell), c.getCellStyle(),
                            keyMap, key);
                }
                startCell++;
            }
        }
    }

    public void writeMegraDataHistory(String tempFilePath,
            List<String> cellList,
            Map<String, List<Map<String, Object>>> mapDataList, int sheet,
            String account, Map<String, Integer> idListkeyMap,
            boolean isSYBFinish) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));
        List<Map<String, Object>> dataList = mapDataList.get(account);
        if (dataList != null && dataList.size() > 0) {
            // columnOperate为变更记录时间单元格
            int columnOperate = 0;
            for (String cell : cellList) {
                // 获取变更记录时间的单元格列数
                Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                if ("operate".equals(cell)) {
                    columnOperate = c.getColumn();
                }
            }
            for (Map<String, Object> map : dataList) {
                String key = "";
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    if (!isSYBFinish) {
                        if ("projectNumber".equals(cell)
                                && map.get(cell) != null) {
                            key = map.get(cell).toString();
                        }
                    } else {
                        if ("contractProjectNumber".equals(cell)
                                && map.get(cell) != null) {
                            key = map.get(cell).toString();
                        }
                    }
                    // 写入数据
                    ExcelUtil.setMegraValueHistory(wsheet, startCell,
                            c.getColumn(), map.get(cell), c.getCellStyle(),
                            idListkeyMap, key, columnOperate);
                }
                startCell++;
            }
        }
    }

    /**
     * 向Excel中输入相同title的多条数据
     * 
     * @param tempFilePath
     *            excel模板文件路径
     * @param cellList
     *            需要填充的数据（模板<!%后的字符串）
     * @param dataList
     *            填充的数据
     * @param sheet
     *            填充的excel sheet,从0开始
     * @throws IOException
     */
    public void writeListData(String tempFilePath, List<String> cellList,
            Map<String, List<Map<String, Object>>> mapDataList, int sheet,
            String account) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));
        List<Map<String, Object>> dataList = mapDataList.get(account);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> map : dataList) {
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    // 写入数据
                    ExcelUtil.setValue(wsheet, startCell, c.getColumn(),
                            map.get(cell), c.getCellStyle());
                }
                startCell++;
            }
        }
    }

    /**
     * 向Excel中输入相同title的多条数据
     * 
     * @param tempFilePath
     *            excel模板文件路径
     * @param cellList
     *            需要填充的数据（模板<!%后的字符串）
     * @param dataList
     *            填充的数据
     * @param sheet
     *            填充的excel sheet,从0开始
     * @throws IOException
     */
    public void writeListData(String tempFilePath, List<String> cellList,
         List<Map<String,Object>> mapDataList, int sheet) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap temp = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) temp.get("STARTCELL"));
        // 数据填充的sheet
        XSSFSheet wsheet = temWorkbook.getSheetAt(sheet);
        // 移除模板开始行数据即<!%
        wsheet.removeRow(wsheet.getRow(startCell));
            for (Map<String, Object> map : mapDataList) {
                for (String cell : cellList) {
                    // 获取对应单元格数据
                    Cell c = getCell(cell, temp, temWorkbook, tempFilePath);
                    // 写入数据
                    ExcelUtil.setValue(wsheet, startCell, c.getColumn(),
                            map.get(cell), c.getCellStyle());
                }
                startCell++;
            }
    }
    
    /**
     * 按模板向Excel中相应地方填充数据
     * 
     * @param tempFilePath
     *            excel模板文件路径
     * @param cellList
     *            需要填充的数据（模板<%后的字符串）
     * @param dataMap
     *            填充的数据
     * @param sheet
     *            填充的excel sheet,从0开始
     * @throws IOException
     */
    public void writeData(String tempFilePath, List<String> cellList,
            Map<String, Object> dataMap, int sheet) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap tem = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook wbModule = getTempWorkbook(tempFilePath);
        // 数据填充的sheet
        XSSFSheet wsheet = wbModule.getSheetAt(sheet);
        if (dataMap != null && dataMap.size() > 0) {
            for (String cell : cellList) {
                // 获取对应单元格数据
                Cell c = getCell(cell, tem, wbModule, tempFilePath);
                ExcelUtil.setValue(wsheet, c.getLine(), c.getColumn(),
                        dataMap.get(cell), c.getCellStyle());
            }
        }
    }

    /**
     * Excel文件读值
     * 
     * @param tempFilePath
     * @param cell
     * @param sheet
     * @return
     * @throws IOException
     */
    public Object getValue(String tempFilePath, String cell, int sheet,
            File excelFile) throws IOException {
        // 获取模板填充格式位置等数据
        HashMap tem = getTemp(tempFilePath, sheet);
        // 模板工作区
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 数据工作区
        XSSFWorkbook dataWorkbook = getDataWorkbook(tempFilePath, excelFile);
        // 获取对应单元格数据
        Cell c = getCell(cell, tem, temWorkbook, tempFilePath);
        // 数据sheet
        XSSFSheet dataSheet = dataWorkbook.getSheetAt(sheet);
        return ExcelUtil.getCellValue(dataSheet, c.getLine(), c.getColumn());
    }

    /**
     * 读值列表值
     * 
     * @param tempFilePath
     * @param cell
     * @param sheet
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> getListValue(String tempFilePath,
            List<String> cellList, int sheet, File excelFile)
        throws IOException {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        // 获取模板填充格式位置等数据
        HashMap tem = getTemp(tempFilePath, sheet);
        // 获取数据填充开始行
        int startCell = Integer.parseInt((String) tem.get("STARTCELL"));
        // 将Excel文件转换为工作区间
        XSSFWorkbook dataWorkbook = getDataWorkbook(tempFilePath, excelFile);
        // 数据sheet
        XSSFSheet dataSheet = dataWorkbook.getSheetAt(sheet);
        // 文件最后一行
        int lastLine = dataSheet.getLastRowNum();

        for (int i = startCell; i <= lastLine; i++) {
            dataList.add(getListLineValue(i, tempFilePath, cellList, sheet,
                    excelFile));
        }
        return dataList;
    }

    /**
     * 读值一行列表值
     * 
     * @param tempFilePath
     * @param cell
     * @param sheet
     * @return
     * @throws IOException
     */
    public Map<String, Object> getListLineValue(int line, String tempFilePath,
            List<String> cellList, int sheet, File excelFile)
        throws IOException {
        Map<String, Object> lineMap = new HashMap<String, Object>();
        // 获取模板填充格式位置等数据
        HashMap tem = getTemp(tempFilePath, sheet);
        // 按模板为写入板
        XSSFWorkbook temWorkbook = getTempWorkbook(tempFilePath);
        // 将Excel文件转换为工作区间
        XSSFWorkbook dataWorkbook = getDataWorkbook(tempFilePath, excelFile);
        // 数据sheet
        XSSFSheet dataSheet = dataWorkbook.getSheetAt(sheet);
        for (String cell : cellList) {
            // 获取对应单元格数据
            Cell c = getCell(cell, tem, temWorkbook, tempFilePath);
            lineMap.put(cell,
                    ExcelUtil.getCellValue(dataSheet, line, c.getColumn()));
        }
        return lineMap;
    }

    /**
     * 获得模板输入流
     * 
     * @param tempFilePath
     * @return
     * @throws FileNotFoundException
     */
    private FileInputStream getFileInputStream(String tempFilePath)
        throws FileNotFoundException {
        if (!tempStream.containsKey(tempFilePath)) {
            tempStream.put(tempFilePath, new FileInputStream(tempFilePath));
        }

        return tempStream.get(tempFilePath);
    }

    /**
     * 获得输入工作区
     * 
     * @param tempFilePath
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    private XSSFWorkbook getTempWorkbook(String tempFilePath)
        throws FileNotFoundException, IOException {
        if (!tempWorkbook.containsKey(tempFilePath)) {
            tempWorkbook.put(tempFilePath, new XSSFWorkbook(
                    getFileInputStream(tempFilePath)));
        }
        return tempWorkbook.get(tempFilePath);
    }

    /**
     * 获取对应单元格样式等数据数据
     * 
     * @param cell
     * @param tem
     * @param wbModule
     * @param tempFilePath
     * @return
     */
    private Cell getCell(String cell, HashMap tem, XSSFWorkbook wbModule,
            String tempFilePath) {
        if (!cellMap.get(tempFilePath).containsKey(cell)) {
            Cell c = new Cell();

            int[] pos = ExcelUtil.getPos(tem, cell);
            if (pos.length > 1) {
                c.setLine(pos[1]);
            }
            c.setColumn(pos[0]);
            c.setCellStyle((ExcelUtil.getStyle(tem, cell, wbModule)));
            cellMap.get(tempFilePath).put(cell, c);
        }
        return cellMap.get(tempFilePath).get(cell);
    }

    /**
     * 获取模板数据
     * 
     * 
     * @param tempFilePath
     *            模板文件路径
     * @param sheet
     * @return
     * @throws IOException
     */
    private HashMap getTemp(String tempFilePath, int sheet) throws IOException {
        if (!tempFileMap.containsKey(tempFilePath)) {
            tempFileMap.put(tempFilePath,
                    ExcelUtil.getTemplateFile(tempFilePath));
            cellMap.put(tempFilePath, new HashMap<String, Cell>());
        }
        return tempFileMap.get(tempFilePath)[sheet];
    }

    /**
     * 资源关闭
     * 
     * @param tempFilePath
     *            模板文件路径
     * @param os
     *            输出流
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void writeAndClose(String tempFilePath, OutputStream os)
        throws FileNotFoundException, IOException {
        if (getTempWorkbook(tempFilePath) != null) {
            getTempWorkbook(tempFilePath).write(os);
            tempWorkbook.remove(tempFilePath);
        }
        if (getFileInputStream(tempFilePath) != null) {
            getFileInputStream(tempFilePath).close();
            tempStream.remove(tempFilePath);
        }
    }

    /**
     * 获得读取数据工作间
     * 
     * @param tempFilePath
     * @param excelFile
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    private XSSFWorkbook getDataWorkbook(String tempFilePath, File excelFile)
        throws FileNotFoundException, IOException {
        if (!dataWorkbook.containsKey(tempFilePath)) {
            dataWorkbook.put(tempFilePath, new XSSFWorkbook(
                    new FileInputStream(excelFile)));
        }
        return dataWorkbook.get(tempFilePath);
    }

    /**
     * 读取数据后关闭
     * 
     * @param tempFilePath
     */
    public void readClose(String tempFilePath) {
        dataWorkbook.remove(tempFilePath);
    }
}
