package util;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connexion.Connexion;


public class Month {
    private int id_mois;
    private String mois;

    // Constructeur par défaut
    public Month() {}

    // Constructeur avec paramètres
    public Month(int id_mois, String mois) {
        this.id_mois = id_mois;
        this.mois = mois;
    }

    // Getter pour id_mois
    public int getIdMois() {
        return id_mois;
    }

    // Setter pour id_mois
    public void setIdMois(int id_mois) {
        this.id_mois = id_mois;
    }

    // Getter pour mois
    public String getMois() {
        return mois;
    }

    // Setter pour mois
    public void setMois(String mois) {
        this.mois = mois;
    }

    public String option (){
        return "\t\t<option value=\""+id_mois+"\">"+mois+"</option>\n";
    }

    public void getById(int id_mois){
        Map<Integer, String> moisMap = new HashMap<>();
        moisMap.put(1, "Janvier");
        moisMap.put(2, "Février");
        moisMap.put(3, "Mars");
        moisMap.put(4, "Avril");
        moisMap.put(5, "Mai");
        moisMap.put(6, "Juin");
        moisMap.put(7, "Juillet");
        moisMap.put(8, "Août");
        moisMap.put(9, "Septembre");
        moisMap.put(10, "Octobre");
        moisMap.put(11, "Novembre");
        moisMap.put(12, "Décembre");

        if (moisMap.containsKey(id_mois)) {
            this.id_mois = id_mois;
            this.mois = moisMap.get(id_mois);
        } else {
            throw new IllegalArgumentException("ID invalide : " + id_mois);
        }
    }
    // Méthode pour obtenir une liste de tous les mois
    public static List<Month> getAll() throws Exception{
        List<Month> moisList = new ArrayList<>();
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT * FROM bakery_month";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_month = resultSet.getInt("id_month");
                String mois = resultSet.getString("month");

                Month month = new Month(id_month, mois);
                moisList.add(month);
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

        return moisList;
    }

}
