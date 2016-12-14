package com.qizaodian;

import com.qizaodian.fetcher.URLConnImageFetcher;

/***
 * 
 * @ClassName: Demo12DownImgeByUrlConn
 * @Description: 利用URLConn 下载图片
 * @author: Administrator
 * @date: 2016-11-18 下午2:31:21
 */
public class Demo12DownImgeByUrlConn {

	public static void main(String[] args) {
		String url = "http://www.qizaodian.com/images/qizaodian11.png";
		String fileName = "qizaodian1.png";
		new URLConnImageFetcher().downImage(url, fileName);
	}
}
