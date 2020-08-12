package music.controller;

import music.service.SongCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SongCommentController.class)
class SongCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongCommentService songCommentService;

    private MockHttpSession session;

    @BeforeEach
    public void setupHttpSession() {
        session = new MockHttpSession();
        session.setAttribute("uid", 1);
    }

    @Test
    void addComment() throws Exception {
        Mockito.when(songCommentService.addComment(Mockito.any())).thenReturn("adding success");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/song/1/comment")
                .session(session)
                .param("content", "comment content");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("adding success"));

        Mockito.verify(songCommentService, Mockito.times(1)).addComment(Mockito.argThat(argument ->
                "comment content".equals(argument.getContent())
                && argument.getSid() == 1
                && argument.getUid() == 1));
    }
}