package forum.service.message;

import forum.dao.Dao;
import forum.entity.MessageEntity;
import forum.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageService")
@Transactional
public class MessageCustomService implements CustomService<MessageEntity> {

    @Autowired
    Dao<MessageEntity> messageDao;

    @Override
    public MessageEntity createEntity(MessageEntity entity) {
        return messageDao.createEntity(entity);
    }

    @Override
    public MessageEntity getEntityById(Long entityId) {
        return messageDao.getEntityById(entityId);
    }

    @Override
    public void updateEntity(MessageEntity entity) {
        messageDao.updateEntity(entity);
    }

    @Override
    public void deleteEntity(MessageEntity entity) {
        messageDao.deleteEntity(entity);
    }

    @Override
    public List<MessageEntity> getAllEntities() {
        return messageDao.getAllEntities();
    }
}
