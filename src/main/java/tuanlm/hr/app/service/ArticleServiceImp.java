package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.ArticleMapper;
import tuanlm.hr.app.models.model.Article;
import tuanlm.hr.app.models.request.UpsertArticleRequest;

/**
 * The Class ArticleServiceImp.
 */
@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
	private final int DEFAULT_ROW_NUM = 4;

	/** The mapper. */
	private ArticleMapper mapper;

	/**
	 * Gets the paging article.
	 *
	 * @param pageNo the page no
	 * @return the paging article
	 */
	@Override
	public List<Article> getPagingArticle(int pageNo) {
		return mapper.getArticleByCondition(pageNo, pageNo * DEFAULT_ROW_NUM, DEFAULT_ROW_NUM);
	}

	/**
	 * Gets the by id.
	 *
	 * @param articleId the article id
	 * @return the by id
	 */
	@Override
	public Article getById(int articleId) {
		return mapper.getArticleById(articleId);
	}

	/**
	 * Delete article by id.
	 *
	 * @param articleId the article id
	 */
	@Override
	public void updateActiveArticleById(int articleId, int mode) {
		mapper.updateActiveArticleById(articleId, mode);
	}

	/**
	 * Upsert article.
	 *
	 * @param request the request
	 */
	@Override
	public void upsertArticle(UpsertArticleRequest request) {
		if (request.getMode() == 1) {
			mapper.updateArticle(request, LocalDateTime.now());
		} 
		else if (request.getMode() == 2) {
			mapper.insertArticle(request, LocalDateTime.now());
		}
		// End
	}
}
