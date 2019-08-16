package com.example.data;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jyeary
 */
@Data
@NoArgsConstructor
public class ClassRoom {

    private String name;
    private List<Student> students = new ArrayList<>();

    public int getTotalStudents() {
        return students.size();
    }

    public List<String> getStudentsMisssingGrades() {
        List<String> names = new ArrayList<>();
        // As per requirements we ignore scores of zero.
        students.stream().filter((s) -> (s.getGrade() == 0)).forEachOrdered((s) -> {
            names.add(s.getName());
        }); 
        return names;
    }

    public int getTotalStudentsWithGrades() {
        return students.size() - getStudentsMisssingGrades().size();
    }

    public Double average() {
        Double total = 0.0;
        for (Student s : students) {
            total += s.getGrade();
        }

        return total / getTotalStudentsWithGrades();
    }

}
