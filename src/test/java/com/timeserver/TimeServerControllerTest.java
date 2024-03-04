package com.timeserver;

import com.timeserver.controller.TimeServerController;
import com.timeserver.service.TimeServerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TimeServerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TimeServerService timeServerService;

    @Test
    public void testGetCurrentTime() throws Exception {
        LocalDateTime nowUTC = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        String formattedTimeUTC = nowUTC.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        when(timeServerService.getCurrentTime()).thenReturn(createResponseMap(formattedTimeUTC));

        mockMvc.perform(get("/api/time")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentTime").value(formattedTimeUTC))
                .andExpect(jsonPath("$.timezone").value("Europe/Moscow"));
    }

    private Map<String, String> createResponseMap(String currentTime) {
        Map<String, String> response = new HashMap<>();
        response.put("currentTime", currentTime);
        response.put("timezone", "Europe/Moscow");
        return response;
    }
}