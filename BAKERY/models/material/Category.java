package material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class Category {
    private int id_category;
    private String name_category;

    public Category(){
    }

    public Category(int id_category,String name_category){
        this.id_category=id_category;
        this.name_category=name_category;
    }
///     Getteur

    public int getIdCategory(){
        return id_category;
    }
    public String getName() {
        return name_category;
    }
///     setteur

    public void setIdCategory(int id_category){
        this.id_category=id_category;
    }
    public void setName(String name_category){
        this.name_category=name_category;
    }

    public void getById(int id_category)throws Exception{
        Connection connection=null;
        String query="SELECT * FROM bakery_categories WHERE id_category = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_category);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdCategory(resultSet.getInt("id_category"));
                setName(resultSet.getString("name_category"));
            }else {
                throw new Exception("category inexistant");
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

    public static List<Category> getAll()throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Category> retour = new ArrayList<Category>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT * FROM bakery_categories";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_category = resultSet.getInt("id_category");
                String name_category = resultSet.getString("name_category");

            
                Category category = new Category(id_category, name_category);

                retour.add(category);
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
