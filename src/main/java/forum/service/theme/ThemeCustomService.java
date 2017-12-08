package forum.service.theme;

import forum.dao.Dao;
import forum.entity.ThemeEntity;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("themeService")
@Transactional
public class ThemeCustomService implements CustomService<ThemeEntity> {

    @Autowired
    Dao<ThemeEntity> themeDao;

    @Override
    public ThemeEntity createEntity(ThemeEntity entity) {
        return themeDao.createEntity(entity);
    }

    @Override
    public ThemeEntity getEntityById(Long entityId) {
        return themeDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(ThemeEntity entity) {
        themeDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(ThemeEntity entity) {
        themeDao.deleteEntity(entity);
    }

    @Override
    public List<ThemeEntity> getAllEntities() {
        return themeDao.getAllEntities();
    }
}
