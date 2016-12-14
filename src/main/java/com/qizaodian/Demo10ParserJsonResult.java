package com.qizaodian;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

import com.qizaodian.fetcher.HttpClientFetcher;

/***
 * 
 * @ClassName: Demo10ParserJsonResult
 * @Description: json 格式结果处理demo
 * @author: Administrator
 * @date: 2016-11-18 上午9:50:38
 */
public class Demo10ParserJsonResult {

	public static void main(String[] args) {
		String url = "https://www.huxiu.com/v2_action/article_list";
		String charset = "utf-8";
		Map<String, String> params = new HashMap<String, String>();
		params.put("huxiu_hash_code", "91607d32e1f5fe0279e58293ae9bc177");
		params.put("page", "2");
		params.put("last_dateline", "1479279660");
		String jsonHtml = new HttpClientFetcher()
				.postHtml(url, charset, params);
		parserJsonResult(jsonHtml, url);

	}

	/**
	 * 解析 json格式的结果
	 * 
	 * @param jsonHtml
	 *            json
	 * @param url
	 */
	private static void parserJsonResult(String jsonHtml, String url) {

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
			new Demo8SpiderHuxiuList().spiderHuXiuArtile(articleUrl);
		}

	}
}
