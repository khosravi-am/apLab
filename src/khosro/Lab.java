package khosro;

public class Lab {
    // the students of class
    private Student[] students;
    // average class grads
    private int avg;
    // class day
    private String day;
    // capacity of class
    private int capacity;
    // current size of class
    private int currentSize = 0;

    /**
     * @param cap capacity of laboratory
     * @param d   day of class
     */
    public Lab(int cap, String d) {
        capacity = cap;
        day = d;

    }

    /**
     * adding student to class
     *
     * @param std list of class student
     */
    public void enrollStudent(Student std) {
        if (currentSize < capacity) {
            students[currentSize] = std;
            currentSize++;
        } else {
            System.out.println("Lab is full!!!");
        }
    }

    /**
     * print all data and average of class
     */
    public void print() {
        for (int i = 0; i < students.length; i++) {
            if (students[i]!=null)
            students[i].print();
        }
        System.out.println("Lab AVG: " + getAvg());
    }

    /**
     * @return list of student of class
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * @param students add list of student to class
     */
    public void setStudents(Student[] students) {
        this.students = students;
        capacity = this.students.length;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null)
                currentSize = i-1 ;
            else currentSize = students.length + 1;
        }
        System.out.println(currentSize);
    }

    /**
     * @return average class grads
     */
    public int getAvg() {
        calculateAvg();
        return avg;
    }

    /**
     * calculate average of class
     */
    public void calculateAvg() {
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum = sum + students[i].getGrade();
        }
        avg = sum / students.length;
    }

    /**
     * @return day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day set day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity set capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
