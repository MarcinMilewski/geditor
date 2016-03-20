package com.geditor.io.importer.parser;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryParser {
    private final byte[] bytes;
    private int index;

    public boolean isComment(String line, char commentMarker) {
        if (line.startsWith(String.valueOf(commentMarker))) {
            return true;
        }
        else return false;
    }

    public char nextChar() {
        return (char) (bytes[index++] & 0xFF);
    }

    public int convertByteToInt8(byte b) {
        return b & 0x000000ff;
    }

    public String getLine() {
        StringBuffer sb = new StringBuffer();
        while (getCurrentChar() != '\n') {
            sb.append(getCurrentChar());
            moveToNextByte();
        }
        moveToNextLine();
        return sb.toString();
    }

    public char nextCharAfterNewLine() {
        char c;
        while ((c = getCurrentChar()) != '\n') {
            moveToNextByte();
        }
        moveToNextByte();
        return getCurrentChar();
    }

    public void moveToNextLine() {
        char c = getCurrentChar();
        boolean firstCharIsNotNewLine = false;
        while (c != '\n') {
            moveToNextByte();
            firstCharIsNotNewLine = true;
        }
        if (firstCharIsNotNewLine == false) {
            moveToNextByte();
        }
    }

    public void moveToNextByte() {
        ++index;
    }

    public byte getCurrentByte() {
        return bytes[index];
    }

    public char getCurrentChar() {
        return (char) (bytes[index] & 0xFF);
    }

}
