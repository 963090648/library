package com.example.library.service;
/**
 * @description:
 * @author: yubin
 * @time: 2023/2/17
 */

import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.entity.Users;
import com.example.library.mapper.BorrowMapper;
import com.example.library.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-17 19:36
 */
@Service
public class BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    /**
     * 添加
     * （添加事物）
     */
    @Transactional
    public Integer addBorrow(Borrow borrow) {
        Book book = bookService.findBook(borrow.getBookId());
        Users users = userService.findUserById(borrow.getUserId());

        // 查询是否已经借阅过该图书
        Borrow bor = findBorrowByUserIdAndBookId(users.getId(),book.getId());
        if (bor!=null) {
            Integer ret = bor.getRet();
            if (ret!=null) {
                // 已借阅, 未归还 不可再借
                if (ret == Constants.NO) {
                    return Constants.BOOK_BORROWED;
                }
            }
        }

        // 库存数量减一
        int size = book.getSize();
        if (size>0) {
            size--;
            book.setSize(size);
            bookService.updateBook(book);
        }else {
            return Constants.BOOK_SIZE_NOT_ENOUGH;
        }

        // 用户可借数量减一
        int userSize = users.getSize();
        if (userSize>0) {
            userSize --;
            users.setSize(userSize);
            userService.updateUser(users);
        }else {
            return Constants.USER_SIZE_NOT_ENOUGH;
        }


        // 添加借阅信息, 借阅默认为未归还状态
        borrow.setRet(Constants.NO);
        borrowMapper.insertBorrow(borrow);

        // 一切正常
        return Constants.OK;
    }

    /**
     * user id查询所有借阅信息
     */
    public List<Borrow> findAllBorrowByUserId(Long userId) {
        return borrowMapper.findAllBorrowByUserId(userId);
    }

    /**
     * user id查询所有 已借阅信息
     */
    public List<Borrow> findBorrowsByUserIdAndRet(Long userId, Integer ret) {
        return borrowMapper.findBorrowsByUserIdAndRet(userId,ret);
    }


    /**
     * 详情
     */
    public Borrow findById(Long id) {
        Borrow borrow = borrowMapper.selectById(id);
        if (null!=borrow) {
            return borrow;
        }
        return null;
    }

    /**
     * 编辑
     */
    public boolean updateBorrow(Borrow borrow) {
        return borrowMapper.updateById(borrow)>0;
    }


    /**
     * 编辑
     */
    public Boolean updateBorrowByRepo(Borrow borrow) {
        return borrowMapper.insert(borrow)>0;
    }

    /**
     * s删除
     */
    public void deleteBorrow(Long id) {
        borrowMapper.deleteById(id);
    }

    /**
     * 查询用户某一条借阅信息
     * @param userId 用户id
     * @param bookId 图书id
     */
    public Borrow findBorrowByUserIdAndBookId(Long userId,Long bookId) {
        return borrowMapper.findBorrowByUserIdAndBookId(userId,bookId);
    }

    public List<Borrow> findAllBorrowByBookId(Long bookId){
        return borrowMapper.findAllBorrowByBookId(bookId);
    }

    /**
     * 归还书籍, 使用事务保证 ACID
     * @param userId 用户Id
     * @param bookId 书籍id
     */
    @Transactional(rollbackFor = Exception.class)
    public void retBook(Long userId,Long bookId) {
        // 用户可借数量加1
        Users user = userService.findUserById(userId);
        Integer size = user.getSize();
        size++;
        user.setSize(size);
        userService.updateUser(user);


        // 书籍库存加1
        Book book = bookService.findBook(bookId);
        Integer bookSize = book.getSize();
        bookSize++;
        book.setSize(bookSize);
        bookService.updateBook(book);
        // 借阅记录改为已归还,删除记录
        Borrow borrow = this.findBorrowByUserIdAndBookId(userId, bookId);
//        borrow.setRet(Constants.YES);
//        borrow.setUpdateTime(new Date());
//        borrowMapper.updateBor(BeanUtil.beanToMap(borrow))>0;
        this.deleteBorrow(borrow.getId());
    }
}