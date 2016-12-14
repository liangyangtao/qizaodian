package com.qizaodian.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qizaodian.fetcher.HttpClientFetcher;
import com.qizaodian.fetcher.HttpClientImageFetcher;

/***
 * 
 * @ClassName: MeizituSpider
 * @Description: 图片下载 http://www.meizitu.com/a/list_1_1.html
 * @author: Administrator
 * @date: 2016-11-25 下午4:01:18
 */
public class MeizituSpider {

	public static void main(String[] args) {
		String url = "http://www.meizitu.com/a/list_1_1.html";
		// 获取列表
		parseList(url);

	}

	/***
	 * 解析 图片列表页
	 * 
	 * @param url
	 */
	private static void parseList(String url) {
		String html = new HttpClientFetcher().getHtml(url,"gb2312");
		Document document = Jsoup.parse(html);
		Elements liElements = document.select(".wp-item");
		for (Element liElement : liElements) {
			// li 下的a
			Element aElement = liElement.select(".tit > a").first();
			String title = aElement.text().trim();
			String href = aElement.absUrl("href");
			// 下载详情页的图片
			downDetailPage(title, href);

		}

	}

	/***
	 * 解析详情页
	 * 
	 * @param title
	 * @param url
	 */
	private static void downDetailPage(String title, String url) {

		String html = new HttpClientFetcher().getHtml(url,"gb2312");
		Document document = Jsoup.parse(html, url);
		Elements imgs = document.select("#picture > p > img");
		for (int i = 0; i < imgs.size(); i++) {
			Element element = imgs.get(i);
			String imgurl =element.attr("src");
			//下载图片
			downImage(imgurl,title,i);
		}

	}

	/***
	 *  下载图片
	 * @param imgurl 图片地址
	 * @param title 标题  文件目录
	 * @param i 图片编号，图片名称
	 */
	private static void downImage(String imgurl, String title, int i) {
		
		// 我们为每个妹子建一个文件夹
		
		new HttpClientImageFetcher().downImage(imgurl,title, i+".jpg");
		// 没有加打印，大家只要看到效果就好
	}
}
