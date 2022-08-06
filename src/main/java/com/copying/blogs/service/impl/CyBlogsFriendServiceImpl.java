package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsFriendMapper;
import com.copying.blogs.model.entity.CyBlogsFriend;
import com.copying.blogs.service.CyBlogsFriendService;
import org.springframework.stereotype.Service;

/**
 * CyBlogsFriendServiceImpl :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Service
public class CyBlogsFriendServiceImpl extends ServiceImpl<CyBlogsFriendMapper, CyBlogsFriend> implements CyBlogsFriendService {
}
