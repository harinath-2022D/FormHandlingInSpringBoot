package com.example.brainio.Dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class RequestFromFrontEndDto {
    private String name;
    private String email;
    private MultipartFile file;
    private String text;
    private String radio;
}
