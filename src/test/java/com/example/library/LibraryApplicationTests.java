package com.example.library;

import com.example.library.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.example.library.user.mapper")
class LibraryApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll(){
        userMapper.selectList(null).forEach(System.out::println);
    }
}
