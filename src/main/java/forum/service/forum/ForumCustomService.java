package forum.service.forum;

import forum.dao.Dao;
import forum.entity.ForumEntity;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("forumService")
@Transactional
public class ForumCustomService implements CustomService<ForumEntity> {

    @Autowired
    Dao<ForumEntity> branchDao;

    @Override
    public ForumEntity createEntity(ForumEntity entity) {
        return branchDao.createEntity(entity);
    }

    @Override
    public ForumEntity getEntityById(Long entityId) {
        return branchDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(ForumEntity entity) {
        branchDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(ForumEntity entity) {
        branchDao.deleteEntity(entity);
    }

    @Override
    public List<ForumEntity> getAllEntities() {
        return branchDao.getAllEntities();
    }
}
