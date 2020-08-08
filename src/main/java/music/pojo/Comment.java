package music.pojo;

/**
 * 歌曲评论实体类
 * @author lyhADT
 */
public class Comment {
    private int cid;
    private String content;
    private int uid;
    private Integer sub_cid;
    private int sid;
    private String cdate;

    private String nickname;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Integer getSub_cid() {
        return sub_cid;
    }

    public void setSub_cid(Integer sub_cid) {
        this.sub_cid = sub_cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", sub_cid=" + sub_cid +
                ", sid=" + sid +
                ", cdate='" + cdate + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
