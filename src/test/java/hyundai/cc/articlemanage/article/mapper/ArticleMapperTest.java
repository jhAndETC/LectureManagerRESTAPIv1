package hyundai.cc.articlemanage.article.mapper;

import lombok.Setter;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Log
class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void articleMapperTest() throws SQLException {
        log.info(articleMapper.getAllArticleList().toString());
    }




}