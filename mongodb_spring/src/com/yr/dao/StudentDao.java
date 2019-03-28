package com.yr.dao;

import java.util.List;
import java.util.Map;

import com.yr.entry.Student;
import com.yr.entry.Teacher;

public interface StudentDao<T> {
	// 添加
	public void insert(T t);

	// 添加多条
	public void insertMany(T t, String collectionName);

	// 查询一条
	public T findOne(Map<String, Object> params, String collectionName);

	// 查询所有
	public List<T> findAll(String collectionName);
	
	// 查询所有老师
	public List<Teacher> findTeacherAll(String collectionName);

	// 删除
	public void remove(Map<String, Object> params, String collectionName);

	// 修改
	public void update(Map<String, Object> params, String collectionName);

	// 分页查询
	public List<T> pageFind(Integer skip, Integer limit, String collectionName);
	
	// 多条件查询
	public List<T> ManyConditionToQuery(Student stu);
	
	public void OneToMany(Teacher tea);

	public void ManyToOne(Student stu);
}
