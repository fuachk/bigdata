package com.diandao.boss.mapper;

import com.diandao.boss.pojo.Customer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CustomerReducer extends Reducer<IntWritable, Customer, Customer, NullWritable> {

    protected void reduce(IntWritable key, Iterable<Customer> values, Context context)
            throws IOException, InterruptedException {
        int count = 0;
        Customer customer = new Customer();
        customer.setUser_area(key.get() + "");
        customer.setCount(count);
        context.write(customer, NullWritable.get());
    }

}
