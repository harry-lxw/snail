package com.winter.controller;

import com.winter.bean.Article;
import com.winter.bean.User;
import com.winter.mapper.ArticleMapper;
import com.winter.mapper.UserMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="snail/admin/li/")
public class AdminController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(value="/main")
    public String toUserList(){
        return "admin";
    }

    @RequestMapping("getUserList")
    @ResponseBody
    public String getUserList(){
        User user = new User();
        List<User> userList = userMapper.getUserList(user);
        return JSONArray.fromObject(userList).toString();
    }




}
