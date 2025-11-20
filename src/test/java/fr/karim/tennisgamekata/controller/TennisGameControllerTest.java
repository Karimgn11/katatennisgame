package fr.karim.tennisgamekata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.karim.controller.TennisGameController;
import fr.karim.dto.TennisGameRequestDto;
import fr.karim.dto.TennisGameResponseDto;
import fr.karim.service.TennisGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TennisGameController.class)
class TennisGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TennisGameService tennisGameService;

    @Test
    void createGame_shouldReturn201_andResponseBody() throws Exception {
        // GIVEN
        TennisGameRequestDto requestDto = TennisGameRequestDto.builder()
                .playerAName("Nadal")
                .playerBName("Djokovic")
                .sequence("ABABAA")
                .build();

        TennisGameResponseDto responseDto = TennisGameResponseDto.builder()
                .id(1L)
                .playerAName("Nadal")
                .playerBName("Djokovic")
                .sequence("ABABAA")
                .winner("A")
                .scoreTimeline(List.of("...", "Player A wins the game"))
                .createdAt(LocalDateTime.now())
                .build();

        when(tennisGameService.createGameAndComputeScore(any(TennisGameRequestDto.class)))
                .thenReturn(responseDto);

        // WHEN + THEN
        mockMvc.perform(post("/api/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.playerAName").value("Nadal"))
                .andExpect(jsonPath("$.playerBName").value("Djokovic"))
                .andExpect(jsonPath("$.winner").value("A"));
    }

    @Test
    void getGame_shouldReturn200_andResponseBody() throws Exception {
        // GIVEN
        Long id = 1L;
        TennisGameResponseDto responseDto = TennisGameResponseDto.builder()
                .id(id)
                .playerAName("Karim")
                .playerBName("Alpha")
                .sequence("AAAA")
                .winner("A")
                .scoreTimeline(List.of("...", "Player A wins the game"))
                .createdAt(LocalDateTime.now())
                .build();

        when(tennisGameService.getGameById(eq(id))).thenReturn(responseDto);

        // WHEN + THEN
        mockMvc.perform(get("/api/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.playerAName").value("Karim"))
                .andExpect(jsonPath("$.playerBName").value("Alpha"))
                .andExpect(jsonPath("$.winner").value("A"));
    }
}
