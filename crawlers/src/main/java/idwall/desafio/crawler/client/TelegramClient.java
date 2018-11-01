package idwall.desafio.crawler.client;

import idwall.desafio.crawler.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "telegramClient", url = "https://api.telegram.org")
public interface TelegramClient {

    @GetMapping(path = "/bot{botToken}/getUpdates")
    Result getUpdates(@PathVariable(name = "botToken") String botToken);

    @GetMapping(path = "/bot{botToken}/getUpdates")
    void getUpdatesWithOffset(@PathVariable(name = "botToken") String botToken,
                              @PathVariable(name = "offset") long offset);

    @PostMapping(path = "/bot{botToken}/sendMessage")
    void sendMessage(@PathVariable(name = "botToken") String botToken,
                     @PathVariable(name = "chat_id") Long chatId,
                     @PathVariable(name = "text") String text,
                     @PathVariable(name = "parse_mode") String parseMode);

}
