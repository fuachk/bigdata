package com.diandao.boss.controller;

import com.diandao.boss.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class Api {
    @Autowired
    ApiService apiService;

    @RequestMapping(value = "/api/getProduct", method = RequestMethod.POST)
    public Map<String, Object> getProduct() {
        Response response = new Response();
        response.setCode(0).setMsg("success").setData(apiService.getProduct());
        return response.doAnswer();
    }

    @PostMapping(value = "/api/getTop")
    public Map<String, Object> getTop() {
        Response response = new Response();
        response.setCode(0).setMsg("success").setData(apiService.getTop());
        return response.doAnswer();
    }

    @PostMapping(value = "/api/getMap")
    public Map<String, Object> getMap() throws InterruptedException, IOException, ClassNotFoundException {
        Response response = new Response();
        response.setCode(0).setMsg("success").setData(apiService.getMap());
        return response.doAnswer();
    }

    @PostMapping(value = "/api/getLine")
    public Map<String, Object> getLine() {
        Response response = new Response();
        response.setCode(0).setMsg("success").setData(apiService.getLine());
        return response.doAnswer();
    }
}
