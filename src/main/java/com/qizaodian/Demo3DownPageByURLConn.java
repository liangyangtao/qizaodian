package com.qizaodian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @ClassName: Demo3DownPageByURLConn
 * @Description: 利用jdk net 包下URLConn下载网页
 * @author: Administrator
 * @date: 2016-11-16 下午2:46:25
 */
public class Demo3DownPageByURLConn {

	public static void main(String[] args) {

		try {
			// 输入URL
			URL url = new URL("http://www.qizaodian.com");
			// 获取 Conn

			URLConnection urlConnection = url.openConnection();
			// 链接
			urlConnection.connect();

			// 获取请求结果
			InputStream in = urlConnection.getInputStream();

			// 输出结果

			// 将inputStream 输出

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in));

			String result = null;
			// 循环读取流，赋值给result ，将result 输出
			while ((result = bufferedReader.readLine()) != null) {
				System.out.println(result);
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
