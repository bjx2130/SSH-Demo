package com.wrok.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用于测试验证框架使用的Bean类
 */
public class Pet {
	@NotNull(message="名字不能为空")  
	private String name;
	@Min(value=4,message="年龄最大不能查过120")
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
