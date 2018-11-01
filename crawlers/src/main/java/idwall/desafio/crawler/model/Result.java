package idwall.desafio.crawler.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private boolean ok;
    private List<Update> result;
}
