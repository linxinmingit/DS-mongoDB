package com.yr.dao;

import java.util.List;
import java.util.Map;
/***
 * 
 * @ClassName:  StudentDao   
 * @Description:TODO 

 * @author Administrator
 * @date:   2018��11��21�� ����9:57:09   
 *   
 * @param <T> 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
public interface StudentDao<T> {
	// ���
	public void insert(T t);

	// ��Ӷ���
	public void insertMany(T t, String collectionName);

	// ��ѯһ��
	public T findOne(Map<String, Object> params, String collectionName);

	// ��ѯ����
	public List<T> findAll(String collectionName);

	// ɾ��
	public void remove(Map<String, Object> params, String collectionName);

	// �޸�
	public void update(Map<String, Object> params, String collectionName);

	// ��ҳ��ѯ
	public List<T> pageFind(Integer skip, Integer limit, String collectionName);

}
