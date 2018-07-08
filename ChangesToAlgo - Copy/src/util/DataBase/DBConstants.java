package util.DataBase;
public enum DBConstants {

    DB_DRIVER_TYPE("jdbc:sqlite"),
    DB_LOCATION("D:\\research\\TopskQuery\\extras\\quadtree.db.enc");

    private String constValue = null;

    DBConstants(String constValue){
        this.setConstValue(constValue);
    }

    public String getConstValue() {
        return constValue;
    }

    private void setConstValue(String constValue) {
        this.constValue = constValue;
    }
}
