package com.example.zendesksample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zendesksample.ui.theme.ZendeskSampleTheme
import zendesk.chat.Chat
import zendesk.chat.ChatConfiguration
import zendesk.chat.ChatEngine
import zendesk.chat.ChatProvidersConfiguration
import zendesk.chat.VisitorInfo
import zendesk.classic.messaging.MessagingActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chatConfiguration = ChatConfiguration.builder()
            .withAgentAvailabilityEnabled(false)
            .build()

        Chat.INSTANCE.init(applicationContext, "", "com.example.zendesksample");
        setContent {
            ZendeskSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GreetingWithButton("Android", chatConfiguration)
                }
            }
        }
    }
}

@Composable
fun GreetingWithButton(name: String, chatConfiguration: ChatConfiguration, modifier: Modifier = Modifier) {
    val context = LocalContext.current // Get the context in Compose

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text greeting
        Text(text = "Hello $name!")

        // Spacer for layout
        Spacer(modifier = Modifier.height(16.dp))

        // Button that triggers the function on click
        Button(onClick = {
            MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(context, chatConfiguration)
        }) {
            Text(text = "Click Me")
        }
    }
}
