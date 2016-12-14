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
 * @ClassName: HttpClientFileFetcher
 * @Description: Httpclient 下载pdf文件
 * @author: Administrator
 * @date: 2016-11-18 下午2:24:50
 */
public class HttpClientFileFetcher {

	public void downPdfFile(String url, String filename) {
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
			InputStream inputStream = httpEntity.getContent();
			// 将流写成文件就行了
			File file = new File(HttpClientFileFetcher.class.getClassLoader()
					.getResource("").getPath()
					+ filename);
			FileOutputStream output = new FileOutputStream(file);

			byte[] b = new byte[1024];
			int l;
			while ((l = inputStream.read(b)) != -1) {
				output.write(b, 0, l);
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
