package interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLoginInterceptor implements Interceptor {

	public void destroy() {}
	public void init() {}

	public String intercept(ActionInvocation invocation) throws Exception {
		String un = (String) ServletActionContext.getRequest().getSession().getAttribute("first_name");
		String url;
		if(null != un){
			url = invocation.invoke();
		}else{
			url = "login";
		}
		return url;
	}
}