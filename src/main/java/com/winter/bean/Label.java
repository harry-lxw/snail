package com.winter.bean;

public class Label {
    private String labelID;             //标签ID
    private String labelName;               //标题
    private String createTime;          //创建时间
    private String status;              //状态 1-展示	99-删除
    private Integer page;               //获取页数
    private Integer num;                //获取每页条数

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelID='" + labelID + '\'' +
                ", labelName='" + labelName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", page=" + page +
                ", num=" + num +
                '}';
    }
}
