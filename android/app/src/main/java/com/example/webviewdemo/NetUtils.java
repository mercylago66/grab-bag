package com.example.webviewdemo;

import android.accounts.NetworkErrorException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {
    public static String post(String url, String content) {
        HttpURLConnection conn = null;
        try {
            //创建一个URL对象
            URL mURL = new URL(url);
            //调用URL的openConnection()方法,获取HttpURLConnection对象
            conn = (HttpURLConnection) mURL.openConnection();

            //设置请求方法为post  注意:这里必须是大写 小写可能不识别
            conn.setRequestMethod("POST");
            //设置读取超时为5秒
            conn.setReadTimeout(5000);
            //设置连接网络超时为10秒
            conn.setConnectTimeout(10000);
            //设置此方法，允许向服务器输出内容
            conn.setDoOutput(true);

            //post请求的参数
            String data = content;
            //获得一个输出流，向服务器写数据，默认情况下，系统不允许向服务器输出任何内容。
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            //刷新输入的内容 不刷新可能会不显示内容
            out.flush();
            //关闭流
            out.close();

            //调用此方法就不必再使用conn.connect()方法
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            } else {
                throw new NetworkErrorException("response status is" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //最终要执行的步骤
        } finally {
            //记得关闭连接
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    public static String get(String url) {
        HttpURLConnection conn = null;
        try {
            //利用String url构建URL对象
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            } else {
                throw new NetworkErrorException("response status is " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }


    private static String getStringFromInputStream(InputStream is) {
        String state = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //代码板块 必须熟练
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            is.close();
            //把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
            state = os.toString();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}