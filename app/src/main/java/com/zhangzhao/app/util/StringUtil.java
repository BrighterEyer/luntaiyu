package com.zhangzhao.app.util;

import com.zhangzhao.app.config.WechatProperties;
import com.zhangzhao.common.util.MD5Util;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class StringUtil {

    /**
     * 获取指定长度的随机字符串
     *
     * @param length
     * @return 随机字符串
     */
    public static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳字符串
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取微信签名
     *
     * @param map 请求参数集合
     * @return 微信请求签名串
     */
    public static String getSign(SortedMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            // 参数中sign、key不参与签名加密
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + map.get("key"));
        return MD5Util.md5(sb.toString()).toUpperCase();
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String mapToxml(Map<String, Object> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            root.addElement(key).addText(map.get(key).toString());
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }

    public static String getIpAddr(HttpServletRequest request) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return request.getRemoteAddr();
        }
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }
}
