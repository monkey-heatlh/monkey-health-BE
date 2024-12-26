package com.monkey_company.monkey_health.domain.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.io.ByteArrayResource;

@Service
@RequiredArgsConstructor
public class AiService {

    private final WebClient webClient;

    public String callFastApi(MultipartFile file) {
        try {
            // MultipartFile을 ByteArrayResource로 변환
            byte[] fileBytes = file.getBytes();
            ByteArrayResource byteArrayResource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();  // 파일 이름 전달
                }
            };

            // form-data 생성
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("video_frame", byteArrayResource);  // video_frame 키로 파일을 추가

            // FastAPI 호출
            return webClient.post()
                    .uri("/analyze-pushup/")  // FastAPI의 URL
                    .contentType(MediaType.MULTIPART_FORM_DATA)  // multipart/form-data 형식
                    .bodyValue(map)  // form-data 본문
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();  // 응답을 동기적으로 처리
        } catch (Exception e) {
            e.printStackTrace();
            return "FastAPI 호출 중 오류 발생: " + e.getMessage();
        }
    }
}
