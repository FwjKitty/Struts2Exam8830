package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDaoI;
import pojo.Customer;
import utils.ConnectionFactory;

public class CustomerDaoImpl implements CustomerDaoI {

	public List<Customer> getPageResult(int page,int pageSize) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		int currentPage = pageSize * (page - 1);
		String sql = "SELECT customer_id,first_name,last_name,email,customer.last_update,address FROM customer,address WHERE customer.address_id=address.address_id ORDER BY customer_id DESC limit "+currentPage+","+pageSize;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Customer> list = new ArrayList<Customer>();
		Customer customer = null;
		while(rs.next()){
			customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setFirst_name(rs.getString("first_name"));;
			customer.setLast_name(rs.getString("last_name"));
			customer.setAddress(rs.getString("address"));
			customer.setEmail(rs.getString("email"));
			customer.setLast_update(rs.getTimestamp("last_update"));
			list.add(customer);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}
	
	public int getCount() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		int result = 0;
		PreparedStatement ps = conn.prepareStatement("select count(*) from customer");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return result;
	}

	public Customer queryById(int customer_id) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT customer_id,first_name,last_name,email,last_update,address_id FROM customer WHERE customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer_id);
		ResultSet rs = ps.executeQuery();
		Customer customer = null;
		while(rs.next()){
			customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setFirst_name(rs.getString("first_name"));
			customer.setLast_name(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setLast_update(rs.getTimestamp("last_update"));
			customer.setAddress_id(rs.getInt("address_id"));
		}
		rs.close();
		ps.close();
		conn.close();
		return customer;
	}

	public boolean save(Customer customer) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "INSERT INTO customer(first_name,last_name,email,last_update,address_id,store_id,create_date) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customer.getFirst_name());
		ps.setString(2, customer.getLast_name());
		ps.setString(3, customer.getEmail());
		ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
		ps.setInt(5, customer.getAddress_id());
		ps.setInt(6, 1);
		ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
		boolean flag = false;
		if(ps.executeUpdate() != 0){
			flag = true;
		}
		ps.close();
		conn.close();
		return flag;
	}

	public boolean delete(int customer_id) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM customer WHERE customer_id=?");
		PreparedStatement ps1 = conn.prepareStatement("DELETE FROM payment WHERE customer_id=?");
		PreparedStatement ps2 = conn.prepareStatement("DELETE FROM rental WHERE customer_id=?");
		ps.setInt(1, customer_id);
		ps1.setInt(1, customer_id);
		ps2.setInt(1, customer_id);
		ps1.executeUpdate();
		ps2.executeUpdate();
		ps.executeUpdate();
		return true;
//		try{
//			ps1.executeUpdate();
//		}catch(Exception e){
//			e.printStackTrace();
//			ps1.close();
//			try{
//				ps2.executeUpdate();
//			}catch(Exception e2){
//				ps2.close();
//				ps.setInt(1, customer_id);
//				if(ps.executeUpdate() != 0){
//					ps.close();
//					conn.close();
//					return true;
//				}
//				ps.close();
//				conn.close();
//				return false;
//			}
//			ps.setInt(1, customer_id);
//			if(ps.executeUpdate() != 0){
//				ps2.close();
//				ps.close();
//				conn.close();
//				return true;
//			}
//			ps2.close();
//			ps.close();
//			conn.close();
//			return false;
//		}
//		ps1.close();
//		try{
//			ps2.executeUpdate();
//		}catch(Exception e2){
//			ps2.close();
//			ps.setInt(1, customer_id);
//			if(ps.executeUpdate() != 0){
//				ps.close();
//				conn.close();
//				return true;
//			}
//			ps.close();
//			conn.close();
//			return false;
//		}
//		ps.setInt(1, customer_id);
//		if(ps.executeUpdate() != 0){
//			ps.close();
//			conn.close();
//			return true;
//		}
//		ps.close();
//		conn.close();
//		return true;
	}

	public boolean update(Customer customer) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "UPDATE customer SET first_name=?,last_name=?,email=?,address_id=?,last_update=?,customer_id=?,store_id=?,create_date=? WHERE customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customer.getFirst_name());
		ps.setString(2, customer.getLast_name());
		ps.setString(3, customer.getEmail());
		ps.setInt(4, customer.getAddress_id());
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setInt(6, customer.getCustomer_id());
		ps.setInt(7, 1);
		ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
		ps.setInt(9, customer.getCustomer_id());
		boolean flag = false;
		if(ps.executeUpdate() != 0){
			flag = true;
		}
		ps.close();
		conn.close();
		return flag;
	}

	public boolean queryByFirst_nameAndLast_name(String first_name, String last_name) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT * FROM customer WHERE first_name=? AND last_name=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, first_name);
		ps.setString(2, last_name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			return true;
		}
		rs.close();
		ps.close();
		conn.close();
		return false;
	}

	@Override
	public List<Customer> queryAll() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT customer_id,first_name,last_name,email,customer.last_update,address FROM customer,address WHERE customer.address_id=address.address_id ORDER BY customer_id DESC";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Customer> list = new ArrayList<Customer>();
		Customer customer = null;
		while(rs.next()){
			customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setFirst_name(rs.getString("first_name"));;
			customer.setLast_name(rs.getString("last_name"));
			customer.setAddress(rs.getString("address"));
			customer.setEmail(rs.getString("email"));
			customer.setLast_update(rs.getTimestamp("last_update"));
			list.add(customer);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}
}