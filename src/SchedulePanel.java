import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;

public class SchedulePanel extends JPanel {

    // Cell States
    private final int NONE = 0;
    private final int OCCUPIED = 1;
    private final int FREE = 2;


    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(400 - 2, 500 - 29);

    private final JTable scheduleTable;
    private int[][] scheduleStates;

    public SchedulePanel() {
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
        scheduleStates = new int[28][5];


        // Instantiate schedule table
        this.scheduleTable = new JTable(data, columnNames);
        scheduleTable.setDefaultEditor(Object.class, null);
        scheduleTable.setDefaultRenderer(Object.class, createScheduleTableRenderer());


        // Wrap schedule table with JScrollPane (to show the table column names)
        JScrollPane tablePane = new JScrollPane(scheduleTable);
        tablePane.setMinimumSize(SCHEDULE_PANEL_SIZE);
        tablePane.setPreferredSize(SCHEDULE_PANEL_SIZE);
        tablePane.setMaximumSize(SCHEDULE_PANEL_SIZE);

        this.add(timeLabelPanel);
        this.add(tablePane);
    }

    private DefaultTableCellRenderer createScheduleTableRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                TableModel model = table.getModel();
                switch (scheduleStates[row][column]) {
                    case NONE -> c.setBackground(Color.white);
                    case OCCUPIED -> c.setBackground(Color.RED.darker());
                    case FREE -> c.setBackground(Color.green);
                }
                return c;
            }
        };
    }

    public void setScheduleStates(int[][] scheduleStates) {
        this.scheduleStates = scheduleStates;
    }
    public void updateSchedule(ArrayList<Time> scheduleList) {
    }
}
