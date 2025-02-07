package com.example.pdf_demo.controller;

import com.example.pdf_demo.entity.Student;
import com.example.pdf_demo.repository.StudentRepository;
import com.example.pdf_demo.util.PdfGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PdfGenaratorUtil pdfGenaratorUtil;

    @GetMapping("student/{studentId}")
    public ResponseEntity<Resource> getStudentInfoPdf(@PathVariable Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student == null) {
            throw new RuntimeException("Student not present");
        }

        Map<String, Object> studentMap = new HashMap<>();
        studentMap.put("ID", student.getId());
        studentMap.put("firstName", student.getFirstName());
        studentMap.put("lastName", student.getLastName());
        studentMap.put("grade", student.getGrade());  // Avoid "class" keyword

        Resource resource = null;
        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            String fileNameUrl = pdfGenaratorUtil.createPdf("Student", studentMap);
            Path path = Paths.get(tempDir + "/" + fileNameUrl);
            resource = new UrlResource(path.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
