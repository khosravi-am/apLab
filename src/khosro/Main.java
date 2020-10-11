package khosro;

public class Main {

    public static void main(String[] args) {
        Student std1 = new Student("Ehsan", "Edalat", "9031066");
        Student std2 = new Student("Seyed", "Ahmadpanah", "9031806");
        Student std3 = new Student("Ahmad", "Asadi", "9031054");
        std1.print();
        std1.setGrade(15);
        std1.print();
        std2.print();
        std2.setGrade(11);
        std2.print();
        std3.print();
        std3.setFirstName("HamidReza");
        std3.print();
        System.out.println(std3.getFirstName());
        Lab l=new Lab(10,"sunday");
        Student[] sd=new Student[5];
        sd[0] = new Student("khos","sdf","123");
        sd[1] = new Student("kho2s","sddaf","12453");
        sd[2] = new Student("ks","sf","124513");
        l.setStudents(sd);
        l.enrollStudent(std1);
        l.enrollStudent(std2);

        l.print();

    }
}
