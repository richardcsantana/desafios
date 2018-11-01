package idwall.desafio.crawler.service;

import idwall.desafio.crawler.configuration.CrawlerConfiguration;
import idwall.desafio.crawler.model.RedditThread;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class RedditFacade {

    private final RedditConnector redditConnector;
    private final RedditCrawler redditCrawler;
    private final CrawlerConfiguration crawlerConfiguration;

    public List<RedditThread> getSubredditInfo(String subreddit) {
        List<RedditThread> result = null;
        try {
            result = redditCrawler
                    .getSubRedditsContentByName(redditConnector.connect(subreddit))
                    .stream()
                    .filter(it -> it.getUpvotes() > crawlerConfiguration.getMinNumberOfUpVotes())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
