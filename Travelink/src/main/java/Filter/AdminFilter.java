/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import com.travelink.Model.Account;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class AdminFilter implements Filter {

    @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            if (session != null) {
                Account account = (Account) session.getAttribute("account");
                if (account == null) {
                    res.sendRedirect("Form_Login.jsp");
                    return;
                }
                else if (account.getRole() != 3){
                    res.sendRedirect("Error.jsp");
                    return;
                }
            }
            chain.doFilter(request, response);
        }
}
