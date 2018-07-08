package UserInput;

import util.DataBase.DBConstants;

import java.util.*;
import java.sql.*;

import static Algorithm3.Haversine.calculateHaversine;

public class input {
    private static String [] inputs;
    private static HashMap<String,List<String>> type=new HashMap<String,List<String>>();
    private static String Location1[];
    private static HashMap<Integer,Double> loc=new HashMap<Integer,Double>();
    private static long t1;

    public static void takeInput() throws SQLException
    {
        Scanner manu=new Scanner(System.in);
        System.out.println("Please Enter number of Queries");
        int n=manu.nextInt();
        int i;
        inputs=new String[n];
        Location1=new String[n];
        String temp=manu.nextLine();
        for(i=0;i<n;i++)
        {
            inputs[i]=manu.nextLine();
            Location1[i]=manu.nextLine();
        }
        t1=System.nanoTime();
        calculateTypeFreq();

    }

    public static void calculateTypeFreq() throws SQLException
    {
        Statement con1=DriverManager.getConnection(DBConstants.DB_DRIVER_TYPE.getConstValue()+":"+DBConstants.DB_LOCATION.getConstValue()).createStatement();
        int i,j;
        for(i=0;i<inputs.length;i++)
        {

                if(!type.containsKey(inputs[i]))
                {
                    ResultSet rs1=con1.executeQuery("SELECT type FROM euro WHERE name LIKE '%"+inputs[i]+"%';");
                    List<String> li=new ArrayList<String>();
                    while(rs1.next())
                    {

                        if(!li.contains(rs1.getString("type")))
                     li.add(rs1.getString("type"));
                    }

                    type.put(inputs[i],li);
                }

        }
    }

    public  static  void calculateLocation()
    {
        int i;
        for(i=0;i<Location1.length;i++)
        {
            String te[]=Location1[i].split(",");
            loc.put(i,calculateHaversine(0.0,0.0,Double.parseDouble(te[0]),Double.parseDouble(te[1])));
        }
    }

    public static HashMap<String, List<String>> getTypeInputs() {
        return type;
    }

    public static String[] getInputs() throws SQLException {

        takeInput();
        return inputs;
    }

    public static long getT1() {
        return t1;
    }

    public static HashMap<Integer, Double> getLoc() {
        calculateLocation();
        return loc;
    }

    public static String getLocation1(int i) {
        return Location1[i];
    }
}
