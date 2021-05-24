package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author marcos
 */
public class DB {
    private static Connection conec = null;
    
    public static Connection getConnection(){
        if(conec == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conec = DriverManager.getConnection(url,props);
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
            
        }
        return conec;
    }
    
    public static void closeConnection(){
        try{
            if(conec != null){
                conec.close();
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
      public static void closeStatement(Statement st){
        try{
            if(st != null){
                st.close();
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
      public static void closeResultSet(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
            
        } catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
    }
    
}
