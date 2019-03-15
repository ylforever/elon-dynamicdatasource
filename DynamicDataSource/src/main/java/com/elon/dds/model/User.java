package com.elon.dds.model;

public class User
{
	private int userId = -1;

	private String name = "";
	
	private int age = -1;
	
	@Override
	public String toString()
	{
		return "name:" + name + "|age:" + age;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
}
