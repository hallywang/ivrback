package com.vivame.util;

import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * viavame All Rights Reserved@2008-2009 作者: 王海利 日期: 2008-12-16 时间: 10:11:27 说明: 该类实现。。。。 修改人:王海利 修改时间: 2008-12-16
 * 10:11:27
 */
public class StringUtil {

    public static long String2Long(Object obj, long def) {
        long result = def;
        if (!isEmpty(Object2Str(obj))) {
            try {
                result = Long.valueOf(Object2Str(obj));
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }

        return result;
    }

    public static int String2Int(String str, int defaultValue) {
        if (str != null) {
            try {
                return Integer.valueOf(str.trim());
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static int[] StringArray2IntArray(String[] str) {
        int[] result = {};
        if (str != null && str.length > 0) {
            result = new int[str.length];
            try {
                for (int i = 0; i < str.length; i++) {
                    result[i] = Integer.parseInt(str[i]);
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 把string数组转换成LongList
     * @param objIds String数字
     * @return
     * @author guoqiang
     */
    public static List<Long> StringArray2LongList(String[] objIds) {
        java.util.List<Long> objIdList = new ArrayList<Long>();

        if (null != objIds && objIds.length > 0) {
            try {
                for (int i = 0; i < objIds.length; i++) {
                    objIdList.add(Long.parseLong(objIds[i]));

                }
            } catch (Exception e) {

            }
        }

        return objIdList;
    }


    public static boolean isEmpty(String args) {
        if (args != null && args.trim().length() > 0)
            return false;
        return true;
    }

    public static String Object2Str(Object obj) {
        if (obj != null && !obj.toString().equalsIgnoreCase("null")) {
            return obj.toString().trim();
        }

        return "";
    }

    /**
     * 得到文件前缀，不包含扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFilePreName(String fileName) {
        if (fileName == null || "".equals(fileName)) return "";

        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 得到文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileExtName(String fileName) {
        if (fileName == null || "".equals(fileName)) return "";

        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    /**
     * 得到文件的简单名称，如 e:/a.txt 得到a.txt
     *
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
        if (fileName == null || "".equals(fileName)) return "";

        return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
    }

    /**
     * 得到文件单独的前缀，不带路径
     *
     * @param fileName
     * @return
     */
    public static String getSingleExtName(String fileName) {
        return StringUtil.getFilePreName(StringUtil.getFileName(fileName));
    }

    public static String encodeWml(String s) {

        if (s == null) s = "";
        return s.replaceAll("&amp;", "&")
                .replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("'", "&apos;")
                .replaceAll("\"", "&quot;")
                ;
    }

    /**
     * Return left characters of a string according to the given length.
     *
     * @param s     Input String
     * @param limit Max display length of the string.
     * @return cutted string
     */
    public static String cutString(String s, int limit) {
        if (s == null || s.length() == 0)
            return "";

        if (s.length() < limit / 2 || limit < 1)
            return s;

        char c;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c > 128 || c < 0) count++;

            count++;

            if (count >= limit) {
                // System.out.println(count);
                int x = count % 2 == 0 ? ++i : i;
                return s.substring(0, x) + "...";
            }
        }

        return s;
    }

    /**
     * 字符串转换成十六进制值
     *
     * @param bin String 我们看到的要转换成十六进制的字符串
     * @return
     */
    public static String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     *
     * @param hex String 十六进制
     * @return String 转换后的字符串
     */
    public static String hex2bin(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return new String(bytes);
    }


    public static void main(String[] args) {
        // System.out.println(StringUtil.getFilePreName("daf.ads.jpg"));
        //  System.out.println(StringUtil.getFileExtName("daf.ads.jpg"));
        System.out.println(StringUtil.encodeWml("<><><><>''''''\"\""));
        System.out.println(StringUtil.getRandomChar2NumStr(6));


    }

    public static String getString(Object obj) {
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }


    /**
     * 获取随机组合指定长度的字符串
     *
     * @param length 指定字符串的长度
     * @return
     * @author RongWei
     * @date 2011-01-11
     */
    public static String getRandomChar2NumStr(int length) {
        String val = "";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    /**
     * 根据模板路径截取专题名称和模板名称
     *
     * @param url 模板路径
     * @return 专题名称和模板名称
     */
    public static String getTopicAndTemp(String url) {
        System.out.println("url===" + url);
        if (StringUtils.isBlank(url)) {
            return url;
        }
        String rtnUrl = "";
        String ext = "gamecms/wap/([a-z0-9\\-]*)/([a-z0-9\\-]*)";
        Pattern pattern = Pattern.compile(ext);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            rtnUrl = matcher.group(1) + "/" + matcher.group(2);
        }
        return rtnUrl;
    }
}
