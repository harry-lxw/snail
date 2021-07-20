package com.winter.bean;

public class Article {
    private String articleID;
    private String labelID;             //标签ID
    private String title;               //标题
    private String content;             //正文
    private String remark;             //正文
    private String fileID;              //图片ID
    private Integer pageView;           //浏览量
    private Integer supportCount;       //点赞量
    private String createTime;          //创建时间
    private String homePageStatus;      //是否在首页展示	0-不展示 1-展示
    private String recommendStatus;     //是否推荐文章	0-不推荐 1-推荐
    private String status;              //状态 1-展示	99-删除
    private Integer limitNum;           //获取条数
    private Integer page;               //获取页数
    private Integer num;                //获取每页条数
    private String filePath;            //附件路径
    private String fileName;            //附件名称
    private String labelName;           //标签名称


    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHomePageStatus() {
        return homePageStatus;
    }

    public void setHomePageStatus(String homePageStatus) {
        this.homePageStatus = homePageStatus;
    }

    public String getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(String recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public String getStatus() {
        return status;
    }

    public Integer getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleID='" + articleID + '\'' +
                ", labelID='" + labelID + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", fileID='" + fileID + '\'' +
                ", pageView=" + pageView +
                ", supportCount=" + supportCount +
                ", createTime='" + createTime + '\'' +
                ", homePageStatus='" + homePageStatus + '\'' +
                ", recommendStatus='" + recommendStatus + '\'' +
                ", status='" + status + '\'' +
                ", limitNum=" + limitNum +
                ", page=" + page +
                ", num=" + num +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", labelName='" + labelName + '\'' +
                '}';
    }
}
