package project.mybox.domain;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class Files {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "file_no")
    private Long fileNo;

    @Column(name = "file_name", length = 40)
    private String fileName;

    @Column(name = "file_ext", length = 10)
    private String fileExt;

    @Column( name = "file_storage")
    private Long fileStorage;

    @Column(name = "reg_dttm")
    private LocalDateTime regDttm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Builder
    private Files( Long fileNo, String fileName, String fileExt, Long fileStorage, LocalDateTime regDttm, Folder folder){
        this.fileNo = fileNo;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileStorage = fileStorage;
        this.regDttm = regDttm;
        this.folder = folder;
    }

}
