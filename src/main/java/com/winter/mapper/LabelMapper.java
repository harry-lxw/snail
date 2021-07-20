package com.winter.mapper;


import com.winter.bean.Article;
import com.winter.bean.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签相关接口
 */
public interface LabelMapper {
    //获取文章列表
    List<Label> getLabelList(Label label);

    //获取指定标签详情
    Label getLabelDetail(@Param(value = "labelID") String labelID);

    //修改/删除标签
    Integer updateLabel(@Param(value = "labelID") String labelID,
                        @Param(value = "labelName") String labelName,
                        @Param(value = "status") String status);

    //新增标签
    Integer addLabel(Label label);

}
