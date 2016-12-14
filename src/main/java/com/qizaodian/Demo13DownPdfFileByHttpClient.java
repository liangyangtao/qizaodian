package com.qizaodian;

import com.qizaodian.fetcher.HttpClientFileFetcher;

/***
 * 
 * @ClassName: Demo13DownPdfFileByHttpClient
 * @Description: 下载pdf文件by Httpclient
 * @author: Administrator
 * @date: 2016-11-24 下午4:09:54
 */
public class Demo13DownPdfFileByHttpClient {

	public static void main(String[] args) {
		String url = "http://www.cninfo.com.cn/cninfo-new/disclosure/szse/download/1202819829?announceTime=2016-11-09";
		String filename = "hello.pdf";
		new HttpClientFileFetcher().downPdfFile(url, filename);
	}
}
