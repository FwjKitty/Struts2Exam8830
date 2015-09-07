package action;

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
			request.setAttribute("page", "1");
			return "editError";
		}
		request.setAttribute("list", list);
		request.setAttribute("customer", c);
		return "editSuccess";
	}
	
	public String update(){
		customerDao = new CustomerDaoImpl();
		addressDao = new AddressDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			System.out.println(customer.getCustomer_id());
			if(customerDao.update(customer)){
				request.setAttribute("msg", "更新成功！");
				request.setAttribute("page", "1");
				return "updateSuccess";
			}else{
				request.setAttribute("customer.customer_id", customer.getCustomer_id());
				request.setAttribute("msg", "更新失败，请重新编辑！");
				return "updateError";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("customer.customer_id", customer.getCustomer_id());
			request.setAttribute("msg", "更新失败，请重新编辑！");
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
			request.setAttribute("page", String.valueOf(page));
			request.setAttribute("acount", String.valueOf(count));
		} catch (SQLException e) {
			e.printStackTrace();
			return "showError";
		}
		return "showSuccess";
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
			request.setAttribute("page", "1");
			return "addError";
		}
	}
	
	public String save(){
		customerDao = new CustomerDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(customerDao.save(customer)){
				request.setAttribute("msg", "添加成功！");
				request.setAttribute("page", "1");
				return "saveSuccess";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败，请重新添加！");
			return "saveError";
		}
		request.setAttribute("msg", "添加失败，请重新添加！");
		return "saveError";
	}
	
	public String del(){
		customerDao = new CustomerDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(customerDao.delete(customer.getCustomer_id())){
				request.setAttribute("msg", "删除Customer成功！");
				request.setAttribute("page", "1");
				return "del";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("page", "1");
			request.setAttribute("msg", "删除Customer失败！");
			return "del";
		}
		request.setAttribute("msg", "删除Customer失败！");
		request.setAttribute("page", "1");
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
}