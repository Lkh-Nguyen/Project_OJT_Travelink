/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import static com.travelink.Database.ReservationDB.getReservationByReservationID;
import com.travelink.Model.Account;
import com.travelink.Model.Reservation;
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
public class PaymentFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session != null) {
            Account account = (Account) session.getAttribute("account");
            if (account != null) {
                // Check account role (assuming you have a method to get account role)
                int accountRole = account.getRole();
                if (accountRole == 1) {
                    Integer pendingReservationID = (Integer) session.getAttribute("pendingReservationID");
                    if (pendingReservationID != null) {
                        Reservation reservation = getReservationByReservationID(pendingReservationID);
                        if (reservation == null) {
                            //Remove all expired reservation related session
                            session.removeAttribute("pendingReservationID");
                            session.removeAttribute("paymentLinkId");
                            session.removeAttribute("checkoutUrl");
                        }
                    }
                }
            }
        }

        // Continue processing the request
        chain.doFilter(request, response);
    }
}
