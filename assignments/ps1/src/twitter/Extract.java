/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        // 特殊处理
        if (tweets == null || tweets.isEmpty()) {
            return null;
        }

        // 循环迭代出最早和最晚时间, 循环不变式保证: max > min
        Instant endTimestamp = tweets.getFirst().getTimestamp(), startTimestamp = tweets.getFirst().getTimestamp();
        for (final Tweet tweet: tweets) {
            final Instant currTimestamp = tweet.getTimestamp();
            if (currTimestamp.isBefore(startTimestamp)) {
                startTimestamp = currTimestamp;
            }
            else if (currTimestamp.isAfter(endTimestamp)) {
                endTimestamp = currTimestamp;
            }
        }

        return new Timespan(startTimestamp, endTimestamp);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        assert tweets != null;   // fail fast

        Set<String> mentionedNames = new HashSet<>();

        for (final Tweet tweet: tweets) {
            String[] words = tweet.getText().split(" ");

            for (final String word: words) {
                // valid name
                if (word.startsWith("@") && word.length() > 1) {
                    final String mentionedName = word.substring(1);
                    if (isValidName(mentionedName) && !existInSet(mentionedName, mentionedNames)) {
                        mentionedNames.add(mentionedName);
                    }
                }
            }
        }

        return Collections.unmodifiableSet(mentionedNames);  // 返回不可变对象
    }

    /*
     * check whether the name of the tweet user is valid
     *
     * @param name user's name, which is supposed to be not null
     *
     * @return whether the user's name is a valid Twitter username (as
     *  defined by Tweet.getAuthor()'s spec)
     */
    private static boolean isValidName(final String name) {
        final String USERNAME_PATTERN = "^[A-Za-z0-9_-]+$";  // 使用正则表达式
        return name != null && !name.isEmpty() && Pattern.matches(USERNAME_PATTERN, name);
    }

    /*
     * check whether the name has already in the set, under the case of case-insensitive
     *
     * @param name the name being checked, not null
     * @param nameSet the set of collected names, not null
     *
     * @return whether the name is in the nameSet under the case of case-insensitive
     */
    private static boolean existInSet(final String name, final Set<String> nameSet) {
        for (final String n: nameSet) {
            if (n.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
