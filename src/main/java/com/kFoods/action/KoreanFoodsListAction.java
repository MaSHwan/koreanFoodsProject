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

	    //������ num�� �����´�
	    String pageNum = request.getParameter("pageNum");
	    
	    if(pageNum == null) {
	    	pageNum = "1";
	    }

	    //���� ������ 
	    int currentPage =Integer.parseInt(pageNum);
	    
	    int startRow = (currentPage-1) * pageSize + 1; 
	    int endRow = currentPage * pageSize;    
	    int count = 0;
	      int number =0;
	      List<FoodChainVO> articleList = null;
	      FoodChainDAO dbPro = FoodChainDAO.getInstance(); // db����
	      count = dbPro.getArticleCount();
	   
	       if(count > 0){  // ���� �������� �ش��ϴ� �۸��
	       //�ϳ��� ���� �ϸ� ����Ʈ�� ����ض�
	       articleList = dbPro.getArticles(startRow, endRow);
	    }	       
	 else { // �˻� �� ���		 
	      //�ϳ��� ���� �ϸ� ����Ʈ�� ����ض�
	      articleList = Collections.emptyList();  
	}	   
	  // �� ��Ͽ� ǥ���� �� ��ȣ
	    number = count - (currentPage - 1) * pageSize;
	
	    // �ش� �信�� ����� �Ӽ� ����
	    request.setAttribute("currentPage",currentPage);
	    request.setAttribute("startRow", startRow);
	    request.setAttribute("endRow", endRow);
	    request.setAttribute("count", count);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("number", number);
	    request.setAttribute("articleList", articleList);
	    
	    // �ش� ��� ��ȯ����
	
		
		return new ActionForward("/koreanFoods/koreanFoodsList.jsp", false);
	}

}
