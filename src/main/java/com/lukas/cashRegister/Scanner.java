package com.lukas.cashRegister;


import com.lukas.model.entities.Item;

interface Scanner {
    Item scan(Long itemCode);
}
