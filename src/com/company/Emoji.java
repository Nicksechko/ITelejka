package com.company;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Emoji {
    private String emoji;

    Emoji(int emojiId) {
        emoji = new String(ByteBuffer.allocate(4).putInt(0xF09F9881 + emojiId).array(), StandardCharsets.UTF_8);
    }

    String getTextInterpetation() {
        return emoji;
    }
}
