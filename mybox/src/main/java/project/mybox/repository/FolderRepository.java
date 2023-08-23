package project.mybox.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.mybox.domain.Folder;
import project.mybox.dto.FolderDto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FolderRepository {

    private final EntityManager em;

    public Optional<Folder> findFolderById( Long parentId ){
        Optional<Folder> parentFolder = null;
        try {
            parentFolder = Optional.ofNullable( em.createQuery("select m from Folder m where folder_id = :folderId", Folder.class)
                            .setParameter("folderId", parentId)
                            .getSingleResult() );
        }catch (NoResultException e) {
            return Optional.empty();
        }finally {
            return parentFolder;
        }
    }

    public Optional<Folder> getFolderList( Long folderId ){
        Optional<Folder> folder = null;
        try{
            folder = Optional.ofNullable(
                    em.createQuery( "SELECT f FROM Folder f " +
                                    "LEFT JOIN FETCH f.childrenFolders " +
                                    "WHERE f.folderId = :folderId ", Folder.class)
                            .setParameter("folderId", folderId)
                            .getSingleResult());

            return folder;
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    public void folderRegist(Folder folder){
        em.persist(folder);
    }

    public void folderRegForm(Folder folder){
        em.persist(folder);
    }

    public void folderRemove(Long folderId){
        Folder folder = em.createQuery("select m from Folder m where folder_id = :folderId", Folder.class)
                .setParameter("folderId", folderId).getSingleResult();
        em.remove(folder);
    }

    public Folder findFolder(String folderId ){
        return em.createQuery("select m from Folder m where folder_id = :folderId", Folder.class)
                .setParameter("folderId", folderId)
                .getSingleResult();
    }
}
