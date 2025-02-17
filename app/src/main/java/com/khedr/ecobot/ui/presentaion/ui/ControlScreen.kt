package com.khedr.ecobot.ui.presentaion.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ecobot.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.net.URL

class ControlScreen :Screen {
    @Composable
    override fun Content() {
        LiveStreamScreen("li6SsVK0a7BXyJEaBIxayFwJ")
    }

    @Composable
    fun LiveStreamScreen(streamId: String) {
        val context = LocalContext.current
        val hlsUrl = remember { mutableStateOf<String?>(null) }
        val coroutineScope = rememberCoroutineScope()
        val isLoading = remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow

        val  gradient = Brush.verticalGradient(
            listOf(Color.White, colorResource(id = R.color.app_color))
        )
        // Fetch live stream data
        LaunchedEffect(streamId) {
            coroutineScope.launch(Dispatchers.IO) {
                fetchLiveStreamUrl(streamId) { url ->
                    hlsUrl.value = url
                    isLoading.value = false
                }
            }
        }

        Scaffold(

        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding),

            ) {
                Row (
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    BackButton {
                        navigator.pop()
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Live Video",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.app_color)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    var open by remember { mutableStateOf(true) }

                    Card(
                        modifier = Modifier
                            .height(32.dp)
                            .width(50.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFF1F4F7)),
                    ) {
                        IconButton(onClick = {
                            open = !open
                        }) {
                            Text(text = if (open) "Off" else "On",
                                color = if ( open )Color.Red else Color(0xFF37C545),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(4.dp))

                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    when {
                        isLoading.value -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = colorResource(id = R.color.app_color)
                                )
                        }

                        hlsUrl.value != null -> {
                            ExoPlayerView(context, hlsUrl.value!!)
                        }

                        else -> {
                            Text(
                                text = "Failed to load stream",
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Divider(
                        color =Color(0xFFF1F4F7),
                        thickness = 3.dp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth().background(gradient),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(onClick = { },
                                    colors = ButtonDefaults.buttonColors(Color.White)
                                    ) {
                                    Text(text = "↑ Forward",
                                        color = colorResource(id = R.color.app_color),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(4.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(onClick = {  },
                                    colors = ButtonDefaults.buttonColors(Color.White)
                                ) {
                                    Text(text = "← Left",
                                        color = colorResource(id = R.color.app_color),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(4.dp))
                                }
                                Spacer(modifier = Modifier.width(50.dp))
                                Button(onClick = {  },
                                    colors = ButtonDefaults.buttonColors(Color.White)
                                ) {
                                    Text(text = "→ Right",
                                        color = colorResource(id = R.color.app_color),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(4.dp))
                                }
                            }    
                            Spacer(modifier = Modifier.height(16.dp))


                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(onClick = { },
                                    colors = ButtonDefaults.buttonColors(Color.White),) {
                                    Text(text = "↓ Backward",
                                        color = colorResource(id = R.color.app_color),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(4.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                   

                }

            }
        }
    }

    // Function to fetch the live stream URL
    fun fetchLiveStreamUrl(streamId: String, onResult: (String?) -> Unit) {
        RetrofitClient.api.getLiveStream(
            streamId,streamId

        ).enqueue(object : Callback<LiveStreamResponse> {
            override fun onResponse(call: Call<LiveStreamResponse>, response: Response<LiveStreamResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body()?.assets?.hls)
                } else {
                    Log.e("LiveStream", "Error: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<LiveStreamResponse>, t: Throwable) {
                Log.e("LiveStream", "API Failure", t)
                onResult(null)
            }
        })
    }

    // ExoPlayer Composable with Cleanup
    @Composable
    fun ExoPlayerView(context: Context, videoUrl: String) {
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
                prepare()
                playWhenReady = true
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                exoPlayer.release() // Clean up player when Composable is removed
            }
        }

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            }
        )
    }
}





data class LiveStreamResponse(
    val assets: LiveStreamAssets
)

data class LiveStreamAssets(
    val hls: String  // The video stream URL
)
interface ApiService {
    @GET("live-streams/{id}")
    fun getLiveStream(
        @Header("Authorization") apiKey: String, // Pass API Key
        @Path("id") liveStreamId: String
    ): Call<LiveStreamResponse>
}

// Retrofit Client Singleton
object RetrofitClient {
    private const val BASE_URL = "https://ws.api.video/"  // Change to production if needed

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}