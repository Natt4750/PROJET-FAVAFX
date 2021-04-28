package application;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FlightController implements Initializable{

    @FXML
    private TableColumn<Flight, String> prenomColumn;

    @FXML
    private TableColumn<Flight, String> departColumn;

    @FXML
    private TextField txtPrenom;

    @FXML
    private DatePicker dateDep;

    @FXML
    private TableView<Flight> volTable;

    @FXML
    private Button btnEffacer;

    @FXML
    private DatePicker dateRet;

    @FXML
    private Button btnRecommencer;

    @FXML
    private Button btnModifier;

    @FXML
    private TableColumn<Flight, String> nomColumn;

    @FXML
    private TableColumn<Flight, String> retourColumn;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNom;
    
 
    
  	public ObservableList<Flight> flightData=FXCollections.observableArrayList();

  	public ObservableList<Flight> getflightData()
  	{
  		return flightData;
  	}

  	@Override
  	public void initialize(URL arg0, ResourceBundle arg1) 
  	{
  		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
  		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
  		departColumn.setCellValueFactory(new PropertyValueFactory<>("depart"));
  		retourColumn.setCellValueFactory(new PropertyValueFactory<>("retour"));
  		volTable.setItems(flightData);

  		btnModifier.setDisable(true);
  		btnEffacer.setDisable(true);
  		btnRecommencer.setDisable(true);

  		showFlights(null);

  		//Mettre a jour l'affichage d'un etudiant selectionne
  		volTable.getSelectionModel().selectedItemProperty().addListener((
  				observable, oldValue, newValue)-> showFlights(newValue));

  	}
  	
  	@FXML
	void ajouter()
	{
		
		if(noEmptyInput())
		{
			Flight tmp=new Flight();	
			tmp=new Flight();
			tmp.setNom(txtNom.getText());;
			tmp.setPrenom(txtPrenom.getText());;
			tmp.setDepart(dateDep.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			tmp.setRetour(dateRet.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

			flightData.add(tmp);
			clearFields();
		}
	}

	
	@FXML
	void clearFields()
	{
		dateDep.setValue(null);
		dateRet.setValue(null);
		txtNom.setText("");
		txtPrenom.setText("");
	}


}
