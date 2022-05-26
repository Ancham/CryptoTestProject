package com.example.crypttestproject.data

class Transaction(
    val coinName: String,
    val transactionType: String,
    val dateTime: String,
    val amount: Long,
    val feeAmount: Long,
    val feeCurrency: String,
    val notes: String
) {
    override fun toString(): String {
        return "coinName: $coinName " +
                "transactionType:  $transactionType " +
                "dateTime:  $dateTime " +
                "amount:  $amount " +
                "feeAmount:  $feeAmount " +
                "feeCurrency:  $feeCurrency " +
                "notes:  $notes "
    }
}