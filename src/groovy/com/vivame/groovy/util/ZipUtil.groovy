package com.vivame.groovy.util

import java.util.zip.ZipInputStream
import org.apache.tools.zip.ZipEntry
import org.apache.tools.zip.ZipOutputStream

/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2009-6-3
 * 时间: 13:42:56
 * 说明: 该类实现。。。。
 * 修改人:王海利
 * 修改时间: 2009-6-3 13:42:56
 */

public class ZipUtil {

  public void zip(String zipFileName, String inputFile) throws Exception {

    zip(zipFileName, new File(inputFile));

  }

  public void zip(String zipFileName, File inputFile) throws Exception {

    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));

    zip(out, inputFile, "");

    System.out.println("zip done");

    out.close();

  }


  public void zip(ZipOutputStream out, File f, String base) throws Exception {

    System.out.println("Zipping  " + f.getName());

    if (f.isDirectory())

    {

      def ff = f.listFiles();

      out.putNextEntry(new ZipEntry(base + "/"));

      base = base.length() == 0 ? "" : base + "/";

      ff.each {
        zip(out, it, base + it.getName());
      }

    }

    else

    {

      out.putNextEntry(new ZipEntry(base));

      FileInputStream ins = new FileInputStream(f);

      int b;

      while ((b = ins.read()) != -1)

        out.write(b);

      ins.close();

    }


  }

  public void unzip(String zipFileName, String outputDirectory) throws Exception {

    ZipInputStream ins = new ZipInputStream(new FileInputStream(zipFileName));

    ZipEntry z;

    while ((z = ins.getNextEntry ()) != null)

    {

      System.out.println("unziping " + z.getName());

      if (z.isDirectory())

      {

        String name = z.getName();

        name = name.substring(0, name.length() - 1);

        File f = new File(outputDirectory + File.separator + name);

        f.mkdir();

        System.out.println("mkdir " + outputDirectory + File.separator + name);

      }

      else {

        File f = new File(outputDirectory + File.separator + z.getName());

        f.createNewFile();

        FileOutputStream out = new FileOutputStream(f);

        int b;

        while ((b = ins.read ()) != - 1)

        out.write(b);

        out.close();

      }

    }



    ins.close ();

  }



  public static void main(String[] args) {
    new ZipUtil().zip("C:\\Documents and Settings\\hailiw\\桌面\\JMag11.jar", "C:\\Documents and Settings\\hailiw\\桌面\\JMag")
    //new ZipUtil().unzip("C:\\Documents and Settings\\hailiw\\桌面\\JMag11.jar","C:\\Documents and Settings\\hailiw\\桌面\\test")
  }
}