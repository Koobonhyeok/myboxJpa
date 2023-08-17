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

    public void folderRemove(Long folderId){
        Folder folder = em.createQuery("select m from Folder m where folder_id = :folderId", Folder.class)
                .setParameter("folderId", folderId).getSingleResult();
        em.remove(folder);
    }

    public Folder findFolder(String folderId ){
        return em.createQuery("select m from Folder m where folder_id = :folderId", Folder.class).setParameter("folderId", folderId).getSingleResult();
    }
}
