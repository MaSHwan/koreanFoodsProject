package com.kFoods.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kFoods.control.ActionForward;
import com.kFoods.model.FoodChainDAO;
import com.kFoods.model.FoodChainVO;

public class ContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// �Ű��������� db��ü�鿡 �Ѱ��ش�?
		
			FoodChainDAO dbPro = FoodChainDAO.getInstance();
			FoodChainVO article = dbPro.getArticle(num);

			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
		
		return new ActionForward("/koreanFoods/content.jsp", false);
	}

}
