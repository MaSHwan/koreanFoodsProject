package com.kFoods.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kFoods.control.ActionForward;

public class MapAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		return new ActionForward("/koreanFoods/map.jsp", false);
	}

}
