package com.diandao.boss.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ApiMapper {
    List<Map> getProduct();

    List<Map> getMap();

    int getLine(@Param("productname") String productname, @Param("month") String month);

    List<Map> getTop();
}
