package iss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dataBaseWorld {
    
    // Metoda pobiera dane i porównuje je z komórkami w MySQL. Jeżeli query znajdzie komórkę na podstawie kordów to wypisze nazwe z kolumny Name. 
    // Jeźeli jednak nie znajdzie to wypisze (Position: searching for position). Metoda ma najciekawsze zastosowanie jeżeli usadowimy ją w pętli i bedziemy 
    // pobierać to nowe pozycje stacji. Wtedy dynamicznie będzie wypisywana przybliźona pozycja. 
    // Do metody dodane zostały jeszcze Exception dla usprawnienia. 
    public void getPosition(double lati, double longi)throws SQLException{
    
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","FG","root");
            
            Statement stm = con.createStatement();
            
            ResultSet rs = stm.executeQuery("SELECT Name FROM sectors WHERE Latitude_1<" + lati + " AND Latitude_2>" + lati + " AND Longitude_1<" + longi + " AND Longitude_2>" + longi);
            
            if(rs.getRow() == 0) {
                System.out.println("Position: searching for position ");    
            }else{
                    String name = rs.getString("Name");
                    System.out.println("Position: " + name);
                
            }
                   
             
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(dataBaseWorld.class.getName()).log(Level.SEVERE, null, ex);
        }      
        catch(SQLTimeoutException ex){
            System.out.println("Czas łączenia z bazą danych został przekroczony.");
        }
        catch(SQLException ex){
            System.out.println("Brak dostępu do bazy danych.");
        }
             
    }
}           