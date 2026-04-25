package component


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import kotlinx.serialization.Serializable

interface SecondComponent {
    val requestState: RequestState
    fun fetch()
    fun retry()
}

class SecondComponentImpl(
    private val param: String,
    private val httpClient: HttpClient,
    componentContext: ComponentContext,
) : SecondComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    override var requestState by mutableStateOf<RequestState>(RequestState.Idle)

    override fun fetch() {
        if (requestState !is RequestState.Idle) return
        scope.launch {
            fetchResponse()
        }
    }

    override fun retry() {
        if (requestState !is RequestState.Error) return
        scope.launch {
            fetchResponse()
        }
    }

    private suspend fun fetchResponse() {
        requestState = RequestState.Loading
        try {
            val response = httpClient.post("https://postman-echo.com/post") {
                contentType(ContentType.Application.Json)
                setBody(EchoRequestBody(param))
            }

            if (!response.status.isSuccess()) {
                requestState = RequestState.Error
            } else {
                val responseBody = response.body<EchoResponseBody>()
                requestState = RequestState.Success(responseBody)
            }
        } catch (t: Throwable) {
            requestState = RequestState.Error
            when (t) {
                is UnresolvedAddressException, is IOException -> {
                    t.printStackTrace()
                }

                else -> {
                    t.printStackTrace()
                }
            }
        }
    }
}

@Serializable
sealed interface RequestState {
    @Serializable
    data object Idle : RequestState

    @Serializable
    data object Loading : RequestState

    @Serializable
    data object Error : RequestState

    @Serializable
    data class Success(val value: EchoResponseBody) : RequestState
}

@Serializable
data class EchoRequestBody(
    val param: String
)

@Serializable
data class EchoResponseBody(
    val data: String
)