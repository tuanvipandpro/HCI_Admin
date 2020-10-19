package tuanlm.hr.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tuanlm.hr.app.models.model.Article;
import tuanlm.hr.app.models.request.UpsertArticleRequest;

/**
 * The Interface ArticleMapper.
 */
@Mapper
public interface ArticleMapper {
	
	/**
	 * Gets the article by cond0ition.
	 *
	 * @param pageNo the page no
	 * @param offset the offset
	 * @param limit the limit
	 * @return the article by condition
	 */
	@Select(""
			+ "<script> "
			+ "SELECT "
			+ "		A.Id 					AS id, "
			+ "		A.Title 				AS title, "
			+ "		A.Short 				AS shortContent, "
			+ "		A.Content 				AS content, "
			+ "		A.Create_Date 			AS createDate, "
			+ "		A.Author				AS authorId, "
			+ "		A.Active				AS active "
			+ "FROM "
			+ "		article AS A "
			+ "	<if test=\"pageNo != 0\"> "
			+ "		WHERE "
			+ "			A.Active = true "
			+ "		ORDER BY "
			+ "			A.create_date DESC "
			+ "		LIMIT "
			+ "			#{limit} "
			+ "		OFFSET "
			+ "			#{offset}"
			+ "	</if> "		
			+ "	<if test=\"pageNo == 0\"> "
			+ "		ORDER BY "
			+ "			A.create_date DESC "
			+ "	</if> "
			+ "</script> ")
	List<Article> getArticleByCondition(int pageNo, int offset, int limit);	
	
	/**
	 * Gets the article by id.
	 *
	 * @param id the id
	 * @return the article by id
	 */
	@Select(""
			+ "SELECT "
			+ "		A.Id 					AS id, "
			+ "		A.Title 				AS title, "
			+ "		A.Short 				AS shortContent, "
			+ "		A.Content 				AS content, "
			+ "		A.Create_Date 			AS createDate, "
			+ "		A.Author				AS authorId "
			+ "FROM "
			+ "		article AS A "
			+ "WHERE "
			+ "		A.active = true "
			+ "		AND A.Id = #{id} "
			+ "")
	Article getArticleById(int id);
	
	/**
	 * Delete article by id.
	 *
	 * @param id the id
	 */
	@Update("<script>"
			+ "UPDATE article "
			+ "<if test=\"mode == 0\">"
			+ "		SET active = false "
			+ "</if>"
			+ "<if test=\"mode == 1\">"
			+ "		SET active = true "
			+ "</if>"
			+ "WHERE id = #{id} "
			+ "</script>")
	void updateActiveArticleById(int id, int mode);
	
	
	/**
	 * Update article.
	 *
	 * @param request the request
	 */
	@Update(""
			+ "UPDATE article "
			+ "SET title = #{request.title}, short = #{request.shortContent}, content = #{request.content} "
			+ "WHERE id = #{request.articleId} "
			+ "")
	void updateArticle(UpsertArticleRequest request, LocalDateTime now);
	
	/**
	 * Insert article.
	 *
	 * @param request the request
	 */
	@Insert(""
			+ "INSERT INTO article(title, short, content, create_date, author, active) "
			+ "VALUES (#{request.title}, #{request.shortContent}, #{request.content}, #{now}, #{request.employeeId}, true)"
			+ "")
	void insertArticle(UpsertArticleRequest request, LocalDateTime now);
}
