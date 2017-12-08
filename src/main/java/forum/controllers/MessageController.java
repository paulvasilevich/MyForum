package forum.controllers;

import forum.converters.EntityConverterDto;
import forum.dto.MessageDTO;
import forum.entity.MessageEntity;
import forum.entity.ThemeEntity;
import forum.service.CustomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Controller
public class MessageController {

    @Autowired
    private CustomService<ThemeEntity> themeEntityCustomService;

    public ModelAndView showpage(@PathVariable int length, @PathVariable int pageId, @PathVariable Long themeId) {
        if (length == 0) {
            length = 20;
        }
        if (pageId != 1) {
            pageId = (pageId - 1) * length + 1;
        }
        ThemeEntity themeEntity = themeEntityCustomService.getEntityById(themeId);
        List<MessageDTO> dtoList = new ArrayList<>();
        for (MessageEntity messageEntity : themeEntity.getMessages().subList()) {
            dtoList.add(EntityConverterDto.convertMessageToDTO(messageEntity));
        }

        return new ModelAndView("theme", "dtoList", dtoList);
    }


}
