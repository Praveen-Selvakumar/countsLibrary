package com.example.coincountslibrary

import android.util.Log

object ProcessMessage {

    var amountPosition = -1;


    var amountStr = ""

    var currentAmount = ""


    fun getProcessedAmount(content: String): String {
        val smsBody: List<String> = content.split(" ")
        val identifierList = arrayListOf<String>(
            "rupees",
            "rs.",
            "rs",
            "inr",
            "inr."
        )
        var i = 0;
        while ( i < smsBody.size) {
            amountStr = smsBody.get(i).toLowerCase();
            System.out.println(amountStr)
            if (amountStr.contains(".")) {
                amountStr = amountStr.replace(".", " ")
                var innerSplitz = amountStr.split(" ") as List<String>
                var j = 0
                if (identifierList.contains(innerSplitz[0]))
                    System.out.println("//dev : Processed Amount :  ${currentAmount} ")
                while (j < innerSplitz.size) {
                    if (identifierList.contains(innerSplitz[j])) {
                        amountPosition = j + 1;
                        currentAmount = innerSplitz.get(amountPosition)
                        break;
                    }
                    j++;
                }
            } else if (identifierList.contains(amountStr)) {
                amountPosition = i + 1;
                currentAmount = smsBody.get(amountPosition);
                System.out.println("//dev : Processed Amount :  ${currentAmount} ")
                break;
            }
            i++;
        }



        return currentAmount
    }


}