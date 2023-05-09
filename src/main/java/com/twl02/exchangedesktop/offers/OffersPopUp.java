package com.twl02.exchangedesktop.offers;

import com.twl02.exchangedesktop.Authentication;
import com.twl02.exchangedesktop.api.ExchangeService;
import com.twl02.exchangedesktop.api.model.ExchangeRates;
import com.twl02.exchangedesktop.api.model.Offer;
import com.twl02.exchangedesktop.api.model.Transaction;
import com.twl02.exchangedesktop.api.model.TransactionRequest;
import com.twl02.exchangedesktop.pending_requests.PendingRequests;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ResourceBundle;

public class OffersPopUp implements Initializable {
    public TableView popupTableView;
    public TableColumn popupUsdAmountColumn;
    public TableColumn popupLbpAmountColumn;
    public TableColumn popupRequestDateColumn;
    public TableColumn popupAcceptButtonColumn;
    public TableColumn popupDeleteButtonColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popupLbpAmountColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, Long>("lbpAmount"));
        popupUsdAmountColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, Long>("usdAmount"));
        popupRequestDateColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, String>("addedDate"));
        popupAcceptButtonColumn.setCellFactory(param -> new TableCell<Transaction,Void>() {
            private final Button acceptButton = new Button("Accept");
            {acceptButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    acceptOffer(1);
                }
            });}
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(acceptButton);
                }
            }


        });

        popupDeleteButtonColumn.setCellFactory(param -> new TableCell<Transaction,Void>() {
            private final Button deleteButton = new Button("Delete");
            {deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    deleteOffer(1);
                }
            });}
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }

        });


        popupTableView.getItems().add(0,new Transaction(1F,2F, true));
    }

    public void acceptOffer(Integer offer_id){
        ExchangeService.exchangeApi().acceptOffer(offer_id, "Bearer " +
                Authentication.getInstance().getToken()).enqueue(new
             Callback<TransactionRequest>() {

                 @Override
                 public void onResponse(Call<TransactionRequest> call, Response<TransactionRequest> response) {
                     Platform.runLater(() -> {
                         PendingRequests.closeOfferPopup();
                     });
                 }

                 @Override
                 public void onFailure(Call<TransactionRequest> call, Throwable
                         throwable) {
                 }
             });

    }

    public void deleteOffer(Integer offer_id){
        ExchangeService.exchangeApi().rejectOffer(offer_id, "Bearer " +
                Authentication.getInstance().getToken()).enqueue(new
                     Callback<TransactionRequest>() {

                         @Override
                         public void onResponse(Call<TransactionRequest> call, Response<TransactionRequest> response) {
                             Platform.runLater(() -> {
                                 PendingRequests.closeOfferPopup();
                             });
                         }

                         @Override
                         public void onFailure(Call<TransactionRequest> call, Throwable
                                 throwable) {
                         }
                     });

    }



}
