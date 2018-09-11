package com.spf.wealth.wealth.hu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 15:33
 */
public class QueryTimeUtil {
    private static String date = "2018-09-11 22:00:00";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static long tenM = 10 * 60 * 1000; //十分钟

    static long fiveM = 5 * 60 * 1000; //五分钟

    public static void ffCommon(TengXunFf tengXunFf, Properties properties) throws Exception {
        while (true) {
            long start = System.currentTimeMillis();
            tengXunFf.login(properties);
            long end = System.currentTimeMillis();
            long sjc = (end - start) / 1000; //时间差 秒
            if (sjc < 30) {
                Thread.sleep((60 - sjc) / 2 * 1000);
            }
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }
    }

    public static void ssCommon(ChongQingSs chongQingSs, Properties properties) throws Exception {
        long falg = 0;
        while (true) {
            if ((new Date()).compareTo(sdf.parse(date)) >= 0) {
                falg = fiveM;
            } else {
                falg = tenM;
            }
            long start = System.currentTimeMillis();
            chongQingSs.login(properties);
            long end = 0l;
            do {
                end = System.currentTimeMillis();
            } while (end - start < falg);
        }
    }

}
