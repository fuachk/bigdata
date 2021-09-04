package com.diandao.boss.controller;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response() {
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 返回json数据
     *
     * @return Map
     */
    public Map<String, Object> doAnswer() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", this.code);
        map.put("msg", this.msg);
        if (this.data == null) {
            map.put("data", null);
        } else {
            map.put("data", this.data);
        }
        return map;
    }
}
