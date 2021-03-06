/*
 * Created on 2005-6-20
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.wuchao.utils.string;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to peform common String manipulation algorithms.
 */
public class StringUtils {

    // Constants used by escapeHTMLTags
    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();

    private static final char[] AMP_ENCODE = "&amp;".toCharArray();

    private static final char[] LT_ENCODE = "&lt;".toCharArray();

    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    private static final char[] ENTER_ENCODE = "<BR>".toCharArray();

    /**
     * Replaces all instances of oldString with newString in line.
     * 
     * @param line
     *            the String to search to perform replacements on
     * @param oldString
     *            the String that should be replaced by newString
     * @param newString
     *            the String that will replace all instances of oldString
     * 
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString,
                                       String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ( (i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case.
     * 
     * @param line
     *            the String to search to perform replacements on
     * @param oldString
     *            the String that should be replaced by newString
     * @param newString
     *            the String that will replace all instances of oldString
     * 
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString,
                                                 String newString) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ( (i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case. The count
     * paramater is set to the number of replaces performed.
     * 
     * @param line
     *            the String to search to perform replacements on
     * @param oldString
     *            the String that should be replaced by newString
     * @param newString
     *            the String that will replace all instances of oldString
     * @param count
     *            a value that will be updated with the number of replaces
     *            performed.
     * 
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString,
                                                 String newString, int[] count) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ( (i = lcLine.indexOf(lcOldString, i)) >= 0) {
            int counter = 0;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = lcLine.indexOf(lcOldString, i)) > 0) {
                counter++ ;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line. The count
     * Integer is updated with number of replaces.
     * 
     * @param line
     *            the String to search to perform replacements on
     * @param oldString
     *            the String that should be replaced by newString
     * @param newString
     *            the String that will replace all instances of oldString
     * 
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString,
                                       String newString, int[] count) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ( (i = line.indexOf(oldString, i)) >= 0) {
            int counter = 0;
            counter++ ;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = line.indexOf(oldString, i)) > 0) {
                counter++ ;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
     * their HTML escape sequences.
     * 
     * @param in
     *            the text to be converted.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     *         with their HTML escape sequences.
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++ ) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            } else if (ch == '\n') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(ENTER_ENCODE);
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * Used by the hash method.
     */
    private static MessageDigest digest = null;

    /**
     * Hashes a String using the Md5 algorithm and returns the result as a
     * String of hexadecimal numbers. This method is synchronized to avoid
     * excessive MessageDigest object creation. If calling this method becomes a
     * bottleneck in your code, you may wish to maintain a pool of MessageDigest
     * objects instead of using this method. <p> A hash is a one-way function --
     * that is, given an input, an output is easily computed. However, given the
     * output, the input is almost impossible to compute. This is useful for
     * passwords since we can store the hash and a hacker will then have a very
     * hard time determining the original password. <p> In Jive, every time a
     * user logs in, we simply take their plain text password, compute the hash,
     * and compare the generated hash to the stored hash. Since it is almost
     * impossible that two passwords will generate the same hash, we know if the
     * user gave us the correct password or not. The only negative to this
     * system is that password recovery is basically impossible. Therefore, a
     * reset password method is used instead.
     * 
     * @param data
     *            the String to compute the hash of.
     * @return a hashed version of the passed-in String
     */
    public synchronized static final String hash(String data) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {
                System.err.println("Failed to load the MD5 MessageDigest. "
                                   + "Jive will be unable to function normally.");
                nsae.printStackTrace();
            }
        }
        // Now, compute hash.
        digest.update(data.getBytes());
        return encodeHex(digest.digest());
    }

    /**
     * Turns an array of bytes into a String representing each byte as an
     * unsigned hex number. <p> Method by Santeri Paavolainen, Helsinki Finland
     * 1996<br> (c) Santeri Paavolainen, Helsinki Finland 1996<br> Distributed
     * under LGPL.
     * 
     * @param bytes
     *            an array of bytes to convert to a hex-string
     * @return generated hex string
     */
    public static final String encodeHex(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        int i;

        for (i = 0; i < bytes.length; i++ ) {
            if ( ((int)bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int)bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * 
     */
    public static final String encodeHex(String str) {
        byte[] bytes = str.getBytes();
        return encodeHex(bytes);
    }

    /**
     * Turns a hex encoded string into a byte array. It is specifically meant to
     * "reverse" the toHex(byte[]) method.
     * 
     * @param hex
     *            a hex encoded String to transform into a byte array.
     * @return a byte array representing the hex String[
     */
    public static final byte[] decodeHex(String hex) {
        char[] chars = hex.toCharArray();
        byte[] bytes = new byte[chars.length / 2];
        int byteCount = 0;
        for (int i = 0; i < chars.length; i += 2) {
            byte newByte = 0x00;
            newByte |= hexCharToByte(chars[i]);
            newByte <<= 4;
            newByte |= hexCharToByte(chars[i + 1]);
            bytes[byteCount] = newByte;
            byteCount++ ;
        }
        return bytes;
    }

    /**
     * Returns the the byte value of a hexadecmical char (0-f). It's assumed
     * that the hexidecimal chars are lower case as appropriate.
     * 
     * @param ch
     *            a hexedicmal character (0-f)
     * @return the byte value of the character (0x00-0x0F)
     */
    private static final byte hexCharToByte(char ch) {
        switch (ch) {
            case '0':
                return 0x00;
            case '1':
                return 0x01;
            case '2':
                return 0x02;
            case '3':
                return 0x03;
            case '4':
                return 0x04;
            case '5':
                return 0x05;
            case '6':
                return 0x06;
            case '7':
                return 0x07;
            case '8':
                return 0x08;
            case '9':
                return 0x09;
            case 'a':
                return 0x0A;
            case 'b':
                return 0x0B;
            case 'c':
                return 0x0C;
            case 'd':
                return 0x0D;
            case 'e':
                return 0x0E;
            case 'f':
                return 0x0F;
        }
        return 0x00;
    }

    // *********************************************************************
    // * Base64 - a simple base64 encoder and decoder.
    // *
    // * Copyright (c) 1999, Bob Withers - bwit@pobox.com
    // *
    // * This code may be freely used for any purpose, either personal
    // * or commercial, provided the authors copyright notice remains
    // * intact.
    // *********************************************************************

    /**
     * Encodes a String as a base64 String.
     * 
     * @param data
     *            a String to encode.
     * @return a base64 encoded String.
     */
    public static String encodeBase64(String data) {
        return encodeBase64(data.getBytes());
    }

    /**
     * Encodes a byte array into a base64 String.
     * 
     * @param data
     *            a byte array to encode.
     * @return a base64 encode String.
     */
    public static String encodeBase64(byte[] data) {
        int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer( ( (len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(cvt.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if ( ++i < len)
                c |= (data[i] >> 4) & 0x0f;

            ret.append(cvt.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if ( ++i < len)
                    c |= (data[i] >> 6) & 0x03;

                ret.append(cvt.charAt(c));
            } else {
                ++i;
                ret.append((char)fillchar);
            }

            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(cvt.charAt(c));
            } else {
                ret.append((char)fillchar);
            }
        }
        return ret.toString();
    }

    /**
     * Decodes a base64 String.
     * 
     * @param data
     *            a base64 encoded String to decode.
     * @return the decoded String.
     */
    public static String decodeBase64(String data) {
        return decodeBase64(data.getBytes());
    }

    /**
     * Decodes a base64 aray of bytes.
     * 
     * @param data
     *            a base64 encode byte array to decode.
     * @return the decoded String.
     */
    public static String decodeBase64(byte[] data) {
        int c, c1;
        int len = data.length;
        StringBuffer ret = new StringBuffer( (len * 3) / 4);
        for (int i = 0; i < len; ++i) {
            c = cvt.indexOf(data[i]);
            ++i;
            c1 = cvt.indexOf(data[i]);
            c = ( (c << 2) | ( (c1 >> 4) & 0x3));
            ret.append((char)c);
            if ( ++i < len) {
                c = data[i];
                if (fillchar == c)
                    break;

                c = cvt.indexOf((char)c);
                c1 = ( (c1 << 4) & 0xf0) | ( (c >> 2) & 0xf);
                ret.append((char)c1);
            }

            if ( ++i < len) {
                c1 = data[i];
                if (fillchar == c1)
                    break;

                c1 = cvt.indexOf((char)c1);
                c = ( (c << 6) & 0xc0) | c1;
                ret.append((char)c);
            }
        }
        return ret.toString();
    }

    private static final int fillchar = '=';

    private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                      + "abcdefghijklmnopqrstuvwxyz"
                                      + "0123456789+/";

    /**
     * Converts a line of text into an array of lower case words using a
     * BreakIterator.wordInstance(). <p>
     * 
     * This method is under the Jive Open Source Software License and was
     * written by Mark Imbriaco.
     * 
     * @param text
     *            a String of text to convert into an array of words
     * @return text broken up into an array of words.
     */
    public static final String[] toLowerCaseWordArray(String text) {
        if (text == null || text.length() == 0) {
            return new String[0];
        }

        ArrayList wordList = new ArrayList();
        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(text);
        int start = 0;

        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
            String tmp = text.substring(start, end).trim();
            // Remove characters that are not needed.
            tmp = replace(tmp, "+", "");
            tmp = replace(tmp, "/", "");
            tmp = replace(tmp, "\\", "");
            tmp = replace(tmp, "#", "");
            tmp = replace(tmp, "*", "");
            tmp = replace(tmp, ")", "");
            tmp = replace(tmp, "(", "");
            tmp = replace(tmp, "&", "");
            if (tmp.length() > 0) {
                wordList.add(tmp);
            }
        }
        return (String[])wordList.toArray(new String[wordList.size()]);
    }

    /**
     * Pseudo-random number generator object for use with randomString(). The
     * Random class is not considered to be cryptographically secure, so only
     * use these random Strings for low to medium security applications.
     */
    private static Random randGen = new Random();

    /**
     * Array of numbers and letters of mixed case. Numbers appear in the list
     * twice so that there is a more equal chance that a number will be picked.
     * We can use the array to get a random number or letter by picking a random
     * array index.
     */
    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                                               + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /**
     * Returns a random String of numbers and letters (lower and upper case) of
     * the specified length. The method uses the Random class that is built-in
     * to Java which is suitable for low to medium grade security uses. This
     * means that the output is only pseudo random, i.e., each number is
     * mathematically generated so is not truly random.<p>
     * 
     * The specified length must be at least one. If not, the method will return
     * null.
     * 
     * @param length
     *            the desired length of the random String to return.
     * @return a random String of numbers and letters of the specified length.
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++ ) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * Intelligently chops a String at a word boundary (whitespace) that occurs
     * at the specified index in the argument or before. However, if there is a
     * newline character before <code>length</code>, the String will be
     * chopped there. If no newline or whitespace is found in
     * <code>string</code> up to the index <code>length</code>, the String
     * will chopped at <code>length</code>. <p> For example, chopAtWord("This
     * is a nice String", 10) will return "This is a" which is the first word
     * boundary less than or equal to 10 characters into the original String.
     * 
     * @param string
     *            the String to chop.
     * @param length
     *            the index in <code>string</code> to start looking for a
     *            whitespace boundary at.
     * @return a substring of <code>string</code> whose length is less than or
     *         equal to <code>length</code>, and that is chopped at
     *         whitespace.
     */
    public static final String chopAtWord(String string, int length) {
        if (string == null) {
            return string;
        }

        char[] charArray = string.toCharArray();
        int sLength = string.length();
        if (length < sLength) {
            sLength = length;
        }

        // First check if there is a newline character before length; if so,
        // chop word there.
        for (int i = 0; i < sLength - 1; i++ ) {
            // Windows
            if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
                return string.substring(0, i + 1);
            }
            // Unix
            else if (charArray[i] == '\n') {
                return string.substring(0, i);
            }
        }
        // Also check boundary case of Unix newline
        if (charArray[sLength - 1] == '\n') {
            return string.substring(0, sLength - 1);
        }

        // Done checking for newline, now see if the total string is less than
        // the specified chop point.
        if (string.length() < length) {
            return string;
        }

        // No newline, so chop at the first whitespace.
        for (int i = length - 1; i > 0; i-- ) {
            if (charArray[i] == ' ') {
                return string.substring(0, i).trim();
            }
        }

        // Did not find word boundary so return original String chopped at
        // specified length.
        return string.substring(0, length);
    }

    /**
     * Escapes all necessary characters in the String so that it can be used in
     * an XML doc.
     * 
     * @param string
     *            the string to escape.
     * @return the string with appropriate characters escaped.
     */
    public static final String escapeForXML(String string) {
        if (string == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = string.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++ ) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '&') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(AMP_ENCODE);
            } else if (ch == '"') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(QUOTE_ENCODE);
            }
        }
        if (last == 0) {
            return string;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * Unescapes the String by converting XML escape sequences back into normal
     * characters.
     * 
     * @param string
     *            the string to unescape.
     * @return the string with appropriate characters unescaped.
     */
    public static final String unescapeFromXML(String string) {
        string = replace(string, "&lt;", "<");
        string = replace(string, "&gt;", ">");
        string = replace(string, "&quot;", "\"");
        return replace(string, "&amp;", "&");
    }

    private static final char[] zeroArray = "0000000000000000".toCharArray();

    /**
     * Pads the supplied String with 0's to the specified length and returns the
     * result as a new String. For example, if the initial String is "9999" and
     * the desired length is 8, the result would be "00009999". This type of
     * padding is useful for creating numerical values that need to be stored
     * and sorted as character data. Note: the current implementation of this
     * method allows for a maximum <tt>length</tt> of 16.
     * 
     * @param string
     *            the original String to pad.
     * @param length
     *            the desired length of the new padded String.
     * @return a new String padded with the required number of 0's.
     */
    public static final String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        }
        StringBuffer buf = new StringBuffer(length);
        buf.append(zeroArray, 0, length - string.length()).append(string);
        return buf.toString();
    }

    /**
     * Formats a Date as a fifteen character long String made up of the Date's
     * padded millisecond value.
     * 
     * @return a Date encoded as a String.
     */
    public static final String dateToMillis(Date date) {
        return zeroPadString(Long.toString(date.getTime()), 15);
    }

    /**
     * This method is used to split the input String. JDK1.4 has this method,but
     * JDK1.3 does not has this method, so I code this method. if strInput =
     * "00|||11||||||22" strToken = "|||" then the out put String is this:
     * string[0] = "00" string[1] = "11" string[2] = "" string[3] = "22"
     * 
     * @param strInput
     * @return String[]
     */
    public static String[] split(String strInput, String splitToken) {
        int last = 0;
        int index = 0;
        int augment = splitToken.length();
        java.util.ArrayList matchList = new java.util.ArrayList();

        // Get each field name,and put it in arrayList.
        while (index <= strInput.length()) {
            index = strInput.indexOf(splitToken, index);

            // If the token is the last split token.
            if (index == -1) {
                String match = strInput.substring(last, strInput.length());
                matchList.add(match);
                break;
            } else {
                String match = strInput.substring(last, index);
                matchList.add(match);

                // move the index to the next startpoint.
                index += augment;
                last = index;
            }
        }

        // After split the String ,then store it in array.
        String[] fieldNames = new String[matchList.size()];
        for (int i = 0; i < matchList.size(); i++ ) {
            fieldNames[i] = (String)matchList.get(i);
            // System.out.println("The Splited is|"+fieldNames[i]+"|");
        }

        return fieldNames;
    }

    /**
     * 如果line的长度满足len，那么就把append补到line的左端
     * 
     * @param line
     * @param append
     * @param len
     * @return
     */
    public static String StringLeftAppendString(String line, String append,
                                                int len) {
        int length = line.length();
        if (length == len) {
            StringBuffer sb = new StringBuffer(line);
            sb.insert(0, append);
            return sb.toString();
        }
        return line;
    }

    /**
     * 
     * @description 求两个数组的并集
     * 
     * @author chenwei
     * @date 2009-6-19
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String[] union(String[] a, String[] b) {

        String[] r = new String[a.length + b.length];
        for (int i = 0; i < a.length; i++ ) {
            r[i] = a[i];
        }
        for (int i = 0; i < b.length; i++ ) {
            r[a.length + i] = b[i];
        }
        return r;
    }

    /**
     * 
     * @description 求两个数组相减
     * 
     * @author chenwei
     * @date 2009-6-19
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String[] subtract(String[] a, String[] b) {
        int length = a.length - b.length;
        List c = new ArrayList();
        if (length >= 0) {
            for (int i = 0; i < a.length; i++ ) {
                if (b.length > 0) {
                    boolean flag = true;
                    for (int j = 0; j < b.length; j++ ) {
                        if (a[i].equals(b[j]))
                            flag = false;
                    }
                    if (flag)
                        c.add(a[i]);
                } else {
                    c.add(a[i]);
                }

            }
        }
        String[] r = new String[c.size()];
        for (int i = 0; i < c.size(); i++ ) {
            r[i] = (String)c.get(i);
        }
        return r;
    }

    /**
     * 
     * @description YYYY-MM-DD格式转换成YYYYMMDD
     * 
     * @author chenwei
     * @date 2009-7-2
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String date2String(String date) {
        String temp = null;
        if (date != null && !"".equals(date)) {
            date = date.trim();
            temp = date.replaceAll("-", "");
        }
        return temp;
    }

    /**
     * 
     * @description YYYY-MM-DD HH:mm:ss格式转换成YYYYMMDDHHmmss
     * 
     * @author chenwei
     * @date 2009-7-2
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String datetime2String(String datetime) {
        String temp = null;
        if (datetime != null && !"".equals(datetime)) {
            datetime = datetime.trim();
            temp = datetime.replaceAll("-", "").replaceAll(":", "").replaceAll(
                " ", "");
        }
        return temp;
    }

    /**
     * 
     * @description YYYYMMDD格式转换成YYYY-MM-DD
     * 
     * @author chenwei
     * @date 2009-7-2
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String string2Date(String str) {
        String temp = null;
        if (str != null && !"".equals(str)) {
            str = str.trim();
            temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                   + str.substring(6, 8);
        }
        return temp;
    }

    /**
     * 
     * @description YYYYMMDDHHmmss格式转换成YYYY-MM-DD HH:mm:ss
     * 
     * @author chenwei
     * @date 2009-7-2
     * @version 1.0.0
     * @return
     * @throws
     */
    public static String string2Datetime(String str) {
        String temp = null;
        if (str != null && !"".equals(str)) {
            str = str.trim();
            if (str.length() >= 14) {
                temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                       + str.substring(6, 8) + " " + str.substring(8, 10) + ":"
                       + str.substring(10, 12) + ":" + str.substring(12, 14);
            } else if (str.length() >= 6 && str.length() < 14) {
                temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                       + str.substring(6, 8);
            }
        }
        return temp;
    }
    
    
    private static char[] numbers = ("01234567890123456789").toCharArray();
    
    /**
     * 
     * @description 生成随即数字字符串
     * @author chenwei
     * @date 2011-6-22
     * @version 1.0.0
     * @history1:@author;@date;@description
     * @history2:@author;@date;@description
     * @param length
     * @return
     */
    public static final String randomNumString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            // randGen.nextInt(71):随机生成一个位于0-71之间的整数
            randBuffer[i] = numbers[randGen.nextInt(19)];
        }
        return new String(randBuffer);
    }
    /*
     * 
     * 删除字符串中的标签
     */
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤  
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    } 
	    public static String delSpace(String htmlStr){ 
	        htmlStr = htmlStr.replaceAll("\n", "");
	        htmlStr = htmlStr.replaceAll("\t", "");
	        return htmlStr.trim(); //返回文本字符串 
	    }
	    public static String deleHTML(String s) {
	    	return delSpace(delHTMLTag(s)).replaceAll("&nbsp;", "").trim().replaceAll("\r", "");
	    }
}