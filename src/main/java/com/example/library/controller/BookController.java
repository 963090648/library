package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import com.example.library.util.CodeEnum;
import com.example.library.util.Result;
import com.example.library.vo.PageIn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-17 19:05
 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService bookService;

    @ApiOperation("图书搜索列表")
    @PostMapping("/list")
    public Result getBookList(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return Result.fail(CodeEnum.PARAM_ERROR);
        }

        return Result.success(CodeEnum.SUCCESS,bookService.getBookList(pageIn));
    }

    @ApiOperation("添加图书")
    @PostMapping("/add")
    public Result addBook(@RequestBody Book book) {
        return Result.success(CodeEnum.SUCCESS,bookService.addBook(book));
    }

    @ApiOperation("编辑图书")
    @PostMapping("/update")
    public Result modifyBook(@RequestBody Book book) {
        return Result.success(CodeEnum.SUCCESS,bookService.updateBook(book));
    }


    @ApiOperation("图书详情")
    @GetMapping("/detail")
    public Result bookDetail(Long id) {
        return Result.success(CodeEnum.SUCCESS,bookService.findBookById(id));
    }

    @ApiOperation("图书详情 根据ISBN获取")
    @GetMapping("/detailByIsbn")
    public Result bookDetailByIsbn(String isbn) {
        return Result.success(CodeEnum.SUCCESS,bookService.findBookByIsbn(isbn));
    }

    @ApiOperation("删除图书")
    @GetMapping("/delete")
    public Result delBook(Integer id) {
        bookService.deleteBook(id);
        return Result.success(CodeEnum.SUCCESS);
    }
}