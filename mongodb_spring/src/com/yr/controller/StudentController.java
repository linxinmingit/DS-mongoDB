package com.yr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.yr.entry.Student;
import com.yr.entry.Teacher;
import com.yr.service.StudentService;

/**
 * 学习网址： http://www.cnblogs.com/dennisit/p/3374297.html
 * @author zxy-un
 * 
 * 2018年8月17日 下午4:59:52
 */
@Controller
public class StudentController {
	
	@Autowired
	private StudentService<Student> studentService;

	/**
	 * 多条件查询
	 * https://blog.csdn.net/u014805893/article/details/71275399
	 * @author zxy-un
	 * 
	 * 下午8:24:10
	 */
	public void ManyConditionToQuery(){
		Student stu = new Student();
		List<Student> listStu = studentService.ManyConditionToQuery(stu);
		for (Student student : listStu) {
			System.out.println(student);
		}
	}
	
	/**
	 * 一对多
	 * @author zxy-un
	 * 
	 * 下午8:25:00
	 */
	public void OneToMany(){
		Student stu = new Student("1", "OneToMany", 12, "男", "哒哒");
		Student stu1 = new Student("2", "OneToMany2", 16, "女", "安卡卡");
		Set<Student> setStudent = new HashSet<>();
		setStudent.add(stu);
		setStudent.add(stu1);
		Teacher tea = new Teacher();
		tea.setTeacherName("懒懒");
		tea.setSetStudent(setStudent);
		studentService.OneToMany(tea);
	}
	
	/**
	 * 多对一
	 * @author zxy-un
	 * 
	 * 下午8:24:52
	 */
	public void ManyToOne(){
		
		Teacher tea = new Teacher();
		tea.setTeacherName("凯瑟");
		
		Student stu = new Student("1", "ManyToOne", 12, "男", "缇娜", tea);
		Student stu1 = new Student("2", "ManyToOne", 15, "女", "言语", tea);
		
		studentService.ManyToOne(stu);
		studentService.ManyToOne(stu1);
		
	}
	
	/**
	 * 添加单条数据(如果没有表,它将会自己帮你创建)
	 * @author zxy-un
	 * 
	 * 下午3:53:38
	 */
	public void insert() {
		Student student = new Student("1", "邹想云", 18, "女", "深圳");
		studentService.insert(student);
		System.out.println("添加成功");
	}

	/**
	 * 批量添加
	 * @author zxy-un
	 * 
	 * 下午3:53:52
	 */
	public void insertMany() { // 一千万 200G
		for (int i = 0; i <= 10; i++) {
			Student student = new Student();
			student.set_id("" + i);
			student.setName("胡萝卜" + i);
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
	 * 查询单值
	 * @author zxy-un
	 * 
	 * 下午3:54:04
	 */
	public void findOne() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "1");
		Student student = studentService.findOne(params, "student");
		System.out.println(student.toString());
	}

	/**
	 * 查询所有
	 * @author zxy-un
	 * 
	 * 下午3:54:56
	 */
	public void findAll() {
//		List<Student> stuList = studentService.findAll("student");
		List<Student> stuList = studentService.findAll("stuTea");
		for (Student student : stuList) {
			System.out.println(student);
		}
	}
	
	public void findTeacherAll() {
		List<Teacher> teaList = studentService.findTeacherAll("teacher");
		for (Teacher teacher : teaList) {
			System.out.println(teacher);
		}
	}

	/**
	 * 条件删除(根据_id)
	 * @author zxy-un
	 * 
	 * 下午3:55:05
	 */
	public void remove() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "5");
		studentService.remove(params, "student");
		System.out.println("删除成功");
	}

	/**
	 * 条件修改(根据_id)
	 * @author zxy-un
	 * 
	 * 下午3:55:24
	 */
	public void update() {
		Map<String, Object> params = new HashMap<>();
		params.put("_id", "0");
		params.put("name", "大张伟");
		params.put("age", 52);
		params.put("addr", "北京市朝阳区");
		studentService.update(params, "student");
		System.out.println("修改成功");
	}

	/**
	 * 分页查询
	 * @author zxy-un
	 * 
	 * 下午3:55:52
	 */
	public void pageFind() {
		List<Student> stuList = new ArrayList<>();
		stuList = studentService.pageFind(2, 5, "student");
		for (Student student : stuList) {
			System.out.println(student);
		}
	}

	/**
	 * main方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentController sc = (StudentController) ctx.getBean(StudentController.class);
//		sc.insert();
//		sc.insertMany();
//		sc.findOne();
		sc.findAll();
//		sc.findTeacherAll();
//		sc.remove();
//		sc.update();
//		sc.pageFind();
//		sc.ManyConditionToQuery();
//		sc.OneToMany();
//		sc.ManyToOne();
		ctx.close();
	}
}
