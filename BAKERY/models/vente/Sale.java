package vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import material.*;
import connexion.Connexion;
public class Sale {
    private int id_sale;
    private ProductParfum productParfum;
    private double quantity;
    private double total_price;
    private Date date_sales;
    private Client client;
    private Seller seller;

    public Sale(){
    }
    public Sale(int id_sale,int id_product,int id_parfum,double quantity,double total_price,Date date_sales)throws Exception{
        this.id_sale=id_sale;
        productParfum = new ProductParfum();
        productParfum.getById(id_product);
        productParfum.setCategories();
        productParfum.setPrice();
        productParfum.setParfum(id_parfum);
        this.quantity=quantity;
        this.total_price=total_price;
        this.date_sales=date_sales;
    }
    public Sale(int id_sale,int id_product,int id_pp,int id_parfum,double quantity,double total_price,Date date_sales,int id_client,int id_seller)throws Exception{
        this.id_sale=id_sale;
        productParfum = new ProductParfum();
        productParfum.getById(id_pp,true);
        productParfum.setCategories();
        productParfum.setPrice();
        this.quantity=quantity;
        this.total_price=total_price;
        this.date_sales=date_sales;
        client = new Client();
        client.getById(id_client);
        seller = new Seller();
        seller.getById(id_seller);
    }
    

    public int getIdSale(){
        return id_sale;
    }
    public Product getProduct(){
        return productParfum;
    }
    public Parfum getParfum(){
        return productParfum.getParfum();
    }
    public double getQte(){
        return quantity;
    }
    public double getPrice(){
        return total_price;
    }
    public Date getDate(){
        return date_sales;
    }
    public Client getClient(){
        return client;
    }



    /// setteur
    public void setClient(Client client){
        this.client=client;
    }
    public void setPrice(){
        System.out.println("price pp = "+productParfum.getPrice()+ " quantity = "+quantity);
        total_price=productParfum.getPrice()*quantity;
    }
    public String line(){
         String retour="";
        retour+="\t\t<td>"+id_sale+"</td>\n";
        if (client!=null) 
        retour+="\t\t<td>"+client.getName()+"</td>\n";
        else 
        retour+="\t\t<td>"+"autre"+"</td>\n";

        retour+="\t\t<td>"+productParfum.getName()+"</td>\n";
        retour+="\t\t<td>"+productParfum.getCategory().getName()+"</td>\n";
        retour+="\t\t<td>"+productParfum.getParfum().getName()+"</td>\n";
        retour+="\t\t<td>"+quantity+"</td>\n";
        retour+="\t\t<td>"+date_sales+"</td>\n";
        retour+="\t\t<td>"+total_price+"</td>\n";

        // retour+="\t</tr>\n";

        return retour;   
    }

    public void getById(int id_sale)throws Exception{
        Connection connection=null;
        String query="select id_sale,s.quantity as quantity,id_product,id_parfum,total_price,date_sales from bakery_sales as s join bakery_product_parfums as pp on s.id_pp=pp.id_pp WHERE id_product = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_sale);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.id_sale=resultSet.getInt("id_sale");
                productParfum = new ProductParfum();
                productParfum.getById(resultSet.getInt("id_product"));
                productParfum.setCategories();
                
                productParfum.setParfum(resultSet.getInt("id_parfum"));
                this.quantity=resultSet.getDouble("quantity");
                this.total_price=resultSet.getDouble("total_price");;
                this.date_sales=resultSet.getDate("date_sales");
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
    
    // public void updateQtePP(Connection c){

    // }

    public int getIdPP()throws Exception {
        Connection connection=null;
        String query="select id_pp from bakery_product_parfums where id_product = ? AND id_parfum = ? AND quantity > ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connexion.connectePostgres();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productParfum.getIdProduct());            
            preparedStatement.setInt(1, productParfum.getParfum().getIdParfum());
            preparedStatement.setDouble(1, quantity);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            } else {
                throw new Exception("Product avec ce parfum n'existe pas ou en nombre insuffisant");
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
    
    public void save (Connection co) throws Exception {
        String query = "INSERT INTO bakery_sales (id_pp,id_client ,quantity, total_price,id_seller,date_sales) VALUES (? , ? , ? ,? ,? , ?)";

        try(PreparedStatement preparedStatement = co.prepareStatement(query)){

            preparedStatement.setInt(1, productParfum.getIdPP());
            preparedStatement.setDouble(2, client.getIdClient());
            preparedStatement.setDouble(3, quantity);
            preparedStatement.setDouble(4, total_price);
            preparedStatement.setInt(5, seller.getIdSeller());
            preparedStatement.setDate(6, date_sales);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Sale> getFiltre(String name_product, String id_category,String id_parfum,String date_min,String date_max )throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Sale> retour = new ArrayList<Sale>();
        
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("SELECT distinct id_sale,id_product,id_parfum,quantity,total_price,date_sales FROM v_all_sales WHERE 1=1 ");
                        
            if (null!=name_product && !name_product.isEmpty()) query.append(" AND name_product LIKE '%"+name_product+"%'");
            if (null!=id_category && !id_category.isEmpty()) query.append(" AND id_category = "+id_category);
            if (null!=id_parfum && !id_parfum.isEmpty()) query.append(" AND id_parfum = "+id_parfum);

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_time <= '"+date_max+"'");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_time >= '"+date_min+"'");

            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_sale = resultSet.getInt("id_sale");
                int product = resultSet.getInt("id_product");
                int parfum = resultSet.getInt("id_parfum");
                double quantity = resultSet.getDouble("quantity");
                double total_price = resultSet.getDouble("total_price");
                 
                Date date_sales = resultSet.getDate("date_sales");
                
                Sale sale = new Sale(id_sale,product,parfum,quantity,total_price,date_sales);
                
                retour.add(sale);
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
    public static List<Sale> getFiltre(String name_product, String id_category,String id_parfum,String date_min,String date_max,String date,String client )throws Exception{
        Connection connection = null;
        Connexion conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // objet de retour
        List<Sale> retour = new ArrayList<Sale>();
    
        try {
            // creation de la connection
            conn = new Connexion();
            connection = conn.connectePostgres();
            // requete pour avoir tous les objets
            StringBuilder query = new StringBuilder("SELECT distinct id_sale,id_client,name,id_product,id_parfum,quantity,total_price,date_sales FROM v_all_sales WHERE 1=1 ");
                        
            if (null!=name_product && !name_product.isEmpty()) query.append(" AND name_product LIKE '%"+name_product+"%' ");
            if (null!=id_category && !id_category.isEmpty()) query.append(" AND id_category = "+id_category+" ");
            if (null!=id_parfum && !id_parfum.isEmpty()) query.append(" AND id_parfum = "+id_parfum+" ");
            if (null!=client && !client.isEmpty()) query.append(" AND name LIKE '%"+client+"%' ");
            if (null!=date && !date.isEmpty()) query.append(" AND date_time = '"+date+"' ");

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_time <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_time >= '"+date_min+"' ");

            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());
            // recuperation des informations et creation de tous les objets
            while (resultSet.next()) {
                int id_sale = resultSet.getInt("id_sale");
                int product = resultSet.getInt("id_product");
                int parfum = resultSet.getInt("id_parfum");
                double quantity = resultSet.getDouble("quantity");
                double total_price = resultSet.getDouble("total_price");
                
                Date date_sales = resultSet.getDate("date_sales");
                
                Sale sale = new Sale(id_sale,product,parfum,quantity,total_price,date_sales);
                
                sale.setClient(new Client(resultSet.getInt("id_client"), resultSet.getString("name")));
                retour.add(sale);
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
    public static List<Object []> productQuantity(String date_min,String date_max)throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Object []> retour = new ArrayList<Object []>();
        Random random = new Random();
        try {
            connection = Connexion.connectePostgres();

            StringBuilder query=new StringBuilder("SELECT id_product , sum(quantity) as quantity FROM v_quantity_product_sale WHERE 1=1 ");

            if (null!=date_max && !date_max.isEmpty()) query.append(" AND date_sales <= '"+date_max+"' ");
            if (null!=date_min && !date_min.isEmpty()) query.append(" AND date_sales >= '"+date_min+"' ");

            query.append(" GROUP BY id_product ORDER BY quantity DESC");
            
            System.out.println(query.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {
                int id_product = resultSet.getInt("id_product");
                int quantity = resultSet.getInt("quantity");
                Product product =new Product();
                product.getById(id_product);
                Object [] toAdd = new Object[5] ;
                toAdd[0] = product;
                toAdd[1] = quantity;
                toAdd[2] = random.nextInt(256);
                toAdd[3] = random.nextInt(256);
                toAdd[4] = random.nextInt(256);

                retour.add(toAdd);
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
}
