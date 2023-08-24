package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.mybox.domain.Files;
import project.mybox.domain.Folder;
import project.mybox.dto.FileDto;
import project.mybox.repository.FileRepository;
import project.mybox.repository.UserRepository;
//import project.mybox.security.JwtTokenProvider;
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
    public Map<String, Object> fileReg(MultipartFile multipartFile, HttpServletRequest request){
        Map<String, Object> pMap = new HashMap<>();
        try {
            String originalFileName = multipartFile.getOriginalFilename();
//            if( common.validation("", originalFileName) ){
                Folder folder = Folder.builder()
                        .folderId(Long.parseLong(request.getParameter("folderId").toString()))
                        .build();

                Files file_info =  Files.builder()
                        .fileName(originalFileName.substring(0, originalFileName.lastIndexOf('.')))
                        .fileStorage(multipartFile.getSize())
                        .fileExt(originalFileName.substring(originalFileName.lastIndexOf('.')+1))
                        .folder(folder)
                        .regDttm(LocalDateTime.now())
                        .build();

                fileRepository.fileRegForm(file_info);
                pMap.put("status", "Success");
                pMap.put("msg", "File make success");
//            }


        }catch (Exception e){
            pMap.put("status", "Fail");
            pMap.put("msg", "Exception");
        }


        return pMap;
    }

    @Transactional
    public Map<String, Object> fileRegForm(MultipartFile multipartFile, HttpServletRequest request){
        Map<String, Object> pMap = new HashMap<>();
        try {
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "C:/MyBox/"+userId+request.getHeader("path");

            String originalFileName = multipartFile.getOriginalFilename();
            if( common.validation(path,originalFileName.substring(0, originalFileName.lastIndexOf('.'))) ){
                // 폴더 사이즈 업데이트
                int result = userRepository.userStorageUpdate(userId, multipartFile.getSize());

                if( result < 0 ){
                    pMap.put("status", "Fail");
                    pMap.put("message", "File upload failed, because storage size full");
                    return pMap;
                }

                File file = new File(path+"/"+originalFileName);
                multipartFile.transferTo(file);

                Folder folder = Folder.builder()
                        .folderId(Long.parseLong(request.getParameter("folderId").toString()))
                        .build();

                Files file_info =  Files.builder()
                        .fileName(originalFileName.substring(0, originalFileName.lastIndexOf('.')))
                        .fileStorage(multipartFile.getSize())
                        .fileExt(originalFileName.substring(originalFileName.lastIndexOf('.')+1))
                        .folder(folder)
                        .regDttm(LocalDateTime.now())
                        .build();

                fileRepository.fileRegForm(file_info);

                pMap.put("status", "success");
                pMap.put("message", "file upload success");
            }else{
                pMap.put("status", "fail");
                pMap.put("message", "file name duplication");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  pMap;
    }

    public Map<String, Object> fileRemove(FileDto fileDto, HttpServletRequest request){
        Map<String, Object> pMap = new HashMap<>();
        try{
            String userId = jwtTokenProvider.getTokenUserId(request);
            String path = "C:/MyBox/"+userId+request.getHeader("path");


            File file = new File( path + File.separator + fileDto.getFileName() );
            // 파일 존재 여부 확인
            if( file.exists() ){

                Long fileStorage = fileRepository.fileRemove( fileDto.getFileNo() );


                // 파일 삭제
                file.delete();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return pMap;
    }

    public Map<String, Object> fileDownload(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> pMap = new HashMap<>();
        try{
            String userId = jwtTokenProvider.getTokenUserId(request);

            String downloadFile = request.getParameter("fileName");
            String uploadDir = "C:/MyBox/"+userId+request.getHeader("path");
            String path = uploadDir+File.separator+downloadFile;

            System.out.println("Path Confirm    ::     " +  path);

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

            pMap.put("status", "Success");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return pMap;
    }

    public static void main(String[] args) {
        String sourceFilePath = "C:\\MyBox\\koo2519\\0\\rnqhsgur\\test.docx";
        // 다운로드 받을 위치
        String destinationFilePath = "C:\\MyBox\\korea6441\\0\\file.docx";

        Path destinationPath = Paths.get(destinationFilePath);
        try (OutputStream outputStream = new FileOutputStream(destinationPath.toFile())) {
//            Files.copy(Paths.get(sourceFilePath), outputStream);
            java.nio.file.Files.copy(Paths.get(sourceFilePath), outputStream);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
