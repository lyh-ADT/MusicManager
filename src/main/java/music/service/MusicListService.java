package music.service;

import java.util.List;
import java.util.Map;

public interface MusicListService {
    List<Map<String , Object>> getMusicListInfo(int mlid);
}
