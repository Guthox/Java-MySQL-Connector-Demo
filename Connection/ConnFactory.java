package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnFactory{

    private static String user = "root";
    private static String pass = "admin";
    private static String database = "Test";
    private static String server = "localhost:3306";

    private static String url = "jdbc:mysql://" + server +  "/" + database;

    // Create a connection to the Database *************************************
    public static Connection connect(){
        try{
            updateUrl();
            return DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e){
            return null;
        }
    }
    // *************************************************************************

    // Disconnect from database ************************************************
    public static void disconnect(Connection conn) throws Exception{
        conn.close();
    }
    // *************************************************************************

    // Get and Set *************************************************************
    public static void setDatabase(String database) {
        ConnFactory.database = database;
    }
    public static String getDatabase() {
        return database;
    }

    public static void setPass(String pass) {
        ConnFactory.pass = pass;
    }
    public static String getPass() {
        return pass;
    }

    public static void setUser(String user) {
        ConnFactory.user = user;
    }
    public static String getUser() {
        return user;
    }

    public static void setServer(String server) {
        ConnFactory.server = server;
    }
    public static String getServer() {
        return server;
    }
    // *************************************************************************
    
    // Update url String *******************************************************
    private static void updateUrl(){
        url = "jdbc:mysql://" + getServer() +  "/" + getDatabase();
    }
    // *************************************************************************
}