package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContent implements IterableText {

    private String text;
    private String filePath;

    private static final char SPACE = ' ';

    public FileContent(String filePath) {

        this.filePath = filePath;
        try {
            text = read();
        } catch (IOException e) {
            text = "";
        }
    }

    public CharIterator charIterator() {

        return new CharIterator(text);
    }

    public WordIterator wordIterator() {

        return new WordIterator(text);
    }

    public String getFilename() {

        return filePath;
    }


    private String read() throws IOException {

        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){

            for (String chunk = br.readLine(); chunk != null; chunk = br.readLine()) {

                result.append(chunk).append(SPACE);
            }
        }
        return stripLastTrailingSpace(result.toString());
    }

    private String stripLastTrailingSpace(String str) {

        int lastIndex;
        if ((lastIndex = str.lastIndexOf(SPACE)) > 0) {

            return str.substring(0, lastIndex);
        }
        return str;
    }
}
