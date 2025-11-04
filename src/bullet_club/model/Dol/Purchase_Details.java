package bullet_club.model.Dol;

import bullet_club.entity.Product;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Purchase_Details implements Data_Conn {
    
    // Calculate total for a line (quantity * price)
    public double calcTotal(int quantity, int price) {
        return (double) quantity * (double) price;
    }

    // Create (add) purchase detail row for an order
    public boolean addDetail(int orderId, bullet_club.entity.Purchase_Details detail) {
        try {
            String sql = "INSERT INTO purchase_details (order_id, product_id, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
            data_base.openConn();
            data_base.createps(sql);

            data_base.getPs().setInt(1, orderId);
            data_base.getPs().setInt(2, detail.getItem().getId());
            data_base.getPs().setInt(3, detail.getQuantity());
            data_base.getPs().setInt(4, detail.getPrice());
            data_base.getPs().setDouble(5, detail.total());

            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // Read by id
    public bullet_club.entity.Purchase_Details getDetailById(int id) {
        try {
            String sql = "SELECT pd.id, pd.order_id, pd.product_id, pd.quantity, pd.price, p.name, p.category, p.description, p.price AS product_price, p.quantity AS product_quantity, p.reorder_level FROM purchase_details pd JOIN product p ON p.id = pd.product_id WHERE pd.id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, id);
            data_base.createRs();
            ResultSet rs = data_base.getRs();

            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getInt("reorder_level")
                );
                bullet_club.entity.Purchase_Details detail = new bullet_club.entity.Purchase_Details(
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        product
                );
                data_base.closeRs();
                data_base.closePs();
                data_base.closseConn();
                return detail;
            }

            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // List details for an order
    public ArrayList<bullet_club.entity.Purchase_Details> listDetailsByOrder(int orderId) {
        ArrayList<bullet_club.entity.Purchase_Details> list = new ArrayList<>();
        try {
            String sql = "SELECT pd.id, pd.order_id, pd.product_id, pd.quantity, pd.price, p.name, p.category, p.description, p.price AS product_price, p.quantity AS product_quantity, p.reorder_level FROM purchase_details pd JOIN product p ON p.id = pd.product_id WHERE pd.order_id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, orderId);
            data_base.createRs();
            ResultSet rs = data_base.getRs();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getInt("reorder_level")
                );
                bullet_club.entity.Purchase_Details detail = new bullet_club.entity.Purchase_Details(
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        product
                );
                list.add(detail);
            }
            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // Update detail
    public boolean updateDetail(int detailId, int quantity, int price) {
        try {
            String sql = "UPDATE purchase_details SET quantity = ?, price = ?, total = ? WHERE id = ?";
            data_base.openConn();
            data_base.createps(sql);
            data_base.getPs().setInt(1, quantity);
            data_base.getPs().setInt(2, price);
            data_base.getPs().setDouble(3, calcTotal(quantity, price));
            data_base.getPs().setInt(4, detailId);
            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete detail
    public boolean deleteDetail(int id) {
        try {
            String sql = "DELETE FROM purchase_details WHERE id = ?";
            data_base.openConn();
            data_base.createps(sql);
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
}
