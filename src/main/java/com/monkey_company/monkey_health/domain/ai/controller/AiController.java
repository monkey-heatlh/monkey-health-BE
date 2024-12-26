package com.monkey_company.monkey_health.domain.ai.controller;

import com.monkey_company.monkey_health.domain.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    @PostMapping("/analyzer-pushup")
    public ResponseEntity<String> analyzePushup(@RequestParam("video_frame") MultipartFile file) {
        // null 체크 추가
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 비어 있습니다.");
        }

        String response = aiService.callFastApi(file);
        return ResponseEntity.ok(response);
    }
}
