package iss;

import java.net.*;
import java.io.*;
import org.json.JSONObject;


public class urlDateDownload  {
    
// Metoda wypisuje dległość i predkość satelity na podstawie pobranych danych ze storny, są one przypisane do tablicy 
    void getDate() throws Exception{
    
        double [][]tab = new double[2][3];
        double way, speed;
        StringBuffer response;
        String inputLine;
        
        URL obj = new URL("http://api.open-notify.org/iss-now.json");  
                
        for(int i=0;i<=1;i++){
                            
            try (BufferedReader in = new BufferedReader(new InputStreamReader(obj.openStream()))) {
                          
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                  
                    response.append(inputLine);
                }
            }

            JSONObject myresponse = new JSONObject(response.toString());     

            tab[i][0] = myresponse.getJSONObject("iss_position").getDouble("latitude");
            tab[i][1] = myresponse.getJSONObject("iss_position").getDouble("longitude");
            tab[i][2] = myresponse.getDouble("timestamp");

            if(i==1){
                break;
            }
            
            Thread.sleep(5000);
                                    
        }
            
            way = Math.sqrt(Math.pow((tab[1][0]-tab[0][0]), 2) + Math.pow((tab[1][1]-tab[0][1]), 2)) * 111.196672;
            
            speed = way/(tab[1][2]-tab[0][2]);
            
            System.out.println("Odlełość między pobranymi punktami A i B: " + way + " km");
            System.out.println("Predkość: " + speed + " km/s");
                                                
    }

// Metoda pobiera szerokość geograficzną stacji kosmicznej
    double getLatitude() throws Exception{

        StringBuffer response;
        String inputLine;

        URL obj = new URL("http://api.open-notify.org/iss-now.json");  

            try (BufferedReader in = new BufferedReader(new InputStreamReader(obj.openStream()))) {

                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);
                }
            }

        JSONObject myresponse = new JSONObject(response.toString());     

        double lati = Double.parseDouble(myresponse.getJSONObject("iss_position").getString("latitude"));

        return lati;
    }
    
    
// Metoda pobiera długość geograficzną stacji kosmicznej    
    double getLongitude() throws Exception{

        StringBuffer response;
        String inputLine;

        URL obj = new URL("http://api.open-notify.org/iss-now.json");  

            try (BufferedReader in = new BufferedReader(new InputStreamReader(obj.openStream()))) {

                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);
                }
            }

        JSONObject myresponse = new JSONObject(response.toString());     

        double longi = Double.parseDouble(myresponse.getJSONObject("iss_position").getString("longitude"));

        return longi;
    }  
}  