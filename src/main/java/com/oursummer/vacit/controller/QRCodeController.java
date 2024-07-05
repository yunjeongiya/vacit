package com.oursummer.vacit.controller;

import com.google.zxing.WriterException;
import com.oursummer.vacit.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QRCodeController {
    private final QRCodeService qrCodeGenerator;

    @GetMapping("/generateQRCode")
    public ResponseEntity<byte[]> generateQRCode() {
        String text = "http://turtle-hwan.iptime.org:30000/KUIT3_Hackathon_Team5-Web/feed";
        try {
            byte[] qrCodeImage = qrCodeGenerator.generateQRCode(text, 250, 250);
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl("no-cache, no-store, must-revalidate");
            headers.setPragma("no-cache");
            headers.setExpires(0);
            headers.setContentType(org.springframework.http.MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}