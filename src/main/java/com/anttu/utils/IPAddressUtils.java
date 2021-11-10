package com.anttu.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

/**
 * 描述
 *
 * @ClassName：ClientIPAddressUtils
 * @Description：获取客户端ip地址
 * @author：Anttu
 * @Date：28/10/2021 19:18
 */
public class IPAddressUtils
{
    /**
     * 获取客户端操作电脑ip
     * x-forwarded-for 容易通过请求头造假，request的 remoteAddress但是容易拿到的代理 ip，因此需要多重判断和获取
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request)
    {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();
            }
        }
        else if (ip.length() > 15)
        {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++)
            {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp)))
                {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request)
    {
        if (request == null)
        {
            // 如果request为空的话，返回本机IP，
            return "127.0.0.1";
        }

        String ipStr = request.getHeader("X-Cluster-Client-Ip");
        if (ipStr == null || ipStr.trim().length() == 0 || "unknown".equalsIgnoreCase(ipStr))
        {
            ipStr = request.getHeader("X-Forwarded-For");
        }

        if (ipStr != null && !"".equals(ipStr.trim()))
        {
            if (ipStr.indexOf(",") < 0)
            {
                return ipStr;
            }
            String[] ips = ipStr.split(",");
            for (String ip : ips)
            { // 第一个IP就是反向代理的真实客户端IP
                if (ip == null || "".equals(ip.trim()) || "unknown".equalsIgnoreCase(ip))
                {
                    continue;
                }
                return ip;
            }
        }
        return request.getRemoteAddr(); // 没有代理则返回
    }

    /**
     * 获取本机IP地址
     * @return
     */
    public static Collection<InetAddress> getAllHostAddress()
    {
        try
        {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements())
            {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements())
                {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    addresses.add(inetAddress);
                }
            }

            return addresses;
        }
        catch (SocketException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Collection<String> getAllNoLoopbackAddresses()
    {
        Collection<String> noLoopbackAddresses = new ArrayList<String>();
        Collection<InetAddress> allInetAddresses = getAllHostAddress();

        for (InetAddress address : allInetAddresses)
        {
            if (!address.isLoopbackAddress())
            {
                noLoopbackAddresses.add(address.getHostAddress());
            }
        }

        return noLoopbackAddresses;
    }

    public static String getFirstNoLoopbackAddress()
    {
        Collection<String> allNoLoopbackAddresses = getAllNoLoopbackAddresses();
        return allNoLoopbackAddresses.iterator().next();
    }

    /**
     * 获取本机地址
     *
     * @return
     */
    public static String getLocalIp() throws Exception
    {
        if (isWindowsOS())
        {
            try
            {
                InetAddress addr = InetAddress.getLocalHost();
                String ipstr = addr.getHostAddress();// 获得本机IP
                return ipstr;
            }
            catch (UnknownHostException e)
            {
                throw new Exception(e);
            }
        }
        else
        {
            Collection<String> ips = getAllNoLoopbackAddresses();
            if (ips != null && !ips.isEmpty())
            {
                return ips.iterator().next();
            }
            else
            {
                throw new Exception("取不到IP");
            }
        }
    }

    /**
     * 是否windows系统
     * @return true---是Windows操作系统
     */
    public static boolean isWindowsOS()
    {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1)
        {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }
}
