package com.winter.mapper;

import com.winter.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//主类已添加MapperScan(value="com.winter.mapper")将整个mapper包扫描到spring容器中
//@Mapper
public interface UserMapper {
    //获取用户列表
    public List<User> getUserList(User user);
}
