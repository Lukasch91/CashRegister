package com.lukas.cashRegister;

import com.lukas.dao.DAOFactory;
import com.lukas.dao.PurchaseDAOJpa;
import com.lukas.db.EMFactory;
import com.lukas.model.entities.Item;
import com.lukas.model.entities.Purchase;
import com.lukas.model.entities.Record;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CashRegister {


    private Purchase purchase;
    private Scanner scanner;

    PurchaseDAOJpa purchaseDAOJpa = DAOFactory.getPurchaseDAOJpa();

    public CashRegister() {
    }

    public void scanItem(Long itemCode, Integer numberOfItems) {
        Item item = scanner.scan(itemCode);
        if (item == null) {
            System.out.println("Item not found");
            return;
        }

        Record record = new Record();
        record.setItemName(item.getItemName());
        record.setItemPrice(item.getItemPriceWithDiscount());
        record.setNumberOfItems(numberOfItems);
        record.setSum(numberOfItems * item.getItemPriceWithDiscount());
        record.setPurchase(purchase);
        purchase.addItemToList(record);


    }

    public void scanItem(Integer numberOfItems) {
        Item item = scanner.scan(null);
        Record record = new Record();
        record.setItemName(item.getItemName());
        record.setItemPrice(item.getItemPriceWithDiscount());
        record.setNumberOfItems(numberOfItems);
        record.setSum(numberOfItems * item.getItemPriceWithDiscount());
        record.setPurchase(purchase);
        purchase.addItemToList(record);
    }

    public void startPurchase() {
        purchase = new Purchase();
    }

    public void endPurchase() throws SQLException, ClassNotFoundException {

//purchase.getRecords().stream().filter(entry -> entry != null).forEach(entry -> System.out.println(entry.getItem().getItemName()
//               + "..." + entry.getItem().getItemPriceWithDiscount() + "..." + entry.getNumberOfItems() +
//                "..." + entry.sumOfItem()));

        List<Record> recordList = purchase.getRecordList();
        for (Record aRecordList : recordList) {
            System.out.println(aRecordList.getItemName() + "...." + aRecordList.getItemPrice() + "...."
                    + aRecordList.getNumberOfItems() + "...." + aRecordList.getSum());
        }

        System.out.println("Sum:" + purchase.sumPrices());
        purchase.setSum(purchase.sumPrices());

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(dt);
        String time = stf.format(dt);

        purchase.setType("P");
        purchase.setDate(date);
        purchase.setTime(time);
        purchaseDAOJpa.addPurchaseToDatabase(purchase);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addWithdrawal(Double withdrawal) {
        purchaseDAOJpa.addWithdrawalToDatabase(withdrawal);
    }

    public Double getCurrentBalance() {
        return purchaseDAOJpa.getCurrentBalance();
    }

    public Double getBalanceByDate(String date) {
       return purchaseDAOJpa.getBalanceByDate(date);
    }
}





