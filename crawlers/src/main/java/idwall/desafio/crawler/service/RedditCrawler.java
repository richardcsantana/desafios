package idwall.desafio.crawler.service;

import idwall.desafio.crawler.model.RedditThread;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RedditCrawler {

    public List<RedditThread> getSubRedditsContentByName(Document doc) {
        Elements things = doc.getElementById("siteTable")
                .getElementsByClass("thing");
        return things.stream().map(it -> {
            String upvotesText = getUpvotes(it);
            if (!upvotesText.isBlank()) {
                return RedditThread.builder().
                        upvotes(Integer.valueOf(upvotesText)).
                        subredditName(getSubredditName(it))
                        .title(getTitle(it))
                        .comments(getComments(it))
                        .link(getLink(it))
                        .build();
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private String getLink(Element it) {
        return it.getElementsByClass("first").get(0)
                .children().get(0).attr("href");
    }

    private String getComments(Element it) {
        return it.getElementsByClass("title").get(0)
                .child(0).attr("data-inbound-url");
    }

    private String getTitle(Element it) {
        return it.getElementsByClass("title").get(0)
                .children().get(0).text();
    }

    private String getSubredditName(Element it) {
        return it.attr("data-subreddit");
    }

    private String getUpvotes(Element it) {
        return it.getElementsByClass("midcol").get(0)
                .children().get(3).attr("title");
    }
}
