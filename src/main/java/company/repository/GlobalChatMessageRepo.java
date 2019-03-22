package company.repository;

import company.model.chat.GlobalChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalChatMessageRepo extends JpaRepository<GlobalChatMessage, Long> {
}
