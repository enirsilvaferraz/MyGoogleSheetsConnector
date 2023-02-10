package com.eferraz.mygooglesheetsconnector.feature.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Sick
import androidx.compose.material.icons.rounded.Sync
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eferraz.googlesheets.providers.GoogleApiContract
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoute(
    activity: ComponentActivity = LocalContext.current as ComponentActivity,
    vm: LoginViewModel = koinViewModel(),
    onFinish: () -> Unit
) {

    @Composable
    fun DoLogin(onSuccess: () -> Unit, onFailure: () -> Unit) {

        val authResultLauncher = rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            if (task?.getResult(ApiException::class.java) != null) onSuccess() else onFailure()
        }

        SideEffect {
            authResultLauncher.launch(1000)
        }
    }

    val state by vm.uiState.collectAsState()

    LoginScreen(
        state = state,
        onStart = {
            vm.onStart(GoogleSignIn.getLastSignedInAccount(activity))
        },
        onLoading = {
            DoLogin(onSuccess = vm::onSuccess, onFailure = vm::onFailure)
        },
        onFinish = onFinish,
        onTryAgainClicked = vm::onTryAgain
    )
}


@Composable
private fun LoginScreen(
    state: LoginViewModel.State,
    onStart: () -> Unit,
    onLoading: @Composable () -> Unit,
    onFinish: () -> Unit,
    onTryAgainClicked: () -> Unit
) {
    MyGoogleSheetsConnectorTheme {
        when (state) {
            LoginViewModel.State.Starting -> ContentIdle(onStart = onStart)
            LoginViewModel.State.Loading -> ContentLoading(onLoading = onLoading)
            LoginViewModel.State.Success -> ContentSuccess(onFinish = onFinish)
            LoginViewModel.State.Failure -> ContentFailure(onStateClick = onTryAgainClicked)
        }
    }
}

@Composable
private fun ContentLoading(onLoading: @Composable () -> Unit) {

    DefaultStatusScreen(
        imageVector = Icons.Rounded.Sync,
        text = "Fazendo login..."
    )

    onLoading()
}

@Composable
private fun ContentIdle(onStart: () -> Unit) {

    DefaultStatusScreen(
        imageVector = Icons.Rounded.Home,
        text = "Faça o login para acessar o app"
    )

    LaunchedEffect(key1 = "Start") {
        onStart()
    }
}

@Composable
private fun ContentSuccess(onFinish: () -> Unit) {

    DefaultStatusScreen(
        imageVector = Icons.Rounded.ThumbUp,
        text = "Tudo certo!"
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "Success") {
        scope.launch {
            delay(700)
            onFinish()
        }
    }
}

@Composable
private fun ContentFailure(message: String? = null, onStateClick: () -> Unit) {
    DefaultStatusScreen(
        imageVector = Icons.Rounded.Sick,
        text = "Algo deu errado!",
        subtext = message,
        buttonText = "Tente novamente",
        onClick = onStateClick
    )
}

@Composable
private fun DefaultStatusScreen(
    imageVector: ImageVector,
    text: String,
    subtext: String? = null,
    buttonText: String? = null,
    onClick: (() -> Unit)? = null
) {
    Surface {
        Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {

            Column(modifier = Modifier.align(Center)) {

                Box(modifier = Modifier.align(CenterHorizontally).size(100.dp).clip(CircleShape)) {

                    Icon(
                        imageVector = imageVector,
                        contentDescription = text,
                        modifier = Modifier.align(Center).size(48.dp)
                    )
                }

                Text(
                    text = text,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 0.dp)
                )

                subtext?.let {
                    Text(
                        text = subtext,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(CenterHorizontally).padding(top = 4.dp)
                    )
                }
            }

            if (onClick != null && buttonText != null) Button(onClick = onClick, modifier = Modifier.align(BottomCenter)) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
internal fun ContentLoadingPreview() {
    MyGoogleSheetsConnectorTheme { ContentLoading {} }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
internal fun ContentSynchronizingPreview() {
    MyGoogleSheetsConnectorTheme { ContentIdle {} }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
internal fun ContentSuccessPreview() {
    MyGoogleSheetsConnectorTheme { ContentSuccess {} }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
internal fun ContentFailurePreview() {
    MyGoogleSheetsConnectorTheme { ContentFailure("Mensagem de Erro") {} }
}