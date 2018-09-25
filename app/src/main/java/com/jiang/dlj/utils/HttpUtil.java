package com.jiang.dlj.utils;

import android.text.TextUtils;
import android.util.Log;

import com.jiang.dlj.entity.Save_Key;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by jiangmac
 * on 15/12/23.
 * Email: www.fangmu@qq.com
 * Phone：186 6120 1018
 * Purpose:HTTP工具类
 * update：细分发送方式 全部使用despost
 */
public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private static final int TIMEOUT_IN_MILLIONS = 15 * 1000;


    /**
     * Get 请求
     *
     * @return 返
     */
    public static String doGet(String urls) {

        URL url;

        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {

            url = new URL(urls);

            LogUtil.e(TAG, "doGet : " + url);

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            if (!TextUtils.isEmpty(SaveUtils.getString(Save_Key.S_校验))) {
                conn.setRequestProperty("token", SaveUtils.getString(Save_Key.S_校验));
            }
            LogUtil.e(TAG, "网页结果：" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();

                return baos.toString();

            } else {
                LogUtil.e(TAG, " responseCode is not 200 ... is" + conn.getResponseCode() + conn.getResponseMessage());
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                LogUtil.e(TAG, e.getMessage());
            }


            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;

    }

    /**
     * Get 请求
     *
     * @param params 请求数据
     * @return 返
     */
    public static String doGet(String urls, Map<String, String> params) {
        String paramsStr = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (TextUtils.isEmpty(entry.getValue())) {
                continue;
            }
            try {
                paramsStr += (entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        URL url;

        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {

            url = new URL(urls + "?" + paramsStr);

            LogUtil.e(TAG, "doGet : " + url);

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();

                LogUtil.e(TAG,"HTTP返回："+baos.toString());
                return baos.toString();

            } else {
                LogUtil.e(TAG, " responseCode is not 200 ... is" + conn.getResponseCode() + conn.getResponseMessage());
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                LogUtil.e(TAG, e.getMessage());
            }


            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;

    }

    /**
     * post 请求
     *
     * @param url   请求地址
     * @param param 请求内容
     * @return 返回DES加密数据
     */

    public static String doPost(String url, Map<String, String> param) {
        StringBuilder paramStr = new StringBuilder();
        for (Map.Entry<String, String> para : param.entrySet()) {
            try {
                paramStr.append(para.getKey()).append("=").append(URLEncoder.encode(para.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        // 发送请求参数
        LogUtil.e(TAG, "http发送 " + url + "?" + paramStr.toString());
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("Charset", "UTF-8");
            //超时时间
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(paramStr.toString());
            // flush输出流的缓冲
            out.flush();

            try {
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (FileNotFoundException e) {
                result = null;
            }
        } catch (SocketTimeoutException e) {
            LogUtil.e(TAG, "发送请求超时！");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        LogUtil.e(TAG, "http返回" + result);
        return result;
    }

    public static Map<String, String> signParam(Map<String, String> paramMap) {
        paramMap.put("device", "android");
        paramMap.put("version", "");
        return paramMap;
    }

    /**
     * post 请求 DES加密发送
     *
     * @param url 请求地址
     * @return 返回DES加密数据
     */

    public static String doPost(String url) {
        StringBuilder paramStr = new StringBuilder();

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        // 发送请求参数
        Log.e(TAG, "http发送 " + url);
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //超时时间
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(paramStr);
            // flush输出流的缓冲
            out.flush();

            try {
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (FileNotFoundException e) {
                result = null;
            }
        } catch (SocketTimeoutException e) {
            LogUtil.e(TAG, "发送请求超时！");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        LogUtil.e(TAG, "HTTP返回：" + result);
        if (result != null) {

            return result;
        } else {
            return null;
        }
    }

    /**
     * PUT 请求
     *
     * @param httpUrl 请求地址
     * @param map     请求内容
     * @return
     */
    public static String doPut(String httpUrl, Map map) {
        String result = "";
        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url != null) {
            try {
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestProperty("content-type", "application/json");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setConnectTimeout(5 * 1000);
                //设置请求方式为 PUT
                urlConn.setRequestMethod("PUT");

                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.setRequestProperty("Accept", "application/json");

                urlConn.setRequestProperty("Charset", "UTF-8");

                DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
                //写入请求参数
                //这里要注意的是，在构造JSON字符串的时候，实践证明，最好不要使用单引号，而是用“\”进行转义，否则会报错
                // 关于这一点在上面给出的参考文章里面有说明

                dos.writeBytes(String.valueOf(ToolUtils.map2Json(map)));
                dos.flush();
                dos.close();

                if (urlConn.getResponseCode() == 200) {
                    InputStreamReader isr = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String inputLine = null;
                    while ((inputLine = br.readLine()) != null) {
                        result += inputLine;
                    }
                    isr.close();
                    urlConn.disconnect();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;

    }

}
