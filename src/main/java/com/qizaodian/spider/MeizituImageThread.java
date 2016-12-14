package com.qizaodian.spider;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.qizaodian.fetcher.HttpClientImageFetcher;

/***
 * 
 * @ClassName: MeizituImageThread
 * @Description: 采集图片的线程
 * @author: Administrator
 * @date: 2016-11-28 下午2:04:55
 */
public class MeizituImageThread implements Runnable {

	// 设定一个原子integer 自增
	public static AtomicInteger atomicInteger = new AtomicInteger();

	// 图片的阻塞队列
	public LinkedBlockingQueue<String> imgsQueue;

	public MeizituImageThread(LinkedBlockingQueue<String> imgsQueue) {
		super();
		this.imgsQueue = imgsQueue;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 如果queue 的大小不为0 则进行图片下载

			if (imgsQueue.size() > 0) {
				try {
					String url = imgsQueue.take();
					System.out.println("正在下图片" + url);
					String pname = "meizitu";

					//自增 的
					int a = atomicInteger.incrementAndGet();

					String filename = a + ".jpg";
					new HttpClientImageFetcher()
							.downImage(url, pname, filename);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
