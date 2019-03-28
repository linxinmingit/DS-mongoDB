package com.yr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yr.entry.Student;
import com.yr.service.StudentService;
/**
 * 
 * @ClassName:  StudentController   
 * @Description:TODO 

 * @author Administrator
 * @date:   2018��11��21�� ����9:56:50   
 *    
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
@Controller
public class StudentController {
	@Autowired
	private StudentService<Student> studentService;

	/**
	 * ���һ��
	 */
	//@Test
	@RequestMapping("/aa")
	public void insert() {
		Student student = new Student("1", "���", 18, "��", "����");
		studentService.insert(student);
		System.out.println("��ӳɹ�");
	}

	/**
	 * ��Ӷ���
	 */
	@RequestMapping("/bb")
	public void insertMany() {
		for (int i = 0; i <= 20; i++) {
			Student student = new Student();
			student.set_id("" + i);
			student.setName("������" + i);
			student.setAge(20 + i);
			student.setAddr("����" + i + "��");
			if (i % 2 == 0) {
				student.setSex("��");
			} else {
				student.setSex("Ů");
			}
			studentService.insertMany(student, "student");
		}
		System.out.println("��ӳɹ�");
	}

	/**
	 * ��ѯһ��
	 */
	//@Test
	@RequestMapping("/cc")
	public void findOne() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "1");
		Student student = studentService.findOne(params, "student");
		System.out.println(student);
	}

	/**
	 * ��ѯ����
	 */
	//@Test
	@RequestMapping("/dd")
	public void findAll() {
		List<Student> stuList = studentService.findAll("student");
		for (Student student : stuList) {
			System.out.println(student);
		}
	}

	/**
	 * ����idɾ��
	 */
	//@Test
	@RequestMapping("/ee")
	public void remove() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "0");
		studentService.remove(params, "student");
		System.out.println("ɾ���ɹ�");
	}

	/**
	 * ����id�޸�
	 */
	//@Test
	@RequestMapping("/ff")
	public void update() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "0");
		params.put("name", "�����ᶼ");
		params.put("age", 88);
		params.put("addr", "�౱");
		studentService.update(params, "student");
		System.out.println("�޸ĳɹ�");
	}

	/**
	 * ��ҳ��ѯ
	 */
	//@Test
	@RequestMapping("/gg")
	public void pageFind() {
		List<Student> stuList = new ArrayList<>();
		stuList = studentService.pageFind(2, 5, "student"); //����2����ȡ5����
		for (Student student : stuList) {
			System.out.println(student);
		}
	}

	/**
	 * main����
	 * 
	 * @param args
	 */
	@BeforeClass
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentController sc = (StudentController) ctx.getBean(StudentController.class);
		// sc.insert();
		// sc.findOne();
		// sc.insertMany();
		// sc.remove();
		// sc.update();
		// sc.findAll();
		sc.pageFind();
		ctx.close();
	}
}
