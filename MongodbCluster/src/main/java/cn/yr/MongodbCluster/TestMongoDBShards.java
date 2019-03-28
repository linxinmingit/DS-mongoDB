package cn.yr.MongodbCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
* @Title:  TestMongoDBShards.java   
* @Package cn.yr.MongodbCluster
* @Description:    mongodb 增删改查
* 
* @author: 林鑫敏
* @date:   2018年11月19日 下午5:57:00   
*/
public class TestMongoDBShards {
	static List<ServerAddress> addresses;
	static MongoClient client;
	static BasicDBObject object;
	private static MongoDatabase mongoDatabase;
	//static DB db;
	static MongoDatabase db;
	private static MongoCollection<Document> collection;
	
	static {
		addresses = new ArrayList<ServerAddress>();
		ServerAddress address1 = new ServerAddress("192.168.1.8" , 20000);
        ServerAddress address2 = new ServerAddress("192.168.1.9" , 20000);
        ServerAddress address3 = new ServerAddress("192.168.1.10" , 20000);
        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);
        client = new MongoClient(addresses);
        
        db = client.getDatabase("testdb");
        collection = db.getCollection("test");
	}
	 public static void main(String[] args) {
//	        try {
//	            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
//	            ServerAddress address1 = new ServerAddress("192.168.1.8" , 20000);
//	            ServerAddress address2 = new ServerAddress("192.168.1.9" , 20000);
//	            ServerAddress address3 = new ServerAddress("192.168.1.10" , 20000);
//	            addresses.add(address1);
//	            addresses.add(address2);
//	            addresses.add(address3);
//	            MongoClient client = new MongoClient(addresses);
//	            DB db = client.getDB( "testdb" );
//	            DBCollection coll = db.getCollection("test");
//	            BasicDBObject object = new BasicDBObject();
//	            object.append( "id" , 30);
//	            DBObject dbObject = coll.findOne(object);
//	            System.out .println(dbObject);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		 TestMongoDBShards test = new TestMongoDBShards();
		 //test.insert();
		 test.query();
		 //test.findBy();
		 //test.updateOne();
		 //test.deleteBy();
	    }
	 /**
	  * 数据添加
	  */
	 public void insert()
	 {
//		 Document document = new Document("id",1200).append("name", "linxinmin").append("age", 20).append("addr","YZ");
//		 List<Document> documents = new ArrayList<Document>();
//		 documents.add(document);
//		 collection.insertMany(documents);
		 
		 for (int i = 1; i <= 100; i++) {
			Document document = new Document("id",i).append("title", "YIIBAI 教程  "+i);
			collection.insertOne(document);
		}
		 
		 System.out.println("数据添加成功！");
	 }
	 
	 /**
	  * 数据查询
	  * 
	  * @param filter 条件
	  * 
	  * 1 升序     -1降序
	  */
	 public void query()
	 {
		 /*Document filter = new Document();
		 filter.append("id", 1);
		 
	     FindIterable<Document> find =  collection.find().sort(filter);//排序
		 
		 //FindIterable<Document> find =  collection.find();
		 MongoCursor<Document> cursor =  find.iterator();
		 while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}*/

		  
		 /*
		 BasicDBObject cond1 = new BasicDBObject();//临时条件对象
		 cond1.put("id",new BasicDBObject("$gt",20));
		 cond1.put("id",new BasicDBObject("$lte",50));
		 FindIterable<Document> cursor= collection.find(cond1);
		 MongoCursor<Document> list =  cursor.iterator();
		 while (list.hasNext()) {
			System.out.println(list.next());
		}*/
		 
		 
/*		 BasicDBList condList = new BasicDBList();//临时条件列表
		 BasicDBObject object = new BasicDBObject();
		 object.append("id",1);
		 //object.append("id", new BasicDBObject("$gt",90));
		 
		 BasicDBObject object2 = new BasicDBObject();
		 object.append("title","w3school");
		 
		 condList.add(object);
		 condList.add(object2);
		 
		 BasicDBObject filterCond = new BasicDBObject();
		 filterCond.put("$and", condList);
		 //filterCond.put("$or", condList);
		 
		 FindIterable<Document> list =  collection.find(filterCond);
		 MongoCursor<Document> cursor =  list.iterator();
		 while (cursor.hasNext()) {
			//System.out.println(cursor.next());
			Document d =  cursor.next();
			System.out.println("title 的值是  :"+d.get("title"));
		}*/
		 
		 
		   //模糊查询
	        Pattern pattern = Pattern.compile("^.*国.*$", Pattern.CASE_INSENSITIVE);
	        BasicDBObject query = new BasicDBObject();
	        //加入查询条件 
	        query.put("name", pattern);
	        query.put("sheng", "11");
	        query.put("level", 3);
	        //按名次升序排序
	        BasicDBObject sort = new BasicDBObject();
	        // 1,表示正序； －1,表示倒序
	        sort.put("name", 1);
	        FindIterable<Document> cur = collection.find(query).sort(sort);	
	        MongoCursor<Document> list = cur.iterator();
	        while (list.hasNext()) {
				//Document document = (Document) list.next();
	        	System.out.println(list.next());
			}
	 }
	 /**
	     * 根据条件查询
	     * 
	     * @param filter
	     *            查询条件 //注意Bson的几个实现类，BasicDBObject, BsonDocument,
	     *            BsonDocumentWrapper, CommandResult, Document, RawBsonDocument
	     * @return 返回集合列表
	     */
//	    public static List<Document> findBy(Bson filter) {
//	        List<Document> results = new ArrayList<Document>();
//	        FindIterable<Document> iterables = collection.find(filter);
//	        MongoCursor<Document> cursor = iterables.iterator();
//	        while (cursor.hasNext()) {
//	            results.add(cursor.next());
//	        }
//	 
//	        return results;
//	    }
	 public void findBy()
	 {
		 Document document = new Document();
		 document.append("id", 8);
	        //List<Document> results = new ArrayList<Document>();
	        FindIterable<Document> iterables = collection.find(document);
	        MongoCursor<Document> cursor = iterables.iterator();
	        while (cursor.hasNext()) {
	            //results.add(cursor.next());
	        	System.out.println(cursor.next());
	        }
		 
	 }
	 /**
	     * 更新查询到的第一个
	     * 
	     * @param filter
	     *            查询条件
	     * @param update
	     *            更新文档
	     * @return 更新结果
	     */
	    public void updateOne() {
	       Document filter = new Document();
	       filter.append("id", 1);//查询条件
	       
	       Document update = new Document();
	       update.append("$set",new Document("title", "CAINIAO 教程"));
	       UpdateResult result = collection.updateOne(filter, update);
	       //UpdateResult result1 = collection.updateMany(filter, update);   //更新多个
	       System.out.println("更新之后的数据  : "+result.getModifiedCount());
	    }
	    /**
	     * 根据条件删除 
	     * 
	     * @param filter 删除条件
	     */
	    public void deleteBy()
	    {
	    	Document filter = new Document();
	    	filter.append("id",22);
	    	
	    	DeleteResult result = collection.deleteOne(filter);
	    	System.out.println("删除了，id为  "+result.getDeletedCount()+"的数据");
	    }
}
