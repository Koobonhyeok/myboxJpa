package project.mybox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.mybox.domain.Files;
import project.mybox.domain.Folder;

@Data
@AllArgsConstructor
public class FileDto {

    private Long fileNo;
    private String fileName;
    private String fileExt;
    private Long fileStorage;
//    private Folder folder;

//    @Builder
//    public FileDto(Long fileNo, String fileName, String fileExt, int fileStorage){
//        this.fileNo = fileNo;
//        this.fileName = fileName;
//        this.fileExt = fileExt;
//        this.fileStorage = fileStorage;
//        this.folder = folder;
//    }

    public FileDto(Files files){
        this.fileNo = files.getFileNo();
        this.fileName = files.getFileName();
        this.fileExt = files.getFileExt();
        this.fileStorage = files.getFileStorage();
    }
}
