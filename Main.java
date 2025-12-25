import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class Main {

    public static void main(String[] args) {

        // MAIN FRAME
        JFrame frame = new JFrame("Student Attendance App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BUTTONS
        JButton addBtn = new JButton("Add Student");
        JButton markBtn = new JButton("Mark Attendance");
        markBtn.addActionListener(e -> {

    JFrame attFrame = new JFrame("Mark Attendance");
    attFrame.setSize(300, 200);

    JLabel rollLabel = new JLabel("Roll No:");
    JTextField rollField = new JTextField(15);

    JButton presentBtn = new JButton("Present");
    JButton absentBtn = new JButton("Absent");

    presentBtn.addActionListener(a -> {
        AttendanceManager.markAttendance(
            rollField.getText(),
            "Unknown",
            "Present"
        );
        JOptionPane.showMessageDialog(attFrame, "Attendance Marked: Present");
    });

    absentBtn.addActionListener(a -> {
        AttendanceManager.markAttendance(
            rollField.getText(),
            "Unknown",
            "Absent"
        );
        JOptionPane.showMessageDialog(attFrame, "Attendance Marked: Absent");
    });

    JPanel panel = new JPanel();
    panel.add(rollLabel);
    panel.add(rollField);
    panel.add(presentBtn);
    panel.add(absentBtn);

    attFrame.add(panel);
    attFrame.setVisible(true);
});

        JButton viewBtn = new JButton("View Attendance");
        viewBtn.addActionListener(e -> {

    JFrame tableFrame = new JFrame("Attendance Table");
    tableFrame.setSize(500, 300);

    String[] columns = {"Roll No", "Name", "Status"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);

    try {
        BufferedReader br = new BufferedReader(new FileReader("attendance.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            model.addRow(data);
        }
        br.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(tableFrame, "No attendance data found");
    }

    JScrollPane scrollPane = new JScrollPane(table);
    tableFrame.add(scrollPane);
    tableFrame.setVisible(true);
});


        // PANEL
        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(markBtn);
        panel.add(viewBtn);

        frame.add(panel);
        frame.setVisible(true);

        // ADD STUDENT BUTTON ACTION
        addBtn.addActionListener(e -> {

            JFrame addFrame = new JFrame("Add Student");
            addFrame.setSize(350, 200);

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(15);

            JLabel rollLabel = new JLabel("Roll No:");
            JTextField rollField = new JTextField(15);

            JButton saveBtn = new JButton("Save");

            JPanel addPanel = new JPanel();
            addPanel.add(nameLabel);
            addPanel.add(nameField);
            addPanel.add(rollLabel);
            addPanel.add(rollField);
            addPanel.add(saveBtn);

            addFrame.add(addPanel);
            addFrame.setVisible(true);

            // SAVE BUTTON ACTION
            saveBtn.addActionListener(ev -> {
                String name = nameField.getText();
                String roll = rollField.getText();

                if (name.isEmpty() || roll.isEmpty()) {
                    JOptionPane.showMessageDialog(addFrame, "Please fill all fields");
                    return;
                }

                try {
                    FileWriter fw = new FileWriter("students.txt", true);
                    fw.write("Name: " + name + ", Roll: " + roll + "\n");
                    fw.close();

                    JOptionPane.showMessageDialog(addFrame,
                            "Student Added Successfully!\nName: " + name + "\nRoll: " + roll);

                    nameField.setText("");
                    rollField.setText("");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(addFrame, "Error saving data");
                }
            });
        });
    }
}
