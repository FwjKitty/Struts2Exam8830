package action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.CustomerDaoI;
import dao.impl.CustomerDaoImpl;
import pojo.Customer;

public class LoginAction extends ActionSupport {

	private String first_name;
	private String last_name;
	private CustomerDaoI customerDao;
	public String execute(){
		customerDao = new CustomerDaoImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(customerDao.queryByFirst_nameAndLast_name(first_name,last_name)){
				request.getSession().setAttribute("first_name", first_name);
				List<Customer> list = customerDao.getPageResult(1,10);
				int count = customerDao.getCount();
				request.setAttribute("list", list);
				request.setAttribute("count", String.valueOf(count));
				request.setAttribute("page", "1");
				return "success";
			}else{
				request.setAttribute("msg", "登录失败！");
				return "login";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "登录失败！");
			return "login";
		}
	}
	
	public String exit(){
		ServletActionContext.getRequest().getSession().setAttribute("first_name", null);
		return "exit";
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
}