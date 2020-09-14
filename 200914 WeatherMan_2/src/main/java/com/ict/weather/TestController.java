package com.ict.weather;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.util.ExportCSVUtil;
import my.vo.MenuVO;

@Controller
public class TestController {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	// 지도와 로드뷰 토글 기능 테스트 컨트롤러 메소드
	@RequestMapping("/mapAndRoadViewToggle")
	public String mapAndRoadViewToggle() {
		return "test/mapAndRoadViewToggle";
	}
	
	
	// 모달창 연습 화면 진입 메소드
	@RequestMapping("/modal")
	public String modal() {
		return "test/modalPanel";
	}
	
	/*public String mybatisTest() {
		System.out.println("테스트중");
		return "errorPage";
	}*/
	
	// 테스트 메소드
	@RequestMapping("/mybatis_test")
	public ModelAndView mybatisTest(String sa, String sb, String ac, String bc) {
		
		Map<String,String> map = new Hashtable<String,String>();
		
		map.put("season_a", sa);
		map.put("season_b", sb);
		map.put("menu_a_count", ac);
		map.put("menu_b_count", bc);

		List<MenuVO> list = ss.selectList("weather.getMenus", map);
		
		ModelAndView mv = new ModelAndView();
		
		if(list != null) {
			System.out.println("mybatis정상실행!");			
		} else {
			System.out.println("mybatis가 정상실행되지 않음");
		}
		mv.addObject("mv", list);
		mv.setViewName("errorPage");
		
		return mv;
	}
	
	
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export() {
		boolean result = ExportCSVUtil.exportCSV();
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("result", result);
		mv.setViewName("test/exportResult");
		
		return mv;
	}
	
	

}
	