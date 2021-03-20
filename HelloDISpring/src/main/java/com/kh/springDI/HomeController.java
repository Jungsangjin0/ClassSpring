package com.kh.springDI;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
//	private Human human;
	
//	@Autowired
//	private Owner owner;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		// Spring ApplicationContext를 사용하기 전
//		Owner owner = new Owner();
		
//		owner.setName("문인수");
//		owner.setAge(20);
//		owner.setGender("M");
//		owner.setPet(new Dog("dog", 3, "M"));
//		System.out.println(owner.toString());
//		System.out.println(owner.getPet().bark());
//		Cat cat = (Cat)owner.getPet();
//		owner.setPet(new Cat("cat", 1, "F"));
//		System.out.println(owner.getPet().bark());
		
		
		//Spring ApplicationContext를 사용할 때
		
		//springapplicationcontext만들기
		//classpath에 읽는 것을 읽어옴
		//genericXmlApplicationContext의 생성자로 파일명을 적어준다
//		ApplicationContext context = new GenericXmlApplicationContext("owner-context.xml");
		
//		owner = (Owner) context.getBean("owner");
//		owner = context.getBean("owner", Owner.class);
		
//		System.out.println(owner);
//		System.out.println(owner.getPet().bark());
//		cat.setAge(20);
		
		
		//annotation을 사용한 bean 생성
//		System.out.println(human);
//		System.out.println(human.getJob().work());
		
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
