package exam1.container;

import java.util.Scanner;

import exam1.controller.ArticleController;
import exam1.controller.MemberController;
import exam1.dao.ArticleDao;
import exam1.service.ArticleService;

public class Container {

	public static Scanner sc;
	public static ArticleController articleController;
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberController memberController;
	public static Scanner scanner;

	static {
		sc = new Scanner(System.in);

		scanner = new Scanner(System.in);

		articleDao = new ArticleDao();

		articleService = new ArticleService();

		articleController = new ArticleController();

		memberController = new MemberController();

	}

}
