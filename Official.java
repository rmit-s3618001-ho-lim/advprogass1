import java.util.Arrays;
import java.util.Comparator;

public class Official extends Participant {

	/* official constructors */
	public Official(String IDs, String types, String names, int ages, String states) {
		super.ID = IDs;
		super.name = names;
		super.age = ages;
		super.state = states;
		super.type = types;
	}

	public Official() {
	}


	/* print out the official in current game details */
	public String print() {
		String offID = getID();
		String offName = getName();
		int offAge = getAge();
		String offState = getState();
		String data = String.format("ID: %-15s \t Name: %-35s \t Age: %-15s \t State: %s", offID, offName, offAge, offState);
		return data;
	}

	/*
	 * method to choose three winners based on the participants' race time for
	 * that game. the three winners are then given points based on their
	 * performance
	 */
	public void summariseScore(Athletes[] competitors, Game gam) {
		int min = Integer.MAX_VALUE;
		Athletes winner1 = null;
		Athletes winner2 = null;
		Athletes winner3 = null;
		/* choosing the first winner and giving him his points */
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					min = competitors[i].getTime();
					winner1 = competitors[i];

				}
			}
		}
		gam.setWinner1(winner1);
		winner1.setPoints(5);

		/* choosing the second winner and giving him his points */
		min = Integer.MAX_VALUE;
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					if (competitors[i] != winner1) {
						min = competitors[i].getTime();
						winner2 = competitors[i];
					}
				}
			}
		}
		gam.setWinner2(winner2);
		winner2.setPoints(3);

		/* choosing the last winner and giving him his points */
		min = Integer.MAX_VALUE;
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					if (competitors[i] != winner1 && competitors[i] != winner2) {
						min = competitors[i].getTime();
						winner3 = competitors[i];
					}
				}
			}
		}
		gam.setWinner3(winner3);
		winner3.setPoints(1);	
		
		sortCompetitors(competitors);
	}
	
	public String currentGamePrint(Game gam){
		String data = String.format("Game ID: %-25s \t Official: %s ", gam.getGameID(), gam.getOfficial().getName());
		return data;
	}
	
	public void sortCompetitors(Athletes[] competitors){
		/*sorting the competitors according to their time */
		Arrays.sort(competitors, new Comparator<Athletes>(){
			public int compare(Athletes ath1, Athletes ath2) {
				if (ath1 == null && ath2 == null) {
	                return 0;
	            }
	            if (ath1 == null) {
	                return 1;
	            }
	            if (ath2 == null) {
	                return -1;
	            }
	            return ath1.compareTo(ath2);
			}});
	}
	
	public String printResults(Athletes competitor, Game gam){
		/*printing out game results */
		Athletes winner1 = gam.getWinner1();
		Athletes winner2 = gam.getWinner2();
		Athletes winner3 = gam.getWinner3();
		
				if(competitor == winner1){
					String data = (String.format(
							"ID: %-15s \t Name: %-25s \t Time: %-4d seconds \t Points earned: 5", competitor.getID(),
							competitor.getName(), competitor.getTime()));
					return data;
				}else if(competitor == winner2){
					String data = (String.format(
							"ID: %-15s \t Name: %-25s \t Time: %-4d seconds \t Points earned: 3", competitor.getID(),
							competitor.getName(), competitor.getTime()));
					return data;
				}else if(competitor == winner3){
					String data = (String.format(
							"ID: %-15s \t Name: %-25s \t Time: %-4d seconds \t Points earned: 1", competitor.getID(),
							competitor.getName(), competitor.getTime()));
					return data;
				}
					String data = (String.format(
							"ID: %-15s \t Name: %-25s \t Time: %-4d seconds \t Points earned: 0", competitor.getID(),
							competitor.getName(), competitor.getTime()));
					return data;
				
				
			}
		   
		

}
