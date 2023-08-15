package project.mybox.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.mybox.domain.Files;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public void fileRegForm(Files file){
        em.persist(file);
    }
}
