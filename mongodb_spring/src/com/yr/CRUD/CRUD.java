package com.yr.CRUD;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class CRUD {
	
	private MongoDatabase database;
	
	public CRUD(MongoDatabase database) { // 构造方法
	    this.database = database;
	}
	
	public static void main(String[] args) throws ParseException {
		// 20000端口为分片后集群的mongos端口,不添加验证直接执行下面一句创建连接(单节点)
		// MongoClient mongoClient = new MongoClient("192.168.46.133", 20000);

		// 添加验证模式
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
		ServerAddress address1 = new ServerAddress("192.168.1.127", 30000);
		ServerAddress address2 = new ServerAddress("192.168.1.130", 30000);
		ServerAddress address3 = new ServerAddress("192.168.1.231", 30000);
		seeds.add(address1);
		seeds.add(address2);
		seeds.add(address3);
		MongoClient mongoClient = new MongoClient(seeds);

		MongoDatabase database = mongoClient.getDatabase("testaa"); // 数据库
		CRUD client = new CRUD(database);
		
		MongoCollection<Document> collection = database.getCollection("zxyTestAgree"); // 获取集合(表)
		System.out.println("集合: "+collection);
		
//		database.createCollection("zxyTestAgree"); // 创建集合
//		System.out.println("集合创建成功");
		
		/** 
		 * 数据
		 * 1. 创建文档 org.bson.Document 参数为key-value的格式 
		 * 2. 创建文档集合List<Document> 
		 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
		 * */
//		for (int i = 0; i < 10; i++) {
//			Document document = new Document("name", "zouxiangyun"+i).  
//					append("des", "databaseTest").  
//					append("age", 18).  
//					append("by", "Money");  
//			List<Document> documents = new ArrayList<Document>();  
//			documents.add(document);  
//			collection.insertMany(documents);  
//		}
//		System.out.println("文档插入成功");
		
//		 /** 
//         * 1. 获取迭代器FindIterable<Document> 
//         * 2. 获取游标MongoCursor<Document> 
//         * 3. 通过游标遍历检索出的文档集合 
//         * */  
//         FindIterable<Document> findIterable = collection.find();  
//         MongoCursor<Document> mongoCursor = findIterable.iterator();  
//         while(mongoCursor.hasNext()){  
//            System.out.println(mongoCursor.next());  
//         }
		
		/**
		 * 更新文档: 把文档中age=18的文档修改,将des字段修改
		 */
//        collection.updateMany(Filters.eq("age", 18), new Document("$set",new Document("des","纸扇不好使")));  
//        //检索查看结果  
//        FindIterable<Document> findIterable1 = collection.find();  
//        MongoCursor<Document> mongoCursor1 = findIterable1.iterator();  
//        while(mongoCursor1.hasNext()){  
//           System.out.println(mongoCursor1.next());  
//        } 
		
//		删除符合条件的第一个文档  
//		collection.deleteOne(Filters.eq("by", "Money"));
//		删除所有符合条件的文档  
//        collection.deleteMany (Filters.eq("age", 18));
//		System.out.println("删除成功!!");
		
		//检索查看结果
//        FindIterable<Document> findIterable2 = collection.find();  
//        MongoCursor<Document> mongoCursor2 = findIterable2.iterator();  
//        while(mongoCursor2.hasNext()){  
//          System.out.println("=========="+mongoCursor2.next());  
//        }
		
		mongoClient.close();
	}
	
}
