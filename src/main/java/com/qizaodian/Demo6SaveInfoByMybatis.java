package com.qizaodian;

import com.qizaodian.mybatis.entity.ArticleInfo;
import com.qizaodian.store.ArticleInfoStore;

/***
 * 
 * @ClassName: Demo6SaveInfoByMybatis
 * @Description: 保存通过Mybatis
 * @author: Administrator
 * @date: 2016-11-17 上午10:06:00
 */
public class Demo6SaveInfoByMybatis {

	public static void main(String[] args) {
		// 先建立一个ArticleInfo
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setTitle("大家好，欢迎进入起早点视频，这是第二条");
		articleInfo.setContent("这个java.lang.UnsupportedClassVersionError异常是由于jar 包版本不一致");
		new ArticleInfoStore().saveArticleInfo(articleInfo);
		// 这个java.lang.UnsupportedClassVersionError异常是由于jar 包版本不一致 
	}
}
