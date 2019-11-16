package com.company;

import java.awt.*;

public class KremlinBot implements Bot {
    Color color = Color.RED;

    @Override
    public String answer(String message) throws BadAnswerException {
        if (message.contains("США")) {
            throw new BadAnswerException("(ваш компьютер взломан русскими хакерами)");
        } else if (message.contains("Путин")) {
            return (new Answer<>(new Emoji(2))).get().getTextInterpetation();
        } else {
            return "Православие, Самодержавие, Народность, " + message + "!";
        }
    }

    @Override
    public String getName() {
        return "Кремлебот";
    }


    @Override
    public Color getColor() { return color; }
}