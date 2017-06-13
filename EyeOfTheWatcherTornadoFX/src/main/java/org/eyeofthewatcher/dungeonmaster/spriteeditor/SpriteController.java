package org.eyeofthewatcher.dungeonmaster.spriteeditor;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.eyeofthewatcher.dungeonmaster.DungeonMaster;
import org.eyeofthewatcher.dungeonmaster.DungeonMasterJSON;
import org.eyeofthewatcher.dungeonmaster.entities.Prop;
import org.eyeofthewatcher.dungeonmaster.entities.Tile;
import org.eyeofthewatcher.dungeonmaster.entities.Weapon;
import org.eyeofthewatcher.dungeonmaster.sprite.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by nmw on 09-06-2017.
 */
public class SpriteController implements Initializable {

    @FXML
    private TableView<Image> imageTabel;


    @FXML
    private TableColumn<Image, Image> spriteView;
    @FXML
    private TableColumn<Image, Image> spriteId;
    private SpriteEditor mainApp;

    @FXML
    private Label selectedID;

    @FXML
    private ImageView selectedSprite;

    @FXML
    private ChoiceBox<Class> choiceType;

    private DungeonMaster dungeonMaster;

    public void setMainApp(SpriteEditor mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table

    }


    @FXML
    public void tableViewClickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking click
        {
            selectedID.setText(imageTabel.getSelectionModel().getSelectedIndex() + "");
            selectedSprite.setImage(imageTabel.getSelectionModel().getSelectedItem());

        }
    }

    @FXML
    public void btnSaveClicked(MouseEvent event) {
        //Depending on type save in appropiate service
        if(choiceType.getSelectionModel().getSelectedItem().isInstance(Weapon.class.getClass())){

        }
        if(choiceType.getSelectionModel().getSelectedItem().isInstance(Tile.class.getClass())){

        }
        if(choiceType.getSelectionModel().getSelectedItem().isInstance(Prop.class.getClass())){

        }

    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dungeonMaster = new DungeonMasterJSON();



        spriteView.setCellFactory(new Callback<TableColumn<Image, Image>, TableCell<Image, Image>>() {
            @Override
            public TableCell<Image, Image> call(TableColumn<Image, Image> param) {
                //Set up the ImageView
                final ImageView imageview = new ImageView();
                imageview.setFitHeight(50);
                imageview.setFitWidth(50);

                //Set up the Table
                TableCell<Image, Image> cell = new TableCell<Image, Image>() {
                    public void updateItem(Image item, boolean empty) {
                        imageview.setImage(item);
                    }
                };

                // Attach the imageview to the cell
                cell.setGraphic(imageview);
                return cell;
            }

        });

        spriteView.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Image, Image>, ObservableValue<Image>>() {
                    public ObservableValue<Image> call(TableColumn.CellDataFeatures<Image, Image> p) {
                        return new ReadOnlyObjectWrapper(p.getValue());
                    }
                }
        );
        try {
            imageTabel.setItems(getTableList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        spriteId.setMinWidth(20);
        spriteId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Image, Image>, ObservableValue<Image>>() {
            @Override
            public ObservableValue<Image> call(TableColumn.CellDataFeatures<Image, Image> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        spriteId.setCellFactory(new Callback<TableColumn<Image, Image>, TableCell<Image, Image>>() {
            @Override
            public TableCell<Image, Image> call(TableColumn<Image, Image> param) {
                return new TableCell<Image, Image>() {
                    @Override
                    protected void updateItem(Image item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        spriteId.setSortable(false);

    }

    private ObservableList<Image> getTableList() throws IOException {
        ObservableList<Image> data = FXCollections.observableArrayList();
        for (BufferedImage image : Arrays.asList(getSprites())) {
            data.add(SwingFXUtils.toFXImage(image, null));
        }
        return data;
    }

    private BufferedImage[] getSprites() throws IOException {
        URL resource = SpriteLoader.class.getResource("DungeonCrawl_ProjectUtumnoTileset.png");
        BufferedImage bigImg = ImageIO.read(resource);
// The above line throws an checked IOException which must be caught.

        final int width = 32;
        final int height = 32;
        final int rows = bigImg.getHeight() / height;
        final int cols = bigImg.getWidth() / width;
        BufferedImage[] sprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
        return sprites;
    }
}
