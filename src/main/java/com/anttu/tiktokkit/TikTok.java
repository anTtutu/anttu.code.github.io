package com.anttu.tiktokkit;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 描述
 *
 * @ClassName：TikTok
 * @Description：无水印
 * @author：Anttu
 *               @Date：2020-02-20 14:02
 */
public class TikTok
{
    private String BASE_API_URL = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids={}&dytk={}";

    public String getVideoUrl2(String url) throws IOException
    {
        Connection conn = getConn(url);
        Document doc = conn.get();
        Element video = doc.getElementById("theVideo");
        url = video.attr("src").replace("playwm", "play");
        conn = getConn(url);
        return getRedirectURL(conn);
    }

    public String getVideoUrl1(String url) throws IOException
    {
        Connection conn = getConn(url);
        Document doc = conn.get();
        Elements scriptElements = doc.getElementsByTag("script");
        for (Element e : scriptElements)
        {
            String text = e.toString();
            if (text.contains("douyin_falcon:page/reflow_video/index"))
            {
                text = text.substring(text.indexOf('{'), text.lastIndexOf('}')).replaceAll("\\s+", "");
                text = text.substring(text.indexOf('{', text.indexOf(')')), text.lastIndexOf('}') + 1);
                JSONObject json = JSONObject.parseObject(text);
                String itemId = json.getString("itemId");
                String dytk = json.getString("dytk");
                String regex = "\\{\\}";
                url = BASE_API_URL.replaceFirst(regex, itemId);
                url = url.replaceFirst(regex, dytk);
                conn = getConn(url);
                text = conn.get().body().text();
                json = JSONObject.parseObject(text).getJSONArray("item_list").getJSONObject(0);
                json = json.getJSONObject("video").getJSONObject("play_addr");
                url = json.getJSONArray("url_list").getString(0);
                return getRedirectURL(getConn(url));
            }
        }
        return null;
    }

    private Connection getConn(String url)
    {
        return Jsoup.connect(url).userAgent(
                "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .ignoreContentType(true);
    }

    private String getRedirectURL(Connection conn) throws IOException
    {
        return conn.followRedirects(false).execute().header("location");
    }

    public static void main (String[] args) throws IOException
    {
        String dy_url = "https://v.douyin.com/JN54Lhx/";
        TikTok t = new TikTok();
        String url = t.getVideoUrl1(dy_url);
        System.out.println("无水印地址：" + url);
    }

}
