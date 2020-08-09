package music.service.impl;

import music.dao.MusicListDao;
import music.service.MusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicListServiceImpl implements MusicListService {
    @Autowired
    MusicListDao musicListDao;

    /**
     *
     * @param mlid  音乐列表id
     * @return  列表相关信息
     */
    @Override
    public List<Map<String, Object>> getMusicListInfo(int mlid) {
        return musicListDao.getMusicListInfo(mlid);
    }
}
