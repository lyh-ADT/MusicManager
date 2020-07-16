package music.pojo;

/**
 * @author lyhADT
 */
public class Song {
    private int sid;
    private String url;
    private String sname;
    private String imgUrl;
    private String lyric;
    private String singer;

    @Override
    public String toString() {
        return "Song{" +
                "sid=" + sid +
                ", url='" + url + '\'' +
                ", sname='" + sname + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", lyric='" + lyric + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
