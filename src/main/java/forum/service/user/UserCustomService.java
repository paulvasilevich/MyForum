package forum.service.user;

import forum.dao.CheckUserDao;
import forum.dao.Dao;
import forum.dao.role.impl.RoleDaoImpl;
import forum.dto.UserDTO;
import forum.entity.Role;
import forum.entity.UserEntity;
import forum.service.CheckUserService;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserCustomService implements CustomService<UserEntity>, CheckUserService {

    @Autowired
    private CheckUserDao userDao;

    @Override
    public UserEntity createEntity(UserEntity entity) {
        return userDao.createEntity(entity);
    }

    @Override
    public UserEntity getEntityById(Long entityId) {
        return userDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(UserEntity entity) {
        userDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(UserEntity entity) {
        userDao.deleteEntity(entity);
    }

    @Override
    public List<UserEntity> getAllEntities() {
        return userDao.getAllEntities();
    }

    @Override
    public boolean checkLogin(String login) {
        return userDao.checkLogin(login);
    }

    @Override
    public boolean checkPassword(UserDTO user) {
        return userDao.checkPassword(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
