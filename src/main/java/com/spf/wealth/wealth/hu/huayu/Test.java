package com.spf.wealth.wealth.hu.huayu;

import com.spf.utils.mail.MailSend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-29 14:41
 */
public class Test {

    @org.junit.Test
    public void test() throws Exception {
        String[] toMails = new String[]{"1215852831@qq.com"};
        MailSend.sendMail("ceshi","ces","1215852831", toMails);
    }

}
