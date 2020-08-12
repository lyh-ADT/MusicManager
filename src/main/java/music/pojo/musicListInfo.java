package music.pojo;

public class musicListInfo {
    Integer mlid;
    Integer uid;
    String mlname;
    String imgurl;
    String description;
    String date;


    @Override
    public String toString() {
        return "musicListInfo{" +
                "mlid=" + mlid +
                ", uid=" + uid +
                ", imgurl='" + imgurl + '\'' +
                ", mlname='" + mlname + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMlname() {
        return mlname;
    }

    public void setMlname(String mlname) {
        this.mlname = mlname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
