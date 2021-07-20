package com.winter.controller;

import com.winter.annotation.LogAnnotation;
import com.winter.bean.Article;
import com.winter.bean.Message;
import com.winter.mapper.ArticleMapper;
import com.winter.mapper.MessageMapper;
import com.winter.util.NumUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="snail/")
public class MessageController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("message/toMessageList")
    public String toMessageList(){
        return "message/messageList";
    }
    @RequestMapping("message/toMessageListAdmin")
    public String toMessageListAdmin(){
        return "message/messageListAdmin";
    }

    @RequestMapping("message/addMessage")
    @ResponseBody
    public Integer addMessage(@RequestParam Map<String,Object> map){
        String messageUsername = (String)map.get("messageUsername");
        String messageContent = (String)map.get("messageContent");
        String articleID = (String)map.get("articleID");
        String messageID = NumUtil.getRandomNum();

        Message message = new Message();
        message.setMessageID(messageID);
        message.setArticleID(articleID);
        message.setContent(messageContent);
        message.setMessageUserID("");
        message.setMessageUsername(messageUsername);
        message.setReplyUserID("");
        message.setReplyUsername("");
        message.setStatus("1");

        Integer count = messageMapper.addMessage(message);
        return count;
    }

    @RequestMapping("message/getMessageList")
    @ResponseBody
    public JSONObject getMessageList(@RequestParam Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<String, Object>();

        String articleID = (String)map.get("articleID");
        String messageID = (String)map.get("messageID");
        List<Message> messageList = messageMapper.getMessageList(articleID, messageID);
        retMap.put("messageList", JSONArray.fromObject(messageList));

        //获取留言条数
        int messageCount = messageMapper.getMessageCount(articleID);
        retMap.put("messageCount", messageCount);
        return JSONObject.fromObject(retMap);
    }

    @RequestMapping("message/deleteMessage")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public Integer deleteMessage(@RequestParam Map<String,Object> map){
        String messageID = (String)map.get("messageID");
        Integer count = messageMapper.deleteMessage(messageID);
        return count;
    }


}
