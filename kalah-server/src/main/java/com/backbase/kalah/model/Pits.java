package com.backbase.kalah.model;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class Pits {

    /**
     * key: 1-based pitId, value: count of stones in the pit
     */
    private final SortedMap<Integer, Integer> pits;

    public Pits(int stones) {
        pits = new TreeMap<>();
        for (PlayerId playerId : PlayerId.values()) {
            pits.put(playerId.kalahPitId(), 0);
            playerId.pitIds()
                    .forEach(pitId -> pits.put(pitId, stones));
        }

    }

    public int get(int pitId) {
        return pits.get(pitId);
    }

    public void put(int pitId, int stones) {
        pits.put(pitId, stones);
    }

    public List<Integer> asList() {
        return new ArrayList<>(pits.values());
    }

    /**
     * Encode state to a colon-separated int values
     */
    public String encode() {
        return asList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(":"));
    }

    /**
     * Decode state from a colon-separated int values
     */
    public static Pits decode(String str) {
        val list = Stream.of(str.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Assert.isTrue(list.size() == 14, "Should have size 14");
        val pits = new TreeMap<Integer, Integer>();
        for (int i = 0; i < list.size(); i++) {
            pits.put(i + 1, list.get(i));
        }
        return new Pits(pits);
    }

}
