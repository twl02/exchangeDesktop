package com.twl02.exchangedesktop.new_request;

import com.twl02.exchangedesktop.api.model.Transaction;
import com.twl02.exchangedesktop.pending_requests.PendingRequests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRequest implements Initializable {


     public TextField amountInputField;
     public Label amountInputLabel;
     public ToggleGroup transactionType;

     public boolean isUpdating;

     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {
          amountInputField.textProperty().addListener((observable, oldValue, newValue) -> {
               System.out.println(newValue);
               System.out.println(oldValue);
               if (newValue.matches("\\d*\\.?\\d*")) {
                    amountInputField.setText(newValue);
               }else{
                    amountInputField.setText(oldValue);

               }
          });

     }

     public void createRequest(ActionEvent actionEvent) {

     }

     public void changeLabel(ActionEvent actionEvent) {
          System.out.println(((RadioButton) transactionType.getSelectedToggle()).getText());
          if(((RadioButton)transactionType.getSelectedToggle()).getText().equals("Buy LBP")){
               amountInputLabel.setText("Amount in USD");
          }else{
               amountInputLabel.setText("Amount in LBP");
          }
     }
}
