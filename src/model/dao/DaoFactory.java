package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

/**
 *
 * @author marcos
 */
public class DaoFactory {
    public static SellerDao crateSellerDao(){
        return new SellerDaoJDBC();
    }
    public static DepartmentDao crateDepartmentDao(){
        return new DepartmentDaoJDBC();
    }
}
