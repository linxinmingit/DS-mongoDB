package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.yr.dao.StudentDao;
import com.yr.entry.Student;
/**
 * 
 * @ClassName:  StudentDaoImpl   
 * @Description:TODO 

 * @author Administrator
 * @date:   2018年11月21日 上午9:57:01   
 *    
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
@Repository
public class StudentDaoImpl implements StudentDao<Student> {
	@Resource
	// @Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 添加一条
	 */
	@Override
	public void insert(Student student) {
		mongoTemplate.insert(student);
	}

	/**
	 * 添加多条
	 */
	@Override
	public void insertMany(Student student, String collectionName) {
		mongoTemplate.insert(student, collectionName);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param map 查询条件
	 * @param collectionName  mongodb文档名称
	 * @return student
	 */
	@Override
	public Student findOne(Map<String, Object> params, String collectionName) {
		Student student = mongoTemplate.findOne(new Query(Criteria.where("_id").is(params.get("_id"))), Student.class,collectionName);
		return student;
	}

	/**
	 * 查询所有
	 * 
	 * @param map 查询条件
	 * @param collectionName  mongodb文档名称
	 * @return stuList  学生list
	 */
	@Override
	public List<Student> findAll(String collectionName) {
		List<Student> stuList = new ArrayList<>();
		stuList = mongoTemplate.findAll(Student.class, collectionName);
		return stuList;
	}

	/**
	 * 根据id删除
	 */
	@Override
	public void remove(Map<String, Object> params, String collectionName) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(params.get("_id"))), Student.class, collectionName);
	}

	/**
	 * 根据id修改
	 */
	@Override
	public void update(Map<String, Object> params, String collectionName) {
		// 要修改的值就是map 添加进去的值所以在这里我们根据key得到value就好
		Update update = Update.update("name", params.get("name"));
		update.set("age", params.get("age"));
		update.set("addr", params.get("addr"));

		mongoTemplate.upsert(new Query(Criteria.where("_id").is(params.get("_id"))), update, Student.class,
				collectionName);
	}

	/**
	 * 分页查询
	 * 
	 * @param skip 从skip开始
	 * @param limit 每页limit条
	 * @param collectionName 文档名称
	 * @return
	 */
	@Override
	public List<Student> pageFind(Integer skip, Integer limit, String collectionName) {
		List<Student> stuList = new ArrayList<>();
		Query query = new Query();
		stuList = mongoTemplate.find(query.skip(skip).limit(limit), Student.class, collectionName);
		return stuList;

	}

}
