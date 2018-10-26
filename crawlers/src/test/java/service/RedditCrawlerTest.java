package service;

import model.RedditThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import service.RedditCrawler;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RedditCrawlerTest {


    @Test
    void parser() throws IOException {
        String fileName = "reddit.html";
        File resource = getResource(fileName);
        Document document = Jsoup.parse(resource, "UTF-8",
                "https://old.reddit.com");
        RedditCrawler redditCrawler = new RedditCrawler();
        List<RedditThread> content = redditCrawler
                .getSubRedditsContentByName(document);

        int expectedSize = 24;
        int actualSize = content.size();
        assertEquals(expectedSize, actualSize);

        List<RedditThread> ordered = content.stream()
                .sorted(Comparator.comparingInt(RedditThread::getUpvotes))
                .collect(Collectors.toList());

        RedditThread firstExpectedCase = RedditThread.builder()
                .title("Tika the three-legged tabby")
                .upvotes(44)
                .subredditName("cats")
                .comments("/r/cats/comments/9rkltd/tika_the_threelegged_tabby/" +
                        "?utm_content=title&utm_medium=new&utm_source=reddit&utm_name=cats")
                .link("https://old.reddit.com/r/cats/comments/9rkltd/tika_the_threelegged_tabby/")
                .build();

        RedditThread lastExpectedCase = RedditThread.builder()
                .title("He watches me scroll on Reddit before sleepy time!")
                .upvotes(14682)
                .subredditName("cats")
                .comments("")
                .link("https://old.reddit.com/r/cats/comments/9rdjvm/" +
                        "he_watches_me_scroll_on_reddit_before_sleepy_time/")
                .build();

        assertEquals(firstExpectedCase, ordered.get(0));
        assertEquals(lastExpectedCase, ordered.get(23));

    }

    private File getResource(String fileName) {
        return new File(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(fileName)).getFile());
    }
}
