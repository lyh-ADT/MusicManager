package music.pojo;

public class music_list_songs {

    String sname;
    Integer sid;
    String singer;
    boolean likeOrNot;

    @Override
    public String toString() {
        return "music_list_songs{" +
                "sname='" + sname + '\'' +
                ", sid=" + sid +
                ", singer='" + singer + '\'' +
                ", likeOrNot=" + likeOrNot +
                '}';
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

    public boolean isLikeOrNot() {
        return likeOrNot;
    }

    public void setLikeOrNot(boolean likeOrNot) {
        this.likeOrNot = likeOrNot;
    }
}
