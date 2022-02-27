package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Db.DB;
import Db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;

		try {
			conn = DB.createConnection();
			ps = conn.prepareStatement("SELECT S.ID, S.NAME, S.EMAIL, S.BIRTHDATE, S.BASESALARY, D.ID, D.NAME "
					+ "FROM SELLER S "
					+ "INNER JOIN DEPARTMENT D "
					+ "ON S.ID = D.ID "
					+ "WHERE S.ID = ?");
			
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}	
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private Department instantiateDepartment(ResultSet rs) {
		try {
			Department department = new Department();
			department.setId(rs.getInt("D.ID"));
			department.setName(rs.getString("D.NAME"));
			return department;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private Seller instantiateSeller(ResultSet rs, Department department) {
		try {
			Seller seller = new Seller();
			seller.setId(rs.getInt("S.ID"));
			seller.setName(rs.getString("S.NAME"));
			seller.setEmail(rs.getString("S.EMAIL"));
			seller.setBirthDate(rs.getDate("S.BIRTHDATE"));
			seller.setBaseSalary(rs.getDouble("S.BASESALARy"));
			seller.setDepartment(department);
			return seller;
		}
		catch(SQLException e) {
			throw new DbException (e.getMessage());
		}
	}
}
