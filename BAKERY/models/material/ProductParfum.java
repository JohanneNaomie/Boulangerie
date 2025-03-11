package material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class ProductParfum extends Product{
    int id_pp;
    Parfum parfum;
    double quantity;
    public ProductParfum(){
        super();
    }
    public ProductParfum(int id_pp,int id_parfum,int id_product,String name_product,double conservation_time_h)throws Exception{
        super(id_product, name_product, conservation_time_h);
        this.id_pp=id_pp;
        parfum = new Parfum();
        parfum.getById(id_parfum);
    }
    public ProductParfum(int id_pp,int id_parfum,int id_product,String name_product,double conservation_time_h,double price)throws Exception{
        super(id_product, name_product, conservation_time_h, price);
        this.id_pp=id_pp;
        parfum = new Parfum();
        parfum.getById(id_parfum);    
    }

    public int getIdPP(){
        return id_pp;
    }
    public void setIdPP(int id_pp){
        this.id_pp=id_pp;
    }
    public Parfum getParfum(){
        return parfum;
    }
    public double getQuantity(){
        return quantity;
    }
    public void setParfum(Parfum parfum){
        this.parfum=parfum;
    }
    public void setParfum(int id_parfum)throws Exception{
        parfum=new Parfum();
        parfum.getById(id_parfum);
    }
    public void setQuantity(double quantity) throws Exception{
        this.quantity=quantity;
    }
    public void save(Connection co)throws Exception{
        String query = "INSERT INTO bakery_product_parfums (id_product , id_parfum ,quantity ) values ( ? , ? ,? )";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setInt(1, id_product);
            preparedStatement.setInt(2, parfum.getIdParfum());
            preparedStatement.setDouble(3, quantity);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
    public boolean used()throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null ;
        try {
            connection = Connexion.connectePostgres();
            String query = "SELECT count(*) as nb FROM bakery_product_parfums WHERE id_product = ? and id_parfum = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_product);
            preparedStatement.setInt(2,parfum.getIdParfum());
            if(resultSet.next()){
                return resultSet.getInt(1)>=1;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=resultSet) resultSet.close();
            if (null!=preparedStatement) preparedStatement.close();
            if (null!=connection) connection.close();
        }
        return false;
    }

    public void getById(int id_pp,boolean b)throws Exception{
        ///false => utilisation de l'ancien
        System.out.println("id_pp = "+id_pp);
        if(!b){
            super.getById(id_pp);
            return;
        } else {
            Connection connection=null;
            String query="SELECT * FROM bakery_product_parfums WHERE id_pp = ?";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connection = Connexion.connectePostgres();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_pp);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    setIdPP(id_pp);
                    super.getById(resultSet.getInt("id_product"));
                    setParfum(resultSet.getInt("id_parfum"));
                }else {
                    throw new Exception("Product inexistant");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (null!=resultSet) resultSet.close();
                if (null!=preparedStatement) preparedStatement.close();
                if (null!=connection) connection.close();
            }
        }
    }

    public String option(){
        return "\t\t<option value=\""+id_pp+"\">"+name_product+" "+parfum.getName()+"</option>\n";
    }
///     liste de tous les products
    public static List<ProductParfum> getAlls()throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<ProductParfum> retour = new ArrayList<ProductParfum>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT id_pp,id_parfum,p.id_product,name_product,conservation_time_h FROM bakery_products as p join bakery_product_parfums as pp on p.id_product=pp.id_product";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_pp = resultSet.getInt("id_pp");
                int id_parfum = resultSet.getInt("id_parfum");
                int id_product = resultSet.getInt("id_product");
                String name_product = resultSet.getString("name_product");
                double conservation_time_h = resultSet.getDouble("conservation_time_h");

            
                ProductParfum productParfum = new ProductParfum(id_pp,id_parfum,id_product, name_product, conservation_time_h);
                productParfum.setCategories();
                productParfum.setPrice();
                retour.add(productParfum);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // fermeture de toutes les connections
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close(); 
        }
        return retour;
    }
}
