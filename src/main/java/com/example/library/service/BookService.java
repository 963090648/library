package com.example.library.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.vo.BookOut;
import com.example.library.vo.PageIn;
import com.example.library.vo.PageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-17 18:49
 */
@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;


    /**
     * 添加用户
     * @param book 图书
     * @return 返回添加的图书
     */
    public Boolean addBook(Book book) {
        return bookMapper.insert(book)>0;
    }

    /**
     * 编辑用户
     * @param book 图书对象
     * @return true or false
     */
    public boolean updateBook(Book book) {
        return bookMapper.updateById(book)>0;
    }

    /**
     * 图书详情
     * @param id 主键
     * @return 图书详情
     */
    public BookOut findBookById(Long id) {
        Book book = bookMapper.selectKeyId(id);
        if (null!=book) {
            BookOut out = new BookOut();
            BeanUtil.copyProperties(book,out);
            out.setPublishTime(DateUtil.format(book.getPublishTime(),"yyyy-MM-dd"));
            return out;
        }
        return null;
    }

    public Book findBook(Long id) {
        Book book = bookMapper.selectKeyId(id);
        if (null!=book) {
            return book;
        }
        return null;
    }

    /**
     * ISBN查询
     * @param isbn
     * @return
     */
    public BookOut findBookByIsbn(String isbn) {
        Book book = bookMapper.findByIsbn(isbn);
        BookOut out = new BookOut();
        if (book == null) {
            return out;
        }
        BeanUtil.copyProperties(book,out);
        out.setPublishTime(DateUtil.format(book.getPublishTime(),"yyyy-MM-dd"));
        return out;
    }

    /**
     * 删除图书
     * @param id 主键
     * @return true or false
     */
    public void deleteBook(Long id) {
        bookMapper.deleteById(id);
    }


    /**
     * 图书搜索查询(mybatis 分页)
     * @param pageIn
     * @return
     */
    public PageOut getBookList(PageIn pageIn) {
        IPage<Book> list = bookMapper.findBookListByLike(new Page<>(pageIn.getCurrPage(), pageIn.getPageSize()),pageIn.getKeyword());
        List<BookOut> bookOuts = new ArrayList<>();
        for (Book book : list.getRecords()) {
            BookOut out = new BookOut();
            BeanUtil.copyProperties(book,out);
            out.setPublishTime(DateUtil.format(book.getPublishTime(),"yyyy-MM-dd"));
            bookOuts.add(out);
        }
        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(bookOuts);
        pageOut.setTotal(list.getTotal());
        pageOut.setCurrPage(list.getCurrent());
        pageOut.setPageSize(list.getSize());
        return pageOut;
    }
}