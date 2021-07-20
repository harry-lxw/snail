package com.winter.bean;

public class Log {
    private String logID;
    private String operationName;
    private String username;
    private String createTime;
    private String content;

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logID='" + logID + '\'' +
                ", operationName='" + operationName + '\'' +
                ", username='" + username + '\'' +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
