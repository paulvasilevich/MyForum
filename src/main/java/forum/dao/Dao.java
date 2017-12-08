package forum.dao;

import java.util.List;
import java.util.Locale;

public interface Dao<E> {

     static final Locale locale = Locale.ENGLISH;

     E createEntity(E entity);

     E getEntityById(Long entityId);

     void updateEntity(E entity);

     void deleteEntity(E entity);

     List<E> getAllEntities();

}
