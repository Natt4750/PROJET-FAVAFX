package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class FinanceCounter {

    @FXML
    private Label lblTotale;

    @FXML
    private TextField txtValue2;

    @FXML
    private TextField txtValue;

    double un;
    double deux;
    double result;
    
   
    @FXML
    void Ajouter()
    {
	try {
		un = Double.parseDouble(txtValue.getText());
		deux = Double.parseDouble(txtValue2.getText());
	} catch (NumberFormatException e) 
	{
		Alert alert=new Alert(AlertType.ERROR);
        alert.setHeaderText("Attention - Erreur");
        alert.setTitle("Erreur");
        alert.setContentText("Tu dois ecrire un nombre");
        alert.show();	
        }

     result = un + deux;
    
    lblTotale.setText(Double.toString(result));
    
    }
    
    
    
    
    
    
}
