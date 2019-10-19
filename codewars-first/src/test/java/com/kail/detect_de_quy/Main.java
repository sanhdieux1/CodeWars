package com.kail.detect_de_quy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    @Test
    public void test() {
        List<String> input = Arrays.asList("1-2", "2-3", "2-4", "1-5", "5-2", "5-1");
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
                                        .childs(new ArrayList<>(Collections.singletonList(pair[1])))
                                        .build())
                ));
        Detetor detetor = Detetor.builder().allNodes(nodes).build();
        System.out.println(detetor.isRecursive());
        System.out.println(detetor.getNodeError());
    }

    @Getter
    @Setter
    @Builder
    public static class Detetor {
        Map<String, Node> allNodes;
        String nodeError;
        boolean isRecursive() {
            AtomicBoolean isRecursive = new AtomicBoolean(false);
            allNodes.values().forEach(node -> {
                List<String> childs = node.getChilds();
                if(childs.contains(node.getRoot())) {
                    isRecursive.set(true);
                    nodeError = node.getRoot();
                    return;
                }
                boolean recursive = childs.stream()
                        .anyMatch(nodeChild -> getChilds(nodeChild).contains(node.getRoot()));
                if(!recursive) {
                    recursive = isChild2(childs, node.getRoot());
                } else {
                    nodeError = node.getRoot();
                }
                isRecursive.set(recursive);
            });
            return isRecursive.get();
        }

        boolean isChild(List<String> childs, String root) {
            if(childs.isEmpty()) {
                return false;
            }
            boolean isChild = false;
            for(String child : childs) {
                List<String> childsOfChild = getChilds(child);
                if(childsOfChild.contains(root)) {
                    nodeError = root;
                    return true;
                } else {
                    isChild = isChild || isChild(childsOfChild, root);
                }
            }
            return isChild;
        }

        boolean isChild2(List<String> childs, String root) {
            LinkedList<String> pool = new LinkedList<>(childs);
            while (!pool.isEmpty()) {
                String child = pool.pop();
                List<String> childsOfChild = getChilds(child);
                if (childsOfChild.contains(root)) {
                    return true;
                } else {
                    pool.addAll(0, childsOfChild);
                }
            }
            return false;
        }

        List<String> getChilds(String key) {
            Node node = allNodes.get(key);
            if(Objects.nonNull(node)) {
                return node.getChilds();
            }
            return Collections.emptyList();
        }

    }

    @Getter
    @Setter
    @Builder
    public static class Node {
        private String root;
        @Delegate
        private List<String> childs;
    }
}
