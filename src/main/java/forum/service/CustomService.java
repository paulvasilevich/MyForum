package forum.service;

import java.util.List;

public interface CustomService<E> {

    E createEntity(E entity);

    E getEntityById(Long entityId);

    void updateEntity(E entity);

    void deleteEntity(E entity);

    List<E> getAllEntities();
}
