package idwall.desafio.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RedditConnector {

    private final String userAgent;

    public RedditConnector(String userAgent) {
        this.userAgent = userAgent;
    }

    public Document connect(String subreddit) throws IOException {
        return Jsoup.connect("http://old.reddit.com/r/" + subreddit)
                .userAgent(userAgent).get();
    }

}
