package music.pojo;

public class music_list_detail {
    String imgurl;
    String mlname;
    String date;
    String description;
    String sname;
    Integer sid;
    String singer;
    boolean likeOrNot;

    @Override
    public String toString() {
        return "music_list_detail{" +
                "imgurl='" + imgurl + '\'' +
                ", mlname='" + mlname + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", sname='" + sname + '\'' +
                ", sid=" + sid +
                ", singer='" + singer + '\'' +
                ", likeOrNot=" + likeOrNot +
                '}';
    }

    public boolean isLikeOrNot() {
        return likeOrNot;
    }

    public void setLikeOrNot(boolean likeOrNot) {
        this.likeOrNot = likeOrNot;
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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
