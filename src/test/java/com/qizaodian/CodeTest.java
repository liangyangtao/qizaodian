package com.qizaodian;

import com.qizaodian.fetcher.HttpClientFetcher;

public class CodeTest {

	public static void main(String[] args) {
		String utf8url = "http://stock.eastmoney.com/news/chyyj.html";
		String g2312Url = "http://www.meizitu.com/a/5478.html";
		System.out.println(new HttpClientFetcher().getHtml(g2312Url));
	}
}
