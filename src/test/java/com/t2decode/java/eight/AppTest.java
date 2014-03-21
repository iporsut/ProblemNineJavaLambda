package com.t2decode.java.eight;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AppTest {

    public List<List<Character>> pack(List<Character> input) {
        List<List<Character>> result = new ArrayList<>();

        input.stream().reduce(result, (accumulatorList, y) -> {
            if (accumulatorList.isEmpty()) {
                accumulatorList.add(new ArrayList<>());
                accumulatorList.get(0).add(y);
            } else {
                List<Character> sub = accumulatorList.get(accumulatorList.size() - 1);
                if (sub.get(0) == y) {
                    sub.add(y);
                } else {
                    sub = new ArrayList<>();
                    sub.add(y);
                    accumulatorList.add(sub);
                }
            }
            return accumulatorList;
        }, (a, b) -> a);

        return result;
    }


    @Test
    public void spikeLambdaMap() {
        List<Character> input = charList("a a a a b c c a a d e e e e");

        List<List<Character>> expect = Arrays.asList(
                charList("a a a a"),
                charList("b"),
                charList("c c"),
                charList("a a"),
                charList("d"),
                charList("e e e e")
        );

        assertEquals(expect, pack(input));
    }

    private List<Character> charList(String str) {
        List<String> tokens = Arrays.asList(str.split("\\s"));
        List<Character> inputList = new ArrayList<>();
        for (String token : tokens) {
            inputList.add(token.charAt(0));
        }
        return inputList;
    }
}
