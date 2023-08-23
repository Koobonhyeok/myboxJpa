package project.mybox.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.mybox.dto.FileDto;
import project.mybox.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    public final FileService fileService;

    @GetMapping("/fileRegForm")
    public ResponseEntity<?> fileRegForm(@RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                         HttpServletRequest request){
        Map<String, Object> pMap = fileService.fileRegForm(multipartFile, request);
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

    @GetMapping("/fileRemove")
    public ResponseEntity<?> fileRemove( @ModelAttribute FileDto fileDto, HttpServletRequest request ){
        Map<String, Object> pMap = fileService.fileRemove( fileDto, request );
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

    @GetMapping("/fileDownload")
    public ResponseEntity<?> fileDownload(HttpServletRequest request, HttpServletResponse response ){
        Map<String, Object> pMap = fileService.fileDownload(request, response);
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

}
