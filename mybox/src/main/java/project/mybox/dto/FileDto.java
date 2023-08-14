package project.mybox.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.mybox.domain.Folder;

@Data
@NoArgsConstructor
public class FileDto {

    private Long fileNo;
    private String fileName;
    private String fileExt;
    private int fileStorage;
    private Folder folder;

    @Builder
    public FileDto(Long fileNo, String fileName, String fileExt, int fileStorage, Folder folder){
        this.fileNo = fileNo;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileStorage = fileStorage;
        this.folder = folder;

    }
}
