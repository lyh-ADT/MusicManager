package music.controller;

import music.service.SongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Test
    public void testGetMp3() throws Exception{
        Mockito.when(songService.getUrlBySid("1")).thenReturn("success");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/song/1/mp3");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("success"));

        Mockito.verify(songService, Mockito.times(1)).getUrlBySid("1");
    }
}
