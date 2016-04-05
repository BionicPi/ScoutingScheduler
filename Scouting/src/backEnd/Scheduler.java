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
	public ArrayList<Integer> teamNumbers;
	
	public Scheduler()
	{
		matchList = new ArrayList<Match>();
		scouters = new ArrayList<Scouter>();
		teamList = new ArrayList<Team>();
		teamNumbers = new ArrayList<Integer>();
	}

	
	private class Match
	{
		public Team blue1, blue2, blue3, red1, red2, red3;
		public int blueInt1, blueInt2, blueInt3, redInt1, redInt2, redInt3;
		public ArrayList<Team> teams;
		public ArrayList<Integer> numbers;
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
			
			blueInt1 = blue1.getTeamNumber();
			blueInt2 = blue2.getTeamNumber();
			blueInt3 = blue3.getTeamNumber();
			redInt1 = red1.getTeamNumber();
			redInt2 = red2.getTeamNumber();
			redInt3 = red3.getTeamNumber();

			
			teams = new ArrayList<Team>();
			teams.add(blue1);
			teams.add(blue2);
			teams.add(blue3);
			teams.add(red1);
			teams.add(red2);
			teams.add(red3);
			
			numbers = new ArrayList<Integer>();
			numbers.add(blueInt1);
			numbers.add(blueInt2);
			numbers.add(blueInt3);
			numbers.add(redInt1);
			numbers.add(redInt2);
			numbers.add(redInt3);
		}
		
		public Match(int matchNumber, int bi1, int bi2, int bi3, int ri1, int ri2, int ri3)
		{
			number = matchNumber;
			
			blueInt1 = bi1;
			blueInt2 = bi2;
			blueInt3 = bi3;
			redInt1 = ri1;
			redInt2 = ri2;
			redInt3 = ri3;
			
			numbers = new ArrayList<Integer>();
			numbers.add(blueInt1);
			numbers.add(blueInt2);
			numbers.add(blueInt3);
			numbers.add(redInt1);
			numbers.add(redInt2);
			numbers.add(redInt3);
		}
		
		
		public int getMatchNumber() 
		{
			return number;
		}
		
		public void getTeamsFromNumbers()
		{
			teams = new ArrayList<Team>();
			for(int i: numbers)
			{
				Team t = new Team(-1);
				for(Team tl: teamList)
					if(tl.getTeamNumber() == i)
						t=tl;
				if(t.getTeamNumber() != -1)
					teams.add(t);
			}
			
			blue1 = teams.get(0);
			blue2 = teams.get(1);
			blue3 = teams.get(2);
			red1 = teams.get(3);
			red2 = teams.get(4);
			red3 = teams.get(5);
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
				for(Match m: t.matches)
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

		public String toString() 
		{
			String s = name + "\nTeams: " + teams + "\n";
			s +="\nMatches: \n";
			for(Match m: matches)
			{
				s += m.getMatchNumber() + " ";
				for(Team t: m.teams)
					if(this.teams.contains(t))
						s += t.getTeamNumber() + " ";
				s += "\n";
			}
			
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
			int b1 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			int b2 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			int b3 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			int r1 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			int r2 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			int r3 = Integer.parseInt(list.substring(i, list.indexOf(",", i)));
			i = list.indexOf(",", i) + 1;
			
//			System.out.println(b1.getTeamNumber() + " " + b2.getTeamNumber() + " " + b3.getTeamNumber() + " " + r1.getTeamNumber() + " " + r2.getTeamNumber() + " " + r3.getTeamNumber() + "");
			
			j++;
			
			matchList.add(new Match(j, b1, b2, b3, r1, r2, r3));
		}
		
		for(Match m: matchList)
			for(int t: m.numbers)
				if(!teamNumbers.contains(t))
					teamNumbers.add(t);
		
		for(int i: teamNumbers)
			teamList.add(new Team(i));
		
		for(Match m: matchList)
			m.getTeamsFromNumbers();
		
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
		
		for(int i = 0; i < teamList.size() - 1; i ++)
			for(int j = i+1; j < teamList.size();)
				if(teamList.get(i).getTeamNumber() == teamList.get(j).getTeamNumber())
					teamList.remove(j);
				else
					j++;
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
			
			//VAHAY.coincidence();
			
//			System.out.println(VAHAY.teamList + "\n" + VAHAY.matchList + "\n" + VAHAY.teamList.size());
			
			VAHAY.addScouter("Brendan/Alyssa");
			VAHAY.addScouter("Clay/Celia");
			VAHAY.addScouter("Ramona/Katie");
			VAHAY.addScouter("Kelly/John");
			VAHAY.addScouter("Daniel/Ricardo");
			VAHAY.addScouter("Sean/Shannon");
			VAHAY.addScouter("Mary/Marielle");
			VAHAY.addScouter("Braidan/Max");
			VAHAY.addScouter("Hannah/Brittany");
			VAHAY.addScouter("Zoe/Emily");
			VAHAY.addScouter("Trent/Alex");
			VAHAY.addScouter("Cori/Meghan");
			VAHAY.addScouter("Rane/FOH");
			VAHAY.addScouter("Ellie/Medeline");
			

			for(int j = 0; j < VAHAY.teamList.size(); j++)
//				if(VAHAY.teamList.get(j).getTeamNumber() != 1111)
					VAHAY.scouters.get(j % VAHAY.scouters.size()).teams.add(VAHAY.teamList.get(j));
			
//			for(Scouter s: VAHAY.scouters)
//				System.out.println(s.teams.size() + " " + s.matches.size());
			
			for(Scouter s: VAHAY.scouters)
				s.filterMatches();
			
//			for(Scouter s: VAHAY.scouters)
//				System.out.println(s.teams.size() + " " + s.matches.size());

			//VAHAY.clearScouterOverlap();
			System.out.println(VAHAY.teamList);
			for(Scouter s: VAHAY.scouters)
				System.out.println(s);

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