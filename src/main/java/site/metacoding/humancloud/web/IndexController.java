package site.metacoding.humancloud.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final HttpSession session;
	@GetMapping("/subscribe/test")
	public String index() {
		session.setAttribute("company1", "company");
		return "index";
	}

	@GetMapping("/subscribe/test2")
	public String index2() {
		session.setAttribute("user1", "user1");
		return "index";
	}
}
