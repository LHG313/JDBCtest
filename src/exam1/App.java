package exam1;

import java.util.Scanner;

import exam1.container.Container;
import exam1.controller.ArticleController;
import exam1.controller.MemberController;

public class App {

	private ArticleController articleController;
	private MemberController memberController = new MemberController();

	public App() {
		articleController = Container.articleController;
		memberController = Container.memberController;
	}

	public void run() {
		Scanner sc = Container.sc;
		while (true) {

			System.out.printf("명령어) ");
			String cmd = Container.sc.nextLine();

			if (cmd.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			} else if (cmd.startsWith("article ")) {
				articleController.doCommand(cmd);
			} else if (cmd.startsWith("member ")) {
				memberController.doCommand(cmd);
			}

		}
		sc.close();

	}
}