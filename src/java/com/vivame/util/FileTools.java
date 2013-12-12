package com.vivame.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;


/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2008-11-24
 * 时间: 18:02:07
 * 说明: 该类实现文件操作
 * 修改人:王海利
 * 修改时间: 2008-11-24 18:02:07
 */
public class FileTools {
    /**
     * 将文件中指定的第index个8个字节写成指定的ldata
     *
     * @param ffile 源文件路径
     * @param tfile 目标文件路径
     * @param index 第几个8个字节
     * @param ldata 需要写入的long值
     * @return true 成功 ； false 失败
     */
    public static boolean changeFile(String ffile, long ldata) {


        boolean r = true;
        long l;

        RandomAccessFile f = null;

        try {
            f = new RandomAccessFile(ffile, "rw");
            try {

                f.readLong();
                f.writeLong(ldata);

            } catch (Exception e) {
                e.printStackTrace();

            }


        } catch (FileNotFoundException e) {
            r = false;
        } finally {

            try {
                if (f != null) f.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return r;
    }


    /**
     * 读取zip文件
     *
     * @param f zip文件
     */
    public static void readZipFile(File f) {
        ZipFile zfile = null;
        try {
            zfile = new ZipFile(f); //zip文件
            Enumeration zList = zfile.getEntries(); //zip中文件列表
            ZipEntry ze; //zip中单个文件
            while (zList.hasMoreElements()) {
                ze = (ZipEntry) zList.nextElement();//zip中单个文件
                System.out.println(ze.getName() + "," + ze.getSize());
                //以ZipEntry为参数得到一个InputStream，并写到OutputStream中
                if (ze.isDirectory()) {
                    System.out.println("zedir =" + ze.getName());


                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zfile != null) {
                try {
                    zfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        FileTools.readZipFile(new File("C:\\Documents and Settings\\hailiw\\桌面\\JMag.jar"));


    }
}
