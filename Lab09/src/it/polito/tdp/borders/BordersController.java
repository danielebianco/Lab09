/**
 * Sample Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {
	
	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cbxCountries"
    private ComboBox<Country> cbxCountries; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrova"
    private Button btnTrova; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
	void doCalcolaConfini(ActionEvent event) {
		int anno = Integer.parseInt(txtAnno.getText());
		if(anno<1816 || anno>2016) {
			txtResult.setText("L'anno inserito e' fuori range. Inserire un anno nell'intervallo 1816 - 2016");
		}
		else {
			String output = "Elenco stati:\n";
			for(Country s : model.createGraph(anno)) {
				output += s.stampa() + "\n";
			}
			txtResult.setText(output + String.format("\nNumero componenti connesse: %d\n",
					model.getNumberOfConnectedComponents()));
		}
	}

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	Country paese = cbxCountries.getValue();
    	if(paese != null) {
    		String output = "Stato selezionato:" + "  " + paese.toString() + "\n";
			for(Country s : model.trovaViciniDepthFirstIterator(paese)) {
				output += s.toString() + "\n";
			}
			txtResult.setText(output);
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert cbxCountries != null : "fx:id=\"cbxCountries\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }
    
    public void setModel(Model model) {
		this.model = model;
		this.cbxCountries.getItems().addAll(model.getCountry());
	}
    
}
