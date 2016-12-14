package com.qizaodian;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 
 * @ClassName:  Demo1DownPageByHttpClient
 * @Description: 利用httpclient 下载网页
 * @author:  Administrator
 * @date:  2016-11-16 
 */
public class Demo1DownPageByHttpClient {

	/**
	 * 1 要下载的url
	 * 
	 * 2下载jar 包
	 * 
	 * 3 写代码
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://www.qizaodian.com";
		// 创建客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建上下文
		HttpClientContext clientContext = HttpClientContext.create();
		// 创建get请求
		HttpGet httpGet = new HttpGet(url);

		try {
			// 获取 response
			CloseableHttpResponse response = httpClient.execute(httpGet,
					clientContext);
			// 获取请求结果
			HttpEntity httpEntity = response.getEntity();
			// 将结果转为String
			String html = EntityUtils.toString(httpEntity);
			// 输出
			System.out.println(html);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
