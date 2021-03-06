import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * This class will control the Cumualtive Stats Panel
 * @author chimezie
 *
 */
public class TeamStandingsController implements Initializable{

	@FXML private TableView<Team> top5;
	@FXML private TableColumn<Team, String> name;
	@FXML private ImageView ivBack; 
	private ArrayList<Team> leagueStandings;
	private TeamStandings stats;
	private String username ;
	private String favoritePlayer; 
	private String favoriteTeam;  
	private LoginModel loginModel = new LoginModel(); 

	public TeamStandingsController() throws MalformedURLException, IOException, SQLException {
		stats = new TeamStandings();
		leagueStandings = new ArrayList<Team>();
		username = loginModel.getUsername(); 
		favoritePlayer = loginModel.getPlayer(username); 
		favoriteTeam = loginModel.getTeam(username);
	}

	public ArrayList<Team> getLeagueStandings(){
		String[] temp = stats.getTeamRankings();
		

		for(int i=0; i< temp.length; i++){
			
			leagueStandings.add(new Team(temp[i]) );
		}

		return leagueStandings;
	}


	public void back(ActionEvent event) throws IOException{
		Stage primaryStage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

		// set title of the stage
		primaryStage.setTitle("Select Category");
		FXMLLoader loaderStandings = new FXMLLoader(getClass().getResource("SelectCategory.fxml"));
		Parent root = loaderStandings.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}


	/**
	* populates the table view with the block information
	* @param event
	*/
	public void leaguestandings(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("TeamStandings.fxml"));
		Scene scene = new Scene(root);
		top5.setEditable(true);
		name.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		top5.getItems().setAll(getLeagueStandings());
	}

  /**
  * A class to make a team object with name and team stats
  */
	public static class Team {

		private final SimpleStringProperty name;

		private Team(String name) {
			this.name = new SimpleStringProperty(name);
		}

		public String getName() {
			return name.get();
		}
	}

  // initializes table_view
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 top5.setEditable(true);
		 name.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		 top5.getItems().setAll(getLeagueStandings());
		 File imageName = new File("resources/" + favoriteTeam + ".jpg"); 
		 Image image = new Image(imageName.toURI().toString());
		 // simple displays ImageView the image as is
		 ivBack.setImage(image);
	}
}
