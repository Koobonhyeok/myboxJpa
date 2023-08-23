package project.mybox.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.mybox.domain.Files;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public void fileRegForm(Files file_info){
        em.persist(file_info);
    }

    public Long fileRemove( Long fileId ){
        Files file_info = em.createQuery("select m from Files m where file_no = :fileId", Files.class)
                .setParameter("fileId", fileId).getSingleResult();

        em.remove(file_info);
        return file_info.getFileStorage();
    }

    public void fileByParentIdDelete( Long parentId ){
        em.createQuery("delete from Files m where folder_id = :parentId")
                .setParameter("parentId", parentId)
                .executeUpdate();

        em.clear();
    }
}
