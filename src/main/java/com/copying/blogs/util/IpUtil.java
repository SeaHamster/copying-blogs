package com.copying.blogs.util;



import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author  JC
 * @CreateTime  2018-11-23
 * @Point Keep a good mood
 * 获取访问接口的IP地址
 **/
@Component
public class IpUtil {
    private static final String UNKNOWN ="unknown";
    private static final String LOCAL_HOST="127.0.0.1";
    private static final String LOCAL_HOST_SERVICE="0:0:0:0:0:0:0:1";
    private static final String INDEX_FLAG=",";
    private static final int IP_LENGTH =15;

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                ipAddress=ipAddress.equals(LOCAL_HOST_SERVICE) ? LOCAL_HOST : ipAddress;
                if (ipAddress.equals(LOCAL_HOST)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
                // = 15
                if (ipAddress.indexOf(INDEX_FLAG) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        return ipAddress;
    }
}


