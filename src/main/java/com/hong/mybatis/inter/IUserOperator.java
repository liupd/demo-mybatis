package com.hong.mybatis.inter;

import com.hong.mybatis.model.Article;
import com.hong.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Cai on 2014/4/21 16:55.
 */
@Repository("userMapper")
public interface IUserOperator {
    public User selectUserByID(int id);

    public List<User> selectUsers(String userName);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public List<Article> getUserArticles(@Param("id")int userid, @Param("offset")int start, @Param("pageSize")int num);

    public Integer getUserArticleTotal(@Param("id")int id);
}
