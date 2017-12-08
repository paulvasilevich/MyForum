package forum.converters;

import forum.dto.MessageDTO;
import forum.dto.UserDTO;
import forum.entity.MessageEntity;
import forum.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityConverterDto {

    public static MessageDTO convertMessageToDTO(MessageEntity entity) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserId(entity.getUser().getUserId());
        messageDTO.setMessageId(entity.getMessageId());
        messageDTO.setContent(entity.getContent());
        messageDTO.setDate(entity.getDate());
        messageDTO.setLogin(entity.getUser().getLogin());

        return messageDTO;
    }

    public static UserDTO convertUserToDTO(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(entity.getLogin());
        userDTO.setPassword(entity.getPassword());

        return userDTO;
    }
}
