package project.mybox.utiles;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Common {

    public boolean validation( String path, String uploadFileNm ){
        boolean validationCheck = true;
        try {

            File files = new File(path);
            if( files.exists() ){
                for( String fileName : files.list() ){
                    if( fileName.contains("//.") ){
                        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
                    }
                    if( fileName.equals(uploadFileNm) ){
                        validationCheck = false;
                        break;
                    }
                }
            }

        }catch (Exception e){
            validationCheck = false;
            e.printStackTrace();
        }
        return validationCheck;
    }
}
