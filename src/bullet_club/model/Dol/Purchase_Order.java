package bullet_club.model.Dol;

import bullet_club.entity.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Purchase_Order implements Data_Conn {

    private boolean isAuthorized(User user) {
        if (user == null || user.getRole() == null) return false;
        String role = user.getRole().toLowerCase();
        return role.equals("admin") || role.equals("manager");
    }

    // Create order (header only). Details should be added separately
    public boolean createOrder(bullet_club.entity.Purchase_Order order, User user) {
        if (!isAuthorized(user)) return false;
        try {
            String sql = "INSERT INTO purchase_order (id, status, total_amount, order_date) VALUES (?, ?, ?, ?)";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, order.getId());
            data_base.getPs().setString(2, order.getStatus());
            data_base.getPs().setInt(3, order.getTotal_Amount());
            data_base.getPs().setDate(4, Date.valueOf(order.getDate()));
            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // Read order by id, including items
    public bullet_club.entity.Purchase_Order getOrderById(int id) {
        try {
            String sql = "SELECT id, status, total_amount, order_date FROM purchase_order WHERE id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, id);
            data_base.createRs();
            ResultSet rs = data_base.getRs();
            if (rs.next()) {
                String status = rs.getString("status");
                int totalAmount = rs.getInt("total_amount");
                LocalDate date = rs.getDate("order_date").toLocalDate();

                // Load details
                bullet_club.model.Dol.Purchase_Details detailsDol = new bullet_club.model.Dol.Purchase_Details();
                ArrayList<bullet_club.entity.Purchase_Details> items = detailsDol.listDetailsByOrder(id);

                bullet_club.entity.Purchase_Order po = new bullet_club.entity.Purchase_Order(
                        id, status, totalAmount, date, items
                );

                data_base.closeRs();
                data_base.closePs();
                data_base.closseConn();
                return po;
            }
            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Update order header
    public boolean updateOrder(bullet_club.entity.Purchase_Order order, User user) {
        if (!isAuthorized(user)) return false;
        try {
            String sql = "UPDATE purchase_order SET status = ?, total_amount = ?, order_date = ? WHERE id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setString(1, order.getStatus());
            data_base.getPs().setInt(2, order.getTotal_Amount());
            data_base.getPs().setDate(3, Date.valueOf(order.getDate()));
            data_base.getPs().setInt(4, order.getId());
            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete order (and its details)
    public boolean deleteOrder(int id, User user) {
        if (!isAuthorized(user)) return false;
        try {
            data_base.openConn();

            // Delete details first due to FK
            String delDetails = "DELETE FROM purchase_details WHERE order_id = ?";
            data_base.createps(delDetails);
            data_base.getPs().setInt(1, id);
            data_base.getPs().executeUpdate();
            data_base.closePs();

            String delOrder = "DELETE FROM purchase_order WHERE id = ?";
            data_base.createps(delOrder);
            data_base.getPs().setInt(1, id);
            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // totalprice: calculate order total from details (sum)
    public long totalprice(int orderId) {
        try {
            String sql = "SELECT COALESCE(SUM(total), 0) AS total FROM purchase_details WHERE order_id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, orderId);
            data_base.createRs();
            ResultSet rs = data_base.getRs();
            long total = 0;
            if (rs.next()) {
                total = rs.getLong("total");
            }
            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
            return total;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // getPerchaseorderinfo: return a brief info string
    public String getPerchaseorderinfo(int orderId) {
        bullet_club.entity.Purchase_Order order = getOrderById(orderId);
        if (order == null) return null;
        return order.order_info();
    }

    // editstatus: update status with authorization
    public boolean editstatus(int orderId, String newStatus, User user) {
        if (!isAuthorized(user)) return false;
        try {
            String sql = "UPDATE purchase_order SET status = ? WHERE id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setString(1, newStatus);
            data_base.getPs().setInt(2, orderId);
            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // updatestock: add purchased quantities to product stock
    public boolean updatestock(int orderId, User user) {
        if (!isAuthorized(user)) return false;
        try {
            String sql = "SELECT product_id, quantity FROM purchase_details WHERE order_id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, orderId);
            data_base.createRs();
            ResultSet rs = data_base.getRs();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int qty = rs.getInt("quantity");
                String upd = "UPDATE product SET quantity = quantity + ? WHERE id = ?";
                data_base.closePs();
                data_base.createps(upd);
                data_base.getPs().setInt(1, qty);
                data_base.getPs().setInt(2, productId);
                data_base.getPs().executeUpdate();
            }
            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
