import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class CourseEnrollment extends JFrame {
    private JTextField nameField, idField, numCoursesField, totalCreditsField;
    private JComboBox<String> programCombo, batchCombo, registrationStatusCombo, bloodGroupCombo;
    private JRadioButton summerRadio, springRadio, autumnRadio, maleRadio, femaleRadio, otherRadio;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public CourseEnrollment() {
        super("Course Enrollment Form");

        // Set the layout and form properties
        setLayout(new GridBagLayout());  // Use GridBagLayout for more control
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(nameField, gbc);

        // ID
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("ID:"), gbc);
        idField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(idField, gbc);

        // Semester Selection (Radio buttons)
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Semester:"), gbc);
        JPanel semesterPanel = new JPanel();
        summerRadio = new JRadioButton("Summer");
        springRadio = new JRadioButton("Spring");
        autumnRadio = new JRadioButton("Autumn");

        ButtonGroup semesterGroup = new ButtonGroup();
        semesterGroup.add(summerRadio);
        semesterGroup.add(springRadio);
        semesterGroup.add(autumnRadio);

        semesterPanel.add(summerRadio);
        semesterPanel.add(springRadio);
        semesterPanel.add(autumnRadio);
        gbc.gridx = 1; gbc.gridy = 2;
        add(semesterPanel, gbc);

        // Program Name
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Program Name:"), gbc);
        programCombo = new JComboBox<>(new String[]{"", "BSc in SWE", "BSc in CSE", "BSc in EEE", "Economics", "English", "LLB"});
        programCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBatchOptions();
            }
        });
        gbc.gridx = 1; gbc.gridy = 3;
        add(programCombo, gbc);

        // Batch Selection
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Batch:"), gbc);
        batchCombo = new JComboBox<>();
        batchCombo.addItem("");  
        gbc.gridx = 1; gbc.gridy = 4;
        add(batchCombo, gbc);

        // Number of Courses
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Number of Courses:"), gbc);
        numCoursesField = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 5;
        add(numCoursesField, gbc);

        // Total Credits
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Total Credits:"), gbc);
        totalCreditsField = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 6;
        add(totalCreditsField, gbc);

        // Registration Status
        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Registration Status:"), gbc);
        registrationStatusCombo = new JComboBox<>(new String[]{"", "Retake", "Supple", "Regular"});
        gbc.gridx = 1; gbc.gridy = 7;
        add(registrationStatusCombo, gbc);

        // Gender Selection (Radio buttons)
        gbc.gridx = 0; gbc.gridy = 8;
        add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new JPanel();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        gbc.gridx = 1; gbc.gridy = 8;
        add(genderPanel, gbc);

        // Blood Group Selection
        gbc.gridx = 0; gbc.gridy = 9;
        add(new JLabel("Blood Group:"), gbc);
        bloodGroupCombo = new JComboBox<>(new String[]{"", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"});
        gbc.gridx = 1; gbc.gridy = 9;
        add(bloodGroupCombo, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 0; gbc.gridy = 10; gbc.gridwidth = 2; // Span across both columns
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitEnrollment();
            }
        });

        // JTable Setup
        String[] columnNames = {"Name", "ID", "Program", "Batch", "Semester", "Num Courses", "Total Credits", "Status", "Gender", "Blood Group"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        
        // Make the JTable larger by setting a preferred size
        dataTable.setPreferredScrollableViewportSize(new Dimension(600, 200));  // Set larger height for the table
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(150);  // Name column width
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(100);  // ID column width
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(150);  // Program column width
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(100);  // Batch column width
        dataTable.getColumnModel().getColumn(4).setPreferredWidth(100);  // Semester column width
        dataTable.getColumnModel().getColumn(5).setPreferredWidth(100);  // Num Courses column width
        dataTable.getColumnModel().getColumn(6).setPreferredWidth(100);  // Total Credits column width
        dataTable.getColumnModel().getColumn(7).setPreferredWidth(150);  // Status column width
        dataTable.getColumnModel().getColumn(8).setPreferredWidth(100);  // Gender column width
        dataTable.getColumnModel().getColumn(9).setPreferredWidth(150);  // Blood Group column width

        gbc.gridx = 0; gbc.gridy = 11; gbc.gridwidth = 2; // Span across both columns for the table
        add(scrollPane, gbc);  // Adding the table scroll pane
    }

    private void updateBatchOptions() {
        String selectedProgram = (String) programCombo.getSelectedItem();
        batchCombo.removeAllItems();
        batchCombo.addItem("");  // Default empty value

        if (selectedProgram == null || selectedProgram.isEmpty()) {
            return;
        }

        switch (selectedProgram) {
            case "BSc in SWE":
                for (int i = 1; i <= 7; i++) {
                    batchCombo.addItem("Batch " + i);
                }
                break;
            case "BSc in CSE":
                for (int i = 55; i <= 61; i++) {
                    batchCombo.addItem("Batch " + i);
                }
                break;
            default:
                for (int i = 48; i <= 53; i++) {
                    batchCombo.addItem("Batch " + i);
                }
                break;
        }
    }

    private void submitEnrollment() {
        String name = nameField.getText();
        String id = idField.getText();
        String semester = summerRadio.isSelected() ? "Summer" :
                          springRadio.isSelected() ? "Spring" : "Autumn";
        String program = (String) programCombo.getSelectedItem();
        String batch = (String) batchCombo.getSelectedItem();
        String numCourses = numCoursesField.getText();
        String totalCredits = totalCreditsField.getText();
        String registrationStatus = (String) registrationStatusCombo.getSelectedItem();
        String gender = maleRadio.isSelected() ? "Male" :
                        femaleRadio.isSelected() ? "Female" : "Other";
        String bloodGroup = (String) bloodGroupCombo.getSelectedItem();

        if (name.isEmpty() || id.isEmpty() || program == null || program.isEmpty() || batch == null || batch.isEmpty() ||
            numCourses.isEmpty() || totalCredits.isEmpty() || bloodGroup.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add data to JTable
        tableModel.addRow(new Object[]{name, id, program, batch, semester, numCourses, totalCredits, registrationStatus, gender, bloodGroup});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseEnrollment form = new CourseEnrollment();
            form.setVisible(true);
        });
    }
}
