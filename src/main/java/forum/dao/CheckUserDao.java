package forum.dao;

import forum.dto.UserDTO;
import forum.entity.UserEntity;

public interface CheckUserDao extends Dao<UserEntity>{

    boolean checkLogin(String login);

    boolean checkPassword(UserDTO user);

    boolean checkEmail(String email);

    UserEntity getUserByLogin(String login);
}
