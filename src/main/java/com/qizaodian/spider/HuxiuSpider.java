package com.qizaodian.spider;





import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qizaodian.fetcher.HttpClientFetcher;
import com.qizaodian.mybatis.entity.ArticleInfo;
import com.qizaodian.store.ArticleInfoStore;
import java.util.HashMap;
import java.util.Map;
/***
 * 
 * @ClassName: HuxiuSpider
 * @Description: 单线程采集Huxiu网
 * @author: Administrator
 * @date: 2016-11-18 上午11:27:57
 */
public class HuxiuSpider {

	public static void main(String[] args) {
		new HuxiuSpider().doSpider();
	}

	/**
	 * 采集入口
	 */
	private void doSpider() {
		// int pageNum = 1041;
		// 1041
		// 2 获取新闻列表
		// 3 采集每篇新闻
		int pageNum = 10;
		for (int i = 1; i < pageNum; i++) {
			// 1 分页
			System.out.println("正在采集第" + i + "页");
			String url = "https://www.huxiu.com/v2_action/article_list";
			String charset = "utf-8";
			Map<String, String> params = new HashMap<String, String>();
			params.put("huxiu_hash_code", "9a6b6dfcb6d093af29bf4660623b340d");
			params.put("page", "" + i);
			params.put("last_dateline", "1479361260");
			String jsonHtml = new HttpClientFetcher().postHtml(url, charset,
					params);
			parserJsonResult(jsonHtml, url);

		}
	}

	/**
	 * 解析 json格式的结果
	 * 
	 * @param jsonHtml
	 *            json
	 * @param url
	 */
	private void parserJsonResult(String jsonHtml, String url) {
		// 2 获取新闻列表
		JSONObject jsonObject = JSONObject.fromObject(jsonHtml);

		String data = jsonObject.getString("data");

		// System.out.println(data);
		// 转换为Jsoup document
		Document document = Jsoup.parse(data, url);
		// 引用Demo 8 里的代码
		Elements aElements = document.select("h2 > a");
		// 上期我们发现有重复的，是a 标签下有img 的也给弄进来了
		//
		for (Element aElement : aElements) {
			System.out.println(aElement);
			// 采集每篇新闻
			String articleUrl = aElement.absUrl("href");
			spiderHuXiuArtile(articleUrl);
		}

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
