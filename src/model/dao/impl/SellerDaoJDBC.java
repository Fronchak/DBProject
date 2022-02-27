package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Db.DB;
import Db.DbException;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	

	
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
		
		Connection conn = null;
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
			while(rs.next()) {
				seller = new Seller(rs.getInt("S.ID"), rs.getString("S.NAME"), rs.getString("S.EMAIL"));
			}		
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
			DB.closeConnection();
			//return seller;
		}
		return seller;
	
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
