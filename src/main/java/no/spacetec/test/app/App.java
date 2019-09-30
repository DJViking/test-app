package no.spacetec.test.app;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        App.launch(args);
    }

    class OurDataObject {

        private final String name;

        public OurDataObject(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class AlsoOurDataObject extends OurDataObject {

        private final String type;

        public AlsoOurDataObject(final String name,
                final String type) {
            super(name);
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    class OurDataObjectLabel extends Label {

        public OurDataObjectLabel(final OurDataObject data) {
            setText(data.getName());

            if (data instanceof AlsoOurDataObject) {
                AlsoOurDataObject alsoData = (AlsoOurDataObject) data;
                if (alsoData.getType() == "TYPE1") {
                    setStyle("-fx-font-weight: bold;");
                }
            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();

        TreeTableView<OurDataObject> treeTableView = new TreeTableView<>();
        treeTableView.setShowRoot(false);
        root.getChildren().add(treeTableView);

        TreeTableColumn<OurDataObject, Label> column = new TreeTableColumn<>("Column");
        column.setPrefWidth(200);
        treeTableView.getColumns().add(column);

        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                new OurDataObjectLabel(param.getValue().getValue())));

        TreeItem<OurDataObject> rootItem = new TreeItem<>(new OurDataObject(""));
        treeTableView.setRoot(rootItem);

        TreeItem<OurDataObject> item1 = new TreeItem<>(new OurDataObject("LEVEL1"));
        item1.setExpanded(true);
        rootItem.getChildren().add(item1);

        TreeItem<OurDataObject> item2 = new TreeItem<>(new OurDataObject("LEVEL2"));
        item2.setExpanded(true);
        item1.getChildren().add(item2);

        TreeItem<OurDataObject> item3 = new TreeItem<>(new AlsoOurDataObject("LEVEL2_1", "TYPE1"));
        item3.setExpanded(true);
        item2.getChildren().add(item3);

        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

}
