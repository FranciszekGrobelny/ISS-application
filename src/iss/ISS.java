package iss;

public class ISS {

    public static void main(String[] args) throws Exception {

        urlDateDownload information = new urlDateDownload();   
        dataBaseWorld posi = new dataBaseWorld();
                
        information.getDate();   
        System.out.println("Szerokość geograficzna: " + information.getLatitude());
        System.out.println("Długość goegraficzna: " + information.getLongitude());
        posi.getPosition(information.getLatitude(),information.getLongitude());
    }
}