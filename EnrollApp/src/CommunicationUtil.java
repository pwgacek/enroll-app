import org.hibernate.Session;
import test.Student;

import javax.persistence.*;
import java.util.List;


public class CommunicationUtil {
    private static  CommunicationUtil communicationUtil = null;
    private static Session session;
    private static boolean logged = false;
    private static Student loggedStudent;
    private CommunicationUtil(){}

    public static CommunicationUtil getCommunicationUtil(Session session) {
        if(communicationUtil == null){
            communicationUtil = new CommunicationUtil();
            CommunicationUtil.session = session;
        }
        return communicationUtil;
    }

    public void printAllStudentNames(){
        Query q = session.createQuery("from test.Student");
        List<Student> allStudents = q.getResultList();
        for(Student student : allStudents){
            System.out.println(student.getFirstName());
        }

    }

    public  void login(int indexNumber){
        StoredProcedureQuery q = session.createStoredProcedureQuery("contains_student_id")
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .setParameter(1,indexNumber);
        logged = (boolean) q.getSingleResult();
        if(logged){
            TypedQuery<Student> q2 = session.createQuery("from test.Student as student where student.id = '"+ indexNumber+ "'", Student.class);
            loggedStudent = q2.getSingleResult();
            System.out.println("successfully logged in!");
            System.out.println("Hello " + loggedStudent.getFirstName());
        }
        else{
            System.out.println("there is no student with this IndexNumber");
        }

//        try{
//            Students student = q.getSingleResult();
//            System.out.println("logged in!" + student.getFirstName());
//
//        }catch(NoResultException e){
//            System.out.println("wrong password");
//        }
    }




}
