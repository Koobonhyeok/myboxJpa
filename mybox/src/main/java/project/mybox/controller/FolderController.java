package project.mybox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.mybox.dto.FolderDto;
import project.mybox.service.FolderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
public class FolderController {

    private final FolderService folderService;

    @GetMapping("/folderReg")
    public ResponseEntity<?> folderReg( @ModelAttribute FolderDto folderDto ){
        Map<String, Object> pMap = folderService.folderReg(folderDto);

        return ResponseEntity.status(HttpStatus.OK).body(pMap);
    }

    @GetMapping("/getFolderList")
    public ResponseEntity<?> getFolderFist( @ModelAttribute FolderDto folderDto ){
        Map<String, Object> pMap = folderService.getFolderList(folderDto);

        return ResponseEntity.status(HttpStatus.OK).body(pMap);
    }

    @GetMapping("/folderRemove")
    public ResponseEntity<?> folderRemove( @ModelAttribute FolderDto folderDto ){
        Map<String, Object> pMap = folderService.folderRemove( folderDto );
        return ResponseEntity.status(HttpStatus.OK).body(pMap);
    }

//    @PostMapping("/folderRegForm")
//    public ResponseEntity<?> folderRegForm(HttpServletRequest request, @ModelAttribute FolderDto folderDto){
//        Map<String, Object> pMap = folderService.folderRegForm(request, folderDto);
//        return new ResponseEntity<>(pMap, HttpStatus.OK);
//    }

//    @GetMapping("/getFolderList")
//    public ResponseEntity<?> getFolderList(HttpServletRequest request){
//        Map<String, Object> pMap = folderService.getFolderList(request);
//
//        return new ResponseEntity<>(pMap, HttpStatus.OK);
//    }

//    @GetMapping("/folderRemove")
//    public ResponseEntity<?> folderRemove(HttpServletRequest request, @ModelAttribute FolderDto folderDto){
//        Map<String, Object> pMap = folderService.folderRemove( request, folderDto );
//        return new ResponseEntity<>(pMap, HttpStatus.OK);
//    }

}
