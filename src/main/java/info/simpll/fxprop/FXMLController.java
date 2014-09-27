package info.simpll.fxprop;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for Scene.fxml
 *
 * @author Bhathiya
 */
public class FXMLController {
    
    //------
    //Table of Properties
    @FXML
    private TableView<PropertyInfo> tvProperties;
    
    @FXML
    private TableColumn<PropertyInfo, String> tcName;

    @FXML
    private TableColumn<PropertyInfo, String> tcType;
    //------
    
    @FXML
    private ComboBox<String> cmbPropertyType;

    @FXML
    private TextArea txtCode;

    @FXML
    private TextField txtProperty;

    @FXML
    private TextField txtClassName;


    //property info observable list : this is bind to table view
    private ObservableList<PropertyInfo> propertyInfoList;
    
    //property type observable list : this is bind to combobox of data types
    private ObservableList<String> propertyTypeList;

    @FXML
    void addOnAction(ActionEvent event) {
        String name = txtProperty.getText().trim();
        if (name.isEmpty()) {
            return;
        }
        String type = cmbPropertyType.getValue();
        propertyInfoList.add(new PropertyInfo(name, type));
        //clear
        txtProperty.clear();
        cmbPropertyType.getSelectionModel().selectFirst();
    }

    @FXML
    void generateOnAction(ActionEvent event) {
        String className = txtClassName.getText().trim();
        if (className.isEmpty()) {
            return;
        }
        PropertyClassBuilder generator = new PropertyClassBuilder(className);
        txtCode.setText(generator.generateCode(propertyInfoList));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        propertyInfoList.clear();
        txtClassName.clear();
        txtCode.clear();
        txtProperty.clear();
        cmbPropertyType.getSelectionModel().selectFirst();
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        PropertyInfo propertyInfo = tvProperties.getSelectionModel().
                getSelectedItem();
        if (propertyInfo != null) {
            propertyInfoList.remove(propertyInfo);
        }
    }

    @FXML
    void initialize() {
        //Create Observable List for types
        propertyTypeList = cmbPropertyType.getItems();
        propertyTypeList.clear();
        propertyTypeList.addAll("String", "Integer", "Boolean", "Double",
                "Float", "Long");
        cmbPropertyType.getSelectionModel().selectFirst();
        //Create Observable List for propertyInfoList
        propertyInfoList = tvProperties.getItems();
        propertyInfoList.clear();
        //Bind Columns
        tcName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        tcType.setCellValueFactory(
                new PropertyValueFactory<>("type"));
    }
}
