import com.example.a3track.api.BackendConstants
import com.example.a3track.api.MarketPlaceApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BackendConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Will not be initialized unless you use it!
     * It is initialized only once. Next time when you use it, you get the value from cache memory.
     */
    val marketPlaceApiService: MarketPlaceApiService by lazy {
        retrofit.create(MarketPlaceApiService::class.java)
    }
}