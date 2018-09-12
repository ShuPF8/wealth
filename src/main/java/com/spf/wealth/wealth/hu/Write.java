package com.spf.wealth.wealth.hu;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-12 10:58
 */
public class Write {

    /**
     *  写文件
     * @param path 路径
     * @param content 内容
     * @param flag 是否不覆盖 false = 覆盖， true = 覆盖
     */
    public static void write(String content, String path, boolean flag) {
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;

        int count = 1000;//写文件行数
        try {
            //经过测试：ufferedOutputStream执行耗时:1,1，1 毫秒
            outSTr = new FileOutputStream(new File(path), flag);
            Buff = new BufferedOutputStream(outSTr);
            Buff.write((content+ "\n\t").getBytes());
            Buff.flush();
            Buff.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
