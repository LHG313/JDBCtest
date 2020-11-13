package exam1.service;

import java.util.List;

import exam1.container.Container;
import exam1.dto.Article;
import exam1.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticlesFromDB() {
		return articleDao.getArticlesFromDB();
	}

	public int add(String title, String body, String nickname, int hit) {
		return articleDao.add(title, body, nickname, hit);
	}

	public void removeFromDB(int inputedId) {
		articleDao.remove(inputedId);

	}

	public void modify(int inputedId, String title, String body) {
		articleDao.modify(inputedId, title, body);
	}

	public Article getAnArticleFromDB(int inputedId) {
		return articleDao.getAnArticleFromDB(inputedId);

	}

}
