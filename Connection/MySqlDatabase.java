package Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.sql.Connection;

public class MySqlDatabase {
    
    private Connection conn;

    private String table = "EXAMPLE_TABLE";

    public MySqlDatabase(){
        setConn(null);
    }

    // Insert data in database *************************************************
    public boolean insert(int id, String name){
        connect();
        
        if (verifyConnection() == false){
            return false;
        }
        
        String query = "INSERT INTO " + getTable() + " VALUES (?, ?)";
        try{
            PreparedStatement stm = getConn().prepareStatement(query);
            stm.setInt(1, id);
            stm.setString(2, name);
            stm.execute();
            disconnect();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    // *************************************************************************
    
    // Retrieve all data from Table ********************************************
    public String select(){
        return select("SELECT * FROM " + getTable());
    }
    // *************************************************************************

    // Retrieve specific data from table
    public String select(int id){
        return select("SELECT * FROM " + getTable() + " WHERE id = " + id);
    }
    // *************************************************************************

    // Retrieve data from query ************************************************
    public String select(String query){
        connect();
        
        if (verifyConnection() == false){
            return "Error";
        }

        try{
            Statement stm = getConn().createStatement();
            ResultSet result = stm.executeQuery(query);
            ResultSetMetaData resultData = result.getMetaData();
            
            String data = "";
            for (int i = 1; i <= resultData.getColumnCount(); i++){
                data += resultData.getColumnName(i);
                data += "\t";
            }
            data += "\n";
            
            while(result.next()){
                for (int i = 1; i <= resultData.getColumnCount(); i++){
                data += result.getString(i);
                data += "\t";
                }
                data += "\n";
            }
            disconnect();
            return data;
        }
        catch (Exception e){
            return "Erro";
        }
    }
    // *************************************************************************

    // Connect to Database *****************************************************
    private boolean connect(){
        setConn(ConnFactory.connect());
        return verifyConnection();
    }
    // *************************************************************************

    // Disconnect from Database ************************************************
    private void disconnect() throws Exception{
        ConnFactory.disconnect(getConn());
        setConn(null);
    }
    // *************************************************************************

    // Verify if there is a connection *****************************************
    private boolean verifyConnection(){
        if(getConn() == null){
            return false;
        }
        else{
            return true;
        }
    }
    // *************************************************************************

    // Get and set *************************************************************
    private void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
    // *************************************************************************

}
