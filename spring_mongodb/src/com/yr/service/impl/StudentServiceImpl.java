package com.yr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.entry.Student;
import com.yr.service.StudentService;
/**
 * 
 * @ClassName:  StudentServiceImpl   
 * @Description:TODO 

 * @author Administrator
 * @date:   2018��11��21�� ����9:57:21   
 *    
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
@Service
public class StudentServiceImpl implements StudentService<Student> {
	@Autowired
	private StudentDao<Student> studentDao;

	/**
	 * ��ѯһ��
	 * 
	 * @param map:��ѯ����
	 * @param collectionName:mongodb�ĵ�����
	 * @return student
	 */
	@Override
	public Student findOne(Map<String, Object> params, String collectionName) {
		Student student = studentDao.findOne(params, collectionName);
		return student;
	}

	/**
	 * ���һ��
	 */
	@Override
	public void insert(Student student) {
		studentDao.insert(student);
	}

	/**
	 * ��Ӷ���
	 */
	@Override
	public void insertMany(Student student, String collectionName) {
		studentDao.insertMany(student, collectionName);
	}

	/**
	 * ��ѯ����
	 */
	@Override
	public List<Student> findAll(String collectionName) {
		return studentDao.findAll(collectionName);
	}

	/**
	 * ����idɾ��
	 */
	@Override
	public void remove(Map<String, Object> params, String collectionName) {
		studentDao.remove(params, collectionName);
	}

	/**
	 * ����id�޸�
	 */
	@Override
	public void update(Map<String, Object> params, String collectionName) {
		studentDao.update(params, collectionName);
	}

	@Override
	public List<Student> pageFind(Integer skip, Integer limit, String collectionName) {
		return studentDao.pageFind(skip, limit, collectionName);
	}

}
