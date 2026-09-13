package com.enesduvan.taskflow.test

//burada testlerimi yapacağım şuanlık hilt ve di nasıl çalışır ufak test

interface Message{
    fun sendmessage(message: String)
}
class emailMessage : Message{
    override fun sendmessage(message: String) {
        println("Email Message: $message")
    }
}
class smsMessage : Message{
    override fun sendmessage(message: String) {
        println("SMS Message: $message")
    }
}
class nofification(val message: Message){
    fun send(message2: String){
        message.sendmessage(message2)
    }
}
/*--------**/
interface userRepository{
    fun getUser(): String
}
class localUser : userRepository{
    override fun getUser(): String {
        return "Local User (Enes)"
    }
}
class remoteUser : userRepository{
    override fun getUser(): String {
        return "Remote User (Duvan)"
    }
}
class userManager(val user : userRepository){
    fun getUserInfo(): String{
        return user.getUser()
    }
}
class main{
    //istenmeyen durum bağımlılık var
    val emailMessage: Message = emailMessage()
    val smsMessage: Message = smsMessage()

    val nofification = nofification(emailMessage)
    val nofification2 = nofification(smsMessage)

    val local = userManager(localUser())
    val remote = userManager(remoteUser())
}