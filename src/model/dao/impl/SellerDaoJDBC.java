package model.dao.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Seller;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.entities.Department;
import java.sql.Statement;

/**
 *
 * @author marcos
 */
public class SellerDaoJDBC implements SellerDao {

    private String comando;
    private Connection conn = DB.getConnection();
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public void insert(Seller seller) {

        try {
            pst = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, Birthdate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, seller.getName());
            pst.setString(2, seller.getEmail());
            pst.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            pst.setDouble(4, seller.getBaseSalary());
            pst.setInt(5, seller.getDepartment().getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro! Nenhuma Linha afetada");
            }
            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                seller.setId(rs.getInt(1));
            }
            System.out.println("Insert Completed!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Seller seller) {
        comando = "UPDATE seller "
                + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                + "WHERE id = ?";
        
        try{
            pst = conn.prepareStatement(comando);
            pst.setString(1, seller.getName());
            pst.setString(2, seller.getEmail());
            pst.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            pst.setDouble(4, seller.getBaseSalary());
            pst.setInt(5, seller.getDepartment().getId());
            pst.setInt(6, seller.getId());
            
            int rowsAffected = pst.executeUpdate();
            
            if(rowsAffected == 0){
                throw new DbException("Erro! Update não executado");
            }
            
            System.out.println("Update Completed!");
            
            
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        comando = "DELETE FROM seller "
                + "WHERE id = ?";
        
        try{
            pst = conn.prepareStatement(comando);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            
           // if (rowsAffected == 0){
           //     throw new DbException("Id inexistente no banco");
           // }
            
            System.out.println("Delete Completed!s");
            
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Seller findById(Integer id) {
        comando = "SELECT seller.*, department.name AS nameDep "
                + "FROM seller JOIN department "
                + "ON seller.departmentId = department.id "
                + "WHERE seller.id = ?";
        try {
            conn = DB.getConnection();
            pst = conn.prepareStatement(comando);

            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                Department dep = instanciarDepartamento(rs);
                return instanciarSeller(rs, dep);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {
        comando = "SELECT seller.*,department.Name AS nameDep "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.id ";
        List<Seller> listaSellers = new ArrayList<>();
        Statement st = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(comando);

            Map<Integer, Department> departments = new HashMap<>();
            while (rs.next()) {
                Department dep = departments.get(rs.getInt("departmentID"));

                if (dep == null) {
                    dep = instanciarDepartamento(rs);
                }
                listaSellers.add(instanciarSeller(rs, dep));
            }
            return listaSellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findByDepartment(Department dep) {
        List<Seller> list = new ArrayList<>();
        comando = "SELECT seller.*, department.name AS nameDep "
                + "FROM seller JOIN department "
                + "ON seller.departmentId = department.id "
                + "WHERE seller.departmentId = ?";
        try {
            pst = conn.prepareStatement(comando);

            pst.setInt(1, dep.getId());
            rs = pst.executeQuery();
            Map<Integer, Department> departMap = new HashMap<>();
            while (rs.next()) {

                Department department = departMap.get(rs.getInt("departmentId"));

                if (department == null) {
                    department = instanciarDepartamento(rs);
                }

                list.add(instanciarSeller(rs, department));
            }
            return list;

        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    public Seller instanciarSeller(ResultSet rs, Department dep) throws SQLException {
        return new Seller(rs.getInt("id"), rs.getString("Name"),
                rs.getString("Email"), rs.getDate("BirthDate"),
                rs.getDouble("BaseSalary"), dep);
    }

    public Department instanciarDepartamento(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("departmentId"), rs.getString("nameDep"));
    }

}
