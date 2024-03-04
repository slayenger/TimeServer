package com.timeserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/time")
public class TimeServerController {


    @GetMapping()
    public ResponseEntity<?> getCurrentTime()
    {
        Map<String, String> response = new HashMap<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        String formattedTime = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        response.put("currentTime", formattedTime);
        response.put("timezone", ZoneId.systemDefault().toString());

        return ResponseEntity.ok(response);
    }
}
