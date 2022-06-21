package project03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;

public class ModifyCommand implements Command{

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) {
      
      String bid = request.getParameter("bid");
      String bname = request.getParameter("bname");
      String btitle = request.getParameter("btitle");
      String bcontent = request.getParameter("bcontent");
      
      Dao dao = new Dao();
      dao.modify(bid,bname, btitle, bcontent);
      
   }
   
}