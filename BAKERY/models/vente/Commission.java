package vente;

import java.util.List;

import connexion.Connexion;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import util.Month;
public class Commission {
    Seller seller;
    util.Month month;
    int year;
    double commission;

    public Commission(){
    }
    public Commission(int id_seller,int id_month,int year,double commission)throws Exception{
        this.year=year;
        System.out.println(year);
        seller=new Seller();
        seller.getById(id_seller);
        month = new util.Month();
        month.getById(id_month);
        this.commission=commission; 
    }

    public Seller getSeller(){
        return seller;
    }
    public Month getMonth(){
        return month;
    } 
    public int getYear(){
        return year;
    }
    public double getCommission(){
        return commission;
    }

    public String line(){
        String retour="";
        retour+="\t\t<td>"+seller.getName()+"</td>\n";
        retour+="\t\t<td>"+commission+"</td>\n";
        retour+="\t\t<td>"+month.getMois()+"</td>\n";
        retour+="\t\t<td>"+year+"</td>\n";


        return retour;   
    }




    public static List<Commission> getFiltre(String date_min,String date_max,String id_seller )throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Commission> retour = new ArrayList<Commission>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("select id_seller,sum(commission) as commission,EXTRACT(MONTH FROM date_sales) as month ,EXTRACT(YEAR FROM date_sales) as year from v_all_commissions WHERE 1=1 ");
                        
            if (null!=id_seller && !id_seller.isEmpty()) query.append(" AND id_seller ="+id_seller+" ");
            else return null;

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_sales <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_sales >= '"+date_min+"' ");

            query.append(" GROUP BY id_seller,month,year ");
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id = resultSet.getInt("id_seller");
                double commission = resultSet.getDouble("commission");
                int id_month = resultSet.getInt("month");
                int year = resultSet.getInt("year");
                
                Commission com = new Commission(id,id_month,year,commission);
                
                retour.add(com);
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

    public static HashMap<String , Double> getEtatCommissionBySexe(String date_min,String date_max )throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        HashMap<String ,Double > retour = new HashMap<String,Double>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("select sexe,sum(commission) as commission from v_all_commissions WHERE 1=1 ");
            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_sales <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_sales >= '"+date_min+"' ");

            query.append(" GROUP BY sexe ");
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                String sexe = resultSet.getString("sexe");
                double commission = resultSet.getDouble("commission");
                retour.put(sexe,commission);
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