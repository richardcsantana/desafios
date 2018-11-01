package idwall.desafio.crawler.model;

import lombok.Data;

@Data
public class InputMessage {
    private Long messageId;
    private Chat chat;
    private String text;
}
