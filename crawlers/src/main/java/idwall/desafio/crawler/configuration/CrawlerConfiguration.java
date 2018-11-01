package idwall.desafio.crawler.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "crawler")
public class CrawlerConfiguration {

    private String userAgent;

    private String botToken;

    private String parseMode;

    private int minNumberOfUpVotes;
}
