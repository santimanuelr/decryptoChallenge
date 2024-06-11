package com.marcket.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcket.api.model.Commiter;
import com.marcket.api.service.CommiterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommiterController.class)
public class CommiterControllerTest {

    public static final String COMMITER_DESCRIPTION = "Commiter description";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommiterService commiterService;


    @Test
    void testCreateCommiter() throws Exception {

        Commiter commiter = new Commiter();
        commiter.setDescription(COMMITER_DESCRIPTION);

        Commiter commiterSaved = new Commiter();
        commiterSaved.setId(1L);
        commiterSaved.setDescription(COMMITER_DESCRIPTION);
        when(commiterService.saveCommiter(commiter)).thenReturn(commiterSaved);

        // Perform the POST request
        mockMvc.perform(post("/commiters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commiter)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value(COMMITER_DESCRIPTION));

        // Assert the response status and body
        verify(commiterService).saveCommiter(eq(commiter)); // Verify service interaction
    }

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
