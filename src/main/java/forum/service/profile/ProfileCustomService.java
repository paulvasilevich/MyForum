package forum.service.profile;

import forum.dao.Dao;
import forum.entity.ProfileEntity;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("profileService")
@Transactional
public class ProfileCustomService implements CustomService<ProfileEntity> {

    @Autowired
    Dao<ProfileEntity> profileDao;

    @Override
    public ProfileEntity createEntity(ProfileEntity entity) {
        return profileDao.createEntity(entity);
    }

    @Override
    public ProfileEntity getEntityById(Long entityId) {
        return profileDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(ProfileEntity entity) {
        profileDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(ProfileEntity entity) {
        profileDao.deleteEntity(entity);
    }

    @Override
    public List<ProfileEntity> getAllEntities() {
        return profileDao.getAllEntities();
    }
}
