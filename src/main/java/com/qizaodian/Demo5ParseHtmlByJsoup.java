package com.qizaodian;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 
 * @ClassName: Demo5ParseHtmlByJsoup
 * @Description: 解析html BY Jsoup
 * @author: Administrator
 * @date: 2016-11-16 下午4:19:39
 */
public class Demo5ParseHtmlByJsoup {

	public static void main(String[] args) {
		// 加载文件 Demo5.html
		File file = new File(Demo5ParseHtmlByJsoup.class.getClassLoader()
				.getResource("Demo5.html").getPath());

		try {
			// 转换为Jsoup 的document
			Document document = Jsoup.parse(file, "utf-8");
            //输出校验下是否转换成功
//			System.out.println(document);
			//通过id 进行选择
//			Element title = document.select("#title").first();
//			System.out.println(title);
			//通过类进行选择
//			Element p1Element = document.select(".p1").first();
//			System.out.println(p1Element);
			//通过标签进行选择
//			Element strongElement = document.select("strong").first();
//			System.out.println(strongElement);
			// 根据属性选择， 符合条件的才会被选择
//			Elements aElements = document.select("a[href]");
//		    for (Element aElement : aElements) {
//		    	System.out.println(aElement);
//			}
			// 
			Elements imgElements = document.select(" p > img ");
		    for (Element imgElement : imgElements) {
		    	System.out.println(imgElement);
			}
			//选择器组合使用   p > img 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
