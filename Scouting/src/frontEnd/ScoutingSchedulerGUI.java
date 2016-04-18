package frontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import backEnd.Scheduler;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScoutingSchedulerGUI {

	private JFrame frame;
	private JTable tableMatchSchedule;
	private JTable tableScouterSchedule_Demo;
	private Scheduler scheduler;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPaneMatchSchedule;
	private JScrollPane scrollPaneScouters;
	private JTextArea textAreaScouters;
	private JTabbedPane tabbedPaneScoutingSchedule;
	private JScrollPane scrollPaneDemoScouter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoutingSchedulerGUI window = new ScoutingSchedulerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScoutingSchedulerGUI() {
		scheduler = new Scheduler();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menuRun = new JMenu("Run...");
		menuBar.add(menuRun);
		
		JMenuItem menuItemGenerate = new JMenuItem("Generate Schedule");
		menuItemGenerate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String s = textAreaScouters.getText();
				s.trim() += "/n";
				for(int i = 0; i < s.length();)
				{
					s.
				}
			}
		});
		menuRun.add(menuItemGenerate);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		scrollPaneMatchSchedule = new JScrollPane();
		tabbedPane.addTab("Match Schedule", null, scrollPaneMatchSchedule, null);
		
		tableMatchSchedule = new JTable();
		scrollPaneMatchSchedule.setViewportView(tableMatchSchedule);
		
		scrollPaneScouters = new JScrollPane();
		tabbedPane.addTab("Scouters", null, scrollPaneScouters, null);
		
		textAreaScouters = new JTextArea();
		scrollPaneScouters.setViewportView(textAreaScouters);
		
		tabbedPaneScoutingSchedule = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Scouting Schedule", null, tabbedPaneScoutingSchedule, null);
		
		scrollPaneDemoScouter = new JScrollPane();
		tabbedPaneScoutingSchedule.addTab("Demo Scouter", null, scrollPaneDemoScouter, null);
		
		tableScouterSchedule_Demo = new JTable();
		scrollPaneDemoScouter.setViewportView(tableScouterSchedule_Demo);
	}

}
