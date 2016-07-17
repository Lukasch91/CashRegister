package com.lukas.cashRegister;

import com.lukas.dao.DAOFactory;
import com.lukas.model.entities.Item;

public class ManualScanner implements Scanner {

    public Item scan(Long itemCode) {
        return DAOFactory.getItemDAOJpa().loadItem(itemCode);
    }
}
