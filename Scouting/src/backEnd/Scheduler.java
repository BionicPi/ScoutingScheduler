package backEnd;

import java.util.ArrayList;

public class Scheduler 
{
	
	public ArrayList<Match> matchList;
	public ArrayList<Scouter> scouters;
	public ArrayList<Team> teamList;
	
	public Scheduler()
	{
		matchList = new ArrayList<Match>();
		scouters = new ArrayList<Scouter>();
		teamList = new ArrayList<Team>();
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
		
		public String toString()
		{
			return number + " " + blue1 + " " + blue2 + " " + blue3 + " " + red1 + " " + red2 + " " + red3 + " ";
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
		
		void sortMatches()
		{
			ArrayList<Match> temp = new ArrayList<Match>();
			
			for(int i = 0; i < matches.size(); i++)
				if(!(temp.size() > 0))
					temp.add(matches.get(i));
				else
					for(int j = 0; i < temp.size();)
						if(temp.get(j).getMatchNumber() > matches.get(i).getMatchNumber())
						{
							temp.add(j, matches.get(i));
							i = temp.size();
						}
						else
							i++;
			
			matches = temp;
		}
		
		void sortTeams()
		{
			ArrayList<Team> temp = new ArrayList<Team>();
			
			for(int i = 0; i < teams.size(); i++)
				if(!(temp.size() > 0))
					temp.add(teams.get(i));
				else
					for(int j = 0; i < temp.size();)
						if(temp.get(j).getTeamNumber() > teams.get(i).getTeamNumber())
						{
							temp.add(j, teams.get(i));
							i = temp.size();
						}
						else
							i++;
			
			teams = temp;
		}
	}
	
	public class Team
	{
		public ArrayList<Match> matches;
		private int number;
		
		public Team(int teamNumber)
		{
			number = teamNumber;
			matches = new ArrayList<Match>();
		}
		
		void filterMatches()
		{
			for(Match m: matchList)
				if(m.teams.contains(this))
					matches.add(m);
		}
		
		public int getTeamNumber()
		{
			return number;
		}
		
		void sortMatches()
		{
			ArrayList<Match> temp = new ArrayList<Match>();
			
			for(int i = 0; i < matches.size(); i++)
				if(!(temp.size() > 0))
					temp.add(matches.get(i));
				else
					for(int j = 0; i < temp.size();)
						if(temp.get(j).getMatchNumber() > matches.get(i).getMatchNumber())
						{
							temp.add(j, matches.get(i));
							i = temp.size();
						}
						else
							i++;
			
			matches = temp;
		}
		
		public String toString()
		{
			return number + "";
		}
	}
	
	public void coincidence()
	{
		boolean intersect = false;
		
		for(int i = 0; i < teamList.size() - 1; i++)
			for(int j = i + 1; j<teamList.size(); j++)
			{
				for(Match m: teamList.get(i).matches)
					if(teamList.get(j).matches.contains(m))
						intersect = true;
				if(!intersect)
					System.out.println(teamList.get(i).getTeamNumber() + " " + teamList.get(j).getTeamNumber());
			}				
	}
	
	public void parseTeamList(String list)
	{
		ArrayList<String> parsed = new ArrayList<String>();
		
		for(int i = 0; i < list.length(); i = list.indexOf(" ", i) + 1)
			parsed.add(list.substring(i, list.indexOf(" ", i)));
		
		for(String s: parsed)
			teamList.add(new Team(Integer.parseInt(s)));
	}
	
	public void parseMatchList(String list)
	{		
		for(int i = 0; i < list.length();)
		{
			int number = Integer.parseInt(list.substring(i, list.indexOf(" ", i)));
			i = list.indexOf(" ", i) + 1;
			Team b1 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			Team b2 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			Team b3 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			Team r1 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			Team r2 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			Team r3 = new Team(Integer.parseInt(list.substring(i, list.indexOf(" ", i))));
			i = list.indexOf(" ", i) + 1;
			
			matchList.add(new Match(number, b1, b2, b3, r1, r2, r3));
		}
		
		for(Match m: matchList)
			for(Team t: m.teams)
				if(!teamList.contains(t))
					teamList.add(t);
		
		for(Team t: teamList)
			t.filterMatches();
	}
	
	void sortMatchList()
	{
		ArrayList<Match> temp = new ArrayList<Match>();
		
		for(int i = 0; i < matchList.size(); i++)
			if(!(temp.size() > 0))
				temp.add(matchList.get(i));
			else
				for(int j = 0; i < temp.size();)
					if(temp.get(j).getMatchNumber() > matchList.get(i).getMatchNumber())
					{
						temp.add(j, matchList.get(i));
						i = temp.size();
					}
					else
						i++;
		
		matchList = temp;
	}
	
	void sortTeamList()
	{
		ArrayList<Team> temp = new ArrayList<Team>();
		
		for(int i = 0; i < teamList.size(); i++)
			if(!(temp.size() > 0))
				temp.add(teamList.get(i));
			else
				for(int j = 0; j < temp.size();)
					if(temp.get(j).getTeamNumber() > teamList.get(i).getTeamNumber())
					{
						temp.add(j, teamList.get(i));
						j = temp.size();
					}
					else if(j < temp.size() - 1)
						j++;
					else
					{
						temp.add(teamList.get(i));
						j = temp.size();
					}
		
		teamList = temp;
	}
	
	
	
	public static void main(String [] args)
	{
		Scheduler VAHAY = new Scheduler();
		
		VAHAY.parseMatchList("1 539 6236 5549 619 2068 612 2 888 614 3274 3373 5587 1123 3 2186 5338 2363 1719 5243 2911 4 4472 4505 4242 6189 3872 611 ");
		VAHAY.sortMatchList();
		VAHAY.sortTeamList();
		
		for(Team t: VAHAY.teamList)
		{
			System.out.println(t.getTeamNumber());
			for(Match m: t.matches)
				System.out.println(m.getMatchNumber());
		}
	}
}