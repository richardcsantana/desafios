package idwall.desafio.crawler.service;

import idwall.desafio.crawler.model.RedditThread;
import org.springframework.stereotype.Service;

@Service
public class HtmlParser {

    public String parseHtml(RedditThread redditThread) {
        StringBuffer sb = new StringBuffer(String.format("<b>%s</b> \n", redditThread.getTitle()));
        sb.append(String.format("<b>Upvotes: </b> %s \n", redditThread.getUpvotes()));
        sb.append(String.format("<b>SubReddit: </b> %s \n", redditThread.getSubredditName()));
        String comments = redditThread.getComments();
        sb.append(String.format("<a href=\"%s\">Link</a> ", redditThread.getLink()));
        if (comments != null && !comments.isBlank()) {
            String commentsToString = "https://reddit.com" + comments;
            sb.append(String.format("- <a href=\"%s\">Comments</a> \n", commentsToString));
        }
        return sb.toString();
    }
}
