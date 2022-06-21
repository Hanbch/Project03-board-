package project03.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.command.ModifyCommand;
import project03.command.Command;
import project03.command.ContentCommand;
import project03.command.DeleteCommand;
import project03.command.ListCommand;
import project03.command.ReplyCommand;
import project03.command.ReplyViewCommand;
import project03.command.WriteCommand;

/**
 * Servlet implementation class Bcontroller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		Command command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		if(com.equals("/list.do")) {
			
			command = new ListCommand();
			command.execute(request, response);
			
			viewPage = "list.jsp";			
		}else if(com.equals("/write_view.do")) {
			
			viewPage = "write_view.jsp";
		}else if(com.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request, response);
			
			viewPage = "list.do";
			
		}else if(com.equals("/content_view.do")) {
			command = new ContentCommand();
			command.execute(request, response);
			
			viewPage = "content_view.jsp";
			
		}else if(com.equals("/reply_view.do")) {
			command = new ReplyViewCommand();
			command.execute(request, response);
			
			viewPage = "reply_view.jsp";
			
		}else if(com.equals("/reply.do")) {
			command = new ReplyCommand();
			command.execute(request, response);
			
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			command = new DeleteCommand();
			command.execute(request, response);
			
			viewPage = "list.do";
		}else if(com.equals("/modify.do")) {
			command = new ModifyCommand();
			command.execute(request, response);
			
			viewPage = "list.do";
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
