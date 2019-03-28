package com.yr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.entry.Student;
import com.yr.entry.Teacher;
import com.yr.service.StudentService;

@Service()
public class StudentServiceImpl implements StudentService<Student> {
	@Autowired
	private StudentDao<Student> studentDao;

	/**
	 * 查询一条
	 * 
	 * @param map:查询条件
	 * @param collectionName:mongodb文档名称
	 * @return student
	 */
	@Override
	public Student findOne(Map<String, Object> params, String collectionName) {
		Student student = studentDao.findOne(params, collectionName);
		return student;
	}

	/**
	 * 添加一条
	 */
	@Override
	public void insert(Student student) {
		studentDao.insert(student);
	}

	/**
	 * 添加多条
	 */
	@Override
	public void insertMany(Student student, String collectionName) {
		studentDao.insertMany(student, collectionName);
	}

	/**
	 * 查询所有
	 */
	@Override
	public List<Student> findAll(String collectionName) {
		return studentDao.findAll(collectionName);
	}
	
	/**
	 * 根据id删除
	 */
	@Override
	public void remove(Map<String, Object> params, String collectionName) {
		studentDao.remove(params, collectionName);
	}

	/**
	 * 根据id修改
	 */
	@Override
	public void update(Map<String, Object> params, String collectionName) {
		studentDao.update(params, collectionName);
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Student> pageFind(Integer skip, Integer limit, String collectionName) {
		return studentDao.pageFind(skip, limit, collectionName);
	}

	/**
	 * 多条件查询
	 */
	@Override
	public List<Student> ManyConditionToQuery(Student stu) {
		return studentDao.ManyConditionToQuery(stu);
	}

	@Override
	public void OneToMany(Teacher tea) {
		studentDao.OneToMany(tea);
	}

	@Override
	public void ManyToOne(Student stu) {
		studentDao.ManyToOne(stu);
	}

	@Override
	public List<Teacher> findTeacherAll(String collectionName) {
		return studentDao.findTeacherAll(collectionName);
	}

}
