import java.util.ArrayList;

import java.util.concurrent.LinkedTransferQueue;

public class Driver {

    public static void main(String[] args) {
        Interpreter dataReader = new Interpreter();
        ArrayList<Student> sample = dataReader.loadData();
        Display display = new Display(sample);

    }
}
