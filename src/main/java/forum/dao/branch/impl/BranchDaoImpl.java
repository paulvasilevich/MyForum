package forum.dao.branch.impl;

import forum.dao.Dao;
import forum.entity.BranchEntity;
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

@Repository("branchDao")
@Transactional
public class BranchDaoImpl implements Dao<BranchEntity>{

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public BranchEntity createEntity(BranchEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
        LOGGER.info(messageSource.getMessage("forum.dao.branch.save", new Object[]{entity}, locale));
        return entity;
    }

    public BranchEntity getEntityById(Long entityId) {
        BranchEntity entity = sessionFactory.getCurrentSession().get(BranchEntity.class, entityId);
        LOGGER.info(messageSource.getMessage("forum.dao.branch.getById", new Object[]{entity}, locale));
        return entity;
    }

    public void updateEntity(BranchEntity entity) {
        BranchEntity mergedEntity = (BranchEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.branch.update", new Object[]{entity}, locale));
    }

    public void deleteEntity(BranchEntity entity) {
        BranchEntity mergedEntity = (BranchEntity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().delete(mergedEntity);
        LOGGER.info(messageSource.getMessage("forum.dao.branch.delete", new Object[]{entity}, locale));
    }

    public List<BranchEntity> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BranchEntity.class);
        criteria.addOrder(Order.desc("dateStart"));
        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("forum.dao.branch.getAll", new Object[]{result}, locale));
        return result;
    }
}
