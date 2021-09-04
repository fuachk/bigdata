package com.diandao.boss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class Request {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String defaultPage(@PathVariable String page, HttpSession session) {
        return page;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(HttpSession session) {
        return "index";
    }
}
