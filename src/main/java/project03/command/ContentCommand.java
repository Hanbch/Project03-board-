package project03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;
import project03.dto.Dto;

public class ContentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				
		String bid = request.getParameter("bid");
	
		Dao dao = new Dao();
		Dto dto = dao.contenView(bid);
		
		request.setAttribute("content_view", dto);	
		
	}
	
	
}
