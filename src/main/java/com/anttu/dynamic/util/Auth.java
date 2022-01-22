package com.anttu.dynamic.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 鉴权工具类
 *
 * @ClassName：Auth
 * @Description：
 * @author：cgtu
 * @Date：2021/3/31/0031 20:19
 */
public class Auth {
    /**
     * 计算签名头部
     * @param urlStr 完整的请求域名+路径，example https://xxxx.tencent-cloud.com/this/is/a/url
     * @param version 签名版本号，目前可写死为"1.0.0"
     * @param timestamp 当前时间戳，精确到秒
     * @param requestclient 调用本接口的客户端，目前可写死为"sr_sdk"
     * @param requestPayloadStr post请求的body部分，把整个body当成一个字符串
     * @param secretId 营销云预先分配给调用方的的secretId
     * @param secretKey 营销云预先分配给调用方的的secretKey
     * @return 返回map，key为头部名称，value为值。请将此map的内容加在http请求头部
     * @throws Exception
     */
    public static HttpHeaders calSignatureV3(
            String urlStr, String version, String timestamp, String requestclient,
            String requestPayloadStr, String secretId, String secretKey
    ) throws Exception {
        // 默认全部使用post json方式
        String httpRequestMethod = "POST";

        // 获取域名和path
        java.net.URL url = new java.net.URL(urlStr);
        String host = url.getHost();
        String path = url.getPath();

        String contentType = "application/json";
        byte[] requestPayload = requestPayloadStr.getBytes(StandardCharsets.UTF_8);

        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:" + contentType + "\nhost:" + host + "\n";
        String signedHeaders = "content-type;host";

        String hashedRequestPayload = sha256Hex(requestPayload);
        String canonicalRequest =
                httpRequestMethod
                        + "\n"
                        + path
                        + "\n"
                        + canonicalQueryString
                        + "\n"
                        + canonicalHeaders
                        + "\n"
                        + signedHeaders
                        + "\n"
                        + hashedRequestPayload;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.parseLong(timestamp + "000")));
        String credentialScope = date + "/" + path + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest.getBytes(StandardCharsets.UTF_8));
        String stringToSign =
                "TC3-HMAC-SHA256\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;


        byte[] secretDate = hmac256(("TC3" + secretKey).getBytes(StandardCharsets.UTF_8), date);
        byte[] secretService = hmac256(secretDate, path);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
        String authorization =
                "TC3-HMAC-SHA256 "
                        + "Credential="
                        + secretId
                        + "/"
                        + credentialScope
                        + ", "
                        + "SignedHeaders="
                        + signedHeaders
                        + ", "
                        + "Signature="
                        + signature;

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        header.set("Host", host);
        header.set("Authorization", authorization);
        header.set("X-TC-Action", path);
        header.set("X-TC-Timestamp", timestamp);
        header.set("X-TC-Version", version);
        header.set("X-TC-SecretId", secretId);
        header.set("X-TC-RequestClient", requestclient);
        return header;
    }

    /**
     *
     * @param b
     * @return
     * @throws Exception
     */
    public static String sha256Hex(byte[] b) throws Exception {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("SHA-256 is not supported." + e.getMessage());
        }
        byte[] d = md.digest(b);
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    /**
     *
     * @param key
     * @param msg
     * @return
     * @throws Exception
     */
    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("HmacSHA256 is not supported." + e.getMessage());
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new Exception(e.getClass().getName() + "-" + e.getMessage());
        }
        return mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
    }

    public static void main (String[] args) throws Exception {
        String str = String.valueOf(System.currentTimeMillis());
        String secret = "3fc65936aa95427101b9666b48e96711a2425cd6541d820ed61a2919d94c418e";
        System.out.println(sha256Hex(str.getBytes(StandardCharsets.UTF_8)));
        System.out.println(DatatypeConverter.printHexBinary(hmac256(secret.getBytes(StandardCharsets.UTF_8), str)));
    }
}
