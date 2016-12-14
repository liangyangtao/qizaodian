package com.qizaodian.fetcher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/***
 * 
 * @ClassName: HttpClientImageFetcher
 * @Description: Httpclient 下载图片
 * @author: Administrator
 * @date: 2016-11-18 下午2:24:50
 */
public class HttpClientImageFetcher {

	/***
	 * 
	 * @param url
	 * @param pname  父文件夹名称
	 * @param filename 自己的名称
	 */
	public void downImage(String url,String pname, String filename) {
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
			// 将结果转为流了
			InputStream inputStream = new ByteArrayInputStream(
					EntityUtils.toByteArray(httpEntity));
			// 将流写成文件就行了
			File pfile = new File(HttpClientImageFetcher.class.getClassLoader()
					.getResource("").getPath()
					+ pname);
			// 先看看父目录是否存在，不存在建立一个
			if(!pfile.exists()){
				pfile.mkdirs();
			}
		    File file = new File(pfile.getAbsoluteFile() +File.separator+filename);
			FileOutputStream output = new FileOutputStream(file);
			byte[] b = new byte[1024];
			while (inputStream.read(b) != -1) {
				output.write(b);
			}
			output.flush();
			inputStream.close();
			output.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public void downImage(String url, String filename) {
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
			// 将结果转为流了
			InputStream inputStream = new ByteArrayInputStream(
					EntityUtils.toByteArray(httpEntity));
			// 将流写成文件就行了
			File file = new File(HttpClientImageFetcher.class.getClassLoader()
					.getResource("").getPath()
					+ filename);
			FileOutputStream output = new FileOutputStream(file);

			byte[] b = new byte[1024];
			while (inputStream.read(b) != -1) {
				output.write(b);
			}
			output.flush();
			inputStream.close();
			output.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
