package Clusters;

public class Objects {
   private String name;
    private Double Long;
    private Double Lat;
    private Double weight;
    private String type;

    public Objects(String n,Double lo,Double la,Double we,String ty)
    {
        name=n;
        Long=lo;
        Lat=la;
        weight=we;
        type=ty;
    }

    public Double getLat() {
        return Lat;
    }

    public Double getLong() {
        return Long;
    }

    public Double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
