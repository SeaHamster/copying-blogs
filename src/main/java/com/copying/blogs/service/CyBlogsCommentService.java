package com.copying.blogs.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.github.pagehelper.PageInfo;

public interface CyBlogsCommentService extends IService<CyBlogsComment> {
    PageInfo<CyBlogsComment> getListByPage(Integer pageNum, Integer pageSize);
    boolean setDeleted(Long commentId, boolean flag);

}
