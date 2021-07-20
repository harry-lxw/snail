package com.winter.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumUtil {

    /**
     * 获取唯一主键：
     *      时间戳+随机数
     * @return
     */
    public static String getRandomNum(){
        Date date = new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddhhmmss");
        String dateStr = sdf.format(date);
        int suffix = new Random().nextInt(100);
        return dateStr+suffix;
    }
}
