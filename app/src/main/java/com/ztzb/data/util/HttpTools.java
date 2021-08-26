package com.ztzb.data.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpTools {

    private String requestMethod;
    private boolean useCaches;
    private String serverUrl;
    private int connectTimeout;
    private String encode;
    private CookieStore store;

    // 初始化实例
    public HttpTools() {
        init();
    }

    // 默认为get请求(地址,回调函数)
    public HttpTools(String url, Callback callback) {
        init();
        serverUrl = url;
        handleData(null, callback);
    }

    // get请求(地址,回调函数)
    public void get(String url, Callback callback) {
        serverUrl = url;
        handleData(null, callback);
    }

    // get请求(地址,map参数,回调函数)
    public void get(String url, Map<String, Object> params, Callback callback) {
        serverUrl = url;
        handleData(params, callback);
    }

    // get请求(地址,object参数,回调函数)
    public void get(String url, Object params, Callback callback) {
        serverUrl = url;
        handleData(objectToMap(params), callback);
    }

    // post请求(地址,回调函数)
    public void post(String url, Callback callback) {
        requestMethod = "POST";
        serverUrl = url;
        handleData(null, callback);
    }

    // post请求(地址,map参数,回调函数)
    public void post(String url, Map<String, Object> params, Callback callback) {
        requestMethod = "POST";
        serverUrl = url;
        handleData(params, callback);
    }

    // post请求(地址,map参数,回调函数)
    public void post(String url, Object params, Callback callback) {
        requestMethod = "POST";
        serverUrl = url;
        handleData(objectToMap(params), callback);
    }

    // 初始化参数
    private void init() {
        requestMethod = "GET";
        useCaches = false;
        connectTimeout = 3000;
        encode = "utf-8";
    }

    //handle中操作页面，以及创建线程
    private void handleData(Map<String, Object> params, Callback callback) {
        @SuppressLint("HandlerLeak")
        android.os.Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                callback.onSuccess((String) msg.obj);
            }
        };
        new Thread(() -> submitAllData(params, callback, handler)).start();
    }

    private void submitAllData(Map<String, Object> params, Callback callback, Handler handler) {
        String data = null;
        if (params != null) {
            data = getRequestData(params, encode).toString();
        }
        try {
            if (requestMethod.equals("GET") && data != null) {
                serverUrl += '?' + data;
            }
            CookieManager manager = new CookieManager(store, null);
            //manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            CookieHandler.setDefault(manager);

            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);//方式
            connection.setConnectTimeout(connectTimeout);//超时
            if (requestMethod.equals("POST")) {
                connection.setDoInput(true);//向服务器输入
                connection.setDoOutput(true);//向服务器输出
                connection.setUseCaches(useCaches);//缓存
                //设置请求体的类型是文本类型
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //设置请求体的长度
                connection.setRequestProperty("Content-Length", String.valueOf(data != null ? data.getBytes().length : 0));
                //获得输出流，向服务器写入数据
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data != null ? data.getBytes() : new byte[0]);
            }
            store = manager.getCookieStore();
            int response = connection.getResponseCode();            //获得服务器的响应码
            printCookie(store);
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = connection.getInputStream();
                Message message = new Message();
                message.obj = getResponseResult(inptStream);       //处理服务器的响应结果
                handler.sendMessage(message);
            } else {
                callback.onFailed("Error: response is" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuffer getRequestData(Map<String, Object> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(Uri.encode(entry.getValue().toString(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }


    private static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    private static String getResponseResult(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer strBuff = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                strBuff.append(line);
            }
            reader.close();
            return strBuff.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface Callback {
        void onSuccess(String res);

        void onFailed(String err);
    }

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }

    // 请求方式
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    // 使用缓存
    public void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches;
    }

    // 请求地址
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // 请求参数
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    // 编码方式
    public void setEncode(String encode) {
        this.encode = encode;
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void responseUpdateCookieHttpURL(CookieStore store) {
        boolean needUpdate = false;
        List<HttpCookie> cookies = store.getCookies();
        HashMap<String, String> cookieMap = null;
        if (cookieMap == null) {
            cookieMap = new HashMap<String, String>();
        }
        for (HttpCookie cookie : cookies) {
            String key = cookie.getName();
            String value = cookie.getValue();
            if (cookieMap.size() == 0 || !value.equals(cookieMap.get(key))) {
                needUpdate = true;
            }
            cookieMap.put(key, value);
        }
    }

    public static void printCookie(CookieStore cookieStore) {
        List<HttpCookie> listCookie = cookieStore.getCookies();
        for (HttpCookie httpCookie : listCookie) {
            System.out.println("--------------------------------------");
            System.out.println("class      : " + httpCookie.getClass());
            System.out.println("comment    : " + httpCookie.getComment());
            System.out.println("commentURL : " + httpCookie.getCommentURL());
            System.out.println("discard    : " + httpCookie.getDiscard());
            System.out.println("domain     : " + httpCookie.getDomain());
            System.out.println("maxAge     : " + httpCookie.getMaxAge());
            System.out.println("name       : " + httpCookie.getName());
            System.out.println("path       : " + httpCookie.getPath());
            System.out.println("portlist   : " + httpCookie.getPortlist());
            System.out.println("secure     : " + httpCookie.getSecure());
            System.out.println("value      : " + httpCookie.getValue());
            System.out.println("version    : " + httpCookie.getVersion());
            System.out.println("httpCookie : " + httpCookie);
        }
    }

}
