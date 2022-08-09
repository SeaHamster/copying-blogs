package com.copying.blogs.controller;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.service.CyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;


@Controller
public class ArchiveShowController {

    @Resource
    private CyCacheService cyCacheService;


    @MyLog
    @GetMapping("/archives")
    public String archivesByYearAndMonth(Model model){
        model.addAttribute("timeLineMap",cyCacheService.findTimeLine());
        model.addAttribute("user",cyCacheService.getAdminInfo());
        return "archives";
    }


}
