package com.example;

import com.example.utils.ClassCSVReader;
import com.example.data.ClassRoom;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author jyeary
 */
public class Main {

    public static void main(String[] args) {
        Path p = Paths.get("src", "main", "resources");
        ClassCSVReader ccsvr = new ClassCSVReader();
        List<ClassRoom> classes =  ccsvr.load(p);
        ReportService rs = new ReportService(classes);
        rs.printReport();
    }
}
