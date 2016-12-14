package com.qizaodian.mybatis.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/***
 * 
 * @ClassName:  DynamicConnectionFactory
 * @Description: 动态加载MyBatis.xml 
 * @author:  Administrator
 * @date:  2016-11-17 上午9:58:27
 */
public class DynamicConnectionFactory {
	
	public static Map<String, SqlSessionFactory> sessionFactorys = new HashMap<String, SqlSessionFactory>();

	public static SqlSessionFactory getInstanceSessionFactory(String environment) {
		if (sessionFactorys.get(environment) == null) {
			String resource = "mybatis.xml";
			try {
				SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
						.build(Resources.getResourceAsReader(resource),
								environment);

				sessionFactorys.put(environment, sessionFactory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactorys.get(environment);
	}
}
