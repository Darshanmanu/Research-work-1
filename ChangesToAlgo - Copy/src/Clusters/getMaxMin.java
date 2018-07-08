package Clusters;
import util.DataBase.DBConstants;

import java.util.*;
import java.sql.*;

public class getMaxMin {

    private static List<Double> set1=new ArrayList<Double>();
    private static List<String> set2=new ArrayList<String>();
    private static HashMap<Double,String> set3=new HashMap<Double,String>();
    private static HashMap<Double,String> set4=new HashMap<Double,String>();
    private static HashMap<String,Integer> typeId=new HashMap<String, Integer>();


    public static HashMap<Double,String> getSetList() throws SQLException
    {
        int count=0;
        HashMap<Double,String> map1=new HashMap<Double, String>();
        Statement con=DriverManager.getConnection(DBConstants.DB_DRIVER_TYPE.getConstValue()+":"+DBConstants.DB_LOCATION.getConstValue()).createStatement();
        ResultSet rs=con.executeQuery("SELECT longitude,latitude,type,name,Weight FROM euro;");
        while(rs.next())
        {
            String lon=Double.toString(rs.getDouble("longitude"));
            String res1=lon+"##"+Double.toString(rs.getDouble("latitude"));
            String type=rs.getString("type");
            String name=rs.getString("name");
            Double weight=Double.parseDouble(rs.getString("weight"));
            if(!map1.containsKey(weight))
            {
                map1.put(weight,name);
                set3.put(weight,res1);
                set1.add(weight);
                set4.put(weight,type);
            }
            if(!set2.contains(type)) {
                count++;
                typeId.put(type,count);
                set2.add(type);
            }
        }

        Collections.sort(set1);
        return map1;
    }

    public static List<Double> getTotalTerms()
    {
        return set1;
    }

    public static List<String> getType()
    {
        return set2;
    }
    public static HashMap<Double,String> getLongLat()
    {
        return set3;
    }

    public static HashMap<Double,String> getTypePairs()
    {
        return set4;
    }

    public static String SendLongLat(Double val)
    {
        return set3.get(val);
    }

    public static String SendType(Double val)
    {
        return set4.get(val);
    }

    public static List<Double> getInputList(Double min,Double max)
    {
        int i;
        List<Double> ret=new ArrayList<Double>();
        for(i=0;i<set1.size();i++)
        {
            if(set1.get(i)>=min&&set1.get(i)<=max)
            {
                ret.add(set1.get(i));
            }

        }
        return  ret;
    }

    public static HashMap<String, Integer> getTypeId() {
        return typeId;
    }
}
