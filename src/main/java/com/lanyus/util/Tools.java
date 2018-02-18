package com.lanyus.util;

/**
 * Created by Administrator on 2018/2/18 0018.
 * 基本的工具类包含判断是否为空、时间格式转换等
 */
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {
    public static Map<String, Object> map = new HashMap<String, Object>();

//    判断型函数
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")||"null".equals(str.trim())) {
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    /**
     * 判断时间字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Date str) {
        if (str == null || str.toString().equals("1900")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }
    /**
     * 判断是否为jpg
     * @param wjccmc
     * @return
     */
    public static boolean isJpg(String wjccmc) {
        if (Tools.isEmpty(wjccmc) || wjccmc.indexOf(".") == -1)
            return false;

        if (wjccmc.toLowerCase().endsWith(".jpg")
                || wjccmc.toLowerCase().endsWith(".jpeg"))
            return true;
        else
            return false;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }



//    转换型函数

    /**
     * 字符串转数字
     * @param str
     * @return
     */
    public static int stringToInt(String str) {
        int returnInt = 0;
        try {
            if (Tools.isEmpty(str))
                returnInt = 0;
            else {
                returnInt = Integer.parseInt(str);
            }
        } catch (Exception e) {
            returnInt = 0;
        }
        return returnInt;
    }

    /**
     * 将传入的字符串数组转换成字符串，转换之后的格式：'xxx','xxx',...,'xxx'
     *
     * @return String
     */
    public static String Array2String(String StringArray[]) {
        try {
            String StringResult = "";
            if (Tools.isEmpty(StringArray)) {
                return StringResult;
            }
            int size = StringArray.length;
            for (int i = 0; i < size; i++) {
                if (Tools.isEmpty((String) StringArray[i]))
                    continue;
                StringResult += "'" + (String) StringArray[i] + "',";
            }
            StringResult = StringResult.substring(0, StringResult.length() - 1);

            return StringResult;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return "";
        }
    }
    /**
     * 把源串sOld中为sPartten的字符串用sReplace替换，如 price=4535.234095 则此函数返回为 4535.23
     */
    public static String replace(String sOld, String sPartten, String sReplace) {
        if (isEmpty(sOld))
            return "";
        else if (isEmpty(sPartten))
            return sOld;
        if (sReplace == null)
            sReplace = "";

        StringBuffer sBuffer = new StringBuffer();
        int isOldLen = sOld.length();
        int isParttenLen = sPartten.length();
        int iIndex = -1;
        int iHead = 0;
        while ((iIndex = sOld.indexOf(sPartten, iHead)) > -1) {
            sBuffer.append(sOld.substring(iHead, iIndex)).append(sReplace);
            iHead = iIndex + isParttenLen;
        }
        sBuffer.append(sOld.substring(iHead, isOldLen));
        return sBuffer.toString();
    }

    /**
     * 时间格式转换
     * @param timeStr
     * @param type
     * @return
     */
    public static String formatTimeStr(String timeStr, int type) {
        String reStr = "";
        if (timeStr == null || timeStr.trim().equals("")) {
            reStr = "";
        } else if (timeStr.indexOf("1900") != -1) {
            reStr = "";
        } else if (type == 1) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                reStr = sdf.format(sdf.parse(timeStr));
            } catch (Exception e) {
                reStr = "";
            }

        } else if (type == 2) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                reStr = sdf.format(sdf.parse(timeStr));
            } catch (Exception e) {
                reStr = "";
            }
        } else if (type == 3) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                reStr = sdf.format(sdf.parse(timeStr));
            } catch (Exception e) {
                reStr = "";
            }
        } else if (type == 4) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                reStr = sdf.format(sdf.parse(timeStr));
            } catch (Exception e) {
                reStr = "";
            }
        }
        return reStr;
    }
    /**
     * 字符串转换成日期
     *
     * @param str
     * @param patter
     *            :转换格式
     * @return date
     */
    public static Date StrToDate(String str, String patter) {

        SimpleDateFormat format = new SimpleDateFormat(patter);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 将yyyy-mm-dd类型转化称大写的二○○年十二月二十五日
     */
    public static String dateConvert(String date) {
        String s = "";
        if (date.length() < 8)
            return "";
        String ls_year = "", ls_month = "", ls_day = "", ls_1 = "", ls_2 = "", ls_3 = "", ls_4 = "";
        ls_1 = date.substring(0, 1);
        ls_2 = date.substring(1, 2);
        ls_3 = date.substring(2, 3);
        ls_4 = date.substring(3, 4);
        ls_month = date.substring(5, 7);
        ls_day = date.substring(8, 10);
        // 处理年度第一位
        if ("1".equals(ls_1))
            ls_1 = "一";
        if ("2".equals(ls_1))
            ls_1 = "二";
        if ("3".equals(ls_1))
            ls_1 = "三";
        if ("4".equals(ls_1))
            ls_1 = "四";
        if ("5".equals(ls_1))
            ls_1 = "五";
        if ("6".equals(ls_1))
            ls_1 = "六";
        if ("7".equals(ls_1))
            ls_1 = "七";
        if ("8".equals(ls_1))
            ls_1 = "八";
        if ("9".equals(ls_1))
            ls_1 = "九";
        if ("0".equals(ls_1))
            ls_1 = "○";
        // 处理年度第二位
        if ("1".equals(ls_2))
            ls_2 = "一";
        if ("2".equals(ls_2))
            ls_2 = "二";
        if ("3".equals(ls_2))
            ls_2 = "三";
        if ("4".equals(ls_2))
            ls_2 = "四";
        if ("5".equals(ls_2))
            ls_2 = "五";
        if ("6".equals(ls_2))
            ls_2 = "六";
        if ("7".equals(ls_2))
            ls_2 = "七";
        if ("8".equals(ls_2))
            ls_2 = "八";
        if ("9".equals(ls_2))
            ls_2 = "九";
        if ("0".equals(ls_2))
            ls_2 = "○";
        // 处理年度第三位
        if ("1".equals(ls_3))
            ls_3 = "一";
        if ("2".equals(ls_3))
            ls_3 = "二";
        if ("3".equals(ls_3))
            ls_3 = "三";
        if ("4".equals(ls_3))
            ls_3 = "四";
        if ("5".equals(ls_3))
            ls_3 = "五";
        if ("6".equals(ls_3))
            ls_3 = "六";
        if ("7".equals(ls_3))
            ls_3 = "七";
        if ("8".equals(ls_3))
            ls_3 = "八";
        if ("9".equals(ls_3))
            ls_3 = "九";
        if ("0".equals(ls_3))
            ls_3 = "○";
        // 处理年度第四位
        if ("1".equals(ls_4))
            ls_4 = "一";
        if ("2".equals(ls_4))
            ls_4 = "二";
        if ("3".equals(ls_4))
            ls_4 = "三";
        if ("4".equals(ls_4))
            ls_4 = "四";
        if ("5".equals(ls_4))
            ls_4 = "五";
        if ("6".equals(ls_4))
            ls_4 = "六";
        if ("7".equals(ls_4))
            ls_4 = "七";
        if ("8".equals(ls_4))
            ls_4 = "八";
        if ("9".equals(ls_4))
            ls_4 = "九";
        if ("0".equals(ls_4))
            ls_4 = "○";
        ls_year = ls_1 + ls_2 + ls_3 + ls_4;

        if ("01".equals(ls_month))
            ls_month = "一";
        if ("02".equals(ls_month))
            ls_month = "二";
        if ("03".equals(ls_month))
            ls_month = "三";
        if ("04".equals(ls_month))
            ls_month = "四";
        if ("05".equals(ls_month))
            ls_month = "五";
        if ("06".equals(ls_month))
            ls_month = "六";
        if ("07".equals(ls_month))
            ls_month = "七";
        if ("08".equals(ls_month))
            ls_month = "八";
        if ("09".equals(ls_month))
            ls_month = "九";
        if ("10".equals(ls_month))
            ls_month = "十";
        if ("11".equals(ls_month))
            ls_month = "十一";
        if ("12".equals(ls_month))
            ls_month = "十二";

        if ("01".equals(ls_day))
            ls_day = "一";
        if ("02".equals(ls_day))
            ls_day = "二";
        if ("03".equals(ls_day))
            ls_day = "三";
        if ("04".equals(ls_day))
            ls_day = "四";
        if ("05".equals(ls_day))
            ls_day = "五";
        if ("06".equals(ls_day))
            ls_day = "六";
        if ("07".equals(ls_day))
            ls_day = "七";
        if ("08".equals(ls_day))
            ls_day = "八";
        if ("09".equals(ls_day))
            ls_day = "九";
        if ("10".equals(ls_day))
            ls_day = "十";
        if ("11".equals(ls_day))
            ls_day = "十一";
        if ("12".equals(ls_day))
            ls_day = "十二";
        if ("13".equals(ls_day))
            ls_day = "十三";
        if ("14".equals(ls_day))
            ls_day = "十四";
        if ("15".equals(ls_day))
            ls_day = "十五";
        if ("16".equals(ls_day))
            ls_day = "十六";
        if ("17".equals(ls_day))
            ls_day = "十七";
        if ("18".equals(ls_day))
            ls_day = "十八";
        if ("19".equals(ls_day))
            ls_day = "十九";
        if ("20".equals(ls_day))
            ls_day = "二十";
        if ("21".equals(ls_day))
            ls_day = "二十一";
        if ("22".equals(ls_day))
            ls_day = "二十二";
        if ("23".equals(ls_day))
            ls_day = "二十三";
        if ("24".equals(ls_day))
            ls_day = "二十四";
        if ("25".equals(ls_day))
            ls_day = "二十五";
        if ("26".equals(ls_day))
            ls_day = "二十六";
        if ("27".equals(ls_day))
            ls_day = "二十七";
        if ("28".equals(ls_day))
            ls_day = "二十八";
        if ("29".equals(ls_day))
            ls_day = "二十九";
        if ("30".equals(ls_day))
            ls_day = "三十";
        if ("31".equals(ls_day))
            ls_day = "三十一";

        s = ls_year + "年" + ls_month + "月" + ls_day + "日";
        return s;
    }
    /**
     * 格式化货币字符串，每三位用','隔开并保留两位有效数字
     *
     * @param num
     * @return
     */
    public static String formatMoney(Number num) {
        DecimalFormat formatter = new DecimalFormat(",##0.00");
        String str = formatter.format(num);
        return str;
    }
    /**
     * 格式化货币字符串，每三位用','隔开并保留deci位有效数字
     *
     * @param num
     * @param deci
     * @return
     */
    public static String formatMoney(Number num, int deci) {
        String str = ",##0" + (deci > 0 ? "." : "");
        for (int i = 0; i < deci; i++) {
            str += "0";
        }
        DecimalFormat formatter = new DecimalFormat(str);
        return formatter.format(num);
    }
    /**
     * 将对应字符串改为UTF-8格式
     *
     * @param s
     * @return
     */
    public static String toUtf(String s) {
        if(isNotEmpty(s)){
            byte[] b;
            try {
                if("UTF-8".equals(getEncoding(s))){
                    return s;
                }
                b = s.getBytes(getEncoding(s));

                String sa = new String(b, "utf-8");// 解码:用什么字符集编码就用什么字符集解码
                return sa;
            } catch (UnsupportedEncodingException e) {
                return null;
            }// 编码
        }else{
            return null;
        }
    }

    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }


}
