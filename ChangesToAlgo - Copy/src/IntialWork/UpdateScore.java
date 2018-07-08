package IntialWork;
import util.DataBase.DBConstants;

import java.io.*;
import java.sql.*;
import java.util.*;

import static Algorithm3.Haversine.calculateHaversine;

public class UpdateScore {

    public static void main(String args[]) throws SQLException, IOException {
       /* File file=new File("en");
        long start= System.nanoTime();
        FileOutputStream fos=new FileOutputStream("Out.txt");
        //System.setOut(new PrintStream(fos));
        for(File f:file.listFiles())
        {
            System.out.println("-----------------------"+f.getName()+"-----------------------------------");
            Scanner scan=new Scanner(f);
            System.out.println(f.getCanonicalPath());
            //while (scan.hasNextLine())
                //System.out.println(scan.nextLine());
        }
        System.out.println("Totallly : " + (double)(System.nanoTime()-start)/1000000000);
        System.exit(0);*/
        HashMap<Integer, String> index = new HashMap<Integer, String>();
        Statement con = DriverManager.getConnection(DBConstants.DB_DRIVER_TYPE.getConstValue() + ":" + DBConstants.DB_LOCATION.getConstValue()).createStatement();
        int i=277668;
        double lon1=0.0,lat1=0.0;
        while(i<=352962)
        {
            ResultSet rs=con.executeQuery("SELECT name,Long,Lat FROM queries WHERE id="+i+";");
            if(!rs.isClosed()) {
            double lat=rs.getDouble("Long");
            double lon=rs.getDouble("Lat");
            if(rs.getString("name")!=null || rs.getString("name").length()!=0) {
                String res = Double.toString(calculateHaversine(lon1, lat1, lon, lat));
                con.executeUpdate("UPDATE queries SET Weight='" + res + "' WHERE id=" + i + " ;");
             }
            }

            i++;
            int res=(int)(((double)i/352962)*100);
            System.out.print("\r Completed..."+res+" %    ("+i+"/352962)     estimated Time : "+((352962-i)/60)+" mins");
        }
    }
}