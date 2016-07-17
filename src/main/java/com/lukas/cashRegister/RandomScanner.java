package com.lukas.cashRegister;

import com.lukas.dao.DAOFactory;
import com.lukas.dao.ItemDAO;
import com.lukas.dao.ItemDAOJpa;
import com.lukas.model.entities.Item;

import java.util.Random;

public class RandomScanner implements Scanner {

    ItemDAOJpa itemDao = DAOFactory.getItemDAOJpa();

//    public int getRandomNumber() {
//
//        long max = itemDao.countItems();
//        Random r = new Random();
//        int randomNumber = r.nextInt(Math.toIntExact(max));  //Risky place1
//        if (randomNumber == 0) {
//            return getRandomNumber();
//        }
//        return randomNumber;
//    }

    public Item scan(Long itemCode) {
        return itemDao.selectRandomItem();
    }
}



