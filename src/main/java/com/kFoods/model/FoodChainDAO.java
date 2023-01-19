package com.kFoods.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kFoods.model.FoodChainVO;

import com.kFoods.model.ConnUtil;

public class FoodChainDAO {
	
	private static FoodChainDAO instance =null;
	
	private FoodChainDAO() {

	}
	
	public static FoodChainDAO getInstance() {
		if(instance == null) {
			synchronized (FoodChainDAO.class) {
				instance = new FoodChainDAO();
			}
		}
		return instance;
	}
	
	//��ü ���� ����� ��Ʈ������ �޼ҵ�
		public int getArticleCount() {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x =0;
			
			try {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select count(*) from koreanFoodsChain"); //��ü ������ ��� �´�
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1); //ù��° �÷��� �����´� ù��° �÷��� num?
				}
				//list�� arraylist�� ������ ������ �װɷ� �ѷ��ش�.
				
				
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			
			return x;
		}// end of  getArticleCount
		
		/*
		 * board ���̺��� �����͸� �����ͼ� ������ �޼ҵ带 �߰�
		 * �̶� ���°��� List<BoardVO>
		 */
		
		
		//����� �� �Խ��� ������ ���� ����ߵ�
		//start : ���۹�ȣ , end ��������
		public List<FoodChainVO> getArticles(int start, int end){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<FoodChainVO> articleList = null; //������ ��ü ����
			
			try {
				conn = ConnUtil.getConnection();                 //������������
				/* pstmt = conn.prepareStatement("select * from board order by num desc"); */
				//���� ������ ���� �ٱ��� ������ ����
				pstmt = conn.prepareStatement("select * from (select rownum rnum, seq, num, nickname, address, x, y, score, taste, service, hygiene, review from (select * from koreanFoodsChain order by num desc)) where rnum >= ? and rnum <= ?"); //rnum�� ���۹�ȣ 
				
				//���� ���������� ���ε� �Լ��� 2����
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				
				rs = pstmt.executeQuery();
	 			
	 			if(rs.next()) {
	 				articleList = new ArrayList<>(end - start +1);
	 				do {
	 					
	 					FoodChainVO article = new FoodChainVO();
	 					
	 					article.setNum(rs.getInt("num"));
	 					article.setName(rs.getString("name"));
	 					article.setNickname(rs.getString("nickname"));
	 					article.setAddress(rs.getString("address"));
	 					article.setX(rs.getDouble("x"));
	 					article.setY(rs.getDouble("y"));
	 					article.setScore(rs.getString("score"));
	 					article.setTaste(rs.getString("taste"));
	 					article.setHygiene(rs.getString("hygiene"));
	 					article.setReview(rs.getString("review"));
	 				
	 				
	 					articleList.add(article);
	 				}while(rs.next()); //�ȿ� ������ ��� �ݺ��ؼ� ������
	 				
	 			}
				
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			
			return articleList;
		} //end of getArticles
	
	
	// �� �� ����
	 public FoodChainVO getArticle(int num) {
		 Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			FoodChainVO article = null;
		 
		try {
			
		conn = ConnUtil.getConnection();
		//pstmt = conn.prepareStatement("update p_board set readcount=readcount+1 where num=?"); //���� ������ ��ȸ���� �ö󰡾ߵ� ���� �Ǿߵȴٴ¶��̴�.
		pstmt.setInt(1, num);
		
		pstmt.executeUpdate();	
		
		pstmt = conn.prepareStatement("select * from koreanFoodsChain where num=? "); //��ȸ
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				article = new FoodChainVO(); //��ü ����
			
				//db���� �����;ߵ�
				article.setNum(rs.getInt("num"));
				article.setName(rs.getString("name"));
				article.setNickname(rs.getString("nickname"));
				article.setAddress(rs.getString("address"));
				article.setX(rs.getDouble("x"));
				article.setY(rs.getDouble("y"));
				article.setScore(rs.getString("score"));
				article.setTaste(rs.getString("taste"));
				article.setHygiene(rs.getString("hygiene"));
				article.setReview(rs.getString("review"));
		}
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return article;
		
	 } // end getArticle
	 
	 
	}
	
	
	

