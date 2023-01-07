package com.diagorn.turingbot.emoji;

/**
 * All known added server emoji
 *
 * @author diagorn
 */
public enum CustomEmojiEnum {
    JAVA("java"),
    FRONTEND("js"),
    SQL("sql"),
    ANALYTICS("analytics"),
    ANDROID("android");

    private String name;

    CustomEmojiEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
