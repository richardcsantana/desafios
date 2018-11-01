package idwall.desafio.crawler.configuration;

import idwall.desafio.crawler.service.RedditConnector;
import idwall.desafio.crawler.service.RedditCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedditConfiguration {

    private final CrawlerConfiguration crawlerConfiguration;

    @Bean
    public RedditConnector redditConnector() {
        return new RedditConnector(crawlerConfiguration.getUserAgent());
    }

    @Bean
    public RedditCrawler redditCrawler() {
        return new RedditCrawler();
    }
}
