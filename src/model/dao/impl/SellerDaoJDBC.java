package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		Seller seller = null;
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
			
				seller = new Seller(rs.getInt("S.ID"), rs.getString("S.NAME"), rs.getString("S.EMAIL"),rs.getDate("S.BIRTHDATE"),
						rs.getDouble("S.BASESALARY"),
						new Department(rs.getInt("D.ID"), rs.getString("D.NAME")));
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

}
