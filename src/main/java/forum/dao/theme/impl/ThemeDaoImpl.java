package forum.dao.theme.impl;

import forum.dao.Dao;
import forum.entity.ThemeEntity;
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

@Repository("themeDao")
@Transactional
public class ThemeDaoImpl implements Dao<ThemeEntity> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public ThemeEntity createEntity(ThemeEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.theme.save", new Object[]{entity}, locale));
        return entity;
    }

    public ThemeEntity getEntityById(Long entityId) {
        ThemeEntity entity = sessionFactory.getCurrentSession().get(ThemeEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.theme.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(ThemeEntity entity) {
        ThemeEntity mergedEntity = (ThemeEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.theme.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(ThemeEntity entity) {
        ThemeEntity mergedEntity = (ThemeEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.theme.delete", new Object[]{entity}, locale));
    }

    public List<ThemeEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ThemeEntity.class);
        criteria.addOrder(Order.desc("dateStart"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.theme.getAll", new Object[]{result}, locale));
        return result;
    }
}
