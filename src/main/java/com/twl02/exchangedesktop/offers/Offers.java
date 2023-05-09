package com.twl02.exchangedesktop.offers;

import com.twl02.exchangedesktop.api.model.Transaction;
import com.twl02.exchangedesktop.pending_requests.PendingRequests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Offers implements Initializable {
    public TableView popupTableView;
    public TableColumn popupUsdAmountColumn;
    public TableColumn popupLbpAmountColumn;
    public TableColumn popupRequestDateColumn;
    public TableColumn popupViewOffersButtonColumn;
    public TableColumn popupDeleteButtonColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popupLbpAmountColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, Long>("lbpAmount"));
        popupUsdAmountColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, Long>("usdAmount"));
        popupRequestDateColumn.setCellValueFactory(new
                PropertyValueFactory<Transaction, String>("addedDate"));
        popupViewOffersButtonColumn.setCellFactory(param -> new TableCell<Transaction,Void>() {
            private final Button viewOffersButton = new Button("View Offers");
            {viewOffersButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        viewOffers(null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });}
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewOffersButton);
                }
            }


        });

        popupDeleteButtonColumn.setCellFactory(param -> new TableCell<Transaction,Void>() {
            private final Button deleteButton = new Button("Delete");
            {deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    deleteOffer();
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

    private void viewOffers(Transaction request) throws IOException {
        PendingRequests.showOffersPopup(request);
    }

//    public void acceptOffer(){
//        System.out.println("Oppaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        PendingRequests.closeOfferPopup();
//    }
//
    public void deleteOffer(){
        System.out.println("Lisaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PendingRequests.closeOfferPopup();
    }




}
