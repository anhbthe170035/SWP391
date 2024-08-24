/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Feedback;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends context.DBContext {

    public boolean insertFeedback(Feedback feedback) {
        String query = "INSERT INTO [dbo].[ProductFeedback] "
                + "([feedbackid]\n"
                + "      ,[orderid]\n"
                + "      ,[sku]\n"
                + "      ,[feedback]\n"
                + "      ,[star]) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, feedback.getFeedbackid());
            st.setInt(2, feedback.getOrderid());
            st.setString(3, feedback.getSku());
            st.setString(4, feedback.getFeedback());
            st.setInt(5, feedback.getStar());
            
            int rowsAffected = st.executeUpdate();
            return rowsAffected < 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "select * from [dbo].[ProductFeedback]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Feedback getFeedbackBySku(String sku) {
        Feedback feedback = null;
        String query = "SELECT * FROM [dbo].[ProductFeedback]\n"
                + "INNER JOIN [dbo].[Orders] on [ProductFeedback].orderid = [Orders].id\n"
                + "WHERE sku = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, sku);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    feedback = mapResultSetToFeedback(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Cân nhắc sử dụng logging
        }

        return feedback;
    }

    private Feedback mapResultSetToFeedback(ResultSet resultSet) throws SQLException {
        int feedbackid = resultSet.getInt("feedbackid");
        int orderid = resultSet.getInt("orderid");
        String sku = resultSet.getString("sku");
        String feedback = resultSet.getString("feedback");
        int star = resultSet.getInt("star");
        
        return new Feedback(feedbackid, orderid, sku, feedback, star);
    }

}
