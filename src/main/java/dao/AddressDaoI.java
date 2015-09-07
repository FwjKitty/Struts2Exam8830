package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Address;

public interface AddressDaoI {

	List<Address> queryAll() throws SQLException;
}
