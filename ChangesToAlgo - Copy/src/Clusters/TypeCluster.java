package Clusters;

import java.util.*;

import static Clusters.getMaxMin.getType;

public class TypeCluster {

    private String name;
    private int numObj;
    private int id;
    public TypeCluster left;
    public TypeCluster right;
    private List<Objects> objs=new ArrayList<Objects>();

    public TypeCluster(String name,int id)
    {
        this.name=name;
        numObj=0;
        this.id=id;
        left=null;
        right=null;
    }



    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public int getNumObj() {
        return numObj;
    }

    public void createObj(int h, String na, String ty, Double lon, Double lat, Double we)
    {
        objs.add(new Objects(na,lon,lat,we,ty));
    }

    public List<Objects> getObjs() {
        return objs;
    }

    public void displayAllObjs()
    {
        int i;
        for(i=0;i<numObj;i++)
        {
            System.out.println("Name===>"+objs.get(i).getName()+"=====>Type:"+objs.get(i).getType()+"====>Long:"+objs.get(i).getLong()+"======>Lat:"+objs.get(i).getLat()+"=====>Weight:"+objs.get(i).getWeight());
        }
    }

    public List<Objects> displayAllObjsName(String nam,List<Objects> ur)
    {
        int i,count=0;
        for(i=0;i<numObj;i++)
        {

            if(objs.get(i).getName().contains(nam)) {
                ur.add(objs.get(i));
            }

        }

        return ur;
    }
    public void Ended()
    {
        numObj=objs.size();
    }

    public boolean checkTerm(String temp)
    {
        int j;
        for(j=0;j<objs.size();j++)
        {
            if(objs.get(j).getName().equals(temp))
            {
                System.out.println(objs.get(j).getName()+"=======>"+objs.get(j).getLat()+"======>"+objs.get(j).getLong());
                return true;
            }
        }
        return false;

    }
}
