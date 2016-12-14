package com.qizaodian.store;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.qizaodian.mybatis.entity.ArticleInfo;
import com.qizaodian.mybatis.factory.DynamicConnectionFactory;
import com.qizaodian.mybatis.mapper.ArticleInfoMapper;

/***
 * 
 * @ClassName: ArticleInfoStore
 * @Description: 存储Article
 * @author: Administrator
 * @date: 2016-11-17 上午9:59:43
 */
public class ArticleInfoStore {

	public int saveArticleInfo(ArticleInfo articleInfo) {
		// 先读取SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = DynamicConnectionFactory
				.getInstanceSessionFactory("development");
		// 通过SqlSessionFactory获取SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 获得 ArticleInfoMapper
		ArticleInfoMapper articleInfoMapper = sqlSession
				.getMapper(ArticleInfoMapper.class);
		// 插入一个ArticleInfo
		int isInsert = articleInfoMapper.insertSelective(articleInfo);
		// 记得要提交，才能插入数据库
		sqlSession.commit();
		// 使用完成后关闭sqlSession
		sqlSession.close();
		if (isInsert == 0) {
			// 如果插入失败 0 是失败，1是成功
			return 0;
		}
		// 如果成功返回 插入的自增id
		return articleInfo.getArticleId();
	}
}
