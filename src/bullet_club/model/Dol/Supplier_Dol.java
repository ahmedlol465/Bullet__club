package bullet_club.model.Dol;

import bullet_club.entity.Supplier;
import java.sql.ResultSet;

public class Supplier_Dol implements Data_Conn {

    //  Add Supplier (Only if doesn't exist)
    public String addSupplier(Supplier supplier) {
        try {
            Supplier existing = getSupplierByName(supplier.getName());
            
            if (existing != null) {
                return "Supplier already exists: " + existing.getEmail();
            }

            String insert = "INSERT INTO supplier (id, name, email) VALUES (?, ?, ?)";
            data_base.openConn();
            data_base.createps(insert);

            data_base.getPs().setInt(1, supplier.getId());
            data_base.getPs().setString(2, supplier.getName());
            data_base.getPs().setString(3, supplier.getEmail());

            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();

            return rows > 0 ? "Supplier added" : "Insert failed";

        } catch (Exception e) {
            System.out.println(e);
            return "Error: " + e.getMessage();
        }
    }

    // Get supplier by name
    public Supplier getSupplierByName(String name) {
        try {
            String q = "SELECT * FROM supplier WHERE name = ?";
            data_base.openConn();
            data_base.createps(q);
            data_base.getPs().setString(1, name);
            data_base.createRs();
            ResultSet rs = data_base.getRs();

            if (rs.next()) {
                Supplier s = new Supplier(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                data_base.closeRs();
                data_base.closePs();
                data_base.closseConn();
                return s;
            }

            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //  Get supplier by email
    public Supplier getSupplierByEmail(String email) {
        try {
            String q = "SELECT * FROM supplier WHERE email = ?";
            data_base.openConn();
            data_base.createps(q);
            data_base.getPs().setString(1, email);
            data_base.createRs();

            ResultSet rs = data_base.getRs();
            if (rs.next()) {
                Supplier s = new Supplier(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                data_base.closeRs();
                data_base.closePs();
                data_base.closseConn();
                return s;
            }

            data_base.closeRs();
            data_base.closePs();
            data_base.closseConn();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //  Update supplier
    public boolean updateSupplier(Supplier supplier) {
        try {
            String query = "UPDATE supplier SET name = ?, email = ? WHERE id = ?";
            data_base.openConn();
            data_base.createps(query);

            data_base.getPs().setString(1, supplier.getName());
            data_base.getPs().setString(2, supplier.getEmail());
            data_base.getPs().setInt(3, supplier.getId());

            int rows = data_base.getPs().executeUpdate();
            data_base.closePs();
            data_base.closseConn();

            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //  Delete supplier by id
    public boolean deleteSupplier(int id) {
        try {
            String query = "DELETE FROM supplier WHERE id = ?";
            data_base.openConn();
            data_base.createps(query);
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
