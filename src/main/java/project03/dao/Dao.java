package project03.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.*;

import project03.dto.Dto;

public class Dao {
	
	private DataSource dataSource;
	
	public Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Dto> list() {
		ArrayList<Dto> dtos = new ArrayList<Dto>();

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {

				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				Dto dto = new Dto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

				dtos.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;

	}
	
	public Dto reply_view(String bid) {
	
		Dto dto = null;

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String query = "select * from mvc_board where bid = ?";
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, Integer.valueOf(bid.trim()));

			rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				dto = new Dto(id, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;

	}

	public Dto contenView(String bid) {
		
		Dto dto = null;

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		uphit(bid);

		try {

			String query = "select * from mvc_board where bid = ?";
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, Integer.valueOf(bid.trim()));

			rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				dto = new Dto(id, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return dto;

	}

	
	
	public void replyShape(String group, String step) {

		System.out.println("replyShape()");

		Connection connection = null;
		PreparedStatement stmt = null;

		try {	
			
			String query = "update mvc_board set bstep = bstep + 1 "
					+ "where bgroup = ? and bstep > ?";
					
			
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(group));
			stmt.setInt(2, Integer.parseInt(step));


			int rn = stmt.executeUpdate();

			System.out.println("업데이트 갯수 :" + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void uphit(String bid) { //조회수 올리는 메서드 content_veiw들어갈떄 메서드 실행

		System.out.println("uphit()");

		Connection connection = null;
		PreparedStatement stmt = null;

		try {

			// 이때 ?,?,? 물음표는 아래에서 setString메소드로 넣어줄 값을 표현하는 것
			/*
			 * 파라미터로 받는 값이 bname, btitle, bcontent 3개므로 3개만 ?로 넣고 나머지는 0으로 설정했다. 글 작성시
			 * 조회수(bhit)는 0이고, 원본 글로 취급하므로 bstep, bindent는 지수를 넣어줄 필요가 없기 때문이다.
			 */

			String query = "update mvc_board set bhit = bhit + 1 where bid = ?";

			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setInt(1,Integer.parseInt(bid));

			int rn = stmt.executeUpdate();

			System.out.println("업데이트 갯수 :" + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void reply(String bid, String btitle, String bname, String bcontent,
			String bgroup, String bstep, String bindent) {
		
		replyShape(bgroup, bstep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String query = "insert into mvc_board(bid,bname,btitle,bcontent, bgroup,bstep,bindent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setInt(4, Integer.parseInt(bgroup));
			preparedStatement.setInt(5, Integer.parseInt(bstep)+1);
			preparedStatement.setInt(6, Integer.parseInt(bindent)+1);

			int rn = preparedStatement.executeUpdate();
			System.out.println("답글 갯수 : "+ rn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void delete(String bid) {

		System.out.println("delete()");

		Connection connection = null;
		PreparedStatement stmt = null;

		try {

	

			String query = "delete from mvc_board where bid = ?";

			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(bid));

			int rn = stmt.executeUpdate();

			System.out.println("삭제 갯수 :" + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	

	public void write(String btitle, String bname, String bcontent) {

		System.out.println("write()");

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			String query = "insert into mvc_board " + "(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent)"
					+ "values (mvc_board_seq.nextval,?,?,?,0, mvc_board_seq.currval,0,0)";

			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.setString(1, bname);
			stmt.setString(2, btitle);
			stmt.setString(3, bcontent);

			int rn = stmt.executeUpdate();

			System.out.println("업데이트 갯수 :" + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void modify(String bid,String bname,String btitle,String bcontent) {

	      System.out.println("수정..");

	      Connection connection = null;
	      PreparedStatement stmt = null;

	      try {

	         String query = "update mvc_board set bname = ?, btitle = ?, bcontent = ? " +
	         " where bid = ?";

	         connection = dataSource.getConnection();
	         stmt = connection.prepareStatement(query);

	         stmt.setString(1, bname);
	         stmt.setString(2, btitle);
	         stmt.setString(3, bcontent);
	         stmt.setInt(4, Integer.parseInt(bid));
	         

	         int rn = stmt.executeUpdate();

	         System.out.println("업데이트 갯수 :" + rn);

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {

	         try {
	            if (stmt != null)
	               stmt.close();
	            if (connection != null)
	               connection.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }

	   }
	
}
