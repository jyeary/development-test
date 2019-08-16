package com.example;

import com.example.data.ClassRoom;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;
import lombok.NonNull;

/**
 *
 * @author John Yeary
 */
public class ReportService {

    private final List<ClassRoom> classes;

    public ReportService(@NonNull final List<ClassRoom> classes) {
        this.classes = classes;
    }

    public void printReport() {
        Double highestAverage = 0.0;
        String name = "";

        try {
            Path reportPath = Paths.get("target", "report.txt");
            try (Formatter formatter = new Formatter(reportPath.toFile())) {
                for (ClassRoom cr : classes) {
                    formatter.format("--------------------- %1s ---------------------\n", cr.getName());
                    formatter.format("Total Number of Students: %1d\n", cr.getTotalStudents());
                    formatter.format("Number of Students for Calculating Class Average: %1d\n", cr.getTotalStudentsWithGrades());
                    formatter.format("Class Average: %.2f\n", cr.average());
                    
                    if (cr.getStudentsMisssingGrades().size() > 0) {
                        formatter.format("Students Missing Grades:\n");
                        cr.getStudentsMisssingGrades().forEach((s) -> {
                            formatter.format("\t%s\n", s);
                        });
                    }
                    if (cr.average() > highestAverage) {
                        highestAverage = cr.average();
                        name = cr.getName();
                    }
                }
                
                formatter.format("=================================================\n");
                formatter.format("The highest average was in %1s with an average of %.2f", name, highestAverage);
                formatter.flush();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Could not print file output");
        }

    }
}
