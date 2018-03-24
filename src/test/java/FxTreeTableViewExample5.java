import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxTreeTableViewExample5 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the RootNode
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);

		// Create a TreeTableView with model
		TreeTableView<Person> treeTable = new TreeTableView<>(rootNode);
		treeTable.setPrefWidth(400);

		// Create the TreeTableColumns
		TreeTableColumn<Person, String> firstNameColumn = TreeTableUtil.getFirstNameColumn();
		TreeTableColumn<Person, String> lastNameColumn = TreeTableUtil.getLastNameColumn();
		TreeTableColumn<Person, LocalDate> birthDateColumn = TreeTableUtil.getBirthDateColumn();
		TreeTableColumn<Person, Person.AgeCategory> ageCategoryColumn = TreeTableUtil.getAgeCategoryColumn();

		// Add columns to the TreeTableView
		treeTable.getColumns().add(firstNameColumn);
		treeTable.getColumns().add(lastNameColumn);
		treeTable.getColumns().add(birthDateColumn);
		treeTable.getColumns().add(ageCategoryColumn);

		// Turn on multiple-selection mode for the TreeTableView
		TreeTableViewSelectionModel<Person> selection = treeTable.getSelectionModel();
		selection.setSelectionMode(SelectionMode.MULTIPLE);

		// Enable cell-level selection
		selection.setCellSelectionEnabled(true);

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
		stage.setTitle("A TreeTableView with a Selection Model");
		// Display the Stage
		stage.show();
	}
}
