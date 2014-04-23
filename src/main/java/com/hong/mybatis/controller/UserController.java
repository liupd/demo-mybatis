package com.hong.mybatis.controller;

import com.hong.mybatis.inter.IUserOperator;
import com.hong.mybatis.model.Article;
import com.hong.mybatis.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Cai on 2014/4/23 9:21.
 */
@Controller
@RequestMapping("/article")
public class UserController {
    @Autowired
    private IUserOperator userMapper;

    @RequestMapping("/list")
    public String list(ModelMap modelMap, Integer pageIndex) {
        int pageSize = 1;
        int stepSize = 20;
        int currentPage = pageIndex == null ? 1 : pageIndex;

        List<Article> articles = userMapper.getUserArticles(9, (currentPage - 1) * pageSize, pageSize);
        int total = userMapper.getUserArticleTotal(9);

        PaginationUtil.PageBean pageBean = PaginationUtil.pagingList(currentPage, pageSize, total, stepSize ,articles);

        modelMap.addAttribute("articles", articles);
        modelMap.addAttribute("page", pageBean);

        return "/list";
    }
}
