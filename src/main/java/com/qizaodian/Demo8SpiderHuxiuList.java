package com.qizaodian;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qizaodian.fetcher.HttpClientFetcher;
import com.qizaodian.mybatis.entity.ArticleInfo;
import com.qizaodian.store.ArticleInfoStore;

/***
 * 
 * @ClassName: Demo8SpiderHuxiuList
 * @Description: 用httpclient 采集虎嗅一页的新闻
 * @author: Administrator
 * @date: 2016-11-17 上午10:58:30
 */
public class Demo8SpiderHuxiuList {

	public static void main(String[] args) {
		new Demo8SpiderHuxiuList().spiderHuxiuList();

	}

	// 采集虎嗅列表页
	public void spiderHuxiuList() {
		// 首页 https://www.huxiu.com/
		String url = "https://www.huxiu.com/";
		// 获取资讯列表页所咋的区域 选择器
		String html = new HttpClientFetcher().getHtml(url);
		Document document = Jsoup.parse(html, url);
		// 获取列表页的区域
		Elements listElements = document.select("div.mod-info-flow");
		//

		// for (Element element : listElements) {
		// // 校验一下是否正确
		// System.out.println(element);
		// }
		// 获取 该区域中a 标签的链接
		Elements aElements = listElements.first().select("a.transition");
		for (Element aElement : aElements) {
			System.out.println(aElement);
			// 采集每篇新闻

			String articleUrl = aElement.absUrl("href");
			spiderHuXiuArtile(articleUrl);
		}
		// 有重复的，包含图片的链接就会包含，我们可以去除，以后给大家讲怎么去重

	}

	// 采集虎嗅单篇新闻
	/**
	 * 
	 * @param url
	 *            输入单片新闻的url
	 */
	public void spiderHuXiuArtile(String url) {
		// 1 输入采集url
		// 2 下载页面html
		String html = new HttpClientFetcher().getHtml(url);
		// System.out.println(html);
		// 提示500 错误， 解决方法，给client 加上请求头
		// 解析页面html
		Document document = Jsoup.parse(html);
		// title
		Elements titleElements = document.select("h1.t-h1");
		String title = titleElements.first().text();
		Elements contentElements = document.select("div#article_content");
		String content = contentElements.first().toString();
		// 存储到Mysql
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setTitle(title);
		articleInfo.setContent(content);
		// 返回插入的id
		System.out.println(new ArticleInfoStore().saveArticleInfo(articleInfo));
	}

}
