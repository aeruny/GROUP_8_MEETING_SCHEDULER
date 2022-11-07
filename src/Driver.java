import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        ArrayList<Student> sample = new ArrayList<>();
        sample.add(new Student("Student 1"));
        sample.add(new Student("Student 2"));
        sample.add(new Student("Student 3"));
        sample.add(new Student("Student 4"));
        sample.add(new Student("Student 5"));
        sample.add(new Student("Student 6"));
        sample.add(new Student("Student 7"));
        sample.add(new Student("Student 8"));
        sample.add(new Student("Student 9"));
        sample.add(new Student("Student 10"));
        sample.add(new Student("Student 11"));
        sample.add(new Student("Student 12"));
        sample.add(new Student("Student 13"));
        sample.add(new Student("Student 14"));
        sample.add(new Student("Student 15"));
        sample.add(new Student("Student 16"));
        Display display = new Display(sample);

    }
}
