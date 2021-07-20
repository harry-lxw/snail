package com.winter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.bean.Article;
import com.winter.bean.Log;
import com.winter.bean.User;
import com.winter.mapper.LogMapper;
import com.winter.util.NumUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="snail/log/")
public class LogController {

    @Autowired
    LogMapper logMapper;

    @RequestMapping("toLogList")
    public String toLogList(){
        return "log/logList";
    }

    @RequestMapping("getLogList")
    @ResponseBody
    public Map<String, Object> getLogList(@RequestParam Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<String, Object>();
        Log log = new Log();
        log.setContent((String)map.get("content"));
        log.setUsername((String)map.get("username"));

        //PageHelper分页工具
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        PageHelper.startPage(page, limit);
        List<Log> logList =  logMapper.getLogList(log);
        PageInfo<Log> pageInfo  = new PageInfo<Log>(logList);
        retMap.put("code", 0);
        retMap.put("msg", "操作成功");
        retMap.put("count", pageInfo.getTotal());
        retMap.put("data", JSONArray.fromObject(logList));
        return retMap;
    }


    @RequestMapping("addLog")
    @ResponseBody
    public Integer addLog(HttpServletRequest request, @RequestParam Map<String,Object> map){
        User user = (User) request.getSession().getAttribute("user");

        Log log = new Log();
        log.setLogID(NumUtil.getRandomNum());
        log.setOperationName((String)map.get("operationName"));
        log.setUsername(user.getUsername());
        Integer count = logMapper.addLog(log);
        return count;
    }
}
