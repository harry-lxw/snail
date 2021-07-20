package com.winter.mapper;

import com.winter.bean.Article;
import com.winter.bean.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 留言相关接口
 */
public interface MessageMapper {
    //获取留言列表
    List<Message> getMessageList(@Param(value="articleID")String articleID, @Param(value="messageID")String messageID);

    //添加留言
    Integer addMessage(Message message);

    //获取评论总条数
    Integer getMessageCount(@Param(value="articleID")String articleID);

    //删除留言
    Integer deleteMessage(@Param(value="messageID")String messageID);
}
