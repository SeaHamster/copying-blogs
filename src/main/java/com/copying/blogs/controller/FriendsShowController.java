package com.copying.blogs.controller;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.service.CyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class FriendsShowController {


    @Resource
    private CyCacheService cyCacheService;

    @MyLog
    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",cyCacheService.getIndexFriends());
        model.addAttribute("user",cyCacheService.getAdminInfo());
        return "friends";

    }

}
