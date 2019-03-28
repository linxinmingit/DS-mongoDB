package cn.yr.MongodbCluster;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
* @Title:  MongodbClusterConn.java   
* @Package cn.yr.MongodbCluster
* @Description:    副本集群读写分离
* 
* @author: 林鑫敏
* @date:   2018年11月19日 下午3:03:49   
*/
public class MongodbClusterConn {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;

	public static void main(String args[]) {
		MongodbClusterConn mongodb = new MongodbClusterConn();
		//mongodb.insert();
		mongodb.select();
	}

	/**
	 * 连接mongodb集群 就算任意一个节点挂掉也不会影响应用程序客户端对整个副本集的读写
	 */
	public void setUp() {
		try {
			List<ServerAddress> addresses = new ArrayList<ServerAddress>();
			ServerAddress address1 = new ServerAddress("192.168.1.8", 27017);
			ServerAddress address2 = new ServerAddress("192.168.1.9", 27017);
			ServerAddress address3 = new ServerAddress("192.168.1.10", 27017);
			
//			ServerAddress address1 = new ServerAddress("192.168.1.8" , 20000);
//            ServerAddress address2 = new ServerAddress("192.168.1.9" , 20000);
//            ServerAddress address3 = new ServerAddress("192.168.1.10" , 20000);
            
			addresses.add(address1);
			addresses.add(address2);
			addresses.add(address3);
			/**
			 * ReadPreference.secondary()设置读操作只在副节点上进行,真正的实现读写分离
			 * 
			 * Primary模式：默认模式，所有的读操作都由复制集的主节点处理；
               primaryPreferred模式：一般情况下，所有的读操作由主节点处理，当主节点不可用的时候，读操作由备份节点处理；
               secondary模式：所有的读操作由复制集的备份节点处理；
               secondaryPreferred模式：一般情况下，所有的读操作由备份节点处理，当所有的备份阶段宕机后，读操作由主节点处理；
               nearest模式：选择复制集中的读延迟最少的节点处理读操作，主节点以及备份节点都有可能处理读操作；
			 */
			MongoClientOptions options = MongoClientOptions.builder().readPreference(ReadPreference.secondary()).build();
			
			//mongoClient = new MongoClient(addresses);
			mongoClient = new MongoClient(addresses,options);
			
			mongoDatabase = mongoClient.getDatabase("test");
			collection = mongoDatabase.getCollection("test");
			System.out.println("Connect to database successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检索所有文档
	 */
	public void select() {
		setUp();
		try {
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 插入
	 */
	public void insert() {
		try {
			setUp();
			Document document = new Document("title", "MongoDB").append("name", "洋葱").append("addr", "中国深圳")
					.append("sex", "男").append("age", 18);
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
