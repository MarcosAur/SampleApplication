package model.dao.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conec;
    private ResultSet rs;
    private PreparedStatement pst = null;
    private String comando;

    public DepartmentDaoJDBC() {
        conec = DB.getConnection();
    }

    @Override
    public void insert(Department department) {
        try {
            pst = conec.prepareStatement("INSERT INTO department "
                    + "(Name) "
                    + "VALUES "
                    + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, department.getName());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro! Nenhuma linha afetada");
            }

            System.out.println("Insert Complete!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }

    }

    @Override
    public void update(Department department) {
        comando = "UPDATE department "
                + "SET Name = ? "
                + "WHERE id = ?";

        try {
            pst = conec.prepareStatement(comando);

            pst.setString(1, department.getName());
            pst.setInt(2, department.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro! Nenhuma Linha afetada");
            }

            System.out.println("Update Completed!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }

    }

    @Override
    public void deleteById(Integer id) {
        comando = "DELETE FROM department "
                + "WHERE id = ?";

        try {
            pst = conec.prepareStatement(comando);

            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            System.out.println("Delete Complete! Rows Affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public Department findById(Integer id) {
        comando = "select * from department\n"
                + "WHERE id = ?";

        try {
            pst = conec.prepareStatement(comando);

            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                return new Department(rs.getInt("Id"), rs.getString("Name"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        comando = "SELECT * FROM department";
        List<Department> departments = new ArrayList<>();
        try {
            pst = conec.prepareStatement(comando);
            
            rs = pst.executeQuery();

            while (rs.next()) {
                departments.add(new Department(rs.getInt("Id"), rs.getString("Name")));
            }
            return departments;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }

    }
}
