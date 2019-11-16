package com.company;

import java.awt.*;

public class GretaThunberg implements Bot {
    Color color = new Color(0, 100, 0);

    @Override
    public String answer(String message) throws BadAnswerException {
        if (message.contains("money") || message.contains("fairytales") || message.contains("economic")) {
            throw new BadAnswerException("People are dying. " +
                    "Entire ecosystems are collapsing. We are in the beginning of a mass extinction. " +
                    "And all you can talk about is money and fairytales of eternal economic growth. How dare you!");
        } else if (message.contains("ecology")) {
            return (new Answer<>(new Emoji(25))).get().getTextInterpetation();
        } else {
            return "I'am " + message + " Greta!";
        }
    }

    @Override
    public String getName() {
        return "Greta Thunberg";
    }

    @Override
    public Color getColor() { return color; }
}
