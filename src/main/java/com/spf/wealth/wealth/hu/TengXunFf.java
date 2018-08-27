package com.spf.wealth.wealth.hu;

import com.spf.utils.HttpUtil;
import com.spf.wealth.wealth.utils.PhotoUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 腾讯分分彩
 */
public class TengXunFf {

    private static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";

    private static final String HOST1 = "https://x1.yeuss.com";

    private static final String HOST2 = "https://x2.yeuss.com";

    private static final String HOST3 = "https://x3.yeuss.com";

    private static final String username = "spf1785";

    private static final String pwd = "";

    private Logger logger = LogManager.getLogger(TengXunFf.class);

    public void login() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        String code = getCode(client);

    }

    private String getCode(CloseableHttpClient client) throws Exception {
        HttpUriRequest request = new HttpGet(HOST1);
        request.addHeader("User-Agent", User_Agent);
        String data = HttpUtil.execute(client, request, logger);
        Document jsoup = Jsoup.parse(data);

        Element loginTabBody = jsoup.getElementsByClass("loginTabBody").get(0);
        Element img = loginTabBody.getElementsByTag("img").get(0);
        String base64 = img.attr("ng-src");
        base64 = base64.substring("data:image/jpeg;base64,".length());

        PhotoUtil.generateImage(base64,System.currentTimeMillis()+".jpeg");
        return null;
    }

}
