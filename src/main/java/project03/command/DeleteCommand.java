package project03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;


public class DeleteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bid = request.getParameter("bid");

		Dao dao = new Dao();
		dao.delete(bid);
		
	}
	
}
