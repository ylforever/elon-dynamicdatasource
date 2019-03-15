package com.elon.dds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elon.dds.model.User;

/**
 * Mybatis映射接口定义。
 * 
 * @author elon
 * @version 2018年2月26日
 */
@Mapper
public interface UserMapper
{
	/**
	 * 查询所有用户数据
	 * @return 用户数据列表
	 */
	@Results(value= {
			@Result(property="userId", column="id"),
			@Result(property="name", column="name"),
			@Result(property="age", column="age")
	})
	@Select("select id, name, age from tbl_user")
	List<User> getUsers();
} 
