package ex0409.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	String dbId="ch";
	String dbPwd="1234";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet....");
		
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost....");
		
		//넘어오는 3개의 정보를 받아서 화면에 출력하기. 
		
		resp.setContentType("text/html;charset=utf-8");//이거 안해주면 물음표로 뜸 
		req.setCharacterEncoding("utf-8");
		
		String id=req.getParameter("userId");
		String pwd=req.getParameter("userPwd");
		String name=req.getParameter("userName");
		
		PrintWriter out=resp.getWriter();
//		out.print("<html>");
//		out.print("<head><title>LoginServlet</title></head>");
//		out.print("<body>");
//		
//		out.println("아이디 :"+id);
//		out.println("비밀번호: "+pwd);
//		out.println("이름: "+name);
//		
//		out.print("</body>");
//		out.print("</html>");
//		
		
		if(dbId.equals(id)&& dbPwd.equals(pwd)) {
			
			//service ----> dao에서 db에 있는 데이터를 가져와서 뷰쪽으로 전달. 
			List<String> list=new ArrayList<String>();
			list.add("등산"); 
			list.add("수영");
			list.add("골프");
			list.add("낚시");
			
			req.setAttribute("list", list);
			
			
			//이동 
			//1.forward 
			RequestDispatcher rd= req.getRequestDispatcher("LoginOk.jsp");
			rd.forward(req, resp);
			
			//2.redirect
			//resp.sendRedirect("LoginOk.jsp?userName="+URLEncoder.encode(name, "utf-8"));
			
		}else {
			//메세지 출력
			//뒤로가기 
			out.println("<script>");
			out.println("alert('"+name+"님 회원정보를 확인해주세요  ')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
