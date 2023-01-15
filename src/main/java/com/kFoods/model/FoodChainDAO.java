package com.kFoods.model;


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
	
	
	
}
