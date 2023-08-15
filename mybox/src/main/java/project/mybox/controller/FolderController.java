package project.mybox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.mybox.domain.Folder;
import project.mybox.dto.FolderDto;
import project.mybox.service.FolderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folderRegForm")
    public ResponseEntity<?> folderRegForm(HttpServletRequest request, @ModelAttribute FolderDto folderDto){
        System.out.println("folderDTO     ::   " + folderDto.toString());
        Map<String, Object> pMap = folderService.folderRegForm(request, folderDto);
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

}
