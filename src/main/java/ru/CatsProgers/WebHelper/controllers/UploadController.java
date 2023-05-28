package ru.CatsProgers.WebHelper.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.CatsProgers.WebHelper.models.Patient;
import ru.CatsProgers.WebHelper.services.ExcelParserService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value(value = "${upload.path}")
    private String path;

    @PostMapping
    public void getFile(@RequestParam("file") MultipartFile file) {
        List<Patient> patients = new ArrayList<>();
        if (file != null) {
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            try {
                File toFile = new File(path + "/" + resultFileName);
                file.transferTo(toFile);
                ExcelParserService.readExcel(toFile.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
