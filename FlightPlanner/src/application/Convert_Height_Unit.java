package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Convert_Height_Unit {

    @FXML
    private TextField txtKilometre;

    @FXML
    private TextField txtPieds;

    @FXML
    private TextField txtMetre;

    double M;
    double P;
    double Km;
    
    
    @FXML
    public void Convert()
    {
    	
    	
    	if(!txtMetre.getText().equals(""))
    	{
    		
    		
			try {
				M = Double.parseDouble(txtMetre.getText());
			} catch (NumberFormatException e) 
			{
				
				Alert alert=new Alert(AlertType.ERROR);
		        alert.setHeaderText("Attention - Erreur");
		        alert.setTitle("Erreur");
		        alert.setContentText("Tu dois ecrire un nombre");
		        alert.show();
			}
    		double Km = M * 1000;
    		double P = M * 3.281;
    		
    		txtMetre.setText(Double.toString(M));
    		txtKilometre.setText(Double.toString(Km));
    		txtPieds.setText(Double.toString(P));
    	}
    	if(!txtPieds.getText().equals(""))
    	{
    		
    		
			try {
				P = Double.parseDouble(txtPieds.getText());
			} catch (NumberFormatException e) 
			{
				Alert alert=new Alert(AlertType.ERROR);
		        alert.setHeaderText("Attention - Erreur");
		        alert.setTitle("Erreur");
		        alert.setContentText("Tu dois ecrire un nombre");
		        alert.show();
			}
    		double M = P / 3.281;
    		double Km = P / 3281;
    		
    		txtMetre.setText(Double.toString(M));
    		txtKilometre.setText(Double.toString(Km));
    		txtPieds.setText(Double.toString(P));
    	}
    	if(!txtKilometre.getText().equals(""))
    	{
    		
    		
			try {
				Km = Double.parseDouble(txtKilometre.getText());
			} catch (NumberFormatException e) 
			{
				Alert alert=new Alert(AlertType.ERROR);
		        alert.setHeaderText("Attention - Erreur");
		        alert.setTitle("Erreur");
		        alert.setContentText("Tu dois ecrire un nombre");
		        alert.show();
			}
    		double M = Km * 1000;
    		double P = Km * 3281;
    		
    		
    		txtMetre.setText(Double.toString(M));
    		txtPieds.setText(Double.toString(P));
    		txtKilometre.setText(Double.toString(Km));
    	}
    }
    
    @FXML
    private void ClearMesures ()
    {
    	txtMetre.setText("");
    	txtPieds.setText("");
    	txtKilometre.setText("");
    }
    
    
    
}
