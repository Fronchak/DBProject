package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(1);
		System.out.println("=============TEST SELLER FIND BY ID ======================");
		System.out.println(seller);
	
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = departmentDao.findById(15);
		System.out.println();
		System.out.println("=============TEST DEPARTMENT FIND BY ID ==================");
		System.out.println(department);
		System.out.println();
		System.out.println("==============TEST FIND BY DEPARTMENT =====================");
		
		List<Seller> list = sellerDao.findByDepartment(seller.getDepartment());	
		for(Seller sellerAux : list) {
			System.out.println(sellerAux);
		}
		System.out.println();
		System.out.println("==============TEST FIND BY DEPARTMENT =====================");
		for(Seller sellerAux2 : sellerDao.findAll()) {
			System.out.println(sellerAux2);
		}
		Seller newSeller = list.get(0);
		sellerDao.insert(newSeller);
		System.out.println("newSeller.getId() = " + newSeller.getId());
		
		Seller sellerUpdate = sellerDao.findById(5);
		sellerUpdate.setName("Gabriel Fronchak Gmack");
		System.out.println("SellerUpdate: " + sellerUpdate);
		sellerDao.update(sellerUpdate);
		sellerDao.deleteById(9);
	}
	

}
