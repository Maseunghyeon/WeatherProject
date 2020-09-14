package com.ict.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import my.dao.HistoryDAO;
import my.dao.MenuDAO;

@Controller
public class HistoryController {

	@Autowired
	private MenuDAO menuDao;
	//@Autowired
	//private HistoryDAO historyDao;
	
	public void dd() {
		
	}
	
}
