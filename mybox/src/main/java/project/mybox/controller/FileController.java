package project.mybox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.mybox.dto.FileDto;
import project.mybox.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    public final FileService fileService;

    @GetMapping("/fileRegForm")
    public ResponseEntity<?> fileRegForm(@RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                         @ModelAttribute FileDto fileDto, HttpServletRequest request){
        Map<String, Object> pMap = fileService.fileRegForm(multipartFile, fileDto, request);
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

}
