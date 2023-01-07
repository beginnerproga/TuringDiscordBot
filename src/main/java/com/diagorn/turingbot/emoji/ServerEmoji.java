package com.diagorn.turingbot.emoji;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.emoji.CustomEmoji;

/**
 * Turing server emojis
 *
 * @author diagorn
 */
public class ServerEmoji implements CustomEmoji {
    /**
     * Emoji image
     */
    private Icon image;
    /**
     * Emoji id
     */
    private long id;
    /**
     * Emoji enum with name
     */
    private CustomEmojiEnum emojiEnum;
    /**
     * If emoji is animated (always false on Turing server at the time)
     */
    private boolean animated;

    public ServerEmoji(long id, CustomEmojiEnum emojiEnum, boolean animated) {
        this.id = id;
        this.emojiEnum = emojiEnum;
        this.animated = animated;
    }

    @Override
    public Icon getImage() {
        return image;
    }

    @Override
    public DiscordApi getApi() {
        return null;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.emojiEnum == null ? null : this.emojiEnum.getName();
    }

    @Override
    public boolean isAnimated() {
        return animated;
    }

    public CustomEmojiEnum getEmojiEnum() {
        return emojiEnum;
    }

    public void setEmojiEnum(CustomEmojiEnum emojiEnum) {
        this.emojiEnum = emojiEnum;
    }
}
