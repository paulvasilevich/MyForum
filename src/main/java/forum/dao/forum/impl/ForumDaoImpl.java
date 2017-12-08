package forum.dao.forum.impl;

import forum.dao.Dao;
import forum.entity.ForumEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Repository("forumDao")
@Transactional
public class ForumDaoImpl implements Dao<ForumEntity> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public ForumEntity createEntity(ForumEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.forum.save", new Object[]{entity}, locale));
        return entity;
    }

    public ForumEntity getEntityById(Long entityId) {
        ForumEntity entity = sessionFactory.getCurrentSession().get(ForumEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.forum.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(ForumEntity entity) {
        ForumEntity mergedEntity = (ForumEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.forum.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(ForumEntity entity) {
        ForumEntity mergedEntity = (ForumEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.forum.delete", new Object[]{entity}, locale));
    }

    public List<ForumEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ForumEntity.class);
        criteria.addOrder(Order.desc("dateStart"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.forum.getAll", new Object[]{result}, locale));
        return result;
    }
}
