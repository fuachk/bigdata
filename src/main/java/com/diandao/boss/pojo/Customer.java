package com.diandao.boss.pojo;

import lombok.Data;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Customer implements DBWritable {
    private String user_area;
    private int count;


    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        user_area = resultSet.getString("user_area");
        count = resultSet.getInt("count");
    }
}
