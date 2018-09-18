package com.spf.wealth.wealth.hu;

import com.alibaba.fastjson.JSONObject;
import com.spf.wealth.wealth.hu.huayu.LotteryCore;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-17 11:07
 */
public class LotteryUtil {

    private static SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     *  写文件
     * @param path 路径
     * @param content 内容
     * @param flag 是否不覆盖 false = 覆盖， true = 覆盖
     */
    public static void write(String content, String path, String name, boolean flag) {
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            //经过测试：ufferedOutputStream执行耗时:1,1，1 毫秒
            outSTr = new FileOutputStream(new File(path + name), flag);
            Buff = new BufferedOutputStream(outSTr);
            Buff.write((content+ "\n\t").getBytes());
            Buff.flush();
            Buff.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void myWrite(Properties properties, String path, String name, String title) {
        long time = System.currentTimeMillis();
        if ((properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()
                || properties.getPrevHMaxLzCount() < properties.gethMaxlzCount() || properties.getPrevQMaxLzCount() < properties.getqMaxlzCount())
                && (time - properties.getPrevWireTime()) > (3 * 50 * 1000)) {
            properties.setPrevWireTime(time);
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevHMaxLzCount(properties.gethMaxlzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            properties.setPrevQMaxLzCount(properties.getqMaxlzCount());
            String content = _sdf.format(new Date()) + title + "今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            write(content, path,name,true);
        }
    }


    public static void dataHandle(List<Properties> propertiess, LotteryCore lotteryCore, JSONObject json, String path,String marker, Logger logger) {
        int indexItart = path.indexOf("/target");
        for (int i = 0; i < propertiess.size(); i++) {
            Properties properties = propertiess.get(i);
            String title = properties.getName() + "[" + properties.getShtj() + "]";
            path = path.substring(0, indexItart) + "\\log\\"+marker +"\\"+ sdf.format(new Date()) +"\\";
            String name = sdf.format(new Date()) + "-"+ title +"统计.txt";
            try {
                lotteryCore.dataHandle(json, properties, i, logger);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            LotteryUtil.myWrite(properties, path, name,title);
        }
    }

    @Test
    public void test() {
        LotteryUtil.write("测试", "E:\\GitHub\\wealth\\log\\huayu2f\\" + sdf.format(new Date()) +"\\", "统计.txt",true);
    }

}