package com.ahdyds.dsws;

import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Endpoint
public class WhiteTestsReqEndpoint {
    @Autowired
    private DbSimulatorService dbSimulatorService;
    @PayloadRoot(namespace = "http://www.tekup.de/soap/models/whitetest", localPart = "StudentRequest")
    public WhiteTestResponse whiteTestResponse(@RequestPayload StudentRequest request) throws DatatypeConfigurationException {
        WhiteTestResponse response = new WhiteTestResponse();
        Student student = dbSimulatorService.getStudent(request.getStudentId());
        response.setStudent(student);
        // pour que cette ligne puisse travailler
        // j'ai changé le type de date dans xml (solution proposé par IntelliJ IDEA)
        Exam exam = dbSimulatorService.getExam(request.getExamCode());
        //response.setDate(LocalDate.now().plusDays(5));
        LocalDate localDate = LocalDate.now().plusDays(5);

        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        response.setDate(xmlGregorianCalendar);
        response.setExam(exam);
        return response;
    }
    // J'ai essaié de generer la classe ExamListResponse
    // mais l'erreur au niveau de whteTest.xsd ligne 25 m'empêche.
    public ExamListResponse examListResponse(@RequestPayload StudentRequest request){
        ExamListResponse response = new ExamListResponse();
        List<Exam> exams = (List<Exam>) dbSimulatorService.examList();
        for(Exam exam : exams){
            response.setExam(exam);
        }
        return response;
    }
}
