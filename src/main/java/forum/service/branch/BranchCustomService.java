package forum.service.branch;

import forum.dao.Dao;
import forum.entity.BranchEntity;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("branchService")
@Transactional
public class BranchCustomService implements CustomService<BranchEntity> {

    @Autowired
    Dao<BranchEntity> branchDao;

    @Override
    public BranchEntity createEntity(BranchEntity entity) {
        return branchDao.createEntity(entity);
    }

    @Override
    public BranchEntity getEntityById(Long entityId) {
        return branchDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(BranchEntity entity) {
        branchDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(BranchEntity entity) {
        branchDao.deleteEntity(entity);
    }

    @Override
    public List<BranchEntity> getAllEntities() {
        return branchDao.getAllEntities();
    }
}
