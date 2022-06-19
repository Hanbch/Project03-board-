package project03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;

public class ReplyCommand implements Command{


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");

		Dao dao = new Dao();
		dao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);

		request.setAttribute("reply_view", dao);
		
	}
	
}
