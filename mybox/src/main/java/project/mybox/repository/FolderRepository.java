package project.mybox.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.mybox.domain.Folder;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FolderRepository {

    private final EntityManager em;

    public void folderRegForm(Folder folder){
        em.persist(folder);
    }
}
