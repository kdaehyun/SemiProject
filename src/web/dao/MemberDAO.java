package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import web.util.ShopException;
import web.vo.MemberVO;

public class MemberDAO {
	
	DataSource ds;
	
	public MemberDAO() {
		//1. ConnectionPool 찾기
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			 ds = (DataSource) envCtx.lookup("jdbc/MyDB");//ConnectionPool
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//C
	public void insertMember(MemberVO vo) throws ShopException{
		Connection con=null;
		PreparedStatement st=null;
		
		try {			
			
			//2. 연결
			con=ds.getConnection();//대여
			
			//3. Statement 생성
			st=con.prepareStatement("INSERT INTO Member (id, pw, name, address,age) VALUES (?,?,?,?,?) ");
			
			//4. SQL 전송
			st.setString(1, vo.getId());
			st.setString(2, vo.getPw());
			st.setString(3, vo.getName());
			st.setString(4, vo.getAddress());
			st.setInt(5, vo.getAge());
			
			int i=st.executeUpdate();		
			
			//5. 결과 얻기
			System.out.println(i+"행이 insert되었습니다");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("회원가입실패");
		}finally {
			//6. 종료
			try {
				st.close();
				con.close();//반납
			}catch(Exception e) {
				
			}
		}
	}
	
	//R
	public String selectMember(MemberVO vo) throws ShopException {
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {			
			
			//2. 연결
			con=ds.getConnection();//대여
			
			//3. Statement 생성
			st=con.prepareStatement("select name from Member where id=? and pw=? ");
			
			//4. SQL 전송
			st.setString(1, vo.getId());
			st.setString(2, vo.getPw());
			
			
			rs=st.executeQuery();		
			
			//5. 결과 얻기
			if(rs.next()) {
				return rs.getString("name");
			}
			
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("login 실패");
		}finally {
			//6. 종료
			try {
				st.close();
				con.close();//반납
			}catch(Exception e) {
				
			}
		}
	}
	
	//U
	public void updateMember() {
		
	}
	
	//D
	public void deleteMember(String id) throws ShopException {
		Connection con=null;
		PreparedStatement st=null;
		
		try {			
			
			//2. 연결
			con=ds.getConnection();//대여
			
			//3. Statement 생성
			st=con.prepareStatement("delete from Member where id=? ");
			
			//4. SQL 전송
			st.setString(1, id);			
			int i=st.executeUpdate();		
			
			//5. 결과 얻기
			System.out.println(i+"행이 delete되었습니다");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("회원탈퇴실패");
		}finally {
			//6. 종료
			try {
				st.close();
				con.close();//반납
			}catch(Exception e) {
				
			}
		}
	}

}
