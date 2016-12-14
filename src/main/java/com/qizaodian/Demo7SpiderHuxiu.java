package com.qizaodian;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.qizaodian.fetcher.HttpClientFetcher;
import com.qizaodian.mybatis.entity.ArticleInfo;
import com.qizaodian.store.ArticleInfoStore;

/***
 * 
 * @ClassName: Demo7SpiderHuxiu
 * @Description: 用httpclient 采集虎嗅一篇新闻
 * @author: Administrator
 * @date: 2016-11-17 上午10:58:30
 */
public class Demo7SpiderHuxiu {

	public static void main(String[] args) {

		// 1 输入采集url
		String url = "https://www.huxiu.com/article/171076.html?f=retweeted";
		// 2 下载页面html
		String html = new HttpClientFetcher().getHtml(url);
		// System.out.println(html);
		// 提示500 错误， 解决方法，给client 加上请求头
		// 解析页面html
		Document document = Jsoup.parse(html);
		// title
		Elements titleElements = document.select("h1.t-h1");
//		for (Element element : titleElements) {
//			System.out.println(element);
//			System.out.println("========================");
//		}
		String title = titleElements.first().text();
		Elements contentElements = document.select("div#article_content");
//		for (Element element : contentElements) {
//			System.out.println(element);
//		}
		String content = contentElements.first().toString();
		// 存储到Mysql
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setTitle(title);
		articleInfo.setContent(content);
		//返回插入的id
		System.out.println(new ArticleInfoStore().saveArticleInfo(articleInfo));
	}
}
