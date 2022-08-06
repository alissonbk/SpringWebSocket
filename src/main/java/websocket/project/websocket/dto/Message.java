package websocket.project.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class Message {
    private String messageContent;
    private Instant date;
}
