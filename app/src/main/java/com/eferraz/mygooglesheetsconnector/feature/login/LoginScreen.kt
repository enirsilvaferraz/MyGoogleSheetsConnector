package com.eferraz.mygooglesheetsconnector.feature.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Sick
import androidx.compose.material.icons.rounded.Sync
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eferraz.googlesheets.providers.GoogleSignInViewModel
import com.eferraz.googlesheets.providers.LoginClientState
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme

@Composable
fun LoginRoute(vm: GoogleSignInViewModel, onFinish: () -> Unit) {

    val state by vm.clientState.collectAsState()

    LoginScreen(state = state, onFinish = onFinish, onStartProcess = vm::onStartProcess, onTryAgainClicked = vm::onTryAgainClicked)
}

@Composable
private fun LoginScreen(state: LoginClientState, onFinish: () -> Unit, onStartProcess: () -> Unit, onTryAgainClicked: () -> Unit) {
    MyGoogleSheetsConnectorTheme {
        when (state) {
            LoginClientState.Idle -> ContentIdle(onStateClick = onStartProcess)
            LoginClientState.Loading -> ContentLoading()
            LoginClientState.Failure -> ContentFailure(onStateClick = onTryAgainClicked)
            LoginClientState.Success -> ContentSuccess(onFinish = onFinish)
        }
    }
}

@Composable
private fun ContentLoading() {
    DefaultStatusScreen(imageVector = Icons.Rounded.Sync, text = "Fazendo login...")
}

@Composable
private fun ContentIdle(onStateClick: () -> Unit) {
    DefaultStatusScreen(imageVector = Icons.Rounded.Home, text = "Faça o login para acessar o app", buttonText = "Login", onClick = onStateClick)
}

@Composable
private fun ContentSuccess(onFinish: () -> Unit) {
    DefaultStatusScreen(imageVector = Icons.Rounded.ThumbUp, text = "Tudo certo!")
    onFinish()
}

@Composable
private fun ContentFailure(message: String? = null, onStateClick: () -> Unit) {
    DefaultStatusScreen(
        imageVector = Icons.Rounded.Sick, text = "Algo deu errado!", subtext = message, buttonText = "Tente novamente", onClick = onStateClick
    )
}

@Composable
private fun DefaultStatusScreen(
    imageVector: ImageVector, text: String, subtext: String? = null, buttonText: String? = null, onClick: (() -> Unit)? = null
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {

            Column(modifier = Modifier.align(Center)) {

                Box(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .size(100.dp)
                        .clip(CircleShape)
                    // .background(Gray)
                ) {

                    Icon(
                        imageVector = imageVector, contentDescription = text, modifier = Modifier
                            .align(Center)
                            .size(48.dp)
                    )
                }

                Text(
                    text = text, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 0.dp)
                )

                subtext?.let {
                    Text(
                        text = subtext,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 4.dp)
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
    MyGoogleSheetsConnectorTheme { ContentLoading() }
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