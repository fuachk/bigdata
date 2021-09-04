package com.diandao.boss.app;

import com.diandao.boss.mapper.CustomerMapper;
import com.diandao.boss.mapper.CustomerReducer;
import com.diandao.boss.pojo.Customer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;

import java.io.IOException;

public class App {
    private String driverclass = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/boss?serverTimezone=UTC";
    private String username = "root";
    private String passwd = "483316";


    public App customerApp() throws InterruptedException, IOException, ClassNotFoundException {
        String sql = "select user_area ,count(1) count from customer group by user_area";
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(this.getClass());
        job.setJobName("customerApp");
        job.setMapperClass(CustomerMapper.class);
        job.setReducerClass(CustomerReducer.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Customer.class);
        job.setOutputKeyClass(Customer.class);
        job.setOutputValueClass(NullWritable.class);
        DBConfiguration.configureDB(job.getConfiguration(), driverclass, url, username, passwd);
        DBInputFormat.setInput(job, Customer.class, sql, "select count(1) from (select user_area ,count(1) count from customer group by user_area) aa");
        DBOutputFormat.setOutput(job, "customer", "user_area", "count");
        job.waitForCompletion(true);
        return this;
    }


}
