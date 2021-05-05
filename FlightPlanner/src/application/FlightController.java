package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	
	@FXML
	public void showFlights(Flight flight)
	{
		if(flight !=null)
		{
			txtNom.setText(flight.getNom());
			txtPrenom.setText(flight.getPrenom());
			dateDep.setAccessibleText(flight.getDepart());
			dateDep.setAccessibleText(flight.getRetour());
			btnModifier.setDisable(false);
			btnEffacer.setDisable(false);
			btnRecommencer.setDisable(false);

		}
		else
		{
			clearFields();
		}


}
	
	@FXML
	public void updateFlight()
	{
		Flight flight=volTable.getSelectionModel().getSelectedItem();

		flight.setNom(txtNom.getText());
		flight.setPrenom(txtPrenom.getText());
		flight.setDepart(dateDep.getAccessibleText());
		flight.setRetour(dateRet.getAccessibleText());
		volTable.refresh();

	}
	
	@FXML
	public void deleteFlight()
	{
		int selectedIndex = volTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >=0)
		{
			Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Effacer");
			alert.setContentText("Confirmer la suppression!");
			Optional<ButtonType> result=alert.showAndWait();
			if(result.get()==ButtonType.OK)
				volTable.getItems().remove(selectedIndex);
		}
	}

	private boolean noEmptyInput()
	{
		String errorMessage="";
		if(txtNom.getText().trim().equals(""))
		{
			errorMessage+="Le champ nom ne doit pas etre vide! \n";
		}
		if(txtPrenom.getText()==null||txtPrenom.getText().length()==0)
		{
			errorMessage+="Le champ prenom ne doit pas etre vide! \n";
		}
		if(dateDep.getValue()==null)
		{
			errorMessage+="Le champ depart ne doit pas etre vide! \n";
		}
		if(dateRet.getValue()==null)
		{
			errorMessage+="Le champ retour ne doit pas etre vide! \n";
		}

		if(errorMessage.length()==0)
		{
			return true;
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Champs manquants");
			alert.setHeaderText("Completer les champs manquants");
			alert.setContentText(errorMessage);
			alert.showAndWait(); 
			return false;
		}
	}
	
	public File getFlightFilePath()
	{
		Preferences prefs= Preferences.userNodeForPackage(Main.class);
		String filePath= prefs.get("filePath", null);

		if (filePath != null)
		{
			return new File(filePath);
		}
		else
		{
			return null;
		}
	}

	public void setFlightFilePath(File file)
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null)
		{
			prefs.put("filePath", file.getPath());
		}
		else
		{
			prefs.remove("filePath");
		}
	}

	public void loadFlightDataFromFile(File file)
	{
		try {
			JAXBContext context = JAXBContext.newInstance(FlightListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			FlightListWrapper wrapper = (FlightListWrapper) um.unmarshal(file);
			flightData.clear();
			flightData.addAll(wrapper.getFlights());
			setFlightFilePath(file);

			Stage pStage= (Stage) volTable.getScene().getWindow();
			pStage.setTitle(file.getName());


		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Les donnees n'ont pas ete trouve");
			alert.setContentText("Les donnees ne pouvaient pas etre trouvees dans le fichier : \n" + file.getPath());
			alert.showAndWait();

		}
	}


	public void saveFlightDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(FlightListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			FlightListWrapper wrapper = new FlightListWrapper();
			wrapper.setFlights(flightData);

			m.marshal(wrapper, file);

			setFlightFilePath(file);
			
			Stage pStage= (Stage) volTable.getScene().getWindow();
			pStage.setTitle(file.getName());

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Donnees non sauvegardees");
			alert.setContentText("Les donnees ne pouvaient pas etre sauvegardees dans le fichier : \n" + file.getPath());
			alert.showAndWait(); 
		}
	}

	@FXML
	private void handleNew()
	{
		getflightData().clear();
		setFlightFilePath(null);
	}
	
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(null);

		if(file !=null) {
			loadFlightDataFromFile(file);
		}
	}

	@FXML
	private void handleSave() {

		File flightFile = getFlightFilePath();
		if (flightFile !=null) {
			saveFlightDataToFile(flightFile);

		} else {
			handleSaveAs();
		}
	}

	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(null);

		if(file !=null) {
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			saveFlightDataToFile(file);
		}
	}
	
	@FXML
	void handleFinances()
	{
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("FinanceCounter.FXML"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			FinanceCounter financecounter=loader.getController();
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setTitle("Finances");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

