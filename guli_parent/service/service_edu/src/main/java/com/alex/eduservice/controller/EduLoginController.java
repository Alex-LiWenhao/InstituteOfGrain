package com.alex.eduservice.controller;

import com.alex.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EduLoginController
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/14 15:29
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
