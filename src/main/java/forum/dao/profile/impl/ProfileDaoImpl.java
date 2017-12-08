package forum.dao.profile.impl;

import forum.dao.Dao;
import forum.entity.ProfileEntity;
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

@Repository("profileDao")
@Transactional
public class ProfileDaoImpl implements Dao<ProfileEntity> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public ProfileEntity createEntity(ProfileEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.profile.save", new Object[]{entity}, locale));
        return entity;
    }

    public ProfileEntity getEntityById(Long entityId) {
        ProfileEntity entity = sessionFactory.getCurrentSession().get(ProfileEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.profile.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(ProfileEntity entity) {
        ProfileEntity mergedEntity = (ProfileEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.profile.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(ProfileEntity entity) {
        ProfileEntity mergedEntity = (ProfileEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.profile.delete", new Object[]{entity}, locale));
    }

    public List<ProfileEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProfileEntity.class);
        criteria.addOrder(Order.desc("dateStart"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.profile.getAll", new Object[]{result}, locale));
        return result;
    }
}
