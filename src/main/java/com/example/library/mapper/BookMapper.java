package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yubin
 * @time: 2023/2/17
 */
@Mapper
@Component
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 模糊分页查询用户
     * @param keyword 关键字
     * @return
     */
    @Select({"SELECT * FROM book where name like CONCAT('%',#{keyword},'%') or `isbn` like CONCAT('%',#{keyword},'%') "})
    IPage<Book> findBookListByLike(Page<Book> page, String keyword);

    /**
     * 根据ISBN查询
     * @param isbn 关键字
     * @return
     */
    @Select({"SELECT * FROM book where  `isbn` =#{keyword} "})
    Book findByIsbn(String isbn);

    @Select({"SELECT * FROM book where  `id` =#{id} "})
    Book selectKeyId(Long id);
}
