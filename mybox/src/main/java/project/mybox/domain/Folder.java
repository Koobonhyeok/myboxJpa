package project.mybox.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "folder")
@Getter
@NoArgsConstructor
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long folderId;

    @Column(name = "folder_name", length = 40)
    private String folderName;

    @CreationTimestamp
    @Column(name = "reg_dttm")
    private LocalDateTime regDttm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_parent_id", referencedColumnName = "folder_id")
    private Folder parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Folder> childrenFolders = new HashSet<>();

    @Builder
    public Folder(Long folderId, String folderName, Folder parent){
        this.folderId = folderId;
        this.folderName = folderName;
        this.parent = parent;
    }
}
