package material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class Production {
    private int id_production;
    private Product product;
    private double quantity;
    private Date date_time;

    public Production (){
    }
    public Production (int id_production,int id_product,double quantity,Date date_time)throws Exception{
        this.id_production=id_production;
        this.product = new Product();
        this.product.getById(id_product);
        this.quantity=quantity;
        this.date_time=date_time;
    }

    public int getIdProduction(){
        return id_production;
    }
    public Product getProduct(){
        return product;
    }
    public double getQunatity(){
        return quantity;
    }
    public Date getDateTime(){
        return date_time;
    }
///setteur
    public void setIdProduction(int id_production){
        this.id_production=id_production;
    }
    public void setProduct(int id_product)throws Exception{
        product=new Product();
        product.getById(id_product);
    }
    public void setQuantity(double quantity){
        this.quantity=quantity;
    }
    public void setDateTime(Date date_time){
        this.date_time=date_time;
    }
///     Getteur 
    public void getById(int id_production)throws Exception {
        Connection connection=null;
        String query="SELECT * FROM bakery_productions WHERE id_production = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_production);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdProduction(resultSet.getInt("id_production"));
                setProduct(resultSet.getInt("id_product"));
                setQuantity(resultSet.getInt("quantity"));
                setDateTime(resultSet.getDate("date_time"));
            }else {
                throw new Exception("production inexistant");
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

/// affichage

    public String line(){

        String retour="";   
        retour+="\t\t<td>"+id_production+"</td>\n";
        retour+="\t\t<td>"+product.getName()+"</td>\n";
        retour+="\t\t<td>"+quantity+"</td>\n";
        retour+="\t\t<td>"+date_time+"</td>\n";
        // retour+="\t</tr>\n";

        return retour;   
    
    }

    public static List<Production> getFiltre(String date_min,String date_max,String id_ingredient , String name_product , String quantity_min )throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Production> retour = new ArrayList<Production>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("SELECT distinct id_product,id_production,quantity,date_time FROM v_all_production WHERE 1=1 ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_time >= '"+date_min+"'");
            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_time <= '"+date_max+"'");
            if (null!=id_ingredient && !id_ingredient.isEmpty()) query.append(" AND id_ingredient = "+id_ingredient);
            if (null!=name_product && !name_product.isEmpty()) query.append(" AND name_product LIKE '%"+name_product+"%'");
            if (null!=quantity_min && !quantity_min.isEmpty()) query.append(" AND quantity >= "+quantity_min);
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_production = resultSet.getInt("id_production");
                int id_product = resultSet.getInt("id_product");
                double quantity = resultSet.getDouble("quantity");
                Date date_time = resultSet.getDate("date_time");
                
                Production production = new Production(id_production,id_product,quantity,date_time);
                retour.add(production);
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

    public void save(Connection co)throws Exception{
        String query = "INSERT INTO bakery_productions (id_product , quantity) values ( ? , ? )";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setInt(1, product.getIdProduct());
            preparedStatement.setDouble(2, quantity);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
}