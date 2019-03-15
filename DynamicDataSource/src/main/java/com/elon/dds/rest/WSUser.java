package com.elon.dds.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elon.dds.datasource.DBIdentifier;
import com.elon.dds.mapper.UserMapper;
import com.elon.dds.model.User;

/**
 * 用户数据访问接口。
 * 
 * @author elon
 * @version 2018年2月26日
 */
@RestController
@RequestMapping(value="/user")
public class WSUser {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询项目中所有用户信息
	 * 
	 * @param projectCode 项目编码
	 * @return 用户列表
	 */
	@RequestMapping(value="/v1/users", method=RequestMethod.GET)
	public List<User> queryUser(@RequestParam(value="projectCode", required=true) String projectCode) 
	{
		DBIdentifier.setProjectCode(projectCode);
		return userMapper.getUsers();
	}
}
