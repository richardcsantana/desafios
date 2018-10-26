package idwall.desafio.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class RedditThread {

    private Integer upvotes;
    private String subredditName;
    private String title;
    private String comments;
    private String link;

    @Override
    public String toString() {
        String commentsToString = "";
        if(!comments.isBlank()){
            commentsToString = "https://reddit.com" + comments;
        }
        return "____________________________________________________________________________________________________________________________________________________" + "\n" +
                "upvotes='" + upvotes + "',\n" +
                "subredditName='" + subredditName + "',\n" +
                "title='" + title + "',\n" +
                "comments='" + commentsToString + "',\n" + // TODO
                "link='" + link + "'";
    }
}
