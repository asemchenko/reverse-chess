package company.service;

import company.model.chat.GlobalChatMessage;
import company.repository.GlobalChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalChatMessageService {

    @Autowired
    private GlobalChatMessageRepo globalChatMessageRepo;

    @Autowired
    private UserService userService;

    public void saveMessage(GlobalChatMessage globalChatMessage){
        setSenderFromDB(globalChatMessage);
        globalChatMessageRepo.save(globalChatMessage);
    }

    private void setSenderFromDB(GlobalChatMessage globalChatMessage){
        globalChatMessage.setSender(userService.returnUserByUserName(globalChatMessage.getSender().getUsername()));
    }

}
