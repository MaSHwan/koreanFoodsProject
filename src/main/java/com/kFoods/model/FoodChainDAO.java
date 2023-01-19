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
	
	//전체 글이 몇개인지 인트형으로 메소드
		public int getArticleCount() {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x =0;
			
			try {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select count(*) from koreanFoodsChain"); //전체 개수를 얻어 온다
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1); //첫번째 컬럼을 가져온다 첫번째 컬럼이 num?
				}
				//list를 arraylist에 저장한 다음에 그걸로 뿌려준다.
				
				
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
		 * board 테이블에서 데이터를 가져와서 보여줄 메소드를 추가
		 * 이때 쓰는것이 List<BoardVO>
		 */
		
		
		//여기다 글 게시판 페이지 수를 해줘야됨
		//start : 시작번호 , end 끝페이지
		public List<FoodChainVO> getArticles(int start, int end){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<FoodChainVO> articleList = null; //리턴할 객체 생성
			
			try {
				conn = ConnUtil.getConnection();                 //내림차순으로
				/* pstmt = conn.prepareStatement("select * from board order by num desc"); */
				//안쪽 쿼리가 서브 바깥쪽 쿼리가 메인
				pstmt = conn.prepareStatement("select * from (select rownum rnum, seq, num, nickname, address, x, y, score, taste, service, hygiene, review from (select * from koreanFoodsChain order by num desc)) where rnum >= ? and rnum <= ?"); //rnum이 시작번호 
				
				//위에 쿼리문에서 바인딩 함수가 2개임
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
	 				}while(rs.next()); //안에 있으면 계속 반복해서 꺼내라
	 				
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
	
	
	// 글 상세 보기
	 public FoodChainVO getArticle(int num) {
		 Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			FoodChainVO article = null;
		 
		try {
			
		conn = ConnUtil.getConnection();
		//pstmt = conn.prepareStatement("update p_board set readcount=readcount+1 where num=?"); //글을 누르면 조회수가 올라가야됨 갱신 되야된다는뜻이다.
		pstmt.setInt(1, num);
		
		pstmt.executeUpdate();	
		
		pstmt = conn.prepareStatement("select * from koreanFoodsChain where num=? "); //조회
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				article = new FoodChainVO(); //객체 만듬
			
				//db에서 가져와야됨
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
	
	
	

