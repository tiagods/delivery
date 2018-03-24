import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxTreeTableViewExample2 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create a TreeTableView with model
		TreeTableView<Person> treeTable = new TreeTableView<>();
		treeTable.setPrefWidth(400);

		// Create three columns
		TreeTableColumn<Person, String> firstNameCol = new TreeTableColumn<>("First Name");
		TreeTableColumn<Person, String> lastNameCol = new TreeTableColumn<>("Last Name");
		TreeTableColumn<Person, String> birthDateCol = new TreeTableColumn<>("Birth Date");

		// Add columns to the TreeTableView
		treeTable.getColumns().add(firstNameCol);
		treeTable.getColumns().add(lastNameCol);
		treeTable.getColumns().add(birthDateCol);

		// Create the VBox
		VBox root = new VBox(treeTable);

		// Set the Style-properties of the VBox
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");

		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title
		stage.setTitle("A simple TreeTableView with Colums");
		// Display the Stage
		stage.show();
	}
}
