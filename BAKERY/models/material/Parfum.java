package material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class Parfum {
    private int id_parfum;
    private String name_parfum;

    public Parfum(){
    }

    public Parfum(int id_parfum,String name_parfum){
        this.id_parfum=id_parfum;
        this.name_parfum=name_parfum;
    }
///     Getteur

    public int getIdParfum(){
        return id_parfum;
    }
    public String getName() {
        return name_parfum;
    }
///     setteur

    public void setIdParfum(int id_parfum){
        this.id_parfum=id_parfum;
    }
    public void setName(String name_parfum){
        this.name_parfum=name_parfum;
    }

///     afdichage
    public String option(){
        return "\t\t<option value=\""+id_parfum+"\">"+name_parfum+"</option>\n";
    }

    public void getById(int id_parfum)throws Exception{
        Connection connection=null;
        String query="SELECT * FROM bakery_parfums WHERE id_parfum = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_parfum);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdParfum(resultSet.getInt("id_parfum"));
                setName(resultSet.getString("name_parfum"));
            }else {
                throw new Exception("parfum inexistant");
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
    public static List<Parfum> getAll()throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Parfum> retour = new ArrayList<Parfum>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT * FROM bakery_parfums";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_parfum = resultSet.getInt("id_parfum");
                String name_parfum = resultSet.getString("name_parfum");

            
                Parfum parfum = new Parfum(id_parfum, name_parfum);

                retour.add(parfum);
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
