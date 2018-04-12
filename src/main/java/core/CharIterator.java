package core;

import java.util.Iterator;

public class CharIterator implements Iterator<String> {

    private int cursor;
    private String in;
    private final int size;

    public CharIterator(String in) {

        this.in = in.trim().toLowerCase();
        size = this.in.length();
    }

    public String next() {

        /* scan until non-whitespace character */
        while (cursor < size && Character.isWhitespace(in.charAt(cursor))) { cursor++; }

        return String.valueOf(in.charAt(cursor++));
    }

    public boolean hasNext() {

        return cursor < in.length();
    }
}
