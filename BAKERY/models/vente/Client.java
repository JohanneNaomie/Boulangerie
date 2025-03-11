package vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;


public class Client {
    private int id_client;
    private String name;

    // Constructeur par défaut
    public Client() {
    }

    // Constructeur avec paramètres
    public Client(int id_client, String name) {
        this.id_client = id_client;
        this.name = name;
    }

    // Getter pour id_client
    public int getIdClient() {
        return id_client;
    }

    // Setter pour id_client
    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    // Getter pour name
    public String getName() {
        return name;
    }

    // Setter pour name
    public void setName(String name) {
        this.name = name;
    }
    
    public String option(){
        return "\t\t<option value=\""+id_client+"\">"+name+"</option>\n";
    }
    public static List<Client> getAll()throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Client> retour = new ArrayList<Client>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT * FROM bakery_clients";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_client = resultSet.getInt("id_client");
                String name = resultSet.getString("name");

                Client client = new Client(id_client, name);
                retour.add(client);
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
    
    public void getById(int id_client) throws Exception {
        Connection connection = null;
        String query = "SELECT * FROM bakery_clients WHERE id_client = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_client);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdClient(resultSet.getInt("id_client"));
                setName(resultSet.getString("name"));
            } else {
                throw new Exception("Client inexistant");
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

    public double lePlusAchateur(String date_min,String date_max)throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();

            StringBuilder query=new StringBuilder("SELECT id_client,sum(quantity) as quantity FROM bakery_sales WHERE 1=1 AND id_client != 1 ");

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_sales <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_sales >= '"+date_min+"' ");

            query.append(" GROUP BY id_client ORDER BY quantity DESC");
            
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());

            if (resultSet.next()) {
                int id_client = resultSet.getInt("id_client");
                double quantity = resultSet.getDouble("quantity");
                getById(id_client);
                return quantity;
            } else {
                return -1;
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
    }

    public int lePlusRegulier(String date_min,String date_max)throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();

            StringBuilder query=new StringBuilder("select id_client,count(*) as nb from bakery_sales WHERE 1=1 AND id_client != 1 ");

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_sales <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_sales >= '"+date_min+"' ");

            query.append(" GROUP BY id_client ORDER BY nb DESC");
            
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());

            if (resultSet.next()) {
                int id_client = resultSet.getInt("id_client");
                int nb = resultSet.getInt("nb");
                getById(id_client);
                return nb;
            } else {
                return -1;
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
    }
}
