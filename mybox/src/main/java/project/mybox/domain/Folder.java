package project.mybox.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "folder")
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long folderId;

    @Column(name = "folder_name", length = 40)
    private String folderName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "reg_dttm")
    private LocalDateTime regDttm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public Folder(Long folderId, String folderName, Long parentId, LocalDateTime regDttm, User user){
        this.folderId = folderId;
        this.folderName = folderName;
        this.parentId = parentId;
        this.regDttm = regDttm;
        this.user = user;
    }
}
