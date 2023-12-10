package hyundai.cc.articlemanage.article.mapper;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.SQLException;
import java.util.List;

public interface ArticleMapper {
    public List<ArticleDTO> getAllArticleList() throws SQLException;

}
