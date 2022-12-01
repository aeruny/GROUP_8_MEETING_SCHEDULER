import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class SchedulePanel extends JPanel {


    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(400 - 2, 500 - 29);

    private ArrayList<ArrayList<Boolean>> scheduleStates;

    public SchedulePanel(ArrayList<ArrayList<Boolean>> schedule) {
        this.scheduleStates = schedule;
        // Time Label
        ImageIcon timeLabelImageIcon = new ImageIcon("time label.png");
        JLabel timeLabel = new JLabel(timeLabelImageIcon);
        JPanel timeLabelPanel = new JPanel();
        timeLabelPanel.add(timeLabel);

        // Table Contents
        String[] columnNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int columnCount = 28;
        Object[][] data = new Object[columnCount][];
        for(int i = 0; i < columnCount; i++) {
            data[i] = new Object[]{"", "", "", "", ""};
        }


        // Instantiate schedule table
        JTable scheduleTable = new JTable(data, columnNames);
        scheduleTable.setDefaultEditor(Object.class, null);
        scheduleTable.setDefaultRenderer(Object.class, createScheduleTableRenderer());


        // Wrap schedule table with JScrollPane (to show the table column names)
        JScrollPane tablePane = new JScrollPane(scheduleTable);
        tablePane.setMinimumSize(SCHEDULE_PANEL_SIZE);
        tablePane.setPreferredSize(SCHEDULE_PANEL_SIZE);
        tablePane.setMaximumSize(SCHEDULE_PANEL_SIZE);

        this.add(timeLabelPanel);
        this.add(tablePane);
        this.repaint();
    }

    private DefaultTableCellRenderer createScheduleTableRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(scheduleStates.get(column).get(row))
                    c.setBackground(Color.green);
                else
                    c.setBackground(Color.RED);
                return c;
            }
        };
    }
    public void updateSchedule(ArrayList<ArrayList<Boolean>> schedule) {
        this.scheduleStates = schedule;
        this.repaint();
    }
}
