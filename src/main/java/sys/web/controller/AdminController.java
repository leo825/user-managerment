package sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sys.auth.annotation.NavMenu;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@NavMenu(name="首页信息",psn="top_root",orderNum=1)
	@RequestMapping(value={"/index",""})
	public String index() {
		return "index";
	}
}
