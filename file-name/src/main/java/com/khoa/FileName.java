package com.khoa;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileName {

//    public static void main(String[] args){
//        String folderUri = "E:\\Hinh Cuoi Process\\Hinh Cuoi Merged";
//        String removeFromFolderUri = "E:\\Hinh Cuoi Process\\Hinh Cuoi Tien selected";
//        File folder = new File(folderUri);
//        File removeFromFolder = new File(removeFromFolderUri);
//        if(folder.isDirectory() && removeFromFolder.isDirectory()) {
//            List<String> fileNamesToRemove = Stream.of(folder.listFiles()).map(File::getName).collect(Collectors.toList());
//            System.out.println(fileNamesToRemove.size());
//            Map<String, Boolean> isDeleted = new HashMap<>();
//            Stream.of(removeFromFolder.listFiles()).filter(f -> fileNamesToRemove.contains(f.getName()))
//                    .forEach(f -> {
//                        isDeleted.put(f.getName(), f.delete());
//                    });
//            Map<Boolean, List<String>> rs = isDeleted.entrySet().stream()
//                    .collect(Collectors.partitioningBy(entry -> entry.getValue(),
//                            Collectors.mapping(entry -> entry.getKey(), Collectors.toList())));
//
//            List<String> khongTimThay = new ArrayList<>();
//            khongTimThay.addAll(fileNamesToRemove);
//            khongTimThay.removeAll(isDeleted.keySet());
//            System.out.println("Da xoa:" + rs.get(Boolean.TRUE).stream().collect(Collectors.joining(",")));
//            System.out.println("Khong the xoa:" + rs.get(Boolean.FALSE).stream().collect(Collectors.joining(",")));
//            System.out.println("Khong tim thay:" + khongTimThay.stream().collect(Collectors.joining(",")));
//        }
//    }

//    public static void main(String[] args){
//        String folderUri = "E:\\Hinh Cuoi Process\\Hinh Cuoi select";
//        String folderUri2 = "E:\\Hinh Cuoi Process\\Hinh Cuoi Tien selected";
//        String folderMerge = "E:\\Hinh Cuoi Process\\Hinh Cuoi Merged";
//
//        File folder1 = new File(folderUri);
//        File folder2 = new File(folderUri2);
//        File folderMer = new File(folderMerge);
//
//        if(folder1.isDirectory() && folder2.isDirectory()) {
//            List<String> fileNames = Stream.of(folder1.listFiles()).map(File::getName).collect(Collectors.toList());
//            List<File> merged = Stream.of(folder2.listFiles()).filter(f -> fileNames.contains(f.getName()))
//                    .collect(Collectors.toList());
//            System.out.println("merge size:" + merged.size());
//            merged.forEach(f -> {
//                try {
//                    FileUtils.copyFileToDirectory(f, folderMer);
//                } catch (IOException e) {
//                    System.out.println("Khong the copy file:" + f.getName());
//                    e.printStackTrace();
//                }
//            });
//
//        }
//    }

    public static void main(String[] args){
        String folderUri = "E:\\Hinh Cuoi Process\\Hinh Cuoi Merged";
        File folder = new File(folderUri);
        System.out.println(Stream.of(folder.listFiles()).map(File::getName).collect(Collectors.joining(",")));
    }
}
