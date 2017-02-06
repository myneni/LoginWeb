package com.concord.contorller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concord.delegate.LoginDelegate;
import com.concord.viewBean.LoginBean;

@Controller
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginDelegate loginDelegate;

	@RequestMapping(value = "/login")
	public ModelAndView displayLogin(HttpServletRequest req, HttpServletResponse res, LoginBean loginBean) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginBean", loginBean);
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView excuteLogin(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("loginBean") LoginBean loginBean) {
		ModelAndView model = null;
		try {
			logger.info("User Login :" + loginBean.getUsername() + " password - " + loginBean.getPassword());

			boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
			if (isValidUser) {
				System.out.println("User Login Successful");
				req.setAttribute("loggedInuser", loginBean.getUsername());
				model = new ModelAndView("Welcome");

			} else {
				model = new ModelAndView("login");
				req.setAttribute("message", "Invalid Credentials!!");
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return model;
	}
}
