package com.company;

import java.awt.*;

interface Bot {
    String answer(String question) throws BadAnswerException;
    String getName();
    Color getColor();
}
