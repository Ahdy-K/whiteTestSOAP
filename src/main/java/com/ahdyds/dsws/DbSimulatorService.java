package com.ahdyds.dsws;

import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.Student;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class DbSimulatorService {
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Exam> exams = new HashMap<>();
    @PostConstruct
    public void populateDb(){
        // Students
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Ahdy Kéfi");
        s1.setAddress("my address");
        Student s2 = new Student();
        s1.setId(2);
        s1.setName("another student");

        students.put(s1.getName(), s1);
        students.put(s2.getName(), s2);
        // Exams
        Exam aws = new Exam();
        aws.setCode("100");
        aws.setName("AWS Solution Architect");
        Exam oca = new Exam();
        aws.setCode("200");
        aws.setName("OCA Java");

        exams.put(aws.getName(),aws);
        exams.put(oca.getName(),oca);
    }

    public Student getStudent (int studentID){
        String ID =Integer.toString(studentID);
        return students.get(ID);
    }
    public Exam getExam(String examID){
        return exams.get(examID);
    }
    // utilisé dans question 3
    public HashMap<String, Exam> examList(){
        return exams;
    }
}
