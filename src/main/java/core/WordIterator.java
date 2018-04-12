package core;

import java.util.Iterator;

public class WordIterator implements Iterator<String> {

    private int cursor;
    private String in;
    private final int size;

    public WordIterator(String in) {

        this.in = in.trim().toLowerCase();
        size = this.in.length();
    }

    public String next() {

        /* scan until non-whitespace character */
        while (cursor < size && Character.isWhitespace(in.charAt(cursor))) { cursor++; }
        int startPos = cursor;

        /* traverse until another whitespace or string end */
        while (cursor < size && !Character.isWhitespace(in.charAt(cursor))) { cursor++; }

        return in.substring(startPos, cursor);
    }

    public boolean hasNext() {

        return cursor < in.length();
    }
}