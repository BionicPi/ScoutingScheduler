package frontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import backEnd.Scheduler;

public class ScoutingSchedulerGUI {

	private JFrame frame;
	private Scheduler scheduler;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPaneMatchSchedule;
	private JScrollPane scrollPaneScouters;
	private JTextArea textAreaScouters;
	private JTabbedPane tabbedPaneScoutingSchedule;
	private TextArea textAreaMatchSchedule;


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
			public void mousePressed(MouseEvent arg0) 
			{
				try
				{
					scheduler = new Scheduler();
					tabbedPaneScoutingSchedule = new JTabbedPane(JTabbedPane.TOP);
					
					String s = textAreaScouters.getText();
					s = s.trim() + "\n";
					for(int i = 0; i < s.length(); i = s.indexOf("\n", i) + 1)
					{
						scheduler.addScouter(s.substring(i, s.indexOf("\n", i)));
					}
					s = textAreaMatchSchedule.getText();
					s = s.trim() + "\n";
					s = s.replace("\n", ",");
					scheduler.parseMatchList(s);
					scheduler.sortTeamList();
					for(int j = 0; j < scheduler.teamList.size(); j++)
						scheduler.scouters.get(j % scheduler.scouters.size()).teams.add(scheduler.teamList.get(j));
					for(Scheduler.Scouter scout: scheduler.scouters)
					{
						scout.filterMatches();
						JTextArea c = new JTextArea();
						c.setName(scout.name);
						c.setText(scout.toString());
						JScrollPane sp = new JScrollPane();
						sp.setViewportView(c);
						tabbedPaneScoutingSchedule.addTab(c.getName(), null, sp, null);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		menuRun.add(menuItemGenerate);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		scrollPaneMatchSchedule = new JScrollPane();
		tabbedPane.addTab("Match Schedule", null, scrollPaneMatchSchedule, null);

		textAreaMatchSchedule = new TextArea();
		textAreaMatchSchedule.setText("Use the following pattern." + "\n" + "r/b doesn't matter, as long as the same alliance has the same tag.\nr1,r2,r3,b1,b2,b3\nr1,r2,r3,b1,b2,b3");
		scrollPaneMatchSchedule.setViewportView(textAreaMatchSchedule);

		scrollPaneScouters = new JScrollPane();
		tabbedPane.addTab("Scouters", null, scrollPaneScouters, null);

		textAreaScouters = new JTextArea();
		scrollPaneScouters.setViewportView(textAreaScouters);

		tabbedPaneScoutingSchedule = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Scouting Schedule", null, tabbedPaneScoutingSchedule, null);
	}

}
