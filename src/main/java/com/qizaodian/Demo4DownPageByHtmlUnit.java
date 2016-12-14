package com.qizaodian;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * @ClassName: Demo4DownPageByHtmlUnit
 * @Description: 利用Htmlunit 下载网页
 * @author: Administrator
 * @date: 2016-11-16 下午3:17:42
 */
public class Demo4DownPageByHtmlUnit {

	public static void main(String[] args) {

		String url = "http://www.qizaodian.com";
		try {
			// 创建Web客户端
			WebClient webClient = new WebClient();
			// 设置 不加载样式
			webClient.getOptions().setCssEnabled(false);
			// 设置不启用javascript 引擎
			webClient.getOptions().setJavaScriptEnabled(false);
			// 获取页面
			HtmlPage page = webClient.getPage(url);
			// 输出 页面
			System.out.println(page.asXml());
			// 关闭浏览器
			webClient.close();

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
