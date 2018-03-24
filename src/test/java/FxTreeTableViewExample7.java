import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FxTreeTableViewExample7 extends Application
{
	// Create the TreeTableView
	private final TreeTableView<Person> treeTable = new TreeTableView<>();
	// Create a TextArea
	private final TextArea textarea = new TextArea();


	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the Root Node
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);

		// Set the Properties of the Root Node
		treeTable.setRoot(rootNode);
		treeTable.setPrefWidth(400);
		treeTable.setEditable(true);
		treeTable.getSelectionModel().selectFirst();

		// Create Columns with Cell Factories
		TreeTableColumn<Person, String> firstNameColumn = TreeTableUtil.getFirstNameColumn();
		firstNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lastNameColumn = TreeTableUtil.getLastNameColumn();
		lastNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateColumn = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateColumn.setCellFactory(TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));

		// Add Columns to The TreeTableView
		treeTable.getColumns().add(firstNameColumn);
		treeTable.getColumns().add(lastNameColumn);
		treeTable.getColumns().add(birthDateColumn);

		// Add a placeholder to the TreeTableView.
		// It is displayed when the root node is deleted.
		treeTable.setPlaceholder(new Label("Click the Add button to add a row."));

		// Create the Label
		Label label = new Label("Please select a row to add/delete.");

		// Create the HBox
		HBox hbox = this.getButtons();

		// Create the VBox
		VBox root = new VBox(label, hbox, treeTable);
		root.setSpacing(10);

		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title
		stage.setTitle("Adding/Deleting Rows in a TreeTableView");
		// Display the Stage
		stage.show();
	}

	private HBox getButtons()
	{
		// Create the Buttons
		Button addButton = new Button("Add");
		Button deleteButton = new Button("Delete");

		// Create EventHandler for the Buttons
		addButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				addRow();
			}
		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				deleteRow();
			}
		});

		// Create and return the HBox
		return new HBox(20, addButton, deleteButton);
	}

	private void addRow()
	{
		if (treeTable.getExpandedItemCount() == 0 )
		{
			// There is no row in the TreeTableView
			addNewRootItem();
		}
		else if (treeTable.getSelectionModel().isEmpty())
		{
			logging("Select a row to add.");
			return;
		}
		else
		{
			// Add Child
			addNewChildItem();
		}
	}

	private void addNewRootItem()
	{
		// Add a root Item
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));
		treeTable.setRoot(item);

		// Edit the item
		this.editItem(item);
	}

	private void addNewChildItem()
	{
		// Prepare a new TreeItem with a new Person object
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));

		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();

		// Get the selected row index
		int rowIndex = sm.getSelectedIndex();

		// Get the selected TreeItem
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);

		// Add the new item as children to the selected item
		selectedItem.getChildren().add(item);

		// Make sure the new item is visible
		selectedItem.setExpanded(true);

		// Edit the item
		this.editItem(item);
	}

	private void editItem(TreeItem<Person> item)
	{
		// Scroll to the new item
		int newRowIndex = treeTable.getRow(item);
		treeTable.scrollTo(newRowIndex);

		// Put the first column in editing mode
		TreeTableColumn<Person, ?> firstCol = treeTable.getColumns().get(0);
		treeTable.getSelectionModel().select(item);
		treeTable.getFocusModel().focus(newRowIndex, firstCol);
		treeTable.edit(newRowIndex, firstCol);
	}

	private void deleteRow()
	{
		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();
		if (sm.isEmpty())
		{
			logging("Select a row to delete.");
			return;
		}

		// Get the selected Entry
		int rowIndex = sm.getSelectedIndex();
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);
		TreeItem<Person> parent = selectedItem.getParent();

		if (parent != null)
		{
			// Remove the Item
			parent.getChildren().remove(selectedItem);
		}
		else
		{
			// Must be deleting the Root Item
			treeTable.setRoot(null);
		}
	}

	private void logging(String message)
	{
		this.textarea.appendText(message + "\n");
	}
}
