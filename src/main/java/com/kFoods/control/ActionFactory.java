package com.kFoods.control;

import com.kFoods.action.Action;
import com.kFoods.action.ContentAction;
import com.kFoods.action.IndexAction;
import com.kFoods.action.KoreanFoodsListAction;
import com.kFoods.action.MapAction;

public class ActionFactory {
	
	private static ActionFactory instance;
	
	private ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		if(instance == null) {
			instance = new ActionFactory();
		}
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		System.out.println(cmd);
		switch (cmd) {
		
		case "map":
			action = new MapAction();
			break;
			
		case "koreanFoodsList":
			action = new KoreanFoodsListAction();
			break;
			
		case "content":
			action = new ContentAction();
			break;
			
		default:
			action = new IndexAction();
			break;
		}
	
		return action;
	}

}
