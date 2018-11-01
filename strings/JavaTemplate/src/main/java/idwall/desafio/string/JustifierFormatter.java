package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

public class JustifierFormatter extends StringFormatter {

    private CharactersFormatter charactersFormatter = new CharactersFormatter();

    @Override
    public String format(String text) {
        String formattedText = charactersFormatter.format(text);
        String[] linesList = formattedText.split("\\n");
        List<String> newLines = new ArrayList<>();

        for (String line : linesList) {
            int missingSpace = 40 - line.trim().length();

            String result;
            if (missingSpace != 0 && !line.trim().isEmpty()) {
                result = justifyLine(line, missingSpace);
            } else {
                result = line;
            }
            newLines.add(result);
        }
        return String.join("\n", newLines);
    }

    private String justifyLine(String line, int missingSpace) {
        String result;
        String[] words = line.split("\\s");

        int spacePerWord = (int) Math.ceil(missingSpace / (words.length - 1));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length - 2; i++) {
            sb.append(words[i]).append(" ");
            for (int j = 0; j < spacePerWord; j++) {
                sb.append(" ");
            }
        }

        String lastButOneWord = words[words.length - 2];
        String lastWord = words[words.length - 1];

        missingSpace = 40 - (sb.length() + lastButOneWord.length() + lastWord.length());
        sb.append(lastButOneWord);
        for (int i = 0; i < missingSpace; i++) {
            sb.append(" ");
        }
        sb.append(lastWord);
        result = sb.toString();
        return result;
    }

}
