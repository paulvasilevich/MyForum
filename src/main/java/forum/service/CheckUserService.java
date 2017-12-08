package forum.service;

import forum.dto.UserDTO;
import forum.entity.UserEntity;

public interface CheckUserService extends CustomService<UserEntity>{

    boolean checkLogin(String login);

    boolean checkPassword(UserDTO user);

    boolean checkEmail(String email);

    UserEntity getUserByLogin(String login);
}
