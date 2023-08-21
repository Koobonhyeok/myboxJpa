package project.mybox.service;

import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.mybox.domain.Folder;
import project.mybox.domain.User;
import project.mybox.dto.FolderDto;
import project.mybox.repository.FolderRepository;
import project.mybox.repository.UserRepository;
import project.mybox.security.JwtTokenProvider;
import project.mybox.utiles.Common;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final UserRepository userRepository;

    private final FolderRepository folderRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final Common common;

    @Transactional
    public Map<String, Object> folderRegForm(HttpServletRequest request, FolderDto folderDto){
        Map<String, Object> pMap = new HashMap<>();
        try {
            // token 값으로 아이디 값 가져오기
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "/MyBox/"+userId+"/"+request.getHeader("path");

            System.out.println("path ::::::    "   + path);
            if( common.validation(path, folderDto.getFolderName()) ){
                File file = new File(path+"/"+folderDto.getFolderName());
                file.mkdir();
                List<User> list = userRepository.findById(userId);
                folderDto.setUser(list.get(0));

                Folder folder = Folder.builder()
                        .folderName(folderDto.getFolderName())
                        .parentId(folderDto.getParentId())
                        .user(folderDto.getUser())
                        .regDttm(LocalDateTime.now())
                        .build();

                folderRepository.folderRegForm(folder);
                pMap.put("result", "success");
                pMap.put("message", "folder mkdir success");
            }else{
                pMap.put("result", "fail");
                pMap.put("message", "folder mkdir fail");
            }


        }catch (Exception e){
            pMap.put("result", "fail");
            pMap.put("message", "folder mkdir fail");
            e.printStackTrace();
        }
        return pMap;
    }

    public Map<String, Object> getFolderList(HttpServletRequest request){
        Map<String, Object> pMap = new HashMap<>();
        try {
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "/MyBox/"+userId+"/"+request.getHeader("path");

            File file = new File(path);
            if( file.exists() ){
                List<String> fileList = new ArrayList<>();
                for( String name : file.list() ){
                    fileList.add(name);
                }
                pMap.put("data", fileList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pMap;
    }

    @Transactional
    public Map<String, Object> folderRemove( HttpServletRequest request, FolderDto folderDto){
        Map<String, Object> pMap = new HashMap<>();
        try{
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "/MyBox/"+userId+request.getHeader("path")+"/"+folderDto.getFolderName()+"/";

            File file = new File(path);

            System.out.println("path    ::   " + path +"    file.exists()   ::  " + file.exists());
            if( file.exists() ){
                FileUtils.cleanDirectory(file);
                if(file.delete()){

                    folderRepository.folderRemove(folderDto.getFolderId());
                }
                pMap.put("status", "Success");
                pMap.put("message", "folder remove success");
            }else{
                pMap.put("status", "Fail");
                pMap.put("message", "folder exists");
            }

        }catch (Exception e){
            pMap.put("status", "Fail");
            pMap.put("message", "folder remove Fail");
            e.printStackTrace();
        }

        return pMap;
    }

    public static void main(String[] args) {

        try {
            String path = "/MyBox/koo2519/0/folder/";
            File file = new File(path);
            FileUtils.cleanDirectory(file);

            file.delete();
//            FileUtils.deleteDirectory(file);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
