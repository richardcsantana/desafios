package idwall.desafio.crawler;

import idwall.desafio.crawler.service.RedditConnector;
import idwall.desafio.crawler.service.RedditCrawler;
import idwall.desafio.crawler.ui.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class.getName());

    private static final String USER_AGENT = "java:idwall.crawlers:v.0.0.1";

    public static void main(String[] args) {
        Gui gui = new Gui();
        RedditConnector redditConnector = new RedditConnector(USER_AGENT);
        RedditCrawler redditCrawler = new RedditCrawler();

        String input = gui.run();
        for (String subreddit : input.split(";")) {
            try {
                Document document = redditConnector.connect(subreddit);
                redditCrawler
                        .getSubRedditsContentByName(document)
                        .stream().filter(it -> it.getUpvotes() > 5000)
                        .forEach(System.out::println);

            } catch (IOException ex) {
                log.error("Error: ", ex.getMessage(), ex);
            }
        }
    }
}
