package com.qizaodian;

import com.qizaodian.fetcher.HttpClientImageFetcher;

/***
 * 
 * @ClassName:  Demo11DownImgeByHttpClient
 * @Description: 利用httpclient 下载图片
 * @author:  Administrator
 * @date:  2016-11-18 下午2:31:21
 */
public class Demo11DownImgeByHttpClient {

	public static void main(String[] args) {
		String url ="http://www.qizaodian.com/qizaodian/images/qizaodian22.png";
		String fileName ="qizaodian1.png";
		new HttpClientImageFetcher().downImage(url, fileName);
	}
}
