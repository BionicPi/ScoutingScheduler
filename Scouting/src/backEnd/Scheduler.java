package backEnd;

import java.util.ArrayList;

public class Scheduler 
{
	
	public static ArrayList<Match> matchList = new ArrayList<Match>();
	public static ArrayList<Scouter> scouters = new ArrayList<Scouter>();

	
	private class Match
	{
		public int blue1, blue2, blue3, red1, red2, red3;
		
		public Match(int b1, int b2, int b3, int r1, int r2, int r3)
		{
			blue1 = b1;
			blue2 = b2;
			blue3 = b3;
			red1 = r1;
			red2 = r3;
			red3 = r3;
		}
	}
	
	private class Scouter
	{
		public ArrayList<Integer> matches;
		public ArrayList<Integer> teams;
		public String name;
		
		public Scouter(String named)
		{
			name = named;
			matches = new ArrayList<Integer>();
			teams = new ArrayList<Integer>();
		}
	}
	
	
	
}