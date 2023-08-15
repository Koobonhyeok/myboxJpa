package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.mybox.domain.Files;
import project.mybox.domain.User;
import project.mybox.dto.FileDto;
import project.mybox.repository.FileRepository;
import project.mybox.repository.UserRepository;
import project.mybox.security.JwtTokenProvider;
import project.mybox.utiles.Common;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    private final Common common;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Map<String, Object> fileRegForm(MultipartFile multipartFile, FileDto fileDto, HttpServletRequest request){
        Map<String, Object> pMap = new HashMap<>();
        try {
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "/MyBox/"+userId+"/"+request.getHeader("path");

            String uploadFileNm = multipartFile.getOriginalFilename();
            if( common.validation(path,uploadFileNm.substring(0, uploadFileNm.lastIndexOf('.'))) ){
                User user = userRepository.loadUserByUsername(userId);
//                user.getStorage() + multipartFile.getSize()


                File file = new File(path+"/"+uploadFileNm);
                multipartFile.transferTo(file);

                Files file_info =  Files.builder()
                        .fileName(uploadFileNm.substring(0, uploadFileNm.lastIndexOf('.')))
                        .fileStorage(multipartFile.getSize())
                        .fileExt(uploadFileNm.substring(uploadFileNm.lastIndexOf('.')+1))
                        .build();

            }
//            fileRepository.fileRegForm();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  pMap;
    }
}
