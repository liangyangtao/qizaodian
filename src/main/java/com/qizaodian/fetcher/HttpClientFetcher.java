package com.qizaodian.fetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/***
 * 
 * @ClassName: HttpClientFetcher
 * @Description: HttpClient 封装类 get post 请求
 * @author: Administrator
 * @date: 2016-11-18 上午9:37:53
 */
public class HttpClientFetcher {
	/**
	 * get 请求
	 * 
	 * 默认编码utf-8
	 * 
	 * @param url
	 * @return
	 */
	public String getHtml(String url) {
		return getHtml(url, "utf-8");
	}

	/***
	 * 
	 * @param url
	 * @param charset
	 *            页面编码
	 * @return
	 */
	public String getHtml(String url, String charset) {
		String html = null;
		// 创建客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建上下文
		HttpClientContext clientContext = HttpClientContext.create();
		// 创建get请求
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		try {
			// 获取 response
			CloseableHttpResponse response = httpClient.execute(httpGet,
					clientContext);
			// 获取请求结果
			HttpEntity httpEntity = response.getEntity();
			// 将结果转为String ,指定编码
			html = EntityUtils.toString(httpEntity, charset);
			// 输出
			// System.out.println(html);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 *            请求网址
	 * @param charset
	 *            参数编码
	 * @param params
	 *            表单参数
	 * @return
	 */
	public String postHtml(String url, String charset,
			Map<String, String> params) {

		String html = null;
		// 创建客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建上下文
		HttpClientContext clientContext = HttpClientContext.create();
		// // 创建get请求
		// HttpGet httpGet = new HttpGet(url);
		// 创建Post 请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");

		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			// 执行post 前得带上参数
			for (String key : params.keySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(key,
						params.get(key));
				nvps.add(nameValuePair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));

			// 获取 response
			CloseableHttpResponse response = httpClient.execute(httpPost,
					clientContext);
			// 获取请求结果
			HttpEntity httpEntity = response.getEntity();
			// 将结果转为String
			html = EntityUtils.toString(httpEntity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
}
