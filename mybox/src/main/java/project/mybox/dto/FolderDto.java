package project.mybox.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.mybox.domain.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FolderDto {

    private Long folderId;
    private String folderName;
    private Long parentId;
    private LocalDateTime regDttm;
    private User user;

    @Builder
    public FolderDto(Long folderId, String folderName, Long parentId, LocalDateTime regDttm, User user){
        this.folderId = folderId;
        this.folderName = folderName;
        this.parentId = parentId;
        this.regDttm = regDttm;
        this.user = user;
    }

}
