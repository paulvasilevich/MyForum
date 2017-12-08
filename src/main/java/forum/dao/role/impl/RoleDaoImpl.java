//package forum.dao.role.impl;
//
//import forum.dao.Dao;
//import forum.entity.Role;
//import org.apache.log4j.Logger;
//import org.hibernate.Criteria;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.CriteriaQuery;
//import org.hibernate.criterion.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import javax.persistence.criteria.CriteriaBuilder;
//import java.util.List;
//
//@Repository("roleDao")
//@Transactional
//public class RoleDaoImpl  implements Dao<Role>{
//
//    private final Logger LOGGER = Logger.getLogger(getClass());
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @Resource(name = "sessionFactory")
//    public SessionFactory sessionFactory;
//
//    public Role createEntity(Role entity) {
//        sessionFactory.getCurrentSession().save(entity);
//        LOGGER.info(messageSource.getMessage("forum.dao.role.save", new Object[]{entity}, locale));
//        return entity;
//    }
//
//    public Role getEntityById(Long entityId) {
//        Role entity = sessionFactory.getCurrentSession().get(Role.class, entityId);
//        LOGGER.info(messageSource.getMessage("forum.dao.role.getById", new Object[]{entity}, locale));
//        return entity;
//    }
//
//    public void updateEntity(Role entity) {
//        Role mergedEntity = (Role) sessionFactory.getCurrentSession().merge(entity);
//        sessionFactory.getCurrentSession().update(mergedEntity);
//        LOGGER.info(messageSource.getMessage("forum.dao.role.update", new Object[]{entity}, locale));
//    }
//
//    public void deleteEntity(Role entity) {
//        Role mergedEntity = (Role) sessionFactory.getCurrentSession().merge(entity);
//        sessionFactory.getCurrentSession().delete(mergedEntity);
//        LOGGER.info(messageSource.getMessage("forum.dao.role.delete", new Object[]{entity}, locale));
//    }
//
//    public List<Role> getAllEntities() {
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
//        criteria.addOrder(Order.asc("id"));
//        List result = criteria.list();
//        LOGGER.info(messageSource.getMessage("forum.dao.role.getAll", new Object[]{result}, locale));
//        return result;
//    }
//}
