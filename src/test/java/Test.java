import com.hong.mybatis.inter.IUserOperator;
import com.hong.mybatis.model.Article;
import com.hong.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Cai on 2014/4/16 18:37.
 */
public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis/Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public void getUserList(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperator userOperator = session.getMapper(IUserOperator.class);
            List<User> users = userOperator.selectUsers(userName);
            for (User user:users) {
                System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
            }
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            User user = (User)session.selectOne("com.hong.mybatis.models.UserMapper.selectUserByID", 1);
//            System.out.println(user.getUserAddress());
//            System.out.println(user.getUserName());
//        } finally {
//            session.close();
//        }

//        try {
//            IUserOperator userOperator = session.getMapper(IUserOperator.class);
//            User user = userOperator.selectUserByID(1);
//            System.out.println(user.getUserAddress());
//            System.out.println(user.getUserName());
//        } finally {
//            session.close();
//        }

        Test testUser = new Test();
//        testUser.getUserList("%sussm%");
//        testUser.addUser();
//        testUser.updateUser();
//        testUser.deleteUser(10);
        testUser.getUserArticles(9);
    }

    public void addUser() {
        User user = new User();
        user.setUserAddress("浦东");
        user.setUserAge("27");
        user.setUserName("Cristiano");

        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperator userOperator = session.getMapper(IUserOperator.class);
            userOperator.addUser(user);
            session.commit();
            System.out.println("当前增加的用户id为：" + user.getId());
        } finally {
            session.close();
        }
    }

    public void updateUser() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperator userOperator = session.getMapper(IUserOperator.class);
            User user = userOperator.selectUserByID(9);
            user.setUserName("Cristiano Ronaldo");
            user.setUserAge("28");
            user.setUserAddress("西班牙 马德里");
            userOperator.updateUser(user);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void deleteUser(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperator userOperator = session.getMapper(IUserOperator.class);
            userOperator.deleteUser(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void getUserArticles(int userid) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperator userOperator = session.getMapper(IUserOperator.class);
            List<Article> articles = userOperator.getUserArticles(userid, 0, 10);
            for (Article article:articles) {
                System.out.println(article.getTitle() + ":" + article.getContent() + ":作者是:" +
                        article.getUser().getUserName() + ":地址:" + article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }
}
