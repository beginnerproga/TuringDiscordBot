package com.diagorn.turingbot.repo;

import com.diagorn.turingbot.emoji.ServerEmoji;
import com.diagorn.turingbot.emoji.ServerEmojiFactory;
import org.javacord.api.entity.emoji.Emoji;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry containing all known emojis
 *
 * @author diagorn
 */
@Repository
public class EmojiRegistry {

    /**
     * All custom emojis container
     */
    private final List<ServerEmoji> customEmoji;

    public EmojiRegistry(ServerEmojiFactory serverEmojiFactory) {
        this.customEmoji = new ArrayList<>();
        this.customEmoji.add(serverEmojiFactory.getJavaEmoji());
        this.customEmoji.add(serverEmojiFactory.getFrontendEmoji());
        this.customEmoji.add(serverEmojiFactory.getSqlEmoji());
        this.customEmoji.add(serverEmojiFactory.getAndroidEmoji());
        this.customEmoji.add(serverEmojiFactory.getAnalyticsEmoji());
    }

    /**
     * Find known emoji by discord emoji
     *
     * @param emoji - discord emoji
     * @return custom emoji if found, null if not
     */
    public ServerEmoji findCustomEmoji(Emoji emoji) {
        if (emoji == null) {
            return null;
        }

        for (ServerEmoji serverEmoji: this.customEmoji) {
            if (serverEmoji.equalsEmoji(emoji)) {
                return serverEmoji;
            }
        }

        return null;
    }
}
