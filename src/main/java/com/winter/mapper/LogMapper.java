package com.winter.mapper;

import com.winter.bean.Log;
import com.winter.bean.User;

import java.util.List;

public interface LogMapper {
    //获取日志列表
    List<Log> getLogList(Log log);

    Integer addLog(Log log);

}
