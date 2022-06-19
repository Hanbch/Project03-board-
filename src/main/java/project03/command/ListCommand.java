package project03.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project03.dao.Dao;
import project03.dto.Dto;

public class ListCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Dao dao = new Dao();
		ArrayList<Dto> dtos = dao.list();
		
		request.setAttribute("dtos",dtos);
	}
}
	
