package com.vivame.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * viavame All Rights Reserved@2008-2009 作者: 王海利 日期: 2008-11-26 时间: 15:24:19 说明: 该类实现。。。。 修改人:王海利 修改时间: 2008-11-26
 * 15:24:19
 */

public class TimeUtil {

    /**
     * @param m （m=-1返回上周一  m=0返回本周一  m=1或者其他值返回下周一）
     * @return yyyy-MM-dd
     */
    public static String getModayOfWeek(int m) {  //当前是星期几
        int dayOfweek = GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfweek == 0)
            dayOfweek = 7;
        Calendar cal = Calendar.getInstance();
        if (m == -1)//返回上周一的yyyy-MM-dd日期格式)
            m = -(dayOfweek - 1) - 7;
        else if (m == 0)//返回本周一的yyyy-MM-dd日期格式
            m = -(dayOfweek - 1);
        else//==1或者其他值 返回下周一的yyyy-MM-dd日期格式
            m = 7 - dayOfweek + 1;
        cal.add(java.util.Calendar.DATE, m);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//这里可以设置你想要的各种格式啊
        return formatter.format(cal.getTime());
    }


    /**
     * @param m （m=-1返回上周一  m=0返回本周一  m=1或者其他值返回下周一）
     * @return date 日期
     */
    public static Date getModayOfWeekDate(int m) {  //当前是星期几
        int dayOfweek = GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfweek == 0)
            dayOfweek = 7;
        Calendar cal = Calendar.getInstance();
        if (m == -1)//返回上周一的yyyy-MM-dd日期格式)
            m = -(dayOfweek - 1) - 7;
        else if (m == 0)//返回本周一的yyyy-MM-dd日期格式
            m = -(dayOfweek - 1);
        else//==1或者其他值 返回下周一的yyyy-MM-dd日期格式
            m = 7 - dayOfweek + 1;
        cal.add(java.util.Calendar.DATE, m);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);


        return (cal.getTime());
    }

    /**
     * 获取某一天的日期格式yyyy-MM-dd
     *
     * @param m (m=0本日 m=1明天)
     * @return str
     */
    public static String getDate(int m) {
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, m);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//这里可以设置你想要的各种格式啊
        return formatter.format(cal.getTime());
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");//这里可以设置你想要的各种格式啊
        return formatter.format(cal.getTime());
    }

    /**
     * @param d
     * @return
     */
    public static String getDate2(Date d) {

        return getDate(d, "yyyy-MM-dd");

    }


    /**
     * @param d
     * @return
     */
    public static String getDate(Date d, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);//这里可以设置你想要的各种格式啊
        return formatter.format(d);

    }


    public static Date parseDate(String date) {
        return TimeUtil.parseDateByString(date, "yyyy-MM-dd", 0);
    }

    /**
     * @param date   日期 如 2008-9-1
     * @param format 如 yyyy-mm-dd
     * @param time   0 表示设置时间为0点0分0秒，1 表示设置为23:59:59
     * @return
     */
    public static Date parseDateByString(String date, String format, int time) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);//这里可以设置你想要的各种格式啊
        if (time == 1) {
            date = date + " 23:59:59";
            format = format + " HH:mm:ss";
            formatter = new SimpleDateFormat(format);//这里可以设置你想要的各种格式啊
        }


        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * @param m (m=0本日 m=1明天,-1 昨天)
     * @return str
     */
    public static Date getDateBefore(int m) {
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, m);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);


        return cal.getTime();
    }


    /**
     * 得到小时和分钟
     *
     * @param d 日期
     * @return 15：32
     */
    public static String getTime(Date d) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(d);
        String mi = "";

        mi = cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : "" + cal.get(Calendar.MINUTE);

        return cal.get(Calendar.HOUR_OF_DAY) + ":" + mi;
    }


    /**
     * 判断是否是当月第一周 0不是 1是 按周一到周日为一周
     *
     * @return
     */
    public static int isFirstWeek() {
        int flag = 0;
        GregorianCalendar cal = new GregorianCalendar();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0)
            week = 7;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (week >= day)
            flag = 1;
        return flag;

    }


    /**
     * 取上一周的第一天日期
     *
     * @return
     */
    public static String getFirstDateOfLastWeek() {
        String firstDate = null;
        GregorianCalendar cal = new GregorianCalendar();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        /*
           * int month=cal.get(Calendar.MONTH)+1; int year=cal.get(Calendar.YEAR);
           */
        if (week == 0)
            week = 7;
        cal.add(Calendar.DATE, -(week + 6));
        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date d = cal.getTime();
        firstDate = aSimpleDateFormat.format(d);
        /*
           * int lastmonth=cal.get(Calendar.MONTH)+1; if(lastmonth!=month){
           * if(month<10) firstDate=year+"-0"+month+"-"+"01"; else
           * firstDate=year+"-"+month+"-"+"01"; }else{ SimpleDateFormat
           * aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); Date
           * d=cal.getTime(); firstDate=aSimpleDateFormat.format(d); }
           */

        return firstDate;
    }

    /**
     * 获取上周最后一天的时间
     * @param flag 是否沿用当前时间的时分秒的标志：<br/>
     *             0 表示不需要，最终返回的为上周第一天的0时0分0秒 <br/>
     *             1 表示使用23时59分59秒999毫秒
     *             其他，  表示沿用当前时间的时分秒 <br/>
     * @return Date类型的时间
     */
    public static Date getLastDOfLastWeek(int flag) {
        String lastDate = null;
        GregorianCalendar cal = new GregorianCalendar();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        cal.add(Calendar.DATE, -week);
                if (flag == 0) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else if (flag == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }

        return cal.getTime();
    }

    /**
     * 获取上周第一天的时间
     *
     * @param flag 是否沿用当前时间的时分秒的标志：<br/>
     *             0 表示不需要，最终返回的为上周第一天的0时0分0秒 <br/>
     *             1 表示使用23时59分59秒999毫秒 <br/>
     *             其他，表示沿用当前时间的时分秒 <br/>
     * @return Date类型的时间
     */
    public static Date getFirstDOfLastWeek(int flag) {
        GregorianCalendar cal = new GregorianCalendar();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        cal.add(Calendar.DATE, -(week + 6));
        if (flag == 0) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else if (flag == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }
        
        return cal.getTime();
    }


    /**
     * 取上一周最后一天日期
     *
     * @return String类型的时间
     */
    public static String getLastDateOfLastWeek() {
        String lastDate = null;
        GregorianCalendar cal = new GregorianCalendar();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0)
            week = 7;
        cal.add(Calendar.DATE, -week);
        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date d = cal.getTime();
        lastDate = aSimpleDateFormat.format(d);
        return lastDate;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(TimeUtil.getDate(new Date(), "yyyyMMddHHmmssSSS"));
//        System.out.println("当前日期-【开始时间】:"+getCurrDateS2ETime (0) + "，【结束时间】:"+getCurrDateS2ETime (1));
        System.out.println(TimeUtil.getNDateLater(TimeUtil.parseDateByString(TimeUtil.getMonthFirstDayDate(0), "yyyy-MM-dd", 0), "M", -3));
        System.out.println(TimeUtil.getDate(TimeUtil.getNDateLater(TimeUtil.parseDateByString(TimeUtil.getMonthFirstDayDate(0), "yyyy-MM-dd", 0), "M", -3), "yyyy-MM-dd HH:mm:ss"));

    }


    /**
     * 获取上周时间
     * @param rtnFlag 返回值类型，0返回Date类型的时间，1返回14位String类型的时间
     * @param timeExtFlag 结尾时间标志：0表示0时0分0秒0毫秒，1表示23时59分59秒999毫秒，其他表示当前时间的时分秒毫秒
     * @param dayOfWeek 周几，取值范围为0-7， <br/>
     *         0：上周日，<br/>
     *         1：上周一，<br/>
     *         2：上周二，<br/>
     *         3：上周三，<br/>
     *         4：上周四，<br/>
     *         5：上周五，<br/>
     *         6：上周六，<br/>
     *         7：本周日，我们习惯将周日作为一周的最后一天， 所有上周日实际上本周的第一天 <br>
     *         >7：任何大于7的数字将会被“取余”操作
     *         <0：任何小于0的数字将会被“绝对值”操作
     * @return 返回Object类型的时间，调用者请根据实际需求进行类型转换
     */
    public static Object getLastWeekTime(int rtnFlag, int timeExtFlag, int dayOfWeek) {
        Object rtnObj = null;
        dayOfWeek = Math.abs(dayOfWeek); //先绝对值
        if (dayOfWeek > 7) {
            dayOfWeek = dayOfWeek % 7; //取余操作
        }

        GregorianCalendar cal = new GregorianCalendar();
        int curWeekNo = cal.get(Calendar.DAY_OF_WEEK) -1; //“现在”是星期几，0星期天，1星期一，6星期六
        cal.add(Calendar.DATE, -(7 + curWeekNo - dayOfWeek));
        if (timeExtFlag == 0) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else if (timeExtFlag == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        } else {
            //时分秒毫秒数沿用“当前时间”的时分秒毫秒数
        }

        rtnObj = cal.getTime();
        if (rtnFlag != 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            rtnObj = df.format(rtnObj);
        }

        return rtnObj;
    }

    /**
     * 获取一段时候后的时间  <br>
     * 如想要获取“现在”之后一个小时后的时间
     *
     * @param baseDate 基础时间,
     * @param field    类型：y：年，M：月，d：天，h：小时，m：分钟，s：秒
     * @param amount   便宜时间量
     * @return 返回一个Date类型的时间
     */
    public static Date getNDateLater(Date baseDate, String field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(baseDate);

        int f = Calendar.SECOND;
        if ("y".equals(field)) {
            f = Calendar.YEAR;
        } else if ("M".equals(field)) {
            f = Calendar.MONTH;
        } else if ("d".equals(field)) {
            f = Calendar.DAY_OF_MONTH;
        } else if ("h".equals(field)) {
            f = Calendar.HOUR_OF_DAY;
        } else if ("m".equals(field)) {
            f = Calendar.MINUTE;
        }

        cal.add(f, amount);

        return cal.getTime();
    }

    public static String formatTimeString(String timeStr) {
        if (StringUtil.isEmpty(timeStr)) {
            return "";
        }

        String rtnCode = "";
        if (timeStr.length() == 14) {
            //将14位时间格式化为 2010-07-27 11:34:20
            rtnCode = timeStr.substring(0, 4) + "-" + timeStr.substring(4, 6) + "-" + timeStr.substring(6, 8) + " "
                    + timeStr.substring(8, 10) + ":" + timeStr.substring(10, 12) + ":" + timeStr.substring(12, 14);

        }

        return rtnCode;
    }

    /**
     * 格式转换，把yyyyMMddHHmmss格式的数据转换成日期行
     * @param date yyyyMMddHHmmss格式的数据
     * @author guoqiang
     */
    public static Date formatStr2Date(String date) throws ParseException{
        if (StringUtil.isEmpty(date)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.parse(date);
    }

    /**
     * 格式化数据，把yyyy-MM-dd的日期格式转换为yyyyMMddHHmmss格式
     * @param date yyyy-MM-dd格式的数据
     * @return yyyyMMddHHmmss格式的数据
     * @author guoqiang
     */
    public static String formatStr2Str(String date) throws ParseException{
        if (StringUtil.isEmpty(date)) {
            return "";
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

        Date d = sdf1.parse(date);
        String fomatDate = sdf2.format(d);
        return fomatDate;
    }

    /**
     * 格式化数据，把yyyy-MM-dd的日期格式转换为yyyyMMdd235959格式
     * @param date yyyy-MM-dd格式的数据
     * @return yyyyMMddHHmmss格式的数据
     * @author guoqiang
     */
    public static String formatStr2EndStr(String date) throws ParseException{
        if (StringUtil.isEmpty(date)) {
            return "";
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

        Date d = sdf1.parse(date);
        String fomatDate = sdf2.format(d);
        String reFomatDate = fomatDate + "235959";
        return reFomatDate;
    }

    /**
     * 格式化数据：把yyyyMMddHHmmss格式的数据转换成 yyyy-MM-dd格式的数据
     * @param d
     * @return
     * @throws ParseException
     */
    public static String formatStr2ShortStr(String d) throws ParseException {
        if (StringUtil.isEmpty(d)) {
            return "";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = sdf2.parse(d);
        return sdf1.format(date);
    }

    /**
     * 获取当前日期的开始时间或结束时间
     * @param timeType
     *          0-开始时间，1-结束时间
     * @return
     *
     * @author RongWei
     * @date 2011-02-11
     */
    public static Date getCurrDateS2ETime(int timeType){
        return parseDateByString(getDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd", timeType);
    }

    /**
     * 获取本周时间
     *
     * @param rtnFlag     返回值类型，0返回Date类型的时间，1返回14位String类型的时间
     * @param timeExtFlag 结尾时间标志：0表示0时0分0秒0毫秒，1表示23时59分59秒999毫秒，其他表示当前时间的时分秒毫秒
     * @param dayOfWeek   周几，取值范围为1-7， <br/>
     *                    1：周一，<br/>
     *                    2：周二，<br/>
     *                    3：周三，<br/>
     *                    4：周四，<br/>
     *                    5：周五，<br/>
     *                    6：周六，<br/>
     *                    7：本周日，我们习惯将周日作为一周的最后一天， 所以本周日实际上是下周的第一天 <br>
     * @return 返回Object类型的时间，调用者请根据实际需求进行类型转换
     */
    public static Object getThisWeekTime(int rtnFlag, int timeExtFlag, int dayOfWeek) {
        Object rtnObj = null;
        dayOfWeek = (dayOfWeek < 1 || dayOfWeek > 7) ? 1 : dayOfWeek;   // 参数不合法，则直接取周一算

        GregorianCalendar cal = new GregorianCalendar();
        int curWeekNo = cal.get(Calendar.DAY_OF_WEEK) - 1; //“现在”是星期几，0星期天，1星期一，6星期六
        curWeekNo = curWeekNo == 0 ? 7 : curWeekNo;
        cal.add(Calendar.DATE, -(curWeekNo - dayOfWeek));
        if (timeExtFlag == 0) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else if (timeExtFlag == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        } else {
            //时分秒毫秒数沿用“当前时间”的时分秒毫秒数
        }

        rtnObj = cal.getTime();
        if (rtnFlag != 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            rtnObj = df.format(rtnObj);
        }

        return rtnObj;
    }

    /**
     * 获取一个月最后一天日期
     *
     * @param m ０：本月，-1：上月，-2:上上月
     * @return String型日期
     * @date 2011-03-28
     */
    public static String getMonthLastDayDate(int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, m);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//一个月的天数
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(calendar.getTime());
    }

    /**
     * 获取一个月第一天日期
     *
     * @param m ０：本月，-1：上月，-2:上上月
     * @return String型日期
     * @author 周婉瓅
     * @date 2011-03-28
     */
    public static String getMonthFirstDayDate(int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(calendar.getTime());
    }


    /**
     * 获取最后一天
     * @param distance 与今年的差距值：0表示今年，-1去年，-2前年，1明年
     * @return 返回yyyy-MM-dd格式的日期
     */
    public static String getLastDayOfYear(int distance) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, distance);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(calendar.getTime());
    }
}
