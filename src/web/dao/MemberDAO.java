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
		//1. ConnectionPool ã��
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
			
			//2. ����
			con=ds.getConnection();//�뿩
			
			//3. Statement ����
			st=con.prepareStatement("INSERT INTO Member (id, pw, name, address,age) VALUES (?,?,?,?,?) ");
			
			//4. SQL ����
			st.setString(1, vo.getId());
			st.setString(2, vo.getPw());
			st.setString(3, vo.getName());
			st.setString(4, vo.getAddress());
			st.setInt(5, vo.getAge());
			
			int i=st.executeUpdate();		
			
			//5. ��� ���
			System.out.println(i+"���� insert�Ǿ����ϴ�");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("ȸ�����Խ���");
		}finally {
			//6. ����
			try {
				st.close();
				con.close();//�ݳ�
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
			
			//2. ����
			con=ds.getConnection();//�뿩
			
			//3. Statement ����
			st=con.prepareStatement("select name from Member where id=? and pw=? ");
			
			//4. SQL ����
			st.setString(1, vo.getId());
			st.setString(2, vo.getPw());
			
			
			rs=st.executeQuery();		
			
			//5. ��� ���
			if(rs.next()) {
				return rs.getString("name");
			}
			
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("login ����");
		}finally {
			//6. ����
			try {
				st.close();
				con.close();//�ݳ�
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
			
			//2. ����
			con=ds.getConnection();//�뿩
			
			//3. Statement ����
			st=con.prepareStatement("delete from Member where id=? ");
			
			//4. SQL ����
			st.setString(1, id);			
			int i=st.executeUpdate();		
			
			//5. ��� ���
			System.out.println(i+"���� delete�Ǿ����ϴ�");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShopException("ȸ��Ż�����");
		}finally {
			//6. ����
			try {
				st.close();
				con.close();//�ݳ�
			}catch(Exception e) {
				
			}
		}
	}

}
