package exam1.controller;

import java.util.List;

import exam1.container.Container;
import exam1.dto.Article;
import exam1.service.ArticleService;

public class ArticleController {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article list")) {
			list(cmd);
		} else if (cmd.startsWith("article delete ")) {
			delete(cmd);
		} else if (cmd.equals("article add")) {
			add(cmd);
		} else if (cmd.startsWith("article detail ")) {
			detail(cmd);
		} else if (cmd.startsWith("article modify")) {
			modify(cmd);
		}

	}

	private void detail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article anArticle = articleService.getAnArticleFromDB(inputedId);

		System.out.println("== 게시물 상세 ==");
		System.out.printf("번호 : %d\n", anArticle.id);
		System.out.printf("날짜 : %s\n", anArticle.regDate);
		System.out.printf("조회수 : %d\n", anArticle.hit);
		System.out.printf("작성자 : %s\n", anArticle.nickname);
		System.out.printf("제목 : %s\n", anArticle.title);
		System.out.printf("내용 : %s\n", anArticle.body);

	}

	private void modify(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("== 게시물 수정 ==");

		System.out.printf("제목 : ");
		String title = Container.sc.nextLine();
		System.out.printf("내용 : ");
		String body = Container.sc.nextLine();
		System.out.printf("작성자 : ");
		String nickname = Container.sc.nextLine();
		System.out.printf("조회수 : ");
		int hit = Container.sc.nextInt();
		Container.sc.nextLine();

		articleService.modify(inputedId, title, body);

	}

	private void add(String cmd) {
		String title;
		String body;
		String nickname;
		int hit;

		System.out.println("== 게시물 등록 ==");
		System.out.printf("제목 : ");
		title = Container.sc.nextLine();
		System.out.printf("내용 : ");
		body = Container.sc.nextLine();
		System.out.printf("작성자 : ");
		nickname = Container.sc.nextLine();
		System.out.printf("조회수 : ");
		hit = Container.sc.nextInt();
		Container.sc.nextLine();

		int articleId = articleService.add(title, body, nickname, hit);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", articleId);

	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("== 게시물 삭제 ==");

		articleService.removeFromDB(inputedId);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

	}

	private void list(String cmd) {
		List<Article> articles = articleService.getArticlesFromDB();

		System.out.println("== 게시물리스트 ==");
		System.out.println("번호 / 날짜 / 작성자 / 제목 / 조회수");

		for (Article article : articles) {
			System.out.printf("%d/%s/%s/%s/%d\n", article.id, article.regDate, article.nickname, article.title,
					article.hit);
		}

	}

}
