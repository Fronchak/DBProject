package model.dao;

import Db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		
		return new SellerDaoJDBC(DB.createConnection());
	}
	
}
