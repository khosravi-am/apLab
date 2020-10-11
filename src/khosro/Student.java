package khosro;

/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 *
 * @author khosro
 * @version 0.0
 */
public class Student {
    // the students first name
    private String firstName;
    // the student last name
    private String lastName;
    // the student id
    private String id;
    // the grade
    private int grade;

    /**
     * Create a new student with a given name and ID number.
     *
     * @param fName first name of student
     * @param lName last name of student
     * @param sID   student ID
     */
    public Student(String fName, String lName, String sID) {
        firstName = fName;
        lastName = lName;
        id = sID;
        grade = 0;
    }

    /**
     * get the first name of student * @return firstName field
     */
    public String getFirstName() {
        return firstName;
    }
     public String getLastName() {
        return lastName;
    }

    /**
     * @param fName set first name of a student
     */
    public void setFirstName(String fName) {
        firstName = fName;
    }

    /**
     *
     * @param lName set last name of a student
     */
    public void setLastName(String lName){
        lastName=lName;
    }

    /**
     *
     * @param g set grade of student
     */
    public void setGrade(int g){
        grade=g;
    }

    /**
     *
     * @return grad of student
     */
    public int getGrade() {
        return grade;
    }

    /**
     *
     * @param id set id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Print the student’s last name and ID number to the output terminal.
     */
    public void print() {
        System.out.println("first name: "+ firstName +", last name: "+lastName + ", student ID: " + id + ", grade: " + grade);
    }
}