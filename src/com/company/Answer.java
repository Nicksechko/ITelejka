package com.company;

public class Answer<T> {
    private T answer;

    Answer(T newAns) {
        answer = newAns;
    }

    T get() {
        return answer;
    }
}
