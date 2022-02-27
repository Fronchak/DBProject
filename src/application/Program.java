package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(5);
		
		System.out.println("=============TEST SELLER FIND BY ID ======================");
		System.out.println(seller);
	
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = departmentDao.findById(15);
		
		System.out.println("=============TEST DEPARTMENT FIND BY ID ==================");
		System.out.println(department);
	}
	

}
