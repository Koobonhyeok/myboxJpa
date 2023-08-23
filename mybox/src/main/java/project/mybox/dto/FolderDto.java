package project.mybox.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.mybox.domain.Folder;
import project.mybox.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FolderDto {

    private Long folderId;
    private String folderName;
    private Long parentId;
    private List<FolderDto> childFolders;

    private String path;

//    private LocalDateTime regDttm;
//    private User user;
//    private List<FolderDto> childFolders;

//    @Builder
//    public FolderDto(Long folderId, String folderName, Long parentId, LocalDateTime regDttm, User user){
//        this.folderId = folderId;
//        this.folderName = folderName;
//        this.parentId = parentId;
//        this.regDttm = regDttm;
//        this.user = user;
//    }

    public static FolderDto of(Folder folder) {
        return FolderDto.builder()
                .folderId(folder.getFolderId())
                .folderName(folder.getFolderName())
                .childFolders(folder.getChildrenFolders().stream()
                        .map(FolderDto::of)
                        .collect(Collectors.toList()))
                .build();
    }

}
