package exam1.controller;

import java.util.Scanner;

import exam1.container.Container;
import exam1.dto.Member;
import exam1.service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		}
		if (cmd.equals("member login")) {
			login(cmd);
		}

	}

	private void login(String cmd) {
		Scanner sc = Container.scanner;

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Member member = memberService.getMemberid(inputedId);

		System.out.printf("로그인아이디: %s\n", member.loginId);
		System.out.printf("로그인비번: %s\n", member.loginId);
	}

	private void join(String cmd) {
		Scanner sc = Container.scanner;

		String loginId;
		String loginPw;
		String name;

		System.out.printf("로그인아이디 : ");
		loginId = sc.nextLine();

		System.out.printf("로그인비번 : ");
		loginPw = sc.nextLine();

		System.out.printf("이름 : ");
		name = sc.nextLine();

		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("%d번 회원이 생성되었습니다.\n", id);

	}

}
