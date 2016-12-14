package com.qizaodian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/***
 * 
 * @ClassName: Demo9DownPageByHttpClientPost
 * @Description: 下载网页BY post
 * @author: Administrator
 * @date: 2016-11-17 下午2:54:52
 */
public class Demo9DownPageByHttpClientPost {

	public static void main(String[] args) {
		String url = "https://www.huxiu.com/v2_action/article_list";
		String charset = "utf-8";
		Map<String, String> params = new HashMap<String, String>();
		params.put("huxiu_hash_code", "91607d32e1f5fe0279e58293ae9bc177");
		params.put("page", "2");
		params.put("last_dateline", "1479279660");
		String jsonHtml = new Demo9DownPageByHttpClientPost().postHtml(url,
				charset, params);
		System.out.println(jsonHtml);
	}

	/**
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
