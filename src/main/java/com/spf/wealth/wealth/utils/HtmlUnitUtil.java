package com.spf.wealth.wealth.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.common.io.ByteStreams;

/**
 * HtmlUnit java 浏览器模拟操作工具
 * Created by ShuPF on 2017/11/17.
 */
public class HtmlUnitUtil {

    protected static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)Chrome/62.0.3202.89 Safari/537.36";

    /**
     * 获取 WebClient 连接实例
     *
     * @return
     */
    public static WebClient getConnection(Ip ip) {
        WebClient webClient = new WebClient();
        init(webClient); //加载配置
        if (ip != null) {
            setProxy(webClient, ip.getIp(), ip.getPort()); //设置代理IP
        }
        return webClient;
    }

    /**
     * Get请求
     *
     * @param url
     * @return HtmlPage
     * @throws Exception
     */
    public static HtmlPage sendGetRequest(WebClient webClient, String url) throws Exception {
        WebRequest webRequest = GetRequest(url);
        HtmlPage htmlPage = webClient.getPage(webRequest);
        return htmlPage;
    }

    /**
     * Get请求
     *
     * @param url
     * @return HtmlPage
     * @throws Exception
     */
    public static WebRequest GetRequest(String url) throws Exception {
        WebRequest webRequest = new WebRequest(new URL(url));
        webRequest.setAdditionalHeader("User-Agent", User_Agent);
        webRequest.setHttpMethod(HttpMethod.GET);
        return webRequest;
    }

    /**
     * Post 请求
     *
     * @param url
     * @param params
     * @return HtmlPage
     * @throws Exception
     */
    public static HtmlPage sendPostRequest(WebClient webClient, String url, Map<String, Object> params) throws Exception {
        WebRequest webRequest = PostRequest(url, params);
        HtmlPage htmlPage = webClient.getPage(webRequest);
        return htmlPage;
    }

    /**
     * Post 请求
     *
     * @param url
     * @param params
     * @return HtmlPage
     * @throws Exception
     */
    public static WebRequest PostRequest(String url, Map<String, Object> params) throws Exception {
        WebRequest webRequest = new WebRequest(new URL(url));
        webRequest.setHttpMethod(HttpMethod.POST);
        webRequest.setAdditionalHeader("User-Agent", User_Agent);
        if (params != null && params.size() > 0) {
            List<NameValuePair> lnv = new ArrayList<>();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                String key = param.getKey();
                Object value = param.getValue();
                if (value instanceof List<?>) {
                    List<?> subInputs = (List<?>) value;
                    for (Object subInput : subInputs) {
                        NameValuePair nv = new NameValuePair(key, String.valueOf(subInput));
                        lnv.add(nv);
                    }
                }
                NameValuePair nv = new NameValuePair(key, String.valueOf(value));
                lnv.add(nv);
            }
            webRequest.setRequestParameters(lnv);
        }
        return webRequest;
    }

    /**
     * 将 HtmlPage 转化为 String
     *
     * @param page
     * @return
     * @throws IOException
     */
    public static String getPageToString(HtmlPage page) throws IOException {
        return new String(getPageToByte(page), "utf-8");
    }

    /**
     * 将 HtmlPage 转化为 byte
     *
     * @param page
     * @return
     * @throws IOException
     */
    public static byte[] getPageToByte(HtmlPage page) throws IOException {
        byte[] responseContent = null;
        WebResponse webResponse = null;
        try {
            webResponse = page.getWebResponse();
            int status = webResponse.getStatusCode();
            // 读取数据内容
            if (status == 200) {
                if (page.isHtmlPage()) {
                    // 等待JS执行完成
                    responseContent = page.asXml().getBytes();
                } else {
                    InputStream bodyStream = webResponse.getContentAsStream();
                    responseContent = ByteStreams.toByteArray(bodyStream);
                    bodyStream.close();
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (webResponse != null) {
                // 关闭响应流
                webResponse.cleanUp();
            }
        }
        return responseContent;
    }

    /***
     * 加载配置
     */
    private static void init(WebClient webClient) {
        // 1 启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        // 3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        // 4 js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 5 设置超时
        webClient.getOptions().setTimeout(60000);
        //6 设置忽略证书
        webClient.getOptions().setUseInsecureSSL(true);
        //7 设置Ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //8设置cookie
        webClient.getCookieManager().setCookiesEnabled(true);
    }

    /**
     * 设置代理IP
     */
    private static void setProxy(WebClient webClient, String address, int port) {
        ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
        proxyConfig.setProxyHost(address);
        proxyConfig.setProxyPort(port);

        DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
                .getCredentialsProvider();
        credentialsProvider.addCredentials("YsProxy", "YsProxy@0023");
    }

    /**
     * 获取验证码
     *
     * @param ta
     * @param image
     * @return
     * @throws IOException
     */
    public static byte[] getCode(String ta, HtmlImage image, Logger logger) throws Exception {
        String code = "";
        ImageReader imageReader = image.getImageReader();
        BufferedImage bufferedImage = imageReader.read(0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        byte[] bytes = out.toByteArray();
        return bytes;
    }

    /**
     * 保存图片到本地
     *
     * @param img
     * @param name
     * @throws IOException
     */
    public void saveImage(HtmlImage img, String name) throws IOException {
        ImageReader imageReader = img.getImageReader();
        BufferedImage bufferedImage = imageReader.read(0);
        BufferedImage inputbig = new BufferedImage(160, 60, BufferedImage.TYPE_INT_BGR);
        inputbig.getGraphics().drawImage(bufferedImage, 0, 0, 160, 60, null); //画图
        File file2 = new File("D:\\Backup\\Desktop\\" + name + ".png");
        ImageIO.write(inputbig, "png", file2);
    }

}