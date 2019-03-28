package com.yr.entry;

import java.io.Serializable;

public class Student implements Serializable {
	private String _id;
	private String name;
	private Integer age;
	private String sex;
	private Teacher teacher;

	@Override
	public String toString() {
		return "Student [_id=" + _id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", teacher=" + teacher
				+ ", addr=" + addr + "]";
	}

	private String addr;

	public Student() {
	}

	public Student(String _id, String name, Integer age, String sex, String addr) {
		this._id = _id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.addr = addr;
	}

	public Student(String _id, String name, Integer age, String sex, String addr, Teacher teacher) {
		super();
		this._id = _id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.teacher = teacher;
		this.addr = addr;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
