package music.controller;

import music.pojo.Comment;
import music.service.SongCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/song/{sid}/comment")
    @ResponseBody
    public String addComment(@PathVariable("sid") Integer sid, String content,@RequestParam("sub_cid") Integer subCid, @SessionAttribute("uid") Integer uid){
        Comment comment = new Comment();
        comment.setSid(sid);
        comment.setContent(content);
        comment.setSub_cid(subCid);
        comment.setUid(uid);
        return songCommentService.addComment(comment);
    }
}
