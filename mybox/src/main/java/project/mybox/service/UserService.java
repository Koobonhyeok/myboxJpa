package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.mybox.domain.Files;
import project.mybox.domain.Folder;
import project.mybox.domain.User;
import project.mybox.dto.UserDto;
import project.mybox.repository.FileRepository;
import project.mybox.repository.UserRepository;
import project.mybox.security.JwtTokenProvider;
import project.mybox.utiles.Common;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    private final Common common;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Map<String, Object> userRegForm(String userId, String password){
        Map<String, Object> pMap = new HashMap<>();
        try {
            // 아이디 중복 체크
            List<User> userList = userRepository.findById(userId);
            if( userList.size() == 0 ){
                User user = User.builder()
                        .userId( userId )
                        .password( password )
                        .storage(0L)
                        .regDttm( LocalDateTime.now() )
                        .build();


                userRepository.userRegForm(user);
                pMap.put("status", "ok");
                pMap.put("message", "회원가입이 완료되었습니다.");
            }else{
                pMap.put("status", "fail");
                pMap.put("message", "회원가입이 가능하지 않습니다.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return pMap;
    }

    @Transactional
    public Map<String, Object> getUserInfo( String userId ){
        Map<String, Object> pMap = new HashMap<>();
        try {
            List<User> userList = userRepository.findById(userId);
            if( userList.size() > 0 ){
                pMap.put("status" , "success");
                pMap.put("data", userList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pMap;
    }

    @Transactional
    public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> pMap = new HashMap<>();
        try{
            String dFile = "test.docx";
            String upDir = "C:\\MyBox\\koo2519\\0\\rnqhsgur";
            String path = upDir+File.separator+dFile;

            System.out.println("Path Confirm   ::   " + path);

            File file = new File(path);
            String userAgent = request.getHeader("User-Agent");
            boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
            String fileName = null;

            if(ie){
                fileName = URLEncoder.encode(file.getName(), "utf-8");
            }else {
                fileName = new String( file.getName().getBytes("utf-8"), "iso-8859-1");
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ServletOutputStream so = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(so);

            byte[] data = new byte[2048];
            int input = 0;
            while ( (input = bis.read(data)) != -1 ){
                bos.write(data, 0, input);
                bos.flush();
            }
            if(bos!=null) bos.close();
            if(bis!=null) bis.close();
            if(so!=null) so.close();
            if(fis!=null) fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return  pMap;
    }

}


//    String sourceFilePath = "C:\\MyBox\\koo2519\\0\\rnqhsgur\\test.docx";
//    // 다운로드 받을 위치
//    String destinationFilePath = "C:\\MyBox\\korea6441\\0\\file.docx";
//
//    Path destinationPath = Paths.get(destinationFilePath);
//        try (OutputStream outputStream = new FileOutputStream(destinationPath.toFile())) {
////            Files.copy(Paths.get(sourceFilePath), outputStream);
//                java.nio.file.Files.copy(Paths.get(sourceFilePath), outputStream);
//                } catch (IOException ex) {
//                System.out.println("Error: " + ex.getMessage());
//                ex.printStackTrace();
//                }