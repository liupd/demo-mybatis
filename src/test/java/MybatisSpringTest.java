import com.hong.mybatis.inter.IUserOperator;
import com.hong.mybatis.model.Article;
import com.hong.mybatis.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Cai on 2014/4/22 13:51.
 */
public class MybatisSpringTest {
    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("spring/spring-base.xml");
    }

    public static void main(String[] args) {
        IUserOperator mapper = (IUserOperator) ctx.getBean("userMapper");
        System.out.println("得到ID为9的用户信息:");
        User user = mapper.selectUserByID(9);
        System.out.println(user.getUserAddress());

        System.out.println("\n得到ID为9的用户所用文章列表:");
        List<Article> articles = mapper.getUserArticles(9, 0, 10);

        for (Article article:articles) {
            System.out.println(article.getContent() + "--" + article.getTitle());
        }
    }
}
