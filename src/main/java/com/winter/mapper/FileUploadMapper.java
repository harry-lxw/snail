package com.winter.mapper;


import com.winter.bean.FileUpload;

import java.util.List;

//主类已添加MapperScan(value="com.winter.mapper")将整个mapper包扫描到spring容器中
//@Mapper
public interface FileUploadMapper {
    //获取附件列表
    List<FileUpload> getFileList(FileUpload File);

    //添加附件
    Integer addFile(FileUpload File);
}
