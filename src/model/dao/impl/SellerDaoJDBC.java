package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					+ "ON S.DEPARTMENTID = D.ID "
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
	public List<Seller> findByDepartment(Department department){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT S.Id, "
					+ "S.Name, "
					+ "S.Email, "
					+ "S.BirthDate, "
					+ "S.BaseSalary,"
					+ "D.ID,"
					+ "D.Name "
					+ "FROM seller S "
					+ "INNER JOIN department D "
					+ "ON S.DepartmentId = D.ID "
					+ "WHERE S.departmentId = ? "
					+ "ORDER BY S.ID");
			//					+ "WHERE S.departmentId = ? "
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("D.ID"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("D.ID"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				
				list.add(seller);
				
			}
			
			return list;
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
		Statement st = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT S.Id, "
					+ "S.Name, "
					+ "S.Email, "
					+ "S.BirthDate, "
					+ "S.BaseSalary, "
					+ "D.Id, "
					+ "D.Name "
					+ "FROM seller S "
					+ "INNER JOIN department D "
					+ "ON S.DepartmentId = D.Id");
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {

				Department dep = map.get(rs.getInt("D.id"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
				}
				
				Seller seller = instantiateSeller(rs,dep);
				list.add(seller);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
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
	
	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
		Seller seller = new Seller();
		seller.setId(rs.getInt("S.ID"));
		seller.setName(rs.getString("S.NAME"));
		seller.setEmail(rs.getString("S.EMAIL"));
		seller.setBirthDate(rs.getDate("S.BIRTHDATE"));
		seller.setBaseSalary(rs.getDouble("S.BASESALARy"));
		seller.setDepartment(department);
		return seller;
	}
	
}
