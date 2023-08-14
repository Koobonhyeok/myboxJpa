package project.mybox.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "file_no")
    private Long fileNo;

    @Column(name = "file_name", length = 40)
    private String fileName;

    @Column(name = "file_ext", length = 10)
    private String fileExt;

    @Column( name = "file_storage")
    private int fileStorage;

    @Column(name = "reg_dttm")
    private LocalDateTime regDttm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
