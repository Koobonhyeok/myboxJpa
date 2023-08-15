package project.mybox.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import project.mybox.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

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
}
