package com.company;

import java.awt.*;

public class UncleSam implements Bot {
    Color color = Color.BLUE;

    @Override
    public String answer(String question) throws BadAnswerException {
        if (question.contains("democracy")) {
            return (new Answer<>(new Emoji(13))).get().getTextInterpetation();
        } else {
            return question + " Big Brother is watching You!";
        }
    }

    @Override
    public String getName() {
        return "Uncle Sam";
    }

    @Override
    public Color getColor() { return color; }
}