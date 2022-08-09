package com.copying.blogs.controller;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.model.entity.CyTag;
import com.copying.blogs.service.CyCacheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {


    @Resource
    private CyCacheService cyCacheService;

    @MyLog
    @GetMapping("/tags/{tagId}")
    public String tags(@PathVariable Long tagId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<CyTag> tags = cyCacheService.getIndexTags();
        if(CollectionUtils.isEmpty(tags)){
            throw new CustomizeException("未查询到标签信息");
        }
        if(tagId==-1){
            tagId = tags.get(0).getTagId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page", cyCacheService.getPageByTag(pageNum,tagId));
        model.addAttribute("activeTagId",tagId);
        model.addAttribute("user", cyCacheService.getAdminInfo());
        return "tags";
    }


}
