package main;
import connexion.Connexion;
import material.Product;
import material.Production;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vente.*;
public class Main {
    public static void main(String[] args) throws Exception {
        
        Connexion conn = new Connexion();
        
        
        Connection connection = conn.connectePostgres();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Date date= (new Date(timestamp.getTime()));
        
        Product product = new Product(1, "Pain", 24.0, 4, date);
        product.update(connection);
        product.savePrice(connection);;

        if (connection != null) connection.close();
    }
}

