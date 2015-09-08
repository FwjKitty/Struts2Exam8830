package action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import dao.AddressDaoI;
import dao.CustomerDaoI;
import dao.impl.AddressDaoImpl;
import dao.impl.CustomerDaoImpl;
import pojo.Address;
import pojo.Customer;

public class CustomerAction {

	private Customer customer;
	private int page;
	private int count;
	private String msg;
	private CustomerDaoI customerDao;
	private AddressDaoI addressDao;
	public String edit(){
		customerDao = new CustomerDaoImpl();
		addressDao = new AddressDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Address> list = new ArrayList<Address>();
		Customer c = new Customer();
		try {
			list = addressDao.queryAll();
			c = customerDao.queryById(customer.getCustomer_id());
		} catch (SQLException e) {
			e.printStackTrace();
			this.setPage(1);
			return "editError";
		}
		request.setAttribute("list", list);
		this.setCustomer(c);
		return "editSuccess";
	}
	
	public String update(){
		customerDao = new CustomerDaoImpl();
		addressDao = new AddressDaoImpl();
		try {
			if(customerDao.update(customer)){
				this.setMsg("更新成功！");
				this.setPage(1);
				return "updateSuccess";
			}else{
				this.setCustomer(customer);
				this.setMsg("更新失败，请重新编辑！");
				return "updateError";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.setCustomer(customer);
			this.setMsg("更新失败，请重新编辑！");
			return "updateError";
		}
		
	}
	
	public String show(){
		customerDao = new CustomerDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			List<Customer> list = customerDao.getPageResult(page, 10);
			int count = customerDao.getCount();
			request.setAttribute("list", list);
			this.setPage(page);
			this.setCount(count);
			return "showSuccess";
		} catch (SQLException e) {
			e.printStackTrace();
			return "showError";
		}
	}
	
	public String add(){
		addressDao = new AddressDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Address> list = new ArrayList<Address>();
		try {
			list = addressDao.queryAll();
			request.setAttribute("list", list);
			return "addSuccess";
		} catch (SQLException e) {
			e.printStackTrace();
			page = 1;
			return "addError";
		}
	}
	
	public String save(){
		customerDao = new CustomerDaoImpl();
		try {
			if(customerDao.save(customer)){
				this.setMsg("添加成功！");
				this.setPage(1);
				return "saveSuccess";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.setMsg("添加失败，请重新添加！");
			return "saveError";
		}
		this.setMsg("添加失败，请重新添加！");
		return "saveError";
	}
	
	public String del() {
		customerDao = new CustomerDaoImpl();
		try {
			if(customerDao.delete(customer.getCustomer_id())){
				//this.setMsg(URLDecoder.decode("删除Customer成功！","UTF-8"));
				this.setMsg("删除Customer成功！");
				this.setPage(1);
				return "del";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.setPage(1);
			//this.setMsg(URLDecoder.decode("删除Customer失败！","UTF-8"));
			this.setMsg("删除Customer失败！");
			return "del";
		}
		this.setMsg("删除Customer失败！");
		this.setPage(1);
		return "del";
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}