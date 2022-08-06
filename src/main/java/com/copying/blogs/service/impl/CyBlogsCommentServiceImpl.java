package com.copying.blogs.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsCommentMapper;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.service.CyBlogsCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CyBlogsCommentServiceImpl extends ServiceImpl<CyBlogsCommentMapper, CyBlogsComment> implements CyBlogsCommentService {
    @Override
    public PageInfo<CyBlogsComment> getListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CyBlogsComment> list = this.list();
        return new PageInfo<>(list);
    }

    @Override
    public boolean setDeleted(Long commentId, boolean flag) {
        return this.update(new LambdaUpdateWrapper<CyBlogsComment>().eq(CyBlogsComment::getCommentId, commentId)
                .set(CyBlogsComment::getIsDelete, flag));
    }

}
