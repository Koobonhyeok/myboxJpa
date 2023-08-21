package project.mybox.testPackage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.mybox.domain.Folder;
import project.mybox.repository.FileRepository;
import project.mybox.repository.FolderRepository;
import project.mybox.repository.UserRepository;

@SpringBootTest
public class FileTest {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void folderFind(){
        Folder folder = folderRepository.findFolder("3");

        System.out.println(folder.toString());
    }

    @Test
    public void folderUpdate(){
//        userRepository.userStorageUpdate("koo2519");
    }
}
