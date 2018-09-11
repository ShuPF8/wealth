package com.spf.wealth.wealth.hu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.spf.utils.HttpUtil;
import com.spf.wealth.wealth.utils.HtmlUnitUtil;
import com.spf.wealth.wealth.utils.PhotoUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 腾讯分分彩
 */
public class TengXunFf2 {

    protected CookieStore cookieStore;

    private static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";

    private static final String HOST1 = "https://x1.yeuss.com";

    private static final String HOST2 = "https://x2.yeuss.com";

    private static final String HOST3 = "https://x3.yeuss.com";

    private static final String username = "spf1785";

    private static final String pwd = "XdaJIE520.";

    private Logger logger = LogManager.getLogger(TengXunFf2.class);

    public void login() throws Exception {
        HttpUriRequest request = null;
        String data = null;
        CloseableHttpClient client = getClient();
        request = new HttpGet(HOST1);
        data = HttpUtil.execute(client, request, logger);

        Map<String, Object> map = new HashMap<>();
        map.put("data","5d000008004700000000000000003d8888865474ae3953ed45778311ffd10faa6e08cb35687e9a092c3ef7611635f1feb4123d4ce0d59ab8083126fdba05dce023ee99c482f38c7581b325ad0779ec17ea1d2ef7be7fffe6090000d9954ac194a779225daaae67815055c2");
        String json = JSON.toJSONString(map);
        request = HttpUtil.jsonRequest("https://x1.yeuss.com/api/safe/captcha", json);
        request.addHeader("token",null);
        request.addHeader("User-Agent", User_Agent);
        request.addHeader("Referer", HOST1);
        data = HttpUtil.execute(client, request, logger);

        JSONObject object = JSONObject.parseObject(data);
        object = object.getJSONObject("data");
        String base64String = object.getString("base64String");
        base64String = base64String.substring("data:image/jpeg;base64,".length());

        PhotoUtil.generateImage(base64String,System.currentTimeMillis()+".jpeg");

        String code = "";


    }

    protected CloseableHttpClient getClient() throws Exception {
        cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).disableAutomaticRetries() // 关闭自动处理重定向
                .setRedirectStrategy(new LaxRedirectStrategy())// 利用LaxRedirectStrategy处理POST重定向问题
                .build();
        return client;
    }

    @Test
    public void test() {
        try {
            login();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    @Test
    public void test2() {
        String base64String = "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";
        PhotoUtil.generateImage(base64String,System.currentTimeMillis()+".jpeg");
    }

}
