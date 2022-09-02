package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository

public class TransactionRepository {
    private final TransactionDAO transactionDAO;
    private final TraderDAO traderDAO;
    private final AssetDAO assetDAO;

    @Autowired
    public TransactionRepository(TransactionDAO transactionDAO, TraderDAO traderDAO, AssetDAO assetDAO) {
        this.transactionDAO = transactionDAO;
        this.traderDAO = traderDAO;
        this.assetDAO = assetDAO;
    }

    //  TODO: Should not be used by basic user, this is an admin-only method.
    //  Returns a list of known transactions in the DB, descending from the newest idTransaction.
    public List<Transaction> findAll() {
        return insertIntoList(transactionDAO.findAll());
    }

    //  TODO: Should not be used by basic user, this is an admin-only method.
    //  Returns a single transaction for a given transaction ID.
    public Transaction findById(int idTransaction) {
        return insertTradersAndAsset(transactionDAO.findById(idTransaction));
    }

    //  Returns a single list containing all transactions relating to a given trader.
    public List<Transaction> getAllTransactionsForTrader(Trader trader) {
        return Stream.concat(getTransactionByBuyer(trader).stream(), getTransactionBySeller(trader).stream()).toList();
    }

    //  Returns a list of transactions where the given trader is buyer.
    public List<Transaction> getTransactionByBuyer(Trader buyer) {
        return insertIntoList(transactionDAO.getTransactionsByBuyerId(buyer.getID()));
    }

    //  Returns a list of transactions where the given trader is seller.
    public List<Transaction> getTransactionBySeller(Trader seller) {
        return insertIntoList(transactionDAO.getTransactionsBySellerId(seller.getID()));
    }

    //  Helper method, inserts Traders and asset into Transaction
    private Transaction insertTradersAndAsset(Transaction t) {
        int id = t.getIdTransaction();
        t.setBuyer(traderDAO.findById(transactionDAO.getBuyerIdByTransactionId(id)));
        t.setSeller(traderDAO.findById(transactionDAO.getSellerIdByTransactionId(id)));
        t.setAsset(assetDAO.getByTicker(transactionDAO.getTickerByTransactionId(id)));
        return t;
    }

    //  Helper method performs insertTraderAndAsset() on list of transactions.
    private List<Transaction> insertIntoList(List<Transaction> tList) {
        for (Transaction t : tList) {
            insertTradersAndAsset(t);
        }
        return tList;
    }

}

