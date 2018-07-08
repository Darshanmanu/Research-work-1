package Clusters;

import java.util.*;

import static Clusters.getMaxMin.getType;

public class MainClusters {
    private Double rangeStart;
    private Double rangeEnd;
    private int id;
    public TypeCluster subclusters[];

    public MainClusters()
    {

        rangeStart=null;
        rangeEnd=null;
    }

    public MainClusters(Double r1,Double r2,int id)
    {
        int i;
        subclusters=new TypeCluster[getType().size()];
        List<String> ty=getType();
        for(i=0;i<ty.size();i++)
        {
            subclusters[i]=new TypeCluster(ty.get(i),i);
        }
        rangeStart=r1;
        rangeEnd=r2;
        this.id=id;
    }

    public Double getRangeStart()
    {
        return rangeStart;
    }
    public Double getRangeEnd()
    {
        return rangeEnd;
    }
    public int getId()
    {
        return id;
    }

    public TypeCluster[] getSubclusters() {
        return subclusters;
    }

    public TypeCluster getSubclusters(int i)
    {
        return subclusters[i];
    }


    public void displaySubClusters()
    {
        int i;
        for(i=0;i<subclusters.length;i++)
        {
            if(subclusters[i].getNumObj()>0)
            {
                System.out.println(subclusters[i].getName().toUpperCase());
                subclusters[i].displayAllObjs();
            }
        }
    }

    public void displaySubClusters(String na)
    {
        for(int i=0;i<subclusters.length;i++)
        {
            if(subclusters[i].getName().equals(na))
            {
                if(subclusters[i].getNumObj()>0) {
                    System.out.println(subclusters[i].getName().toUpperCase());
                    subclusters[i].displayAllObjs();
                }
            }
        }
    }

    public boolean displayTermDetails(String temp)
    {
        for(int i=0;i<subclusters.length;i++)
        {
            if(subclusters[i].checkTerm(temp))
                return true;
        }
        return false;
    }

    public int indexOfCluster(String ty)
    {
        int i;
        for(i=0;i<subclusters.length;i++)
        {
            if(subclusters[i].getName().equals(ty)) {
                return i;
            }
        }
         return -1;
    }

    public void addObjsToClusters(int id,int count,String na,String ty,Double we,Double lo,Double la)
    {
        subclusters[id].createObj(count,na,ty,lo,la,we);
    }

    public void addObjsEnd()
    {
        int i;
        for(i=0;i<subclusters.length;i++) {
            subclusters[i].Ended();
        }
    }

    public List<Objects> traverseSubclusters(int id,String temp,List<Objects> ur)
    {
        TypeCluster d=subclusters[id].left;
        TypeCluster d2=subclusters[id].right;
        while(d!=null || d2!=null)
        {
            if(d!=null)
            {
                ur=d.displayAllObjsName(temp,ur);
                d=d.left;
            }

            if(d2!=null)
            {
                ur=d2.displayAllObjsName(temp,ur);
                d2=d2.right;
            }
        }
        return ur;
    }
}
