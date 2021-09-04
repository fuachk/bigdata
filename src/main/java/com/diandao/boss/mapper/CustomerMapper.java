package com.diandao.boss.mapper;

import com.diandao.boss.pojo.Customer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CustomerMapper extends Mapper<LongWritable, Customer, Text, IntWritable> {

        public void map(LongWritable key, Customer value, Context context) throws IOException, InterruptedException, IOException {
            String line = value.getUser_area() + "," + value.getCount();
            String[] arr = line.split(" ");
            for (String s : arr) {
                context.write(new Text(s), new IntWritable(1));
            }
        }

}
