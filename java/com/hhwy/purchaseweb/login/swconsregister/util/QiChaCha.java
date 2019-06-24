package com.hhwy.purchaseweb.login.swconsregister.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class QiChaCha {


	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cookie", "UM_distinctid=15e60f6283424f-07be617a2d8525-333f5b02-100200-15e60f6283511d; hasShow=1; acw_tc=AQAAAMVadhwGNA0A0tOxbu7TaNKUPjDV; _uab_collina=150486530140850555304985; _umdata=85957DF9A4B3B3E8359DD742F9EA991E5C36917A6545C6D3FF269B02264CD96949EEEB6EF3D3096CCD43AD3E795C914C16329E1E0F159A34C95642CFBFD3AAEC; PHPSESSID=14b9c10nbbsil91pfoo0rpsq75; zg_did=%7B%22did%22%3A%20%2215e60f628c6181-0be7b9a8ea1753-333f5b02-100200-15e60f628c81ce%22%7D; zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201504865298647%2C%22updated%22%3A%201504865370153%2C%22info%22%3A%201504865298663%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.baidu.com%22%2C%22cuid%22%3A%20%2254be06f1f26c139f5650cab9e2a33977%22%7D; CNZZDATA1254842228=1287961051-1504862666-https%253A%252F%252Fwww.baidu.com%252F%7C1504862666");
            connection.setRequestProperty("Host", "www.qichacha.com");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            
            
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            
            result=uncompress(connection.getInputStream());
            
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Host", "www.qichacha.com");
            conn.setRequestProperty("Origin", "http://www.qichacha.com/");
            conn.setRequestProperty("Referer", "http://www.qichacha.com/");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36 X-Requested-With	XMLHttpRequest");
            conn.setRequestProperty("Cookie", "UM_distinctid=15e60f6283424f-07be617a2d8525-333f5b02-100200-15e60f6283511d; hasShow=1; acw_tc=AQAAAMVadhwGNA0A0tOxbu7TaNKUPjDV; _uab_collina=150486530140850555304985; _umdata=85957DF9A4B3B3E8359DD742F9EA991E5C36917A6545C6D3FF269B02264CD96949EEEB6EF3D3096CCD43AD3E795C914C16329E1E0F159A34C95642CFBFD3AAEC; PHPSESSID=14b9c10nbbsil91pfoo0rpsq75; zg_did=%7B%22did%22%3A%20%2215e60f628c6181-0be7b9a8ea1753-333f5b02-100200-15e60f628c81ce%22%7D; zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201504865298647%2C%22updated%22%3A%201504865370153%2C%22info%22%3A%201504865298663%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.baidu.com%22%2C%22cuid%22%3A%20%2254be06f1f26c139f5650cab9e2a33977%22%7D; CNZZDATA1254842228=1287961051-1504862666-https%253A%252F%252Fwww.baidu.com%252F%7C1504862666");
    		        
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            
            result=uncompress(conn.getInputStream());
            
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 

	// 解压缩   
	 public static String uncompress(InputStream in) throws IOException {   
	   ByteArrayOutputStream out = new ByteArrayOutputStream();   
	    GZIPInputStream gunzip = new GZIPInputStream(in);   
	    byte[] buffer = new byte[256];   
	    int n;   
	   while ((n = gunzip.read(buffer))>= 0) {   
	    out.write(buffer, 0, n);   
	    }   
	    // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
	    return out.toString("UTF-8");   
	  }
	 
}
