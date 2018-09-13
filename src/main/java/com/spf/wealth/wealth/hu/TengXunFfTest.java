package com.spf.wealth.wealth.hu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.mail.MailSend;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 10:09
 */
@Component
public class TengXunFfTest {

    private String path = this.getClass().getClassLoader().getResource("").getPath();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private int indexItart = path.indexOf("/target");

    private String NineNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";

    private String TenNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";

    private String SevenNum = "02 03 04 05 06 08 09 13 14 15 17 18 19 20 24 26 27 28 30 31 35 36 37 39 40 41 42 46 48 49 50 51 53 57 58 59 60 62 63 64 68 69 71 72 73 75 79 80 81 82 84 85 86 90 91 93 94 95 96 97";

    private JSONObject json = new JSONObject();

    private TengXunFf tengXunFf = new TengXunFf();

    private Logger logger = LogManager.getLogger(TengXunFfTest.class);

    @Test
    public void execute() throws Exception {
        int nextqh = 648;
        if (nextqh == 1441) {
            nextqh = 1;
        }
        Properties sevenProperties = new Properties("腾讯分分", SevenNum,nextqh,6,6,"和7,11");
        Properties nineProperties = new Properties("腾讯分分", NineNum,nextqh,6,6,"和9,11");
        Properties tenProperties = new Properties("腾讯分分", TenNum,nextqh,6,6,"和8,10");

        int count = 0;
        while (true) {
            count++;
            long start = System.currentTimeMillis();

            login(nextqh);

            executeNine(count,nineProperties);
            excuteSeven(count,sevenProperties);
            excuteTen(count,tenProperties);

            nextqh++;
            logger.info("--------------------------------------- 执行结束 {}----------------------------------------\n", _sdf.format(new Date()));

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

    public void executeNine(int count, Properties properties) throws Exception {
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-腾讯分分[和9-11]统计.txt";

        tengXunFf.execute(json, properties,1);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 腾讯分分[和9-11]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() +"\n";
            Write.write(content, path,true);
        }
    }

    public void excuteTen(int count, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-腾讯分分[和8-10]统计.txt";

        tengXunFf.execute(json, properties,2);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 腾讯分分[和8-10]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
        }
    }

    public void excuteSeven(int count, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-腾讯分分[和7-11]统计.txt";

        tengXunFf.execute(json, properties,3);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 腾讯分分[和7-11]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
        }
    }

    public void login(int nextqh) throws Exception {
        Integer qh = 0;
        do {
            Thread.sleep(3000);
            json = tengXunFf.login(nextqh);
            JSONArray datas = json.getJSONArray("data");
            String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
            qh = Integer.valueOf(kjqh.split("-")[1]);
        } while (nextqh - qh != 0);

    }

}
