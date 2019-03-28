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
 * @date:   2018��11��21�� ����9:57:01   
 *    
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
@Repository
public class StudentDaoImpl implements StudentDao<Student> {
	@Resource
	// @Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * ���һ��
	 */
	@Override
	public void insert(Student student) {
		mongoTemplate.insert(student);
	}

	/**
	 * ��Ӷ���
	 */
	@Override
	public void insertMany(Student student, String collectionName) {
		mongoTemplate.insert(student, collectionName);
	}

	/**
	 * ����������ѯ
	 * 
	 * @param map ��ѯ����
	 * @param collectionName  mongodb�ĵ�����
	 * @return student
	 */
	@Override
	public Student findOne(Map<String, Object> params, String collectionName) {
		Student student = mongoTemplate.findOne(new Query(Criteria.where("_id").is(params.get("_id"))), Student.class,collectionName);
		return student;
	}

	/**
	 * ��ѯ����
	 * 
	 * @param map ��ѯ����
	 * @param collectionName  mongodb�ĵ�����
	 * @return stuList  ѧ��list
	 */
	@Override
	public List<Student> findAll(String collectionName) {
		List<Student> stuList = new ArrayList<>();
		stuList = mongoTemplate.findAll(Student.class, collectionName);
		return stuList;
	}

	/**
	 * ����idɾ��
	 */
	@Override
	public void remove(Map<String, Object> params, String collectionName) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(params.get("_id"))), Student.class, collectionName);
	}

	/**
	 * ����id�޸�
	 */
	@Override
	public void update(Map<String, Object> params, String collectionName) {
		// Ҫ�޸ĵ�ֵ����map ��ӽ�ȥ��ֵ�������������Ǹ���key�õ�value�ͺ�
		Update update = Update.update("name", params.get("name"));
		update.set("age", params.get("age"));
		update.set("addr", params.get("addr"));

		mongoTemplate.upsert(new Query(Criteria.where("_id").is(params.get("_id"))), update, Student.class,
				collectionName);
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param skip ��skip��ʼ
	 * @param limit ÿҳlimit��
	 * @param collectionName �ĵ�����
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
