package material;
import connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    protected int id_product;
    protected String name_product;
    protected double conservation_time_h;
    protected double price;
    protected Category category;
    protected Date date_time;
    public Product(){
    }
    public Product (int id_product,String name_product,double conservation_time_h) throws Exception{
        this.id_product=id_product;
        this.name_product=name_product;
        this.conservation_time_h=conservation_time_h;
        this.price=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.date_time=new Date(timestamp.getTime());
    }
    public Product (int id_product,String name_product,double conservation_time_h,double price)throws Exception{
        this.id_product=id_product;
        this.name_product=name_product;
        this.conservation_time_h=conservation_time_h;
        this.price=price;        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.date_time=new Date(timestamp.getTime());
    }
    public Product (int id_product,String name_product,double conservation_time_h,double price,Date date_time)throws Exception{
        this.id_product=id_product;
        this.name_product=name_product;
        this.conservation_time_h=conservation_time_h;
        this.price=price;        
        this.date_time=date_time;        

    }

///     Getteur
    public int getIdProduct(){
        return id_product;
    }
    public String getName(){
        return name_product;
    }
    public double getConservatonTimeH(){
        return conservation_time_h;
    }
    public double getPrice(){
        return this.price;
    }
    public Category getCategory(){
        return category ;
    }
    public Date getDate(){
        return date_time;
    }

    //      getteur by id
    public void getById(int id_product) throws Exception{
        Connection connection=null;
        String query="SELECT * FROM bakery_products WHERE id_product = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_product);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdProduct(resultSet.getInt("id_product"));
                setName(resultSet.getString("name_product"));
                setConservationTimeH(resultSet.getDouble("conservation_time_h"));
            }else {
                throw new Exception("Ingredient inexistant");
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

///     Setteur
    public void setIdProduct(int id_product){
        this.id_product=id_product;
    }
    public void setName(String name_product){
        this.name_product=name_product;
    }
    public void setConservationTimeH(double conservation_time_h){
        this.conservation_time_h=conservation_time_h;
    }
    public void setDate(Date date_time){
        this.date_time=date_time;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public void setPrice() throws Exception{
        Connection connection = null;
        String query="SELECT * FROM bakery_product_prices WHERE id_product = ? order by date_time desc";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_product);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                setPrice(resultSet.getDouble("price"));
                setDate(resultSet.getDate("date_time"));
            } else {
                setPrice(0);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (null!=resultSet) resultSet.close();
            if (null!=preparedStatement) preparedStatement.close();
            if (null!=connection) connection.close();  
        }
    }
    
    public void setCategories()throws Exception {
        Connection connection = null;
        String query="SELECT id_category FROM bakery_product_categories WHERE id_product = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_product);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                category = new Category();
                category.getById(resultSet.getInt(1));
                
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (null!=resultSet) resultSet.close();
            if (null!=preparedStatement) preparedStatement.close();
            if (null!=connection) connection.close();  
        }
    }
    
///     affichage
    public String line() {
        String retour="";

        retour+="\t\t<td>"+name_product+"</td>\n";
        retour+="\t\t<td>"+conservation_time_h+"</td>\n";
        retour+="\t\t<td>"+price+"</td>\n";

        // retour+="\t\t<td><a href=\"Product?action=update&id_product="+id_product+"\">update<a> <a href=\"Product?action=delete&id_product="+id_product+"\">delete<a></td>\n";
        // retour+="\t</tr>\n";

        return retour;   
    }
    public String option(){
        return "\t\t<option value=\""+id_product+"\">"+name_product+"</option>\n";
    }
//      setteur avec connection
    public void setIdProduct()throws Exception{
        Connection connection=null;
        String query="SELECT max(id_product) as id_product FROM bakery_products";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                setIdProduct(resultSet.getInt(1));
            } 
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null!=resultSet) resultSet.close();
            if (null!=statement) statement.close();
            if (null!=connection) connection.close(); 
        }
    }


///     insert
    public void save(Connection co)throws Exception{
        String query = "INSERT INTO bakery_products (name_product , conservation_time_h) values ( ? , ? )";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setString(1, name_product);
            preparedStatement.setDouble(2, conservation_time_h);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            setIdProduct();
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
    public void savePrice(Connection co )throws Exception{
        String query = "INSERT INTO bakery_product_prices (id_product, price,date_time) VALUES (? , ?,?)";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setInt(1, id_product);
            preparedStatement.setDouble(2, price);
            preparedStatement.setDate(3, date_time);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

///     update
    public void update(Connection co)throws Exception{
        String query = "UPDATE bakery_products set name_product = ? , conservation_time_h = ? WHERE id_product = ? ";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setString(1, name_product);
            preparedStatement.setDouble(2, conservation_time_h);
            preparedStatement.setInt(3, id_product);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
///     delete 
    public void delete()throws Exception {
        Connection connection = null;
        try {
            connection = Connexion.connectePostgres();
            connection.setAutoCommit(false);

            deletePrice(connection);
            delete(connection);
            connection.commit();
        } catch (Exception e){
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
            throw e;
        } finally {
            if (null!=connection) connection.close();
        }
    }
    public void delete(Connection co)throws Exception{
        String query = "DELETE FROM bakery_products WHERE id_product = ?";
        try (PreparedStatement preparedStatement = co.prepareStatement(query)){
            preparedStatement.setInt(1, id_product);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void deletePrice(Connection co)throws Exception{
        String query = "DELETE FROM bakery_product_prices WHERE id_product = ?";
        try (PreparedStatement preparedStatement = co.prepareStatement(query)){
            preparedStatement.setInt(1, id_product);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
///     liste de tous les products
    public static List<Product> getAll()throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Product> retour = new ArrayList<Product>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            String query = "SELECT * FROM bakery_products";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_product = resultSet.getInt("id_product");
                String name_product = resultSet.getString("name_product");
                double conservation_time_h = resultSet.getDouble("conservation_time_h");

            
                Product product = new Product(id_product, name_product, conservation_time_h);
                product.setCategories();
                product.setPrice();
                retour.add(product);
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
    public static List<Product> getAllHistoPrice(String id_product,String date_min , String date_max)throws Exception {
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Product> retour = new ArrayList<Product>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("SELECT * FROM bakery_product_prices WHERE 1=1 ");
            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_time <= '"+date_max+"'");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_time >= '"+date_min+"'");
            if (null!=id_product && !id_product.isEmpty()) query.append(" AND id_product = "+id_product);


            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id = resultSet.getInt("id_product");
                double price = resultSet.getDouble("price");
                Date date = resultSet.getDate("date_time");
                Product product = new Product();
                product.getById(id);
                product.setPrice(price);
                product.setDate(date);
                retour.add(product);
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
    


    /// Creation de nouvelle class recommended 
    /// changement de nom de attribut
    /// 

}
