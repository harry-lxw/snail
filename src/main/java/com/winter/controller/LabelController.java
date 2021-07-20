package com.winter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.annotation.LogAnnotation;
import com.winter.bean.Article;
import com.winter.bean.Label;
import com.winter.bean.Message;
import com.winter.mapper.ArticleMapper;
import com.winter.mapper.LabelMapper;
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
@RequestMapping(value="snail/label/")
public class LabelController {

    @Autowired
    private LabelMapper labelMapper;

    @RequestMapping(value="toLabelListAdmin")
    public String toLabelListAdmin(){
        return "label/labelList";
    }

    @RequestMapping(value="toAddLabel")
    public String toAddLabel(){
        return "label/addLabel";
    }

    @RequestMapping(value="toHomeLabel")
    public String toHomeLabel(){
        return "label/homeLabel";
    }

    @RequestMapping(value="toUpdateLabel")
    public String toUpdateLabel(@RequestParam Map<String,Object> map, Model model){
        String labelID = (String)map.get("labelID");
        Label label = labelMapper.getLabelDetail(labelID);
        model.addAttribute("label", label);
        return "label/updateLabel";
    }

    /**
     * 获取标签列表
     * @param map
     * @return
     */
    @RequestMapping("getLabelList")
    @ResponseBody
    public Map<String, Object> getLabelList(@RequestParam Map<String,Object> map){
        Map<String, Object> retMap = new HashMap<String, Object>();
        Label label = new Label();
        label.setLabelName((String)map.get("labelName"));
        label.setStatus((String)map.get("status"));

        //PageHelper分页工具
        String pageStr = (String)map.get("page");
        String limitStr = (String)map.get("limit");
        Integer page = 0;
        Integer limit = 999;
        if(!"".equals(pageStr) && pageStr != null){
            page = Integer.parseInt((String)map.get("page"));
        }
        if(!"".equals(limitStr) && limitStr != null){
            limit = Integer.parseInt((String)map.get("limit"));
        }
        PageHelper.startPage(page, limit);
        List<Label> labelList = labelMapper.getLabelList(label);
        PageInfo<Label> pageInfo = new PageInfo<Label>(labelList);
        retMap.put("code", 0);
        retMap.put("msg", "操作成功");
        retMap.put("count", pageInfo.getTotal());   //获取列表数量
        retMap.put("data", JSONArray.fromObject(labelList));
        return retMap;
    }

    /**
     * 删除标签
     * @param map
     * @return
     */
    @RequestMapping("deleteLabel")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public int deleteLabel(@RequestParam Map<String,Object> map){
        String labelID = (String)map.get("labelID");
        int count = labelMapper.updateLabel(labelID, "", "99");
        return count;
    }

    /**
     * 修改标签
     * @param map
     * @return
     */
    @RequestMapping("updateLabel")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public int updateLabel(@RequestParam Map<String,Object> map){
        String labelID = (String)map.get("labelID");
        String labelName = (String)map.get("labelName");
        int count = labelMapper.updateLabel(labelID, labelName, "");
        return count;
    }

    /**
     * 添加标签
     * @param map
     * @return
     */
    @RequestMapping("addLabel")
    @ResponseBody
    @LogAnnotation      //AOP记录日志注解
    public Integer addLabel(@RequestParam Map<String,Object> map){
        Label label = new Label();
        label.setLabelID(NumUtil.getRandomNum());
        label.setLabelName((String)map.get("labelName"));
        label.setStatus("1");

        Integer result = labelMapper.addLabel(label);
        return result;
    }

}
