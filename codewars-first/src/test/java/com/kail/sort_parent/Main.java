package com.kail.sort_parent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    
    public static Comparator<String> getComparator(Map<String, Node> nodes) {
        return (v1, v2) -> {
            List<String> parentsV1 = getAllParents(v1, nodes);
            if(parentsV1.contains(v2)) {
                return 1;
            }
            List<String> parentsV2 = getAllParents(v2, nodes);
            if(parentsV2.contains(v1)) {
                return -1;
            }
            return 0;
        };
    }
    @Test
    public void test() {
        List<String> input = Arrays.asList("CR2-PR2", "");
        List<String> listItem = input.stream()
                .map(s -> s.split("-"))
                .map(Stream::of)
                .flatMap(Function.identity())
                .distinct()
                .collect(Collectors.toList());
        listItem.addAll(Arrays.asList("10", "5", "6"));
        Map<String, Node> nodes = new HashMap<>();
        input.stream()
                .map(s -> s.split("-"))
                .forEach(pair -> nodes.compute(pair[0],
                        (key, value) -> Optional.ofNullable(value)
                                .map(i -> {
                                    i.add(pair[1]);
                                    return i;
                                })
                                .orElse(Node.builder().root(key)
                                        .parents(new ArrayList<>(Collections.singletonList(pair[1])))
                                        .build())
                ));
    
        listItem.sort(getComparator(nodes));
        listItem.forEach(System.out::println);
    }
    
    private static List<String> getAllParents(String key, Map<String, Node> allNodes) {
        List<String> result = new ArrayList<>();
        Node node = allNodes.get(key);
        if(Objects.nonNull(node)) {
            List<String> parents = node.getParents();
            result.addAll(parents);
            parents.forEach(v -> result.addAll(getAllParents(v, allNodes)));
        }
        return result;
    }

    @Getter
    @Setter
    @Builder
    public static class Node {
        private String root;
        @Delegate
        private List<String> parents;
    }
}
