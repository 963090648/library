package com.example.library.mapper;/**
 * @description:
 * @author: yubin
 * @time: 2023/2/15
 */

import com.example.library.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-15 18:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void findAll(){
        List<Users> userList = mapper.selectList(null);
        for (Users u : userList){
            System.out.printf("ssssssssss===="+u.toString());
        }
    }
}