package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @description:
 * @author: yubin
 * @time: 2023/2/15
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<Users> {

    @Select({"<script>",
            "SELECT * FROM users ",
            "<where>",
            "<if test = 'username != null' >username = #{username}</if>",
            "</where>",
            "</script>"})
    Users findByUsername(String username);


    @Select({"SELECT * FROM users where username like CONCAT('%',#{keyword},'%') or `nickname` like CONCAT('%',#{keyword},'%') "})
    IPage<Users> findListByLike(Page<Users> page,String keyword);

}
