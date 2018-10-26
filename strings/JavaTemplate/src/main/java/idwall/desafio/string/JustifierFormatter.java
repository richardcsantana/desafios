package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JustifierFormatter extends StringFormatter {
    @Override
    public String format(String text) {
        List<String> lines = new ArrayList<>();
        String[] linesList = text.split("\\n");
        for (String line : linesList) {
            String[] stringList = line.split("\\s");
            List<String> formattedLine = formatInput(stringList);
            lines.addAll(formattedLine);
        }
        return String.join("\n", lines);
    }

    private List<String> formatInput(String[] stringList) {
        List<String> lines = new ArrayList<>();
        String aux = "";
        for (String each : stringList) {
            if (aux.length() + each.length() > 40) {
                String trimmed = aux.trim();
                trimmed = justify(trimmed);
                lines.add(trimmed);
                aux = "";
            }
            aux = aux.concat(each).concat(" ");
        }
        lines.add(aux.trim());
        return lines;
    }

    private String justify(String trimmed) {
        int missingSpaces = 40 - trimmed.length();
        String[] words = trimmed.split("\\s");
        StringBuffer sb = new StringBuffer();
        Random randomGenerator = new Random();
        for (int j = 0; j < words.length; j++) {
            int eachWord = words.length - j / missingSpaces;
            sb.append(words[j]).append(" ");
            if (missingSpaces > 0) {
                int randomNumber = randomGenerator.nextInt(missingSpaces);
                missingSpaces -= randomNumber;
                for (int i = 0; i < randomNumber; i++) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString().trim();
    }
}
