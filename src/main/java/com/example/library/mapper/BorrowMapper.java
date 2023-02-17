package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: yubin
 * @time: 2023/2/17
 */
@Mapper
@Component
public interface BorrowMapper extends BaseMapper<Borrow> {

    /**
     * 根据用户id和图书编码查询借阅信息
     * @param userId 用户id
     * @param bookId 图书id
     * @return
     */
    @Select({"SELECT * FROM borrow where `user_id` =#{userId} and `book_id` =#{bookId}  "})
    Borrow findBorrowByUserIdAndBookId(Long userId, Long bookId);

    /**
     * 根据用户id和图书编码查询借阅信息
     * @param userId 用户id
     * @param ret 图书id
     * @return
     */
    @Select({"SELECT * FROM borrow where `user_id` =#{userId} and `ret` =#{ret}  "})
    List<Borrow> findBorrowsByUserIdAndRet(Long userId,Integer ret);


    /**
     * 根据用户id和图书编码查询借阅信息
     * @param userId 用户id
     * @return
     */
    @Select({"SELECT * FROM borrow where `user_id` =#{userId}  "})
    List<Borrow> findAllBorrowByUserId(Long userId);
}
