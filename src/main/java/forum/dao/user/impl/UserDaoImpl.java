package forum.dao.user.impl;

import forum.dao.CheckUserDao;
import forum.dao.Dao;
import forum.dto.UserDTO;
import forum.entity.Role;
import forum.entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements Dao<UserEntity>, CheckUserDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

//    @Autowired
//    private Dao<Role> roleDao;

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public UserEntity createEntity(UserEntity entity) {
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleDao.getEntityById(1L));
//        entity.setRoles(roles);
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.user.save", new Object[]{entity}, locale));
        return entity;
    }

    public UserEntity getEntityById(Long entityId) {
        UserEntity entity = sessionFactory.getCurrentSession().get(UserEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.user.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(UserEntity entity) {
        UserEntity mergedEntity = (UserEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.user.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(UserEntity entity) {
        UserEntity mergedEntity = (UserEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.user.delete", new Object[]{entity}, locale));
    }

    public List<UserEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
        criteria.addOrder(Order.asc("userId"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.user.getAll", new Object[]{result}, locale));
        return result;
    }

    public boolean checkLogin(String login) {
        String stringHqlQuery = "FROM UserEntity WHERE login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(stringHqlQuery);
        query.setParameter("login", login);
        return query.list().size() > 0;
    }

    public boolean checkPassword(UserDTO user) {
        String login = user.getLogin();
        String password = user.getPassword();

        String stringHqlQuery = "FROM UserEntity WHERE password=:password AND login=:login";
        Query query = sessionFactory.getCurrentSession().createQuery(stringHqlQuery);
        query.setParameter("password", password);
        query.setParameter("login", login);
        List userEntities = query.list();
        return userEntities.size() > 0;
    }

    public boolean checkEmail(String email) {
        String stringHqlQuery = "FROM UserEntity WHERE email = :email";
        Query query = sessionFactory.getCurrentSession().createQuery(stringHqlQuery);
        query.setParameter("email", email);
        List userEntities = query.list();
        return userEntities.size() > 0;
    }

    public UserEntity getUserByLogin(String login) {
        String stringHqlQuery = "FROM  UserEntity WHERE login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(stringHqlQuery);
        query.setParameter("login", login);
        LOGGER.info(messageSource.getMessage("forum.dao.user.getUserByLogin", new Object[]{login}, locale));
        return (UserEntity) query.uniqueResult();
    }
}
