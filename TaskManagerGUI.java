import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerGUI {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private TaskManager taskManager;

    public TaskManagerGUI() {
        taskManager = new TaskManager();
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField taskField = new JTextField(20);
        panel.add(taskField);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskField.getText();
                if (!taskName.isEmpty()) {
                    taskManager.addTask(taskName);
                    updateTaskList();
                    taskField.setText("");
                }
            }
        });
        panel.add(addButton);

        JButton completeButton = new JButton("Complete Task");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    taskManager.markTaskAsCompleted(index);
                    updateTaskList();
                }
            }
        });
        panel.add(completeButton);

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    taskManager.removeTask(index);
                    updateTaskList();
                }
            }
        });
        panel.add(removeButton);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerGUI();
            }
        });
    }
}
