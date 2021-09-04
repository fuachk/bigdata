package com.diandao.boss;

import com.diandao.boss.app.App;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@MapperScan("com.diandao.boss.dao")
public class BossApplication {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, URISyntaxException, IOException {
        System.setProperty("hadoop.home.dir", "D:\\java\\hadoop-2.9.2");
        App app = new App();
        app.customerApp();
        SpringApplication.run(BossApplication.class, args);
    }

}
