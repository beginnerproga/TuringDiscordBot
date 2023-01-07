package com.diagorn.turingbot.emoji;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Factory, producing known custom server emoji
 *
 * @author diagorn
 */
@Component
public class ServerEmojiFactory {

    @Value("${emoji.id.frontend}")
    private long frontendEmojiId;

    @Value("${emoji.id.java}")
    private long javaEmojiId;

    @Value("${emoji.id.sql}")
    private long sqlEmojiId;

    @Value("${emoji.id.android}")
    private long androidEmojiId;

    @Value("${emoji.id.analytics}")
    private long analyticsEmojiId;

    /**
     * @return frontend emoji
     */
    public ServerEmoji getFrontendEmoji() {
        return new ServerEmoji(frontendEmojiId, CustomEmojiEnum.FRONTEND, false);
    }

    /**
     * @return java emoji
     */
    public ServerEmoji getJavaEmoji() {
        return new ServerEmoji(javaEmojiId, CustomEmojiEnum.JAVA, false);
    }

    /**
     * @return sql emoji
     */
    public ServerEmoji getSqlEmoji() {
        return new ServerEmoji(sqlEmojiId, CustomEmojiEnum.SQL, false);
    }

    /**
     * @return android emoji
     */
    public ServerEmoji getAndroidEmoji() {
        return new ServerEmoji(androidEmojiId, CustomEmojiEnum.ANDROID, false);
    }

    /**
     * @return analytics emoji
     */
    public ServerEmoji getAnalyticsEmoji() {
        return new ServerEmoji(analyticsEmojiId, CustomEmojiEnum.ANALYTICS, false);
    }
}
