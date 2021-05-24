/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 *
 * @author marcos
 */
public class DepartmentService {

    private DepartmentDao depDao = DaoFactory.crateDepartmentDao();

    public List<Department> findAll() {

        List<Department> list = depDao.findAll();

        return list;
    }

}
