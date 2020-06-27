
import kotlinx.coroutines.*

suspend fun getStockPrice(company: String) : Int{
    println("Fetching Stock Price")
    Thread.sleep(10000)
    return 100
}

fun getCallerIp() : String {
    println("Fetching Caller IP ")
    return java.net.URL("https://api.ipify.org").readText()
}

fun CoroutineScope.launchCoRoutines() {
    val companies = listOf<String>("Google", "Amazon", "Microsoft")

    launch {

        var startTime = System.currentTimeMillis()
        val sharePrice = mutableListOf<Deferred<Int>>()
        for (company in companies) {
            sharePrice += async {
                getStockPrice(company).toInt()
            }
        }

        for (share in sharePrice) {
            println(share.await())
        }
        var endTime = System.currentTimeMillis()

        println(endTime - startTime)

    }

}

 suspend fun main()  {
     coroutineScope{
         launchCoRoutines()
     }
     println("Request Sent")
   // Thread.sleep(55000)


}
/*
 fun main() {
    launch{

    }
}
*/
