package com.kFoods.action;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kFoods.control.ActionForward;
import com.kFoods.model.FoodChainDAO;
import com.kFoods.model.FoodChainVO;

public class KoreanFoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int pageSize = 10;

	    //페이지 num을 가져온다
	    String pageNum = request.getParameter("pageNum");
	    
	    if(pageNum == null) {
	    	pageNum = "1";
	    }

	    //현재 페이지 
	    int currentPage =Integer.parseInt(pageNum);
	    
	    int startRow = (currentPage-1) * pageSize + 1; 
	    int endRow = currentPage * pageSize;    
	    int count = 0;
	      int number =0;
	      List<FoodChainVO> articleList = null;
	      FoodChainDAO dbPro = FoodChainDAO.getInstance(); // db연결
	      count = dbPro.getArticleCount();
	   
	       if(count > 0){  // 현재 페이지에 해당하는 글목록
	       //하나라도 존재 하면 리스트를 출력해라
	       articleList = dbPro.getArticles(startRow, endRow);
	    }	       
	 else { // 검색 일 경우		 
	      //하나라도 존재 하면 리스트를 출력해라
	      articleList = Collections.emptyList();  
	}	   
	  // 글 목록에 표시할 글 번호
	    number = count - (currentPage - 1) * pageSize;
	
	    // 해당 뷰에서 사용할 속성 저장
	    request.setAttribute("currentPage",currentPage);
	    request.setAttribute("startRow", startRow);
	    request.setAttribute("endRow", endRow);
	    request.setAttribute("count", count);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("number", number);
	    request.setAttribute("articleList", articleList);
	    
	    // 해당 뷰로 반환해줌
	
		
		return new ActionForward("/koreanFoods/koreanFoodsList.jsp", false);
	}

}
