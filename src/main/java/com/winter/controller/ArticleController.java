package com.winter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.annotation.LogAnnotation;
import com.winter.bean.Article;
import com.winter.bean.FileUpload;
import com.winter.bean.Message;
import com.winter.bean.User;
import com.winter.mapper.ArticleMapper;
import com.winter.mapper.FileUploadMapper;
import com.winter.mapper.MessageMapper;
import com.winter.util.NumUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="snail/article/")
public class ArticleController {

    @Value(value="${upload.fileRootPath}")
    private String fileRootPath;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private FileUploadMapper fileMapper;

    //跳转到首页
    @RequestMapping(value="toHome")
    public String toHome(@RequestParam Map<String,Object> map, Model model){
        return "article/home";
    }

    @RequestMapping(value="/article/toUpdateArticle")
    public String toUpdateArticle(@RequestParam Map<String,Object> map, Model model){
        String articleID = (String)map.get("articleID");
        List<Article> articleList = articleMapper.getArticleDetailByID(articleID);

        String fileID = "";
        if(articleList.size()>0){
            model.addAttribute("article", JSONObject.fromObject(articleList.get(0)));
            fileID = articleList.get(0).getFileID();
        }
        FileUpload file = new FileUpload();
        file.setFileID(fileID);
        List<FileUpload> fileList = fileMapper.getFileList(file);
        if(fileList.size()>0){
            model.addAttribute("file", JSONObject.fromObject(fileList.get(0)));
        }
        return "article/updateArticle";
    }

    @RequestMapping(value="/article/toAddArticle")
    public String toAddArticle(){
        return "article/addArticle";
    }

    //跳转到指定标签文章页面
    @RequestMapping(value="toLabelArticle")
    public String toLabelArticle(@RequestParam Map<String,Object> map, Model model){
        String labelID = (String)map.get("labelID");
        String labelName = (String)map.get("labelName");
        model.addAttribute("labelID",labelID);
        model.addAttribute("labelName",labelName);
        return "article/labelArticle";
    }

    //跳转到指定标签文章子页面
    @RequestMapping(value="toLabelArticleList")
    public String toLabelArticleList(@RequestParam Map<String,Object> map, Model model){
        String labelID = (String)map.get("labelID");
        String labelName = (String)map.get("labelName");
        model.addAttribute("labelID",labelID);
        model.addAttribute("labelName",labelName);
        return "article/labelArticleList";
    }

    //跳转到管理版首页
    @RequestMapping(value="toAdminHome")
    public String toAdminHome(@RequestParam Map<String,Object> map, Model model){
        return "article/adminHome";
    }

    //跳转到文章详情页
    @RequestMapping(value="toArticleDetail")
    public String toArticleDetail(@RequestParam Map<String,Object> map, Model model){
        Map<String, Object> retMap = new HashMap<String, Object>();
        String articleID = (String)map.get("articleID");
        String messageID = (String)map.get("messageID");
//        model.addAttribute("articleID", articleID);
        //获取文章信息
        List<Article> articleList = articleMapper.getArticleDetailByID(articleID);
        model.addAttribute("article", JSONObject.fromObject(articleList.get(0)));
        //获取文章留言信息
        List<Message> messageList = messageMapper.getMessageList(articleID,messageID);
        retMap.put("messageList", JSONArray.fromObject(messageList));
        model.addAttribute("messageList", JSONObject.fromObject(retMap.toString()));
        //获取留言条数
        int messageCount = messageMapper.getMessageCount(articleID);
        model.addAttribute("messageCount", messageCount);
        //浏览量+1
        articleMapper.updateArticlePageViewCount(articleID);
        return "article/articleDetail";
    }

    @RequestMapping("getArticleList")
    @ResponseBody
    public Map<String, Object> getArticleList(@RequestParam Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<String, Object>();
        Article article = new Article();
        article.setTitle((String)map.get("title"));
        article.setContent((String)map.get("content"));
        article.setLabelID((String)map.get("labelID"));
        article.setHomePageStatus((String)map.get("homePageStatus"));
        article.setRecommendStatus((String)map.get("recommendStatus"));

        //PageHelper分页工具
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        PageHelper.startPage(page, limit);
        List<Article> articleList = articleMapper.getArticleList(article);

        PageInfo<Article> pageInfo  = new PageInfo<Article>(articleList);
        retMap.put("code", 0);
        retMap.put("msg", "操作成功");
        retMap.put("count", pageInfo.getTotal());
        retMap.put("data", JSONArray.fromObject(articleList));
        return retMap;
    }

    @RequestMapping("getArticleListByStatus")
    @ResponseBody
    public String getArticleListByStatus(@RequestParam Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<String, Object>();

        String recommendStatus = (String)map.get("recommendStatus");
        String homePageStatus = (String)map.get("homePageStatus");
        String labelID = (String)map.get("labelID");
        String pageStr = (String)map.get("page");
        String limitNumStr = (String)map.get("limitNum");
        int limitNum = 0;
        if(!"".equals(limitNumStr) && limitNumStr != null){
            limitNum = Integer.valueOf(limitNumStr);
        }
        int page = 0;
        if(!"".equals(pageStr) && pageStr != null){
            page = Integer.valueOf(pageStr);
            if(page>0){
                page = (page-1)*limitNum;
            }
        }
        Integer count = articleMapper.getArticleListCount(labelID);
        List<Article> articleList = articleMapper.getArticleListByStatus(recommendStatus, homePageStatus, labelID, page, limitNum);
        retMap.put("homePageArticleList", JSONArray.fromObject(articleList));
        retMap.put("count", count);
        return JSONObject.fromObject(retMap).toString();
    }

    @RequestMapping("deleteArticle")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public int deleteArticle(@RequestParam Map<String,Object> map){
        String articleID = (String)map.get("articleID");
        int count = articleMapper.deleteArticle(articleID);
        return count;
    }

    /**
     * 添加文章
     * @param map
     * @return
     */
    @RequestMapping("addArticle")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public Integer addArticle(@RequestParam Map<String,Object> map, HttpServletRequest request){
        Article article = new Article();
        article.setArticleID(NumUtil.getRandomNum());
        article.setTitle((String)map.get("title"));
        article.setRemark((String)map.get("remark"));
        article.setHomePageStatus((String)map.get("homePageStatus"));
        article.setRecommendStatus((String)map.get("recommendStatus"));
        article.setLabelID((String)map.get("labelID"));
        article.setContent((String)map.get("content"));
        article.setPageView(0);
        article.setSupportCount(0);
        article.setStatus("1");
        String filePath = (String) map.get("filePath");

        String fileID = NumUtil.getRandomNum();
        //添加附件
        User user = (User) request.getSession().getAttribute("user");
        FileUpload file = new FileUpload();
        file.setFileID(fileID);
        file.setFileName((String) map.get("fileName"));
        file.setFilePath(filePath);
        file.setUsername(user.getUsername());
        fileMapper.addFile(file);

        article.setFileID(fileID);
        //添加文章
        Integer result = articleMapper.addArticle(article);
        return result;
    }

    /**
     * 修改文章
     * @param map
     * @return
     */
    @RequestMapping("updateArticle")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public Integer updateArticle(@RequestParam Map<String,Object> map){
        Article article = new Article();
        article.setArticleID((String)map.get("articleID"));
        article.setTitle((String)map.get("title"));
        article.setRemark((String)map.get("remark"));
        article.setHomePageStatus((String)map.get("homePageStatus"));
        article.setRecommendStatus((String)map.get("recommendStatus"));
        article.setContent((String)map.get("content"));

        Integer result = articleMapper.updateArticle(article);
        return result;
    }

    /**
     * 上传附件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "article/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject retObj = new JSONObject();
        String imgUrl = "";
        String showImgUrl = "";
        try {
            String fileUrl = fileRootPath;
            Date dateTime = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHH");
            String currentDate = sdf1.format(dateTime);
            fileUrl = fileUrl + currentDate;

            File dir = new File(fileUrl);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            imgUrl = fileUrl +"\\"+ fileName;
            File upload_file = new File(imgUrl);
            file.transferTo(upload_file);

            showImgUrl = "/snail/"+currentDate+"/"+fileName;
            retObj.put("filePath", showImgUrl);
            retObj.put("fileName", fileName);
        } catch (Exception e) {
            e.printStackTrace();
            retObj.put("code", 1);
            retObj.put("msg", "上传失败");
            return retObj;
        }
        retObj.put("code", 0);
        retObj.put("msg", "上传成功");
        return retObj;
    }

    /**
     * 修改文章点赞量+1
     * @param map
     * @return
     */
    @RequestMapping(value = "updateArticleSupportCount")
    @ResponseBody
    public Integer updateArticleSupportCount(@RequestParam Map<String,Object> map){
        String articleID = (String)map.get("articleID");
        return articleMapper.updateArticleSupportCount(articleID);
    }

    /**
     * 修改文章浏览量+1
     * @param map
     * @return
     */
    @RequestMapping(value = "updateArticlePageViewCount")
    @ResponseBody
    public Integer updateArticlePageViewCount(@RequestParam Map<String,Object> map){
        String articleID = (String)map.get("articleID");
        return articleMapper.updateArticlePageViewCount(articleID);
    }
}
