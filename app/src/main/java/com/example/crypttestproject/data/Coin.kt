package com.example.crypttestproject.data

class Coin(val id: String, val symbol: String, val name: String) {
    override fun toString(): String {
        return "id: $id symbol: $symbol name: $name"
    }
}