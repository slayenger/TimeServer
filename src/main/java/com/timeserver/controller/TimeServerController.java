package com.timeserver.controller;

import com.timeserver.service.TimeServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/time")
public class TimeServerController {

    private final TimeServerService timeServerService;

    @GetMapping()
    public ResponseEntity<?> getCurrentTime()
    {
        return ResponseEntity.status(HttpStatus.OK).body(timeServerService.getCurrentTime());
    }
}
