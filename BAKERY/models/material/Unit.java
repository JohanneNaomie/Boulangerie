package material;

import connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Unit {
    private int id_unit;
    private String name_unit;

    public Unit(int id_unit,String name_unit){
        this.id_unit=id_unit;
        this.name_unit=name_unit;
    }

    public Unit(){}
///     Getteur
    public int getIdUnit(){
        return id_unit;
    }
    public String getName(){
        return name_unit;
    }
    public void getById(int id) throws Exception{
        Connection connection = null;
        Connexion conn = null;
        String query = "SELECT * FROM bakery_units where id_unit = ?";
        try {
            
            conn = new Connexion();
            connection = conn.connectePostgres();
            PreparedStatement preparedStatement  = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setIdUnit(id);
                setName(resultSet.getString("name_unit"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally{
            connection.close();
        }
    }

///     Setteur

    public void setIdUnit(int id_unit){
        this.id_unit=id_unit;
    }
    
    public void setName(String name_unit){
        this.name_unit=name_unit;
    }

///     Option 

    public String option(){
        return "\t\t<option value=\""+id_unit+"\">"+name_unit+"</option>\n";
    }

    public static List<Unit> getAll() throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Unit> units = new ArrayList<Unit>();
        try {
            connection = Connexion.connectePostgres();
            String query = "SELECT * FROM bakery_units";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Unit unit= new Unit();
                unit.setIdUnit(resultSet.getInt("id_unit"));
                unit.setName(resultSet.getString("name_unit"));
                units.add(unit);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return units;
    }
}
