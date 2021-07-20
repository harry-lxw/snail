package com.winter.bean;

public class Message {
    private String messageID;
    private String messageUserID;       //留言人ID
    private String messageUsername;     //留言人姓名
    private String replyUserID;         //回复人ID
    private String replyUsername;       //回复人姓名
    private String content;             //留言内容
    private String createTime;          //留言时间
    private String status;              //状态    1-展示	99-删除
    private String articleID;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessageUserID() {
        return messageUserID;
    }

    public void setMessageUserID(String messageUserID) {
        this.messageUserID = messageUserID;
    }

    public String getMessageUsername() {
        return messageUsername;
    }

    public void setMessageUsername(String messageUsername) {
        this.messageUsername = messageUsername;
    }

    public String getReplyUserID() {
        return replyUserID;
    }

    public void setReplyUserID(String replyUserID) {
        this.replyUserID = replyUserID;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID='" + messageID + '\'' +
                ", messageUserID='" + messageUserID + '\'' +
                ", messageUsername='" + messageUsername + '\'' +
                ", replyUserID='" + replyUserID + '\'' +
                ", replyUsername='" + replyUsername + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", articleID='" + articleID + '\'' +
                '}';
    }
}
