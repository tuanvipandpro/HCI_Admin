package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertArticleRequest {
	
	/** The mode. 
	 * 	1 : Update
	 *  2 : Insert
	 */
	@NotNull
	private int mode;
	
	/** The employee id. */
	@NotNull
	private int employeeId;
	
	/** The article id. */
	private int articleId;
	
	@NotNull
	/** The title. */
	private String title;
	
	@NotNull
	/** The short content. */
	private String shortContent;
	
	@NotNull
	/** The content. */
	private String content;
}
