package idwall.desafio.crawler.schedule;

import idwall.desafio.crawler.model.RedditThread;
import idwall.desafio.crawler.service.HtmlParser;
import idwall.desafio.crawler.service.RedditFacade;
import idwall.desafio.crawler.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class Polling {

    private final TelegramService telegramService;
    private final RedditFacade redditFacade;
    private final HtmlParser htmlParser;

    @Scheduled(fixedDelay = 1000)
    public void answerTelegram() {
        try {
            telegramService.getUpdates().forEach(it -> {
                if (it.getMessage() != null) {
                    String text = it.getMessage().getText();
                    if (text.toLowerCase().contains("/nadaprafazer") && text.length() > 14) {
                        Arrays.asList(text.substring(14).split(";")).forEach(
                                subreddit -> {
                                    List<RedditThread> redditThreads = redditFacade.getSubredditInfo(subreddit);
                                    redditThreads.stream().map(htmlParser::parseHtml)
                                            .forEach(redditThread -> telegramService.sendMessage(it.getMessage()
                                                    .getChat().getId(), redditThread));

                                }
                        );
                    }
                }
            });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
