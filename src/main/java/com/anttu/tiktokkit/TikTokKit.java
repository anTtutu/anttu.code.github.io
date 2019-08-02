package com.anttu.tiktokkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述
 *
 * @ClassName: TikTokKit
 * @Description: 抖音解析
 * @Author: hk
 * @Date: 2019/8/2 17:46
 */
public class TikTokKit
{
    private static String cookies = "odin_tt=9a16fa42e650a96379a5901a3d146c7c244dc0c35971927f6e13c208fc4bcf9cc952542516f78dc9098ac4d179f3b127cddfdff2942d259dda9ca33de8ae7677; install_id=43619087057; ttreq=1$4c4b4cc4b31e6f2f4203b62a1df12b43e224434c; qh[360]=1";

    public TikTokKit() {

    }

    /**
     *
     * 这里获取作品ID
     */
    public static String getId(String url) {
        String result = sendGet(url);
        result = getSubString(result, "/share/video/", "/?");
        return result;
    }

    /**
     * 解析真实地址返回的数据其实是json格式的,Java语言本身不支持json解析,需要借助第三方jar
     *
     * 这里就直接使用getsubstring
     *
     */
    public static String getUrl(String url) {
        String result = sendGet(url);
        result = getSubString(result, "play_addr_lowbr", "width");
        result = getSubString(result, "url_list\":[\"", "\",\"");
        return result;
    }

    private static String getUrlFromContent(String strContent) {
        String regex = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strContent);
        String result = null;
        while (matcher.find()) {
            result = matcher.group();
        }

        return result.toString();

    }

    /**
     * 取出中间文本
     *
     */
    private static String getSubString(String str, String left, String right) {
        String strRight = "";

        int indexLeft = str.indexOf(left);
        if (indexLeft == -1) {
            return "";// 没有找到直接返回空以免出现异常
        } else {
            strRight = str.substring(indexLeft);
        }
        int length = str.length() - strRight.length();
        int indexRight = strRight.indexOf(right);
        if (indexRight == -1) {
            return "";
        }
        String result = str.substring(length + left.length(), length + indexRight);
        return result;
    }

    private static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Accept-Encoding", "utf-8");
            connection.setRequestProperty("Host", "api-hl.amemv.com");
            connection.setRequestProperty("user-agent", "okhttp/3.10.0.1");
            connection.setRequestProperty("cookie", cookies);
            // 建立实际的连接
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // 发送异常
            return "发送失败,请检查URL地址是否正确";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                // 关闭异常
                System.out.println("关闭异常");
            }
        }
        return result;
    }

    public static void main(String[] args) {

        //		String url = " http://v.douyin.com/ACxoU1";// 抖音短视频链接

        // 得到分享内容中的地址
        String strContent = "#在抖音，记录美好生活#阿姨怒撕港独标语、怒怼港独分子！香港是中国的香港！#我爱你中国 #我和我的祖国 http://v.douyin.com/AVkpBp/ 复制此链接，打开【抖音短视频】，直接观看视频！";
        String url = getUrlFromContent(strContent);
        System.out.println("抖音视频表面地址为:" + url);

        // 获取视频ID
        String videoId = TikTokKit.getId(url);
        System.out.println("抖音视频的ID为:" + videoId);

        // 获取视频真实地址
        String videlUrl = TikTokKit.getUrl(
                "https://api-hl.amemv.com/aweme/v1/aweme/detail/?retry_type=no_retry&iid=43619087057&device_id=57318346369&ac=wifi&channel=update&aid=1128&app_name=aweme&version_code=251&version_name=2.5.1&device_platform=android&ssmix=a&device_type=MI+8&device_brand=xiaomi&language=zh&os_api=22&os_version=5.1.1&uuid=865166029463703&openudid=ec6d541a2f7350cd&manifest_version_code=251&resolution=1080*1920&dpi=480&update_version_code=2512&_rticket=1559206461097&ts=1559206460&as=a115996edcf39c7adf4355&cp=9038c058c7f6e4ace1IcQg&mas=01af833c02eb8913ecc7909389749e6d89acaccc2c662686ecc69c&aweme_id="
                        + videoId);
        System.out.println("抖音视频真实地址为:" + videlUrl);
    }
}
