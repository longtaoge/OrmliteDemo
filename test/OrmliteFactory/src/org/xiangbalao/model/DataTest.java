package org.xiangbalao.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class DataTest {
	@Id
	@Column(unique = true, nullable = false)
	private String id;

	@Column
	private String name;

	@Column
	private String age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DataTest [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
