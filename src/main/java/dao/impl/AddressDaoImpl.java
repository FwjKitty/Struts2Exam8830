package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AddressDaoI;
import pojo.Address;
import utils.ConnectionFactory;

public class AddressDaoImpl implements AddressDaoI {

	public List<Address> queryAll() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT address_id,address FROM address";
		List<Address> list = new ArrayList<Address>();
		Address address;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			address = new Address();
			address.setAddress_id(rs.getInt("address_id"));
			address.setAddress(rs.getString("address"));
			list.add(address);
		}
		return list;
	}
}