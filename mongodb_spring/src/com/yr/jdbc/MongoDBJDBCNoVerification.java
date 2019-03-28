package com.yr.jdbc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 单节点无验证连接
 * @author zxy-un
 * 
 * 2018年8月17日 下午2:54:29
 */
public class MongoDBJDBCNoVerification {

	public static void main(String args[]) {
		try {
			// 连接到 mongodb 服务
			@SuppressWarnings("resource")
			MongoClient mongoClient = new MongoClient("192.168.1.231", 20000);

			// 连接到数据库
			@SuppressWarnings("unused")
			MongoDatabase mongoDatabase = mongoClient.getDatabase("testaa");
			System.out.println("Connect to database successfully  ");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}