package crud.dao;

import crud.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
    public class UserDaoImpl implements UserDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public void save(User user) {
            entityManager.persist(user);
        }

        @Override
        @Transactional
        public List<User> findAll() {
            List<User> users;
            TypedQuery<User> query = entityManager.createQuery("select u from User u order by u.id", User.class);
            users = query.getResultList();
            return users;
        }

        @Override
        @Transactional
        public User findById(long id) {
            User user = entityManager.find(User.class, id);
            entityManager.detach(user);
            return user;
        }

        @Override
        @Transactional
        public void update(User user) {
            entityManager.merge(user);
        }

        @Override
        @Transactional
        public void deleteById(long id) {
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
        }
    }

