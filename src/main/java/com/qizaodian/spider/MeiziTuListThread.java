package com.qizaodian.spider;

import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qizaodian.fetcher.HttpClientFetcher;

/***
 * 
 * @ClassName: MeiziTuListThread
 * @Description: 列表页线程
 * @author: Administrator
 * @date: 2016-11-28 下午1:54:10
 */
public class MeiziTuListThread implements Runnable {

  
	// 列表页的Queue
	public LinkedBlockingQueue<String> listQueue;

	// 图片的Queue
	public LinkedBlockingQueue<String> imgsQueue;

	public MeiziTuListThread(LinkedBlockingQueue<String> listQueue,
			LinkedBlockingQueue<String> imgsQueue) {
		super();
		this.listQueue = listQueue;
		this.imgsQueue = imgsQueue;
	}

	/***
	 * 解析 图片列表页
	 * 
	 * @param url
	 */
	private void parseList(String url) {
		String html = new HttpClientFetcher().getHtml(url, "gb2312");
		Document document = Jsoup.parse(html);
		Elements liElements = document.select(".wp-item");
		for (Element liElement : liElements) {
			// li 下的a
			Element aElement = liElement.select(".tit > a").first();
			String title = aElement.text().trim();
			String href = aElement.absUrl("href");
			// 下载详情页的图片
			//
			downDetailPage(title, href);

		}

	}

	/***
	 * 解析详情页
	 * 
	 * @param title
	 * @param url
	 */
	private void downDetailPage(String title, String url) {

		String html = new HttpClientFetcher().getHtml(url, "gb2312");
		Document document = Jsoup.parse(html, url);
		Elements imgs = document.select("#picture > p > img");
		for (int i = 0; i < imgs.size(); i++) {
			Element element = imgs.get(i);
			String imgurl = element.attr("src");
			System.out.println(" 获取到  img  链接" + imgurl);
			// 我们这里将详情页的图片链接放在阻塞队列里
			try {
				imgsQueue.put(imgurl);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// downImage(imgurl, title, i);
		}

	}

	public void run() {
		// 在循环里判断 listQueue 是否为空
		// 不为空则进行列表页的解析，解析出img 的详情页
		while (true) {
			// 休眠 1秒
			try {
				// 为了避免 给图片网 造成压力，我们这里休眠时间长一点
				// 每 5秒采集一页
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (listQueue.size() > 0) {
				try {
					String url = listQueue.take();
					// 这个是每页的url
					parseList(url);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
