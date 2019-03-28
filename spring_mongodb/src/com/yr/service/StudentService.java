package com.yr.service;

import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:  StudentService   
 * @Description:TODO 

 * @author Administrator
 * @date:   2018年11月21日 上午9:57:29   
 *   
 * @param <T> 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
public interface StudentService<T> {
	// 添加一条
	public void insert(T t);

	// 添加多条
	public void insertMany(T t, String collectionName);

	// 查询一条
	public T findOne(Map<String, Object> params, String collectionName);

	// 查询所有
	public List<T> findAll(String collectionName);

	// 删除
	public void remove(Map<String, Object> params, String collectionName);

	// 修改
	public void update(Map<String, Object> params, String collectionName);

	// 分页查询
	public List<T> pageFind(Integer skip, Integer limit, String collectionName);
}
