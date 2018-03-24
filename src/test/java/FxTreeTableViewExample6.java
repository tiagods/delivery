import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FxTreeTableViewExample6 extends Application
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

		// Create a TreeTableView with a model
		TreeTableView<Person> treeTable = new TreeTableView<Person>(rootNode);
		treeTable.setPrefWidth(400);

		// Must make the TreeTableView editable
		treeTable.setEditable(true);

		// Create Columns with Cell Factories
		TreeTableColumn<Person, String> firstNameColumn = TreeTableUtil.getFirstNameColumn();
		firstNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lastNameColumn = TreeTableUtil.getLastNameColumn();
		lastNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateColumn = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();

		birthDateColumn.setCellFactory(TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));

		// Add Columns to the Tree
		treeTable.getColumns().add(firstNameColumn);
		treeTable.getColumns().add(lastNameColumn);
		treeTable.getColumns().add(birthDateColumn);

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
		stage.setTitle("Editing Data in a TreeTableView");
		// Display the Stage
		stage.show();
	}
}
