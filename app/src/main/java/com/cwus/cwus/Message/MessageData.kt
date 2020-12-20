package com.cwus.cwus.Message


data class Message (val userName : String, val messageContent : String, val roomName: String,var viewType : Int)
data class initialData (val userName : String, val roomName : String)
data class SendMessage(val userName : String, val messageContent: String, val roomName: String)