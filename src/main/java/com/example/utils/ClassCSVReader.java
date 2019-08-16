package com.example.utils;

import com.example.data.ClassRoom;
import com.example.data.Student;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author jyeary
 */
public class ClassCSVReader {

    private final List<ClassRoom> classes = new ArrayList<>();

    public List<ClassRoom> load(final Path directory) {

        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory);

            for (Path p : directoryStream) {

                ClassRoom cr = new ClassRoom();
                cr.setName(p.toFile().getName().split(".csv")[0]);

                try (Reader in = new FileReader(p.toFile())) {
                    Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().withSkipHeaderRecord().parse(in);
                    for (CSVRecord record : records) {
                        String name = record.get(0);
                        String grade = record.get(1);
                        // Strip the precision as per requirements.
                        int score = (int) Double.parseDouble(grade);
                        cr.getStudents().add(new Student(name, score));
                    }
                }
                classes.add(cr);
            }
        } catch (IOException ex) {
            System.err.println("Exception occurred while reading files.");
        }
        return classes;
    }

}
