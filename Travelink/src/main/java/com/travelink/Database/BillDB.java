/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Bill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillDB implements DatabaseInfo {

    public static void createBillView() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Check if the view already exists
                String checkViewQuery = "SELECT COUNT(*) FROM information_schema.views WHERE table_name = 'Bill'";
                try (Statement checkStatement = conn.createStatement(); ResultSet resultSet = checkStatement.executeQuery(checkViewQuery)) {
                    resultSet.next();
                    int viewCount = resultSet.getInt(1);

                    // If the view doesn't exist, create it
                    if (viewCount == 0) {
                        String createViewQuery = "CREATE VIEW Bill AS "
                                + "SELECT r.Reservation_ID, r.CheckInDate, r.CheckOutDate, r.Number_of_guests, r.Reservation_Date, r.Account_ID, r.Status, r.Total_Price, "
                                + "       rm.Room_ID, rm.Name as Room_Name, rm.Price as Room_Price, h.Name, h.CheckInTimeStart, h.CheckInTimeEnd, h.CheckOutTimeStart, h.CheckOutTimeEnd,rr.Amount,h.Hotel_ID "
                                + "FROM Reservation r "
                                + "JOIN Reserved_Room rr ON r.Reservation_ID = rr.Reservation_ID "
                                + "JOIN Room rm ON rr.Room_ID = rm.Room_ID "
                                + "JOIN Hotel h on rm.Hotel_ID = h.Hotel_ID";
                        ps = conn.prepareStatement(createViewQuery);
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating view Bill: " + e);
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
    }

    public static List<Bill> getBillByHotelID(int hotel_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                String query = "WITH RankedBills AS ( "
                        + "    SELECT *, "
                        + "           ROW_NUMBER() OVER (PARTITION BY Reservation_ID ORDER BY Name) as rn "
                        + "    FROM Bill "
                        + "    WHERE hotel_ID = ? )"
                        + "SELECT * "
                        + "FROM RankedBills "
                        + "WHERE rn = 1";
                ps = conn.prepareStatement(query);
                ps.setInt(1, hotel_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return list_bill;
    }

    public static List<Bill> getBillDateByHotelID(int hotel_ID, java.sql.Date dateOfStart, java.sql.Date dateOfEnd) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                String query
                        = "WITH RankedBills AS ( "
                        + "    SELECT *, "
                        + "           ROW_NUMBER() OVER (PARTITION BY Reservation_ID ORDER BY Name) as rn "
                        + "    FROM Bill "
                        + "    WHERE hotel_ID = ? "
                        + "    AND Reservation_Date BETWEEN ? AND ?"
                        + ") "
                        + "SELECT * "
                        + "FROM RankedBills "
                        + "WHERE rn = 1 ";
                ps = conn.prepareStatement(query);
                ps.setInt(1, hotel_ID);
                ps.setDate(2, dateOfStart);
                ps.setDate(3, dateOfEnd);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return list_bill;
    }

    public static List<Bill> getBillDateByReservationID(int reservationID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                String query = "SELECT * FROM Bill WHERE Reservation_ID = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, reservationID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return list_bill;
    }

    public static List<Bill> getBillByCustomerID(int account_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                String query = "SELECT * FROM Bill WHERE Account_ID = ? ORDER BY Reservation_ID desc";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return list_bill;
    }

    public static List<Bill> getBillNotYetStayByCustomerID(int account_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Query to select bills based on checkInDate, checkOutDate, status 'finished' or 'processing'
                String query = "SELECT * FROM Bill WHERE Account_ID = ? AND "
                        + "CheckInDate >= GETDATE() AND (Status = 'NOT PAID' OR Status = 'PAID') ORDER BY Reservation_ID desc";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting bills: " + e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return list_bill;
    }

    public static List<Bill> getBillProcessByCustomerID(int account_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Query to select bills based on checkInDate, checkOutDate, status 'finished' or 'processing'
                String query = "SELECT * FROM Bill WHERE Account_ID = ? AND "
                        + "(CheckOutDate >= GETDATE() AND CheckInDate <= GETDATE()) AND (Status = 'PROCESSING') ORDER BY Reservation_ID desc";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting bills: " + e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return list_bill;
    }

    public static List<Bill> getBillFinishedByCustomerID(int account_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Query to select bills based on checkInDate, checkOutDate, status 'finished' or 'processing'
                String query = "SELECT * FROM Bill WHERE Account_ID = ? AND "
                        + "CheckOutDate < GETDATE() AND (Status = 'FINISHED' OR Status = 'FEEDBACKED') ORDER BY Reservation_ID desc";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting bills: " + e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return list_bill;
    }

    public static List<Bill> getBillCancelByCustomerID(int account_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Query to select bills based on Account_ID and status 'CANCEL' or 'REFUNDING'
                String query = "SELECT * FROM Bill WHERE Account_ID = ? AND (Status = 'CANCEL' OR Status = 'REFUNDING') ORDER BY Reservation_ID desc";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));

                    // Convert java.sql.Date to LocalDate safely
                    java.sql.Date checkInDate = rs.getDate("CheckInDate");
                    if (checkInDate != null) {
                        bill.setCheckInDate(checkInDate.toLocalDate());
                    }

                    java.sql.Date checkOutDate = rs.getDate("CheckOutDate");
                    if (checkOutDate != null) {
                        bill.setCheckOutDate(checkOutDate.toLocalDate());
                    }

                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));

                    // Convert java.sql.Time to LocalTime safely
                    java.sql.Time checkInTimeStart = rs.getTime("CheckInTimeStart");
                    if (checkInTimeStart != null) {
                        bill.setCheckInTimeStart(checkInTimeStart.toLocalTime());
                    }

                    java.sql.Time checkInTimeEnd = rs.getTime("CheckInTimeEnd");
                    if (checkInTimeEnd != null) {
                        bill.setCheckInTimeEnd(checkInTimeEnd.toLocalTime());
                    }

                    java.sql.Time checkOutTimeStart = rs.getTime("CheckOutTimeStart");
                    if (checkOutTimeStart != null) {
                        bill.setCheckOutTimeStart(checkOutTimeStart.toLocalTime());
                    }

                    java.sql.Time checkOutTimeEnd = rs.getTime("CheckOutTimeEnd");
                    if (checkOutTimeEnd != null) {
                        bill.setCheckOutTimeEnd(checkOutTimeEnd.toLocalTime());
                    }

                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));

                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting bills: " + e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return list_bill;
    }

    public static List<Bill> getBillByCustomerIDAndReservationID(int account_ID, int reservation_ID) {
        createBillView();
        List<Bill> list_bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                String query = "SELECT * FROM Bill WHERE Account_ID = ? AND Reservation_ID = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, account_ID);
                ps.setInt(2, reservation_ID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setAccount_ID(rs.getInt("Account_ID"));
                    bill.setReservationID(rs.getInt("Reservation_ID"));
                    bill.setCheckInDate(rs.getDate("CheckInDate").toLocalDate());
                    bill.setCheckOutDate(rs.getDate("CheckOutDate").toLocalDate());
                    bill.setNumber_of_guest(rs.getInt("Number_of_guests"));
                    bill.setReservationDate(rs.getDate("Reservation_Date").toLocalDate());
                    bill.setRoom_ID(rs.getInt("Room_ID"));
                    bill.setRoom_Name(rs.getString("Room_Name"));
                    bill.setRoom_price(rs.getInt("Room_Price"));
                    bill.setHotel_Name(rs.getString("Name"));
                    bill.setCheckInTimeStart(rs.getTime("CheckInTimeStart").toLocalTime());
                    bill.setCheckInTimeEnd(rs.getTime("CheckInTimeEnd").toLocalTime());
                    bill.setCheckOutTimeStart(rs.getTime("CheckOutTimeStart").toLocalTime());
                    bill.setCheckOutTimeEnd(rs.getTime("CheckOutTimeEnd").toLocalTime());
                    bill.setStatus(rs.getString("Status"));
                    bill.setTotal_price(rs.getInt("Total_Price"));
                    bill.setAmount(rs.getInt("Amount"));
                    bill.setHotel_ID(rs.getInt("Hotel_ID"));
                    list_bill.add(bill);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return list_bill;
    }

    public static void main(String[] args) throws SQLException {
        List<Bill> list = BillDB.getBillNotYetStayByCustomerID(2);
        for (Bill b : list) {
            System.out.println(b.toString());
        }

    }

}
