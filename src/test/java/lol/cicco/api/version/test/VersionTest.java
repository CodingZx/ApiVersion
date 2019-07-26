package lol.cicco.api.version.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VersionTest {
    private MockMvc mockMvc;

    @Autowired
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testApiVersion() throws Exception {
        String uri = "/version";
        mockMvc.perform(get(uri)).andExpect(status().isOk()).andExpect(content().string("version-default"));

        mockMvc.perform(get(uri).header("api-version", "0.0.1")).andExpect(status().is4xxClientError());

        mockMvc.perform(get(uri).header("api-version", "a.0.1")).andExpect(status().is4xxClientError());

        mockMvc.perform(get(uri).header("api-version", "1.0.1")).andExpect(status().isOk())
                .andExpect(content().string("version-1.0.1"));

        mockMvc.perform(get(uri).header("api-version", "1.0.2")).andExpect(status().isOk())
                .andExpect(content().string("version-1.0.1"));

        mockMvc.perform(get(uri).header("api-version", "1.2.2")).andExpect(status().isOk())
                .andExpect(content().string("version-1.0.1"));

        mockMvc.perform(get(uri).header("api-version", "2.3.4")).andExpect(status().isOk())
                .andExpect(content().string("version-2.0.1"));

        mockMvc.perform(get(uri).header("api-version", "5.0.2")).andExpect(status().isOk())
                .andExpect(content().string("version-5.0.1.6"));


    }
}
