import java.io.FileWriter;
import java.io.IOException;

public class AttendanceManager {

    public static void markAttendance(String roll, String name, String status) {
        try {
            FileWriter fw = new FileWriter("attendance.txt", true);
            fw.write(roll + "," + name + "," + status + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
