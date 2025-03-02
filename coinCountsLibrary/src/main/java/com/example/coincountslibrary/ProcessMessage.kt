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
        while (i < smsBody.size) {
            amountStr = smsBody.get(i).toLowerCase();
            System.out.println("The amount String is ${amountStr}")
            //Log.d(TAG, "AmountStr: ${amountStr}")
            if (identifierList.contains(amountStr)) {
                amountPosition = i + 1;
                currentAmount = smsBody.get(amountPosition);
                //Log.d(TAG, "getProcessedAmount1: ${currentAmount}")
                System.out.println("getProcessedAmount1: ${currentAmount}")
                return currentAmount
            } else if (amountStr.contains(".")) {
                amountStr = amountStr.replace(".", " ")
                var innerSplitz = amountStr.split(" ") as List<String>
                var j = 0
                if (identifierList.contains(innerSplitz[0]))
                    while (j < innerSplitz.size) {
                        if (identifierList.contains(innerSplitz[j])) {
                            amountPosition = j + 1;
                            currentAmount = innerSplitz.get(amountPosition)
                            //Log.d(TAG, "getProcessedAmount2: ${currentAmount}")
                            System.out.println("getProcessedAmount2: ${currentAmount}")
                            return currentAmount
                        }
                        j++;
                    }
            }
            i++;
        }



        return currentAmount
    }


}