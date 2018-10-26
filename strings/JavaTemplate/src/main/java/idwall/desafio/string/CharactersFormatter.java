package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

public class CharactersFormatter extends StringFormatter {
    @Override
    public String format(String text) {
        List<String> lines = new ArrayList<>();
        String[] linesList = text.split("\\n");
        for (String line : linesList) {
            String[] stringList = line.split("\\s");
            lines.addAll(formatInput(stringList));
        }
        return String.join("\n", lines);
    }

    private List<String> formatInput(String[] stringList) {
        List<String> lines = new ArrayList<>();
        String aux = "";
        for (String each : stringList) {
            if (aux.length() + each.length() > 40) {
                lines.add(aux.trim());
                aux = "";
            }
            aux = aux.concat(each).concat(" ");
        }
        lines.add(aux.trim());
        return lines;
    }
}
