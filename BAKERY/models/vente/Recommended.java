package vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import material.Product;
import util.*;
public class Recommended extends Product {
    private util.Month month;
    private int annee; 
    public Recommended(){
    }
    public Recommended (int id_product,String name_product,double conservation_time_h,util.Month month,int annee) throws Exception{
        super(id_product,name_product,conservation_time_h);
        this.month=month;
        this.annee=annee;
    }
    public Recommended(int id_product,String name_product,double conservation_time_h,double price,util.Month month,int annee)throws Exception{
        super(id_product, name_product, conservation_time_h, price);
        this.month=month;
        this.annee=annee;
    }

    public util.Month getMonth(){
        return month;
    }   
    public void setMonth (util.Month month){
        this.month=month;
    }
    public int getAnnee (){
        return annee;
    }
    public void setAnnee(int annee){
        this.annee=annee;
    }
    public void addRecommended(Connection co) throws Exception{
        String query = "INSERT INTO bakery_recommended (id_product , id_month ,annee) values ( ? ,? , ? )";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setInt(1, id_product);
            preparedStatement.setInt(2,month.getIdMois());
            preparedStatement.setInt(3,annee);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            setIdProduct();
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
    public static List<Recommended> getRecommendedProducts(String mois ,String annee)throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Recommended> retour = new ArrayList<Recommended>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("SELECT distinct id_product,annee,r.id_month FROM bakery_recommended as r join bakery_month as m on r.id_month=m.id_month WHERE 1=1 ");
            if (null!=mois && !mois.isEmpty()) query.append(" AND r.id_month = "+mois+" ");
            if (null!=annee && !annee.isEmpty()) query.append(" AND annee = "+annee+" ");

            query.append(" order by annee,r.id_month");
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                Recommended recommended = new Recommended();
                recommended.getById(resultSet.getInt("id_product"));   
                recommended.setCategories();
                util.Month month = new Month();
                month.getById(resultSet.getInt("id_month"));
                recommended.setMonth(month);
                recommended.setAnnee(resultSet.getInt("annee"));
                recommended.setPrice();
                retour.add(recommended);
            }
            
            return retour;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // fermeture de toutes les connections
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close(); 
        }
    }
    
///     affichage
    public String line() {
        String retour="";

        retour+="\t\t<td>"+name_product+"</td>\n";
        retour+="\t\t<td>"+getCategory().getName()+"</td>\n";
        retour+="\t\t<td>"+month.getMois()+"</td>\n";
        retour+="\t\t<td>"+annee+"</td>\n";

        // retour+="\t\t<td><a href=\"Product?action=update&id_product="+id_product+"\">update<a> <a href=\"Product?action=delete&id_product="+id_product+"\">delete<a></td>\n";
        // retour+="\t</tr>\n";

        return retour;   
    }

}