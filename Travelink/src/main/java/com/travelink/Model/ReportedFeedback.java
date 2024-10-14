package com.travelink.Model;

import com.travelink.Database.AccountDB;
import java.sql.Timestamp;

public class ReportedFeedback {

    public int getReportedFeedbackId() {
        return reportedFeedbackId;
    }

    public void setReportedFeedbackId(int reportedFeedbackId) {
        this.reportedFeedbackId = reportedFeedbackId;
    }
    private int reportedFeedbackId;
    private Timestamp reportTime;
    private String reason;
    private int feedbackId;
    private int accountId;

    public ReportedFeedback() {
    }
    
    public ReportedFeedback(Timestamp reportTime, String reason, int feedbackId, int accountId) {
        this.reportTime = reportTime;
        this.reason = reason;
        this.feedbackId = feedbackId;
        this.accountId = accountId;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public String getReason() {
        return reason;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public Account getAccount(int accountID){
        return AccountDB.getAccountByAccountID(accountID);
    }
    
    @Override
    public String toString() {
        return "ReportedFeedback{" + "reportedFeedbackId=" + reportedFeedbackId + ", reportTime=" + reportTime + ", reason=" + reason + ", feedbackId=" + feedbackId + ", accountId=" + accountId + '}';
    }
    
}   
