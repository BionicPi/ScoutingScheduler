package backEnd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler 
{
	
	final static String ls = System.getProperty("line.separator");

	
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
			red2 = r2;
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
			this.sortTeams();
			for(Team t: teams)
				for(Match m: matchList)
					if(m.teams.contains(t))
						matches.add(m);
			this.sortMatches();
		}
		
		void sortMatches()
		{
			ArrayList<Match> temp = new ArrayList<Match>();
			
			for(int i = 0; i < matches.size(); i++)
				if(!(temp.size() > 0))
					temp.add(matches.get(i));
				else
					for(int j = 0; j < temp.size();)
						if(temp.get(j).getMatchNumber() > matches.get(i).getMatchNumber())
						{
							temp.add(j, matches.get(i));
							j = temp.size();
						}
						else if(j < temp.size() - 1)
							j++;
						else
						{
							temp.add(matches.get(i));
							j = temp.size();
						}
			
			matches = temp;
		}
		
		void sortTeams()
		{
			ArrayList<Team> temp = new ArrayList<Team>();
			
			for(int i = 0; i < teams.size(); i++)
				if(!(temp.size() > 0))
					temp.add(teams.get(i));
				else
					for(int j = 0; j < temp.size();)
						if(temp.get(j).getTeamNumber() > teams.get(i).getTeamNumber())
						{
							temp.add(j, teams.get(i));
							j = temp.size();
						}
						else if(j < temp.size() - 1)
							j++;
						else
						{
							temp.add(teams.get(i));
							j = temp.size();
						}
			
			teams = temp;
		}

		public String getMatchNumbers() 
		{
			String s = "";
			for(Match m: matches)
				s += m.getMatchNumber() + " ";
			return s;
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
		
//		void sortMatches()
//		{
//			ArrayList<Match> temp = new ArrayList<Match>();
//			
//			for(int i = 0; i < matches.size(); i++)
//				if(!(temp.size() > 0))
//					temp.add(matches.get(i));
//				else
//					for(int j = 0; i < temp.size();)
//						if(temp.get(j).getMatchNumber() > matches.get(i).getMatchNumber())
//						{
//							temp.add(j, matches.get(i));
//							i = temp.size();
//						}
//						else
//							i++;
//			
//			matches = temp;
//		}
		
		public String toString()
		{
			return number + "";
		}
	}
	
	public void coincidence()
	{
		boolean intersect = false;
		
		for(Team t: teamList)
			for(Match m: t.matches)
				for(Team ot: teamList)
				{
					if(ot.matches.contains(m))
						intersect = true;
					if(!intersect)
						System.out.println(t.getTeamNumber() + " " + ot.getTeamNumber());
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
		int j = 0;
//		System.out.println(list.replaceAll(ls, ","));
		list = list.replaceAll(ls, ",");
		for(int i = 0; i < list.length();)
		{
			Team b1 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			Team b2 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			Team b3 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			Team r1 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			Team r2 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			Team r3 = new Team(Integer.parseInt(list.substring(i, list.indexOf(",", i))));
			i = list.indexOf(",", i) + 1;
			
//			System.out.println(b1.getTeamNumber() + " " + b2.getTeamNumber() + " " + b3.getTeamNumber() + " " + r1.getTeamNumber() + " " + r2.getTeamNumber() + " " + r3.getTeamNumber() + "");
			
			j++;
			
			matchList.add(new Match(j, b1, b2, b3, r1, r2, r3));
		}
		
		for(Match m: matchList)
			for(Team t: m.teams)
				if(!teamList.contains(t))
					teamList.add(t);
		
		for(Team t: teamList)
			t.filterMatches();
	}
	
	public void clearScouterOverlap()
	{
		for(int i = 0; i < 5 * scouters.size(); i++)
		{
			scouters.get(i % scouters.size()).sortMatches();
			for(int j = 0; j < scouters.get(i % scouters.size()).matches.size()-1; j++)
				if(scouters.get(i % scouters.size()).matches.get(j).getMatchNumber() == scouters.get(i % scouters.size()).matches.get(j + 1).getMatchNumber())
					scouters.get((i+1) % scouters.size()).matches.add(scouters.get(i % scouters.size()).matches.remove(j + 1));
		}
	}
	
//	void sortMatchList()
//	{
//		ArrayList<Match> temp = new ArrayList<Match>();
//		
//		for(int i = 0; i < matchList.size(); i++)
//			if(!(temp.size() > 0))
//				temp.add(matchList.get(i));
//			else
//				for(int j = 0; i < temp.size();)
//					if(temp.get(j).getMatchNumber() > matchList.get(i).getMatchNumber())
//					{
//						temp.add(j, matchList.get(i));
//						i = temp.size();
//					}
//					else
//						i++;
//		
//		matchList = temp;
//	}
	
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
	
	public void addScouter(String s)
	{
		this.scouters.add(new Scouter(s));
	}
	
	
	
	public static void main(String [] args)
	{
		Scheduler VAHAY = new Scheduler();
		
		try {
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();

			FileReader file = new FileReader("MatchSchedule.csv");
			BufferedReader bFile = new BufferedReader(file);
			
			while((line = bFile.readLine()) != null)
			{
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
//			System.out.println(stringBuilder.toString());

			bFile.close();
			file.close();
			
			

			VAHAY.parseMatchList(stringBuilder.toString());
//			VAHAY.sortMatchList();
			VAHAY.sortTeamList();
//			System.out.println(VAHAY.matchList);
			
			VAHAY.coincidence();
			
			VAHAY.addScouter("Megan/Alex");
			VAHAY.addScouter("Emily");
			VAHAY.addScouter("Alyssa");
			VAHAY.addScouter("Katie");
			VAHAY.addScouter("Maddie");
			VAHAY.addScouter("Ramona");
			VAHAY.addScouter("John");
			VAHAY.addScouter("Sean");
			VAHAY.addScouter("Brendan");
			VAHAY.addScouter("Celia");
			VAHAY.addScouter("Daniel/Ricardo");
			VAHAY.addScouter("Braidan/Max");
			VAHAY.addScouter("Mary/Marielle");
			VAHAY.addScouter("Hannah/Brittany");
			

			for(int j = 0; j < VAHAY.teamList.size(); j++)
				if(VAHAY.teamList.get(j).getTeamNumber() != 1111)
					VAHAY.scouters.get(j%VAHAY.scouters.size()).teams.add(VAHAY.teamList.get(j));
			

			
			for(Scouter s: VAHAY.scouters)
				s.filterMatches();

			VAHAY.clearScouterOverlap();
			
			for(Scouter s: VAHAY.scouters)
				System.out.println(s.name + ls + s.getMatchNumbers());

//			for(Team t: VAHAY.teamList)
//			{
//				System.out.println(t.getTeamNumber());
//				for(Match m: t.matches)
//					System.out.println(m.getMatchNumber());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}