package com.copying.blogs.controller;

import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.service.CyCacheService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author fzz
 * @date 2022/8/25
 */

@Api("简历")
@Controller
public class ResumeController {

    @Resource
    private CyCacheService cyCacheService;

    /**
     * 显示简历页面
     * @return java.lang.String
     */
    @MyLog
    @GetMapping("/resume")
    public String friends(Model model){
        model.addAttribute("user",cyCacheService.getAdminInfo());
        return "resume";
    }
}
