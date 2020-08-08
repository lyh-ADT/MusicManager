package music.controller;

import music.pojo.Comment;
import music.service.SongCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 歌曲评论控制层
 * @author lyhADT
 */
@Controller
public class SongCommentController {

    @Autowired
    private SongCommentService songCommentService;

    @GetMapping("/song/{sid}/comments")
    @ResponseBody
    public List<Comment> getAllComments(@PathVariable("sid") String sid){
        return songCommentService.getAllComments(sid);
    }
}
