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
 * @date:   2018年11月21日 上午9:56:50   
 *    
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 */
@Controller
public class StudentController {
	@Autowired
	private StudentService<Student> studentService;

	/**
	 * 添加一条
	 */
	//@Test
	@RequestMapping("/aa")
	public void insert() {
		Student student = new Student("1", "洋葱", 18, "男", "深圳");
		studentService.insert(student);
		System.out.println("添加成功");
	}

	/**
	 * 添加多条
	 */
	@RequestMapping("/bb")
	public void insertMany() {
		for (int i = 0; i <= 20; i++) {
			Student student = new Student();
			student.set_id("" + i);
			student.setName("大胖胖" + i);
			student.setAge(20 + i);
			student.setAddr("深圳" + i + "区");
			if (i % 2 == 0) {
				student.setSex("男");
			} else {
				student.setSex("女");
			}
			studentService.insertMany(student, "student");
		}
		System.out.println("添加成功");
	}

	/**
	 * 查询一条
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
	 * 查询所有
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
	 * 根据id删除
	 */
	//@Test
	@RequestMapping("/ee")
	public void remove() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "0");
		studentService.remove(params, "student");
		System.out.println("删除成功");
	}

	/**
	 * 根据id修改
	 */
	//@Test
	@RequestMapping("/ff")
	public void update() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "0");
		params.put("name", "九门提都");
		params.put("age", 88);
		params.put("addr", "燕北");
		studentService.update(params, "student");
		System.out.println("修改成功");
	}

	/**
	 * 分页查询
	 */
	//@Test
	@RequestMapping("/gg")
	public void pageFind() {
		List<Student> stuList = new ArrayList<>();
		stuList = studentService.pageFind(2, 5, "student"); //跳过2条后，取5条；
		for (Student student : stuList) {
			System.out.println(student);
		}
	}

	/**
	 * main方法
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
