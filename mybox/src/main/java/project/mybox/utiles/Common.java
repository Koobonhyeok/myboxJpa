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
                    System.out.println("fileName   :  "+ fileName+"     uploadFileNm    ::   "+uploadFileNm);
                    if( fileName.contains(".") ){
                        System.out.println("안들어오나");
                        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
//                        .replaceAll(".", "")
                    }
                    System.out.println("change file name    ::    " + fileName);
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
