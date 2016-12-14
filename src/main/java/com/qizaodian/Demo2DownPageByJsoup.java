package com.qizaodian;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/***
 * 
 * @ClassName: Demo2DownPageByJsoup
 * @Description: 利用Jsoup 下载网页
 * @author: Administrator
 * @date: 2016-11-16
 */
public class Demo2DownPageByJsoup {

	public static void main(String[] args) {

		// 输入下载的url
		String url = "http://www.qizaodian.com";
		// 下载jar
		// 参考官方文档
		try {
			// get 请求url
			Document doc = Jsoup.connect(url).get();
			// 获取html
			String html = doc.toString();
			// 输出
			System.out.println(html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
