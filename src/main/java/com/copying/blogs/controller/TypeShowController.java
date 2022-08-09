package com.copying.blogs.controller;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.model.entity.CyBlogsType;
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
public class TypeShowController {


    @Resource
    private CyCacheService cyCacheService;

    @MyLog
    @GetMapping("/types/{typeId}")
    public String types(@PathVariable Long typeId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<CyBlogsType> types = cyCacheService.getIndexTypes();
        if(CollectionUtils.isEmpty(types)){
            throw new CustomizeException("没有查询到分类信息");
        }
        if(typeId==-1){
            typeId =types.get(0).getTypeId();
        }
        model.addAttribute("types",types);
        model.addAttribute("page", cyCacheService.getPageByType(pageNum,typeId));
        model.addAttribute("activeTypeId",typeId);
        model.addAttribute("user", cyCacheService.getAdminInfo());

        return "types";

    }

}
