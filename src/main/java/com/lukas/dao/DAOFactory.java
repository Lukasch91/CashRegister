package com.lukas.dao;

import com.lukas.db.ConnectionFactory;
import com.lukas.db.EMFactory;

import java.sql.Connection;

/**
 * Created by Lukas on 6/13/2016.
 */
public class DAOFactory {

    public static ItemDAO getItemDAO() {
        return new ItemDAO(ConnectionFactory.getConnection());
    }

    public static PurchaseDAO getPurchaseDAO() {
        return new PurchaseDAO(ConnectionFactory.getConnection());
    }

    public static PurchaseDAO getPurchaseDAO(Connection connection) {
        return new PurchaseDAO(connection);
    }

    public  static  PurchaseDAOJpa getPurchaseDAOJpa (){
        return new PurchaseDAOJpa(EMFactory.getInstance().getEM());
    }

//    public  static  RecordsDAOJpa getRecordsDAOJpa (){
//        return new RecordsDAOJpa(EMFactory.getInstance().getEM());
//    }

    public static RecordsDAO getRecordsDAO() {
        return new RecordsDAO(ConnectionFactory.getConnection());
    }

    public static RecordsDAO getRecordsDAO(Connection connection) {
        return new RecordsDAO(connection);
    }


    public static ItemDAOJpa getItemDAOJpa() {
        return new ItemDAOJpa(EMFactory.getInstance().getEM());
    }
}
