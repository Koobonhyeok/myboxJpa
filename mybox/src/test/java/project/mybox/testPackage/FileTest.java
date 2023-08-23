package project.mybox.testPackage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.mybox.domain.Folder;
import project.mybox.repository.FileRepository;
import project.mybox.repository.FolderRepository;
import project.mybox.repository.UserRepository;
import project.mybox.utiles.Common;

import javax.persistence.NoResultException;

@SpringBootTest
public class FileTest {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Common common;

    @Test
    public void folderFind(){
        System.out.println( common.validation( "C:\\MyBox\\koo2519\\0\\rnqhsgur", "rnqhsgu1r" ) );
    }

}
