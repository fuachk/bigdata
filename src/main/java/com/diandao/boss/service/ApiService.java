package com.diandao.boss.service;

import com.diandao.boss.dao.ApiMapper;
import com.diandao.boss.mapper.CustomerMapper;
import com.diandao.boss.mapper.CustomerReducer;
import com.diandao.boss.pojo.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;


@Service
@Transactional
public class ApiService {
    @Resource
    ApiMapper apiMapper;

    public List<Map> getProduct() {
        return apiMapper.getProduct();
    }

    public List<Map> getTop() {
        return apiMapper.getTop();
    }

    public List<Map> getMap() throws InterruptedException, IOException, ClassNotFoundException {
        return apiMapper.getMap();
    }

    public Map<String, Object> getLine() {
        Map<String, Object> map = new HashMap<>();
        List<Map> productList = apiMapper.getProduct();
        //获取产品名称
        List<String> productNames = new ArrayList<>();
        for (Map product : productList) {
            productNames.add((String) product.get("name"));
        }
        map.put("productNames", productNames);
        //获取日期
        List<String> monthList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int cmonth = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        for (int i = 5; i >= 0; i--) {
            int month = cmonth - i;
            String monthStr = String.valueOf(month);
            if (month < 10) {
                monthStr = "0" + monthStr;
            }
            monthList.add(year + monthStr);
        }
        map.put("months", monthList);
        //根据产品和日期获取对应数量
        List<Map> lines = new ArrayList<>();
        for (String productName : productNames) {
            Map<String, Object> line = new HashMap<>();
            List<Integer> lineList = new ArrayList<>();
            for (String month : monthList) {
                int cnt = apiMapper.getLine(productName, month);
//                System.out.println(productName + "/" + month + "/" + cnt);
                lineList.add(cnt);
            }
            line.put("name", productName);
            line.put("type", "line");
            line.put("stack", "总量");
            line.put("data", lineList);
            lines.add(line);
        }
        map.put("lines", lines);
        return map;
    }
}
