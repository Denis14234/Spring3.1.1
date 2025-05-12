package spring.Module31.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import spring.Module31.model.User;

import java.util.List;

    @Repository
    public class UserDaoImp implements UserDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public void add(User user) {
            entityManager.persist(user);
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<User> listUsers() {
            TypedQuery<User> query = entityManager.createQuery("from User", User.class);
            return query.getResultList();
        }
        @Override
        public void delete(User user) {
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        }

        @Override
        public User findById(Long id) {
            return entityManager.find(User.class, id);
        }

        @Override
        public void update(User user) {
            entityManager.merge(user);
        }
    }
