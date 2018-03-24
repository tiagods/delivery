import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxTreeTableViewExample1 extends Application
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
		stage.setTitle("A simple TreeTableView");
		// Display the Stage
		stage.show();
	}
}
