package com.winter.aop;

import com.winter.bean.*;
import com.winter.mapper.ArticleMapper;
import com.winter.mapper.LabelMapper;
import com.winter.mapper.LogMapper;
import com.winter.mapper.MessageMapper;
import com.winter.util.NumUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class LogAdvice {

    @Autowired
    LogMapper  logMapper;
    @Autowired
    ArticleMapper articleMappper;
    @Autowired
    LabelMapper labelMappper;
    @Autowired
    MessageMapper messageMappper;


    @Pointcut("@annotation(com.winter.annotation.LogAnnotation)")
    private void logAdvicePointcut(){}

    @Before("logAdvicePointcut()")
    private void addArticleLog(JoinPoint point){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        User user = (User)session.getAttribute("user");

        Object[] args = point.getArgs();
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.stream(args);
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        //过滤后序列化无异常
//        String string = JSONArray.fromObject(logArgs).toString();


        //拦截的方法
        String methodName = point.getSignature().getName();
        //拦截的方法参数
//        Object[] args = point.getArgs();
        String content = "";         //操作内容
        String operationName = "";   //操作人
        if("addArticle".equals(methodName)){
            operationName = "添加文章";
            content += "操作后数据："+ JSONArray.fromObject(logArgs.get(0)).toString();
        }else if("updateArticle".equals(methodName)){
            operationName = "修改文章";
            JSONObject article = JSONObject.fromObject(logArgs.get(0));
            String articleID = article.getString("articleID");
            List<Article> articleList = articleMappper.getArticleDetailByID(articleID);

            if(articleList.size()>0){
                content += "原数据："+ articleList.toString()+"\n\n";
            }
            content += "操作后数据："+ JSONArray.fromObject(logArgs).toString();
        }else if("deleteArticle".equals(methodName)){
            operationName = "删除文章";
            JSONObject article = JSONObject.fromObject(logArgs.get(0));
            String articleID = article.getString("articleID");
            List<Article> articleList = articleMappper.getArticleDetailByID(articleID);

            if(articleList.size()>0){
                content += "原数据："+ articleList.toString();
            }
        }else if("addLabel".equals(methodName)){
            operationName = "添加标签";
            content += "操作后数据："+ JSONArray.fromObject(logArgs).toString();
        }else if("updateLabel".equals(methodName)){
            operationName = "修改标签";
            JSONObject labelObj = JSONObject.fromObject(logArgs.get(0));
            String labelID = labelObj.getString("labelID");
            Label label = labelMappper.getLabelDetail(labelID);

            if(label != null){
                content += "原数据："+ label.toString()+"\n\n";
            }
            content += "操作后数据："+ JSONArray.fromObject(logArgs).toString();
        }else if("deleteLabel".equals(methodName)){
            operationName = "删除标签";
            JSONObject labelObj = JSONObject.fromObject(logArgs.get(0));
            String labelID = labelObj.getString("labelID");
            Label label = labelMappper.getLabelDetail(labelID);

            if(label != null){
                content += "原数据："+ label.toString();
            }
        }else if("deleteMessage".equals(methodName)){
            operationName = "删除留言";
            JSONObject messageObj = JSONObject.fromObject(logArgs.get(0));
            String messageID = messageObj.getString("messageID");
            List<Message> messageList = messageMappper.getMessageList("", messageID);

            if(messageList.size()>0){
                content += "原数据："+ messageList.toString();
            }
        }

        Log log = new Log();
        log.setLogID(NumUtil.getRandomNum());
        log.setOperationName(operationName);
        log.setUsername(user.getUsername());
        log.setContent(content);

        logMapper.addLog(log);

        System.out.printf("进入记录");
    }
}
