package tuanlm.hr.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Article;
import tuanlm.hr.app.models.request.UpsertArticleRequest;
import tuanlm.hr.app.service.ArticleService;

/**
 * The Class ArticleController.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/article")
public class ArticleController {
	
	/** The service. */
	private ArticleService service;
	
	/**
	 * Gets the article by paging.
	 *
	 * @param pageNo the page no
	 * @return the article by paging
	 */
	@Operation(summary = "Lấy tin tức theo trang (mặc định là 4 tin tức một trang). Nếu truyền pageNo là 0, sẽ lấy tất cả tin tức", 
			security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{pageNo}")
	public ResponseEntity<List<Article>> getArticleByPaging(@PathVariable @Parameter(description = "Index của trang") int pageNo) {
		List<Article> articles = service.getPagingArticle(pageNo);
		return (articles == null) ? new ResponseEntity<List<Article>>(HttpStatus.NOT_FOUND) : new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	}	
	
	/**
	 * Gets the article by id.
	 *
	 * @param articleId the article id
	 * @return the article by id
	 */
	@Operation(summary = "Lấy thông tin của tin tức theo id. ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("get-by-id/{articleId}")
	public ResponseEntity<Article> getArticleById(@PathVariable @Parameter(description = "Id của tin tức") int articleId) {
		Article article = service.getById(articleId);
		return (article == null) ? new ResponseEntity<Article>(HttpStatus.NOT_FOUND) : new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	
	/**
	 * Delete article by id.
	 *
	 * @param articleId the article id
	 * @return the response entity
	 */
	@Operation(summary = "Xóa tin tức theo id. ", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("update-active-by-id/{articleId}/{mode}")
	public ResponseEntity<Void> deleteArticleById(
			@PathVariable @Parameter(description = "Id của tin tức") int articleId,
			@PathVariable @Parameter(description = "Mode (0: Delete, 1: Active)") int mode) {
		service.updateActiveArticleById(articleId, mode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Upsert article.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Update hoặc tạo mới tin tức. Mode 1: Update, 2: Insert ", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/upsert-article")
	public ResponseEntity<Void> upsertArticle(@RequestBody @Valid UpsertArticleRequest request) {
		service.upsertArticle(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
