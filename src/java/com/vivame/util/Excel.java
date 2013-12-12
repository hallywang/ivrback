package com.vivame.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2008-12-12
 * 时间: 17:30:03
 * 说明: 该类实现excle的操作
 * 修改人:王海利
 * 修改时间: 2008-12-12 17:30:03
 */
public class Excel {
    /**
     * 读取excel
     *
     * @param file       excel文件
     * @param sheetIndex sheet
     * @param skipTitle  true跳过标题行，也就是第一行不要
     * @return ArrayList
     */
    public static ArrayList<ArrayList<String>> readXls(File file, int sheetIndex, boolean skipTitle) {
        try {
            return Excel.readXls(new FileInputStream(file), sheetIndex, skipTitle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取excel
     *
     * @param in         excel文件
     * @param sheetIndex sheet
     * @param skipTitle  true跳过标题行，也就是第一行不要
     * @return ArrayList
     */
    public static ArrayList<ArrayList<String>> readXls(InputStream in, int sheetIndex, boolean skipTitle) {
        ArrayList<ArrayList<String>> exceldata = new ArrayList<ArrayList<String>>();
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFWorkbook wb = null; //excel工作簿
        try {
            wb = new HSSFWorkbook(fs); //读取文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(sheetIndex); //得到第sheetIndex个sheet
        Iterator i = sheet.iterator();
        if (skipTitle) i.next();//如果跳过标题，则第一行不要
        while (i.hasNext()) {    //循环sheet的所有行
            HSSFRow row = (HSSFRow) i.next();
            Iterator ii = row.iterator();
            ArrayList<String> rowdata = new ArrayList<String>();
            boolean isnull = true;
            while (ii.hasNext()) { //循环每行的所有列
                HSSFCell cell = (HSSFCell) ii.next();
                if (!"".equals(cell.getRichStringCellValue().toString())) { //不为空时候
                    rowdata.add(cell.getRichStringCellValue().toString());
                    isnull = false;
                }
            }
            if (!isnull)
                exceldata.add(rowdata);

        }

        return exceldata;
    }

    public static void main(String[] args) {
        File f = new File("/e:/1.xls");
        ArrayList<ArrayList<String>> a = Excel.readXls(f, 0, false);

        for (ArrayList<String> strings : a) {
            for (String string : strings) {
                System.out.print(string + ",");
            }
            System.out.println(" ");
        }
    }
}
