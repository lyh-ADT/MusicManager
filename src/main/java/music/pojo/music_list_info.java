package music.pojo;

public class music_list_info {
    String mlname;
    Integer mlid;
    Integer uid;

    @Override
    public String toString() {
        return "music_list_info{" +
                "mlname='" + mlname + '\'' +
                ", mlid=" + mlid +
                ", uid=" + uid +
                '}';
    }

    public String getMlname() {
        return mlname;
    }

    public void setMlname(String mlname) {
        this.mlname = mlname;
    }

    public Integer getMlid() {
        return mlid;
    }

    public void setMlid(Integer mlid) {
        this.mlid = mlid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
