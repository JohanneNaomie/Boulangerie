package vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class Seller {
    private int id_seller;
    private String name;
    private String sexe;
    // Constructeur par défaut
    public Seller() {
    }

    // Constructeur avec paramètres
    public Seller(int id_seller, String name) {
        this.id_seller = id_seller;
        this.name = name;
    }
    public Seller(int id_seller,String name,String sexe){
        this.id_seller = id_seller;
        this.name = name;
        this.sexe=sexe;
    }

    // Getter pour id_seller
    public int getIdSeller() {
        return id_seller;
    }

    // Setter pour id_seller
    public void setIdSeller(int id_seller) {
        this.id_seller = id_seller;
    }

    // Getter pour name
    public String getName() {
        return name;
    }

    // Setter pour name
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSexe() {
        return sexe;
    }

    // Setter pour Sexe
    public void setSexe(String Sexe) {
        this.sexe = Sexe;
    }
    
    
    public String option(){
        return "\t\t<option value=\"" + id_seller + "\">" + name + "</option>\n";
    }

    public static List<Seller> getAll() throws Exception {
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // Objet de retour
        List<Seller> retour = new ArrayList<Seller>();
        
        try {
            // Création de la connexion
            conn = new Connexion();
            connection = conn.connectePostgres();
            // Requête pour avoir tous les objets
            String query = "SELECT * FROM bakery_sellers";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // Récupération des informations et création de tous les objets
            while (resultSet.next()) {
                int id_seller = resultSet.getInt("id_seller");
                String name = resultSet.getString("name");

                Seller seller = new Seller(id_seller, name);
                retour.add(seller);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Fermeture de toutes les connexions
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close(); 
        }
        return retour;
    }
    
    public void getById(int id_seller) throws Exception {
        Connection connection = null;
        String query = "SELECT * FROM bakery_sellers WHERE id_seller = ? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_seller);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdSeller(resultSet.getInt("id_seller"));
                setName(resultSet.getString("name"));
                if(resultSet.getInt("id_sexe")==1){
                    setSexe("male");
                }else {
                    setSexe("female");
                }
            } else {
                throw new Exception("Seller inexistant");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}