package project.mybox.repository;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import project.mybox.domain.Folder;
import project.mybox.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User loadUserByUsername(String userId){
        return em.createQuery("select m from User m where user_id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public List<User> findById(String userId ){
        return em.createQuery( "select m from User m where user_id = :userId", User.class )
                .setParameter("userId", userId).getResultList();
    }

    public void userRegForm(User user){
        em.persist(user);
    }

    public int userStorageUpdate(String userId, Long fileSize){
        User user = em.createQuery("select m from User m where user_id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
        // 여기다 size 체크하는게 맞나??
        Long maxStorage = 32212254720L;

        if( maxStorage > ( user.getStorage()+fileSize ) ){
            user.setStorage(user.getStorage() + fileSize );
            return 1;
        }

        return -1;
    }

    public Optional<Folder> getFolderList(Long folderId) {
        System.out.println(" folderId   ::  " + folderId);
        String sql = "SELECT f FROM Folder f " +
                "LEFT JOIN FETCH f.childrenFolders " +
                "WHERE f.folderId = :folderId ";
        Optional<Folder> folder = null;
        try {
            folder = Optional.ofNullable(em.createQuery(sql, Folder.class)
                    .setParameter("folderId", folderId)
                    .getSingleResult());
            return folder;
        } catch (NoResultException e) {
            return folder = Optional.empty();

        }
    }
}
