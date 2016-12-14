package com.qizaodian.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * 
 * @ClassName: MutilThreadMeizituSpider
 * @Description: 多线程采集妹子图
 * @author: Administrator
 * @date: 2016-11-28 下午1:49:24
 */
public class MutilThreadMeizituSpider {

	// list 队列
	public static LinkedBlockingQueue<String> listQueue = new LinkedBlockingQueue<String>();
	// 图片src队列
	public static LinkedBlockingQueue<String> imgsQueue = new LinkedBlockingQueue<String>();

	public static void main(String[] args) {
		// listQueue 列表页进行封装
		for (int i = 1; i <= 90; i++) {
			String url = "http://www.meizitu.com/a/list_1_" + i + ".html";
			try {
				listQueue.put(url);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 采集列表页的线程
		new Thread(new MeiziTuListThread(listQueue, imgsQueue)).start();
		// 采集图片的线程
		// 这里我们用线程池
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// 10个线程下载图片
		for (int i = 0; i < 10; i++) {
			executorService.execute(new MeizituImageThread(imgsQueue));
		}
		executorService.shutdown();

	}

}
