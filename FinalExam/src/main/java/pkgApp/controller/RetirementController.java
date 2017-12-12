package pkgApp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {


    private RetirementApp mainApp = null;

    @FXML
    private TextField txtYearsToWork;
    @FXML
    private TextField txtAnnualReturn;
    @FXML
    private TextField txtYearsRetired;
    @FXML
    private TextField txtRetiredReturn;
    @FXML
    private TextField txtRequiredIncome;
    @FXML
    private TextField txtMonthlySSI;
    @FXML
    private Label txtYearsToWorkLabel;
    @FXML
    private Label amountToSaveByMonth;
    @FXML
    private Label totalAmountSaved;

    @FXML
    public RetirementApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(RetirementApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void btnClear(ActionEvent event) {
        System.out.println("Clear pressed");
        txtYearsToWork.setText("");
        txtAnnualReturn.setText("");
        txtYearsRetired.setText("");
        txtRetiredReturn.setText("");
        txtRequiredIncome.setText("$");
        txtMonthlySSI.setText("$");
        txtRequiredIncome.setStyle("");
        txtMonthlySSI.setStyle("");
        amountToSaveByMonth.setText("$");
        totalAmountSaved.setText("$");
        txtYearsToWorkLabel.setText("");
    }

    @FXML
    public void btnCalculate(ActionEvent event) {
        if (IsInputValid()) {
            int yearToWork = Integer.parseInt(txtYearsToWork.getText());
            double annualReturn = Double.parseDouble(txtAnnualReturn.getText().substring(0, txtAnnualReturn.getText().length() - 1)) * 0.01;

            int yearsRetired = Integer.parseInt(txtYearsRetired.getText());
            double retiredAnnualReturn = Double.parseDouble(txtRetiredReturn.getText().substring(0, txtRetiredReturn.getText().length() - 1)) * 0.01;
            double requiredIncome = Double.parseDouble(txtRequiredIncome.getText().replace(",", "").replace("$", ""));
            double monthlySSI = Double.parseDouble(txtMonthlySSI.getText().replace(",", "").replace("$", ""));

            Retirement retirement = new Retirement(yearToWork, annualReturn, yearsRetired, retiredAnnualReturn, requiredIncome, monthlySSI);

            DecimalFormat df = new DecimalFormat("#,###.00");

            double  doubleTotalAmountSaved = retirement.TotalAmountSaved();
            double doubleAmountToSaveByMonth = retirement.AmountToSave(doubleTotalAmountSaved);

            String strTotalAmountSaved= df.format(-doubleTotalAmountSaved);
            String strAmountToSaveByMonth = df.format(doubleAmountToSaveByMonth);
     
            amountToSaveByMonth.setText("$     "+strAmountToSaveByMonth);
            totalAmountSaved.setText("$     "+strTotalAmountSaved);
        }
    }

    private boolean IsInputValid() {
    		String errorMessage = "";
    		
    		if (txtYearsToWork.getText() == null || !txtYearsToWork.getText().matches("^[0-9]+$")) {
    			errorMessage  += "No valid Years To Work!\n";
            }
            if (txtAnnualReturn.getText() == null || !txtAnnualReturn.getText().matches("^[0-9.]%")) {
            	errorMessage += "No valid Annual Return Rate!\n";
            }
            if (txtYearsRetired.getText() == null || !txtYearsRetired.getText().matches("^[0-9]+$")) {
            	errorMessage += "No valid Years To Retired!\n";
            }
            if (txtRetiredReturn.getText() == null || !txtRetiredReturn.getText().matches("^[0-9.]%+$")) {
            	errorMessage += "No valid Annual Return Rate!\n";
            }
            if (txtRequiredIncome.getText() == null || !txtRequiredIncome.getText().matches("^[$][0-9,.]+$")) {
            	errorMessage += "No valid Required Income!\n";
            }
            if (txtMonthlySSI.getText() == null || !txtMonthlySSI.getText().matches("^[$][0-9,.]+$")) {
            	errorMessage += "No valid Monthly SSI!\n";
            }
            
            if (errorMessage.length() == 0) {
                return true;
            } else {
                // Show the error message.
                Alert alert = new Alert(AlertType.ERROR);
               // alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);

                alert.showAndWait();

                return false;
            }
    }
    
    @FXML
    public void checkYearsToWork(KeyEvent event) {
        String txtYearsToWorkContent = txtYearsToWork.getText();
        if (txtYearsToWorkContent.length() > 0 && !txtYearsToWorkContent.matches("^[0-9]+")) {
            txtYearsToWork.setStyle("-fx-text-fill: #ff0000;");
        } else {
            txtYearsToWork.setStyle("");
        }
    }

    @FXML
    public void checkAnnualReturn(KeyEvent event) {
        String txtAnnualReturnContent = txtAnnualReturn.getText();
        if (txtAnnualReturnContent != null && !txtAnnualReturnContent.matches("^[0-9.%]+")) {
            if (txtAnnualReturnContent.length() > 0 && !"%".equals(txtAnnualReturnContent.substring(txtAnnualReturnContent.length() - 1))) {
                txtAnnualReturnContent = txtAnnualReturnContent + "%";
                txtAnnualReturn.setText(txtAnnualReturnContent);
            }
            txtAnnualReturn.setStyle("-fx-text-fill: #ff0000;");
        } else {
            if (txtAnnualReturnContent != null && txtAnnualReturnContent.length() > 0 && !"%".equals(txtAnnualReturnContent.substring(txtAnnualReturnContent.length() - 1))) {
                txtAnnualReturnContent = txtAnnualReturnContent + "%";
                txtAnnualReturn.setText(txtAnnualReturnContent);
            }
            txtAnnualReturn.setStyle("");
            if (txtAnnualReturnContent != null && txtAnnualReturnContent.length() > 0 && txtAnnualReturnContent.indexOf("%") < (txtAnnualReturnContent.length() - 1)) {
                txtAnnualReturn.setStyle("-fx-text-fill: #ff0000;");
            }

        }
    }

    @FXML
    public void checkYearsRetired(KeyEvent event) {

        String txtYearsRetiredContent = txtYearsRetired.getText();
        if (txtYearsRetiredContent.length() > 0 && !txtYearsRetiredContent.matches("^[0-9]+$")) {
            txtYearsRetired.setStyle("-fx-text-fill: #ff0000;");
        } else {
            txtYearsRetired.setStyle("");
        }
    }

    @FXML
    public void checkRetiredReturn(KeyEvent event) {

        String txtRetiredReturnContent = txtRetiredReturn.getText();
        {
            if (txtRetiredReturnContent != null && !txtRetiredReturnContent.matches("^[0-9.%]+")) {
                if (txtRetiredReturnContent.length() > 0 && !"%".equals(txtRetiredReturnContent.substring(txtRetiredReturnContent.length() - 1))) {
                    txtRetiredReturnContent = txtRetiredReturnContent + "%";
                    txtRetiredReturn.setText(txtRetiredReturnContent);
                }
                txtRetiredReturn.setStyle("-fx-text-fill: #ff0000;");
            } else {
                if (txtRetiredReturnContent != null && txtRetiredReturnContent.length() > 0 && !"%".equals(txtRetiredReturnContent.substring(txtRetiredReturnContent.length() - 1))) {
                    txtRetiredReturnContent = txtRetiredReturnContent + "%";
                    txtRetiredReturn.setText(txtRetiredReturnContent);
                }
                txtRetiredReturn.setStyle("");
                if (txtRetiredReturnContent != null && txtRetiredReturnContent.length() > 0 && txtRetiredReturnContent.indexOf("%") < (txtRetiredReturnContent.length() - 1)) {
                    txtRetiredReturn.setStyle("-fx-text-fill: #ff0000;");
                }

            }
        }
    }

    @FXML
    public void checkRequiredIncome(KeyEvent event) {
        String txtRequiredIncomeContent = txtRequiredIncome.getText();
        if (txtRequiredIncomeContent.length() > 0 && !txtRequiredIncomeContent.matches("^[0-9.,$]+$")) {
            if (!"$".equals(txtRequiredIncomeContent.substring(0, 1))) {
                txtRequiredIncome.setText("$" + txtRequiredIncomeContent);
            }
            txtRequiredIncome.setStyle("-fx-text-fill: #ff0000;");

        } else {
            if (txtRequiredIncomeContent != null && txtRequiredIncomeContent.length() > 0 && !("$".equals(txtRequiredIncomeContent.substring(0, 1)))) {
                txtRequiredIncome.setText("$" + txtRequiredIncomeContent);
            }
            txtRequiredIncome.setStyle("");
        }
        txtRequiredIncome.end();
    }

    @FXML
    public void checkMonthlySSI(KeyEvent event) {
        String txtMonthlySSIContent = txtMonthlySSI.getText();
        if (txtMonthlySSIContent.length() > 0 && !txtMonthlySSIContent.matches("^[0-9.,$]+$")) {
            if (!"$".equals(txtMonthlySSIContent.substring(0, 1))) {
                txtMonthlySSI.setText("$" + txtMonthlySSIContent);
            }
            txtMonthlySSI.setStyle("-fx-text-fill: #ff0000;");
        } else {
            if (txtMonthlySSIContent != null && txtMonthlySSIContent.length() > 0 && !"$".equals(txtMonthlySSIContent.substring(0, 1))) {
                txtMonthlySSI.setText("$" + txtMonthlySSIContent);
            }
            txtMonthlySSI.setStyle("");
        }
        txtMonthlySSI.end();
    }
}
