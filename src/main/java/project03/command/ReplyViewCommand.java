package project03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;
import project03.dto.Dto;

public class ReplyViewCommand implements Command{


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				
		String bid = request.getParameter("bid");
	
		Dao dao = new Dao();
		Dto dto = dao.reply_view(bid);

		request.setAttribute("reply_view", dto);	
		
	}
	
}
