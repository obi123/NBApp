import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is responsible for testing the FullGamesSchedule class.
 * @author obinnaasinugo
 */
public class FullGameScheduleTest {

	FullGameSchedule testSchedule; 
	
	/**
	 * Initialize object for testing. 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Before 
	public void setup() throws MalformedURLException, IOException{
		testSchedule = new FullGameSchedule(); 
	}
	
	/**
	 * Test to see if getSeasonGames() returns a non-empty list of size games for a particular team. 
	 * The total amount of games per season is 82, so test to see if the list is 
	 * of size 82 games for the respective team. 
	 */
	@Test
	public void testGetTeamGames() {
		String team = "Bulls"; // input team name  
		ArrayList<String[]> teamSchedule = testSchedule.getSeasonGames(team); // grab schedule of all the team games 
		
//		for(String[] game : teamSchedule){
//			System.out.println(Arrays.toString(game));
//		}
		
		assertNotNull("List of games cannot be empty", teamSchedule); // test to see if list is not empty or null
		assertEquals(82,teamSchedule.size()); // test if list is of size 82 
	}
	
	/**
	 * Test to see if getGamesOnDay is a non-empty list of games for a particular day and empty for a day 
	 * where no games were played this season or played in a different season year. 
	 */
	@Test
	public void testGetGamesOnDay() {
		String pastDate = "2015-11-11"; // input date where game does not exist for this year's season 
		String currentDate = "2016-11-11"; // input date for games played this season 
		ArrayList<String[]> games = testSchedule.getGamesOnDay(pastDate); // grab schedule of all the team games 
		ArrayList<String[]> currentGames = testSchedule.getGamesOnDay(currentDate); // grab schedule of all the team games 
		
		assertNotNull("List of games cannot be empty", games); // test if list is non-empty 
		assertEquals(pastDate + " does not exist for the current season.", 0, games.size()); // test to see if list size is 0 for date where games weren't played or DNE for calendar season 
		assertEquals("Games were played on " + currentDate, 0, games.size()); // test to see if size of list is greater than 0 
		assertNotNull("List of games cannot be empty", currentGames); // test to see if list is non-empty 	
	}
	
	/**
	 * Test to see if a team plays on a given day and return the game details if so, 
	 * otherwise the array containing game info should be null. 
	 */
	@Test
	public void testGetGame(){
		String team = "Bulls"; // store team of interest
		String date = "2016-12-16"; // Bulls played on this date 
		String notPlayingDate = "2016-12-18"; // Bulls did not play on this date 
		String[] game = testSchedule.getGame(team, date); // store game info 
		String[] noGame = testSchedule.getGame(team, notPlayingDate); // should remain null 
	
		assertNotNull("Team is playing on " + date, game); // test to see if game has info 
		assertNull("Team is not playing on " + notPlayingDate, noGame); // test to see if noGame is null 
		
	}
	
}
