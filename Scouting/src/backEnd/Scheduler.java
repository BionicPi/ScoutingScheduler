package backEnd;

import java.util.ArrayList;

public class Scheduler 
{
	
	public ArrayList<Match> matchList;
	public ArrayList<Scouter> scouters;
	public ArrayList<Team> teams;
	
	public Scheduler()
	{
		matchList = new ArrayList<Match>();
		scouters = new ArrayList<Scouter>();
		teams = new ArrayList<Team>();
	}
	
	public void test()
	{
		teams.add(new Team(234));
		
//		Team 234 ;//= new Team(234);
//		Team 435= new Team(435);
//		Team 1225= new Team(1225);
//		Team 1501= new Team(1501);
//		Team 1533= new Team(1533);
//		Team 2640= new Team(2640);
//		Team 2642= new Team(2642);
		//Team 2655= new Team(2655),Team 2682= new Team(2682),Team 3196= new Team(3196),Team 3215= new Team(3215),Team 3331= new Team(3331),Team 3336= new Team(3336),Team 3402= new Team(3402),Team 3506= new Team(3506),Team 3680= new Team(3680),Team 3822= new Team(3822),Team 3845= new Team(3845),Team 3971= new Team(3971),Team 4290= new Team(4290),Team 4561= new Team(4561),Team 4767= new Team(4767),Team 4829= new Team(4829),Team 4935= new Team(4935),Team 5188= new Team(5188),Team 5511= new Team(5511),Team 5679= new Team(5679),Team 5727= new Team(5727);
//		public Team 5854 = new Team(5854);
//		public Team 6004= new Team(6004);
	}

	
	private class Match
	{
		public Team blue1, blue2, blue3, red1, red2, red3;
		public ArrayList<Team> teams;
		private int number;
		
		public Match(int matchNumber, Team b1, Team b2, Team b3, Team r1, Team r2, Team r3)
		{
			number = matchNumber;
			
			blue1 = b1;
			blue2 = b2;
			blue3 = b3;
			red1 = r1;
			red2 = r3;
			red3 = r3;
			
			teams = new ArrayList<Team>();
			teams.add(blue1);
			teams.add(blue2);
			teams.add(blue3);
			teams.add(red1);
			teams.add(red2);
			teams.add(red3);
		}
		
		
		public int getMatchNumber() 
		{
			return number;
		}
	}
	
	private class Scouter
	{
		public ArrayList<Match> matches;
		public ArrayList<Team> teams;
		public String name;
		
		public Scouter(String named)
		{
			name = named;
			matches = new ArrayList<Match>();
			teams = new ArrayList<Team>();
		}
		
		void filterMatches()
		{
			for(Team t: teams)
				for(Match m: matchList)
					if(m.teams.contains(t))
						matches.add(m);
		}
	}
	
	public class Team
	{
		public ArrayList<Match> matches;
		private int number;
		
		public Team(int teamNumber)
		{
			number = teamNumber;
		}
		
		void filterMatches()
		{
			for(Match m:matchList)
				if(m.teams.contains(this))
					matches.add(m);
		}
		
		public int getTeamNumber()
		{
			return number;
		}
	}
	
	public static void coincidence()
	{
		boolean intersect = false;
		
		for(int i = 0; i < teams.size() - 1; i++)
			for(int j = i + 1; j<teams.size(); j++)
			{
				for(Match m: teams.get(i).matches)
					if(teams.get(j).matches.contains(m))
						intersect = true;
				if(!intersect)
					System.out.println(teams.get(i).getTeamNumber() + " " + teams.get(j).getTeamNumber());
			}				
	}
	
	public static void main(String [] args)
	{
			}
}