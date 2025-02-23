package com.onz.bars.bar.controller;


import com.onz.bars.bar.service.KakaoLocalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api/location/kakao")
@RestController
@RequiredArgsConstructor
public class KakaoLocalApiController {

    private final KakaoLocalApiService kakaoLocalApiService;

    @GetMapping
    public ResponseEntity<String> saveBars(@RequestParam(name = "keyword") String keyword,
                                           @RequestParam(name = "province") String province,
                                           @RequestParam(name = "district") String district,
                                           @RequestParam(name = "subdistrict") String subdistrict) throws IOException, InterruptedException {

        String query = String.join(" ", province, district, subdistrict, keyword);
        kakaoLocalApiService.saveBarAndMenuAndOpenHour(query, 1);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/all")
    public ResponseEntity<String> saveAllBars() throws IOException, InterruptedException {

        return ResponseEntity.ok("success");
    }
}
