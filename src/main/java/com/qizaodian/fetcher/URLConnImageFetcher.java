package com.qizaodian.fetcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/***
 * 
 * @ClassName: URLConnImageFetcher
 * @Description: 利用URLConn 下载Image
 * @author: Administrator
 * @date: 2016-11-18 下午3:57:28
 */
public class URLConnImageFetcher {

	/**
	 * 
	 * @param imagUrl
	 *            图片地址
	 * @param fileName
	 *            保存文件的名字
	 */
	public void downImage(String imagUrl, String fileName) {
		try {
			// 输入URL
			URL url = new URL(imagUrl);
			// 获取 Conn

			URLConnection urlConnection = url.openConnection();
			// 链接
			urlConnection.connect();

			// 获取请求结果
			InputStream inputStream = urlConnection.getInputStream();

			// 将inputStream 输出
			// 将流写成文件就行了
			File file = new File(URLConnImageFetcher.class.getClassLoader()
					.getResource("").getPath()
					+ fileName);
			FileOutputStream output = new FileOutputStream(file);

			byte[] b = new byte[1024];
			int l = 0;
			while ((l = inputStream.read(b)) != -1) {
				output.write(b, 0, l);
			}
			output.flush();
			inputStream.close();
			output.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
