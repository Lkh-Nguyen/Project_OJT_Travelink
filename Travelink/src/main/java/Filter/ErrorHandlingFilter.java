/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ErrorHandlingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        try {
//            chain.doFilter(request, response);
//        } catch (Throwable t) {
//            // Handle exception
//            t.printStackTrace();
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.sendRedirect("Error.jsp"); // Redirect to error page
//        }
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code
    }

}
