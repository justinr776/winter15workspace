import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class LRUSimGUI {

	private JFrame mainFrame;
	private JTable table;
	private JLabel lblProcessAccessing, lblProcess;
	private LRUSim sim = new LRUSim("input.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LRUSimGUI window = new LRUSimGUI();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LRUSimGUI() {
		initialize();
	}

	// TODO: have this update the display fully
	public void updateDisplay() {
		lblProcessAccessing.setText("Process #" + sim.getpID()
				+ " accessing the new Page #" + sim.getPageRef());
		lblProcess.setText("Process #" + sim.getpID() + "'s Page Table");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 700, 550);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		mainFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton btnNext = new JButton("Run Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sim.runNextLine();
				updateDisplay();
			}
		});
		buttonPanel.add(btnNext);

		JButton btnNextFault = new JButton("Run to Next Fault");
		btnNextFault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sim.runTillNextFault();
				updateDisplay();
			}
		});
		buttonPanel.add(btnNextFault);

		JButton btnRunToEnd = new JButton("Run to End");
		btnRunToEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sim.runTillEnd();
				updateDisplay();
			}
		});
		buttonPanel.add(btnRunToEnd);

		/* North Panel for displaying current process accessing info */
		JPanel panel = new JPanel();
		mainFrame.getContentPane().add(panel, BorderLayout.NORTH);

		lblProcessAccessing = new JLabel("Process #" + sim.getpID()
				+ " accessing Page #" + sim.getPageRef());
		panel.add(lblProcessAccessing);

		JPanel panel_3 = new JPanel();
		mainFrame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("182px"), ColumnSpec.decode("182px"),
				ColumnSpec.decode("182px"), }, new RowSpec[] { RowSpec
				.decode("455px"), }));

		lblProcess = new JLabel("Process #" + sim.getpID() + "'s Page Table");
		panel_3.add(lblProcess, "1, 1, fill, center");

		JLabel lblPhysicalMemory = new JLabel("Physical Memory");
		panel_3.add(lblPhysicalMemory, "2, 1, center, top");

		String[] columnNames = { "Frame #", "ProcID", "Page #" };
		Object[][] data = { { "Test", "this", "page" },
				{ "testing", new Integer(1), "page 1" } };
		table = new JTable(data, columnNames);

		panel_3.add(table, "2, 1, fill, center");
	}
}
