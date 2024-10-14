package com.travelink.Servlet;

import com.travelink.Database.FeedbackDB;
import com.travelink.Model.Account;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReactFeedbackServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ReactFeedbackServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int feedbackID = 0;
        try {
            feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid feedback ID", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            LOGGER.log(Level.SEVERE, "User not logged in");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int accountID = account.getAccount_ID();

        try {
            switch (action) {
                case "like":
                    handleLikeAction(accountID, feedbackID);
                    break;
                case "unlike":
                    handleUnlikeAction(accountID, feedbackID);
                    break;
                case "dislike":
                    handleDislikeAction(accountID, feedbackID);
                    break;
                case "undislike":
                    handleUndislikeAction(accountID, feedbackID);
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Unknown action: " + action);
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Database error", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleLikeAction(int accountID, int feedbackID) throws SQLException {
        if (!FeedbackDB.hasUserLikedFeedback(accountID, feedbackID)) {
            FeedbackDB.incrementLikesCount(feedbackID);
            FeedbackDB.addUserFeedbackLike(accountID, feedbackID);
        }
    }

    private void handleUnlikeAction(int accountID, int feedbackID) throws SQLException {
        if (FeedbackDB.hasUserLikedFeedback(accountID, feedbackID)) {
            FeedbackDB.decrementLikesCount(feedbackID);
            FeedbackDB.removeUserLike(accountID, feedbackID);
        }
    }

    private void handleDislikeAction(int accountID, int feedbackID) throws SQLException {
        if (!FeedbackDB.hasUserDislikedFeedback(accountID, feedbackID)) {
            FeedbackDB.incrementDisLikesCount(feedbackID);
            FeedbackDB.addUserFeedbackDislike(accountID, feedbackID);
        }
    }

    private void handleUndislikeAction(int accountID, int feedbackID) throws SQLException {
        if (FeedbackDB.hasUserDislikedFeedback(accountID, feedbackID)) {
            FeedbackDB.decrementDisLikesCount(feedbackID);
            FeedbackDB.removeUserDislike(accountID, feedbackID);
        }
    }
}
