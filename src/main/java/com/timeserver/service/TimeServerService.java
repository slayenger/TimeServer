package com.timeserver.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeServerService {


    public Map<String, String> getCurrentTime()
    {
        Map<String, String> response = new HashMap<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        String formattedTime = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        response.put("currentTime", formattedTime);
        response.put("timezone", ZoneId.systemDefault().toString());

        return response;
    }


}
