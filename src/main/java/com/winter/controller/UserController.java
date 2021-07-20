package com.winter.controller;

import com.winter.bean.User;
import com.winter.mapper.UserMapper;
import com.winter.util.MD5SaltUtil;
import com.winter.util.MD5Util;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="snail/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value="/main")
    public String toMain(){
        return "main";
    }
    @RequestMapping(value="/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("getUserList")
    @ResponseBody
    public String getUserList(){
        User user = new User();
        List<User> userList = userMapper.getUserList(user);
        return JSONArray.fromObject(userList).toString();
    }

    /**
     * 验证登录信息
     * @return
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest request, @RequestParam Map<String,Object> map){
        String userid = (String)map.get("userid");
        String password = (String)map.get("password");
        password = MD5SaltUtil.decodeByXor(password);
        MD5Util md5 = new MD5Util();
        String enPassword = md5.encodePassword(password);

        User user = new User();
        user.setUserid(userid);
        List<User> resultList = userMapper.getUserList(user);
        if(resultList.size() > 0){
            User user1 = resultList.get(0);
            request.getSession().setAttribute("user", user1);
            String rightPwd = user1.getPassword();
            if(enPassword.equals(rightPwd)){
                return "success";
            }else{  //密码错误
                return "fail";
            }
        }else{      //账号不存在
            return "fail";
        }
    }

}
