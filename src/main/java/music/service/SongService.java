package music.service;

import java.util.Map;

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

    /**
     * 获取歌曲ID的信息，歌曲名、歌手、封面url
     * @param sid 歌曲ID
     * @param uid　用户ID，　若不为空返回的信息中包含是否收藏到我最喜欢
     * @return {
     *     sid: 歌曲ID,
     *     name: 歌曲名,
     *     imgUrl: 歌曲封面链接,
     *     singer: 歌手,
     *     liked: true/false
     * }
     */
    Map<String, Object> getSongInfo(String sid, String uid);

    /**
     * 返回歌曲对应的歌词
     * @param sid 歌曲ID
     * @return 歌词
     */
    String getSongLyricUrl(String sid);
}
