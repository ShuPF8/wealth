package com.spf.wealth.wealth.hu;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 15:33
 */
public class QueryTimeUtil {

    public static void ffCommon(TengXunFf tengXunFf, Properties properties) throws Exception {
        while (true) {
            long start = System.currentTimeMillis();
            tengXunFf.login(properties);
            long end = 0l;
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }
    }

    public static void ssCommon(ChongQingSs chongQingSs, Properties properties) throws Exception {
        while (true) {
            long start = System.currentTimeMillis();
            chongQingSs.login(properties);
            long end = 0l;
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }
    }

}
