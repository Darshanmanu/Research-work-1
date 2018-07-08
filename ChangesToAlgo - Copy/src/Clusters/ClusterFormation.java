package Clusters;

import com.sun.org.apache.xpath.internal.SourceTree;

//2.2secs;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static Algorithm3.Haversine.calculateHaversine;
import static Clusters.getMaxMin.*;
import static UserInput.input.*;

public class ClusterFormation {
    public static void main(String args[]) throws SQLException
    {
        Scanner manu=new Scanner(System.in);
        long t11=System.nanoTime();
        HashMap<Double,String> mq=getSetList();
        List<Double> set1=getTotalTerms();

        Double max=set1.get(set1.size()-1);
        Double min=set1.get(0);
        Double start=min;
        Double Diff=max-min;
        Double addUp=Diff/10;

        MainClusters clusters[]=new MainClusters[10];
        int i;
        for(i=0;i<10;i++)
        {
            clusters[i]=new MainClusters(start,start+addUp,i);
            start=start+addUp;
        }

        for(i=0;i<10;i++)
        {
            Double cmin=clusters[i].getRangeStart();
            Double cmax=clusters[i].getRangeEnd();
            List<Double> inputList =getInputList(cmin,cmax);
            int countobj=0;
            int j=0;
            while(j<inputList.size())
            {
                String name=mq.get(inputList.get(j));
                Double weight=inputList.get(j);
                String type=SendType(inputList.get(j));
                String LonLat=SendLongLat(inputList.get(j));
                String s[]=LonLat.split("##");
                Double Lo=Double.parseDouble(s[0]);
                Double La=Double.parseDouble(s[1]);
                int id=clusters[i].indexOfCluster(type);
                clusters[i].addObjsToClusters(id,countobj,name,type,weight,Lo,La);
                j++;
                countobj++;
            }
            clusters[i].addObjsEnd();
        }

        for(i=0;i<10;i++)
        {
            if(i>0)
            {
                TypeCluster sub0[]=clusters[i-1].getSubclusters();
                TypeCluster sub[]=clusters[i].getSubclusters();
                for(int h=0;h<getType().size();h++)
                {
                    clusters[i-1].subclusters[h].right=clusters[i].subclusters[h];
                    clusters[i].subclusters[h].left=clusters[i-1].subclusters[h];
                }

            }
        }
        long t12=System.nanoTime();

        String in[]=getInputs();

        HashMap<String,List<String>> typelist=getTypeInputs();
        HashMap<Integer,Double> userLocation=getLoc();
        HashMap<String,Integer> typeId=getTypeId();



        int clusterid2=-1;


        int j;

        for(int t=0;t<in.length;t++) {
            int clusterid =0;
            Double uR = userLocation.get(t);
            String ul[]=getLocation1(t).split(",");
            Double ulon=Double.parseDouble(ul[1]);
            Double ulat=Double.parseDouble(ul[0]);
            System.out.println("Result For user :"+(t+1));
            for (i = 0; i < clusters.length; i++) {
                Double maxR = clusters[i].getRangeEnd();
                Double minR = clusters[i].getRangeStart();
              //  System.out.println(maxR + "----" + minR);
               // System.out.println(uR);
                if (uR <= maxR && uR >= minR) {
                    clusterid = i;
                   // System.out.println("Getting in here");
                    break;
                }
            }


                    List<Objects> userResult=new ArrayList<Objects>();
                    List<String> rt = typelist.get(in[t]);
                    for (int k = 0; k < rt.size(); k++) {

                        int id = typeId.get(rt.get(k));
                        id--;
                        TypeCluster temp = clusters[clusterid].getSubclusters(id);
                        userResult=temp.displayAllObjsName(in[t],userResult);
                       // if(userResult.size()<15) {
                            userResult= clusters[clusterid].traverseSubclusters(id, in[t],userResult);
                        //}
                    }
            if(userResult.size()>0)
            {
                HashMap<Double,Objects> m1=new HashMap<Double, Objects>();
                List<Double> lsd=new ArrayList<Double>();
                for(Objects h:userResult) {
                    Double rw=calculateHaversine(ulon,ulat,h.getLong(),h.getLat());
                    m1.put(rw,h);
                }
                TreeMap<Double,Objects> tm=new TreeMap<Double, Objects>(m1);
                int count=0;
                /*for(Double fg:tm.keySet())
                {
                    Objects sd=tm.get(fg);
                    System.out.println(sd.getName()+"=======>"+fg+"============>"+sd.getType());
                }*/
                System.out.println("<------------------------------------------------------------------------------------------------------------------------------->");
                for(Double fg:tm.keySet())
                {
                    if(count>15)
                        break;
                    Objects sd=tm.get(fg);
                    System.out.println(sd.getName()+"=======>"+fg+"============>"+sd.getType());
                    count++;
                }
            }

        }
        long t2=System.nanoTime();
        double ctime=(double)(t12-t11)/(Math.pow(10,9));
        double etime=(double)(t2-getT1())/(Math.pow(10,9));
        System.out.println("Time Required for Cluster Formation :"+ctime);
        System.out.println("Time Required to provide the user result :"+etime);
        System.out.println("Total Time:"+(ctime+etime));




    }
}
