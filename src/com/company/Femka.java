package com.company;

import java.awt.*;

public class Femka implements Bot {
    Color color = new Color(0xFF, 0x00, 0x7F);

    @Override
    public String answer(String question) throws BadAnswerException {
        if (question.contains("тупая")) {
            throw new BadAnswerException("Ой, всё!");
        } else if (question.contains("владычица")) {
            return (new Answer<>(new Emoji(0))).get().getTextInterpetation();
        } else {
            return (new Answer<>(question)).get() + "ка";
        }
    }

    @Override
    public String getName() {
        return "Фемка";
    }

    @Override
    public Color getColor() { return color; }
}