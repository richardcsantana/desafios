package idwall.desafio.crawler.service;

import idwall.desafio.crawler.client.TelegramClient;
import idwall.desafio.crawler.configuration.CrawlerConfiguration;
import idwall.desafio.crawler.model.Result;
import idwall.desafio.crawler.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final TelegramClient telegramClient;
    private final CrawlerConfiguration crawlerConfiguration;

    public List<Update> getUpdates() {
        Result updates = telegramClient.getUpdates(crawlerConfiguration.getBotToken());
        List<Update> result = updates.getResult();
        result.stream().max(Comparator.comparing(Update::getUpdateId)).ifPresent(it ->
                telegramClient.getUpdatesWithOffset(crawlerConfiguration.getBotToken(),
                        it.getUpdateId() + 1));
        return result;
    }

    public void sendMessage(long chatId, String text) {
        telegramClient.sendMessage(crawlerConfiguration.getBotToken(),
                chatId,
                text,
                crawlerConfiguration.getParseMode());
    }
}
