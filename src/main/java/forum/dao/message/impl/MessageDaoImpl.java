package forum.dao.message.impl;

import forum.dao.Dao;
import forum.entity.MessageEntity;
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

@Repository("messageDao")
@Transactional
public class MessageDaoImpl implements Dao<MessageEntity> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public MessageEntity createEntity(MessageEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.message.save", new Object[]{entity}, locale));
        return entity;
    }

    public MessageEntity getEntityById(Long entityId) {
        MessageEntity entity = sessionFactory.getCurrentSession().get(MessageEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.message.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(MessageEntity entity) {
        MessageEntity mergedEntity = (MessageEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.message.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(MessageEntity entity) {
        MessageEntity mergedEntity = (MessageEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.message.delete", new Object[]{entity}, locale));
    }

    public List<MessageEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.addOrder(Order.desc("dateStart"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.message.getAll", new Object[]{result}, locale));
        return result;
    }
}
