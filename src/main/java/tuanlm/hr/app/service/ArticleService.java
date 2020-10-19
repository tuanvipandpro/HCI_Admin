package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Article;
import tuanlm.hr.app.models.request.UpsertArticleRequest;

/**
 * The Interface ArticleService.
 */
public interface ArticleService {
	
	/**
	 * Gets the paging article.
	 *
	 * @param pageNo the page no
	 * @return the paging article
	 */
	List<Article> getPagingArticle(int pageNo);
	
	/**
	 * Gets the by id.
	 *
	 * @param articleId the article id
	 * @return the by id
	 */
	Article getById(int articleId);
	
	/**
	 * Delete article by id.
	 *
	 * @param articleId the article id
	 * @param mode the mode
	 */
	void updateActiveArticleById(int articleId, int mode);
	
	/**
	 * Upsert article.
	 *
	 * @param request the request
	 */
	void upsertArticle(UpsertArticleRequest request);
}
