package music.service;

/**
 * @author lyhADT
 */
public interface SongService {
    /**
     * 根据歌曲ID获取对应的MP3连接
     * @param sid 歌曲ID
     * @return 对应的MP3连接
     */
    String getUrlBySid(String sid);
}
