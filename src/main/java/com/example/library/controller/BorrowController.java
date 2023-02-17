package com.example.library.controller;

import cn.hutool.core.date.DateUtil;
import com.example.library.entity.Borrow;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import com.example.library.util.CodeEnum;
import com.example.library.util.Constants;
import com.example.library.util.Result;
import com.example.library.vo.BackOut;
import com.example.library.vo.BookOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-17 19:50
 */
@Api(tags = "借阅管理")
@RestController
@RequestMapping("/borrow")
public class BorrowController {


    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @ApiOperation("借阅列表")
    @GetMapping("/list")
    public Result getBorrowList(Long userId) {
        return Result.success(CodeEnum.SUCCESS,borrowService.findAllBorrowByUserId(userId));
    }

    @ApiOperation("借阅图书")
    @PostMapping("/add")
    public Result addBorrow(@RequestBody Borrow borrow) {
        Integer result = borrowService.addBorrow(borrow);
        if (result == Constants.BOOK_BORROWED) {
            return Result.success(CodeEnum.BOOK_BORROWED);
        }else if (result == Constants.USER_SIZE_NOT_ENOUGH) {
            return Result.success(CodeEnum.USER_NOT_ENOUGH);
        }else if (result == Constants.BOOK_SIZE_NOT_ENOUGH) {
            return Result.success(CodeEnum.BOOK_NOT_ENOUGH);
        }
        return Result.success(CodeEnum.SUCCESS,Constants.OK);
    }

    @ApiOperation("编辑借阅")
    @PostMapping("/update")
    public Result modifyBorrow(@RequestBody Borrow borrow) {
        return Result.success(CodeEnum.SUCCESS,borrowService.updateBorrow(borrow));
    }


    @ApiOperation("借阅详情")
    @GetMapping("/detail")
    public Result borrowDetail(Long id) {
        return Result.success(CodeEnum.SUCCESS,borrowService.findById(id));
    }

    @ApiOperation("删除归还记录")
    @GetMapping("/delete")
    public Result delBorrow(Long id) {
        borrowService.deleteBorrow(id);
        return Result.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("已借阅列表")
    @GetMapping("/borrowed")
    public Result borrowedList(Long userId) {
        List<BackOut> outs = new ArrayList<>();
        if (userId!=null&&userId>0) {
            // 获取所有 已借阅 未归还书籍
            List<Borrow> borrows = borrowService.findBorrowsByUserIdAndRet(userId, Constants.NO);
            for (Borrow borrow : borrows) {
                BackOut backOut = new BackOut();
                BookOut out = bookService.findBookById(borrow.getBookId());
                BeanUtils.copyProperties(out,backOut);

                backOut.setBorrowTime(DateUtil.format(borrow.getCreateTime(),Constants.DATE_FORMAT));

                String endTimeStr = DateUtil.format(borrow.getEndTime(), Constants.DATE_FORMAT);
                backOut.setEndTime(endTimeStr);
                // 判断是否逾期
                String toDay = DateUtil.format(new Date(), Constants.DATE_FORMAT);
                int i = toDay.compareTo(endTimeStr);
                if (i>0) {
                    backOut.setLate(Constants.YES_STR);
                }else {
                    backOut.setLate(Constants.NO_STR);
                }

                outs.add(backOut);
            }
        }

        return Result.success(CodeEnum.SUCCESS,outs);
    }

    @ApiOperation("归还书籍")
    @PostMapping("/ret")
    public Result retBook(Long userId, Long bookId) {
        // 归还图书
        borrowService.retBook(userId,bookId);
        return Result.success(CodeEnum.SUCCESS);
    }
}