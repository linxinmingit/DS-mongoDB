package com.yr.entry;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
	private String teacherName;
	private Set<Student> setStudent = new HashSet<>();
	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Set<Student> getSetStudent() {
		return setStudent;
	}
	public void setSetStudent(Set<Student> setStudent) {
		this.setStudent = setStudent;
	}
	
	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + ", setStudent=" + setStudent + "]";
	}
	
}
