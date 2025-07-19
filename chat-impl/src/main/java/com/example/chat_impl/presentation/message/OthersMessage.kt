package com.example.chat_impl.presentation.message

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OthersMessage(
    text: String,
    time: String,
    isLast: Boolean = true,
    username: String? = null,
    messageTextColor: Color = MaterialTheme.colorScheme.onSurface,
    timeTextColor: Color = MaterialTheme.colorScheme.onBackground,
    usernameTextColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    messageBodyTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    timeTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    usernameTextStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    cornerRoundShape: CornerBasedShape = MaterialTheme.shapes.small,
    paddingFromLeft: Dp = 12.dp,
    onMessageClick: () -> Unit = {},
    // Придумать, какие значения передавать для динамичкского кубического безэ
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .then(if(isLast) {
                Modifier.drawBehind() {
                    val widthPx = size.width
                    val heightPx = size.height

                    val path = Path().apply {

                        // Извечный прямой левый нижний угол
                        moveTo(paddingFromLeft.toPx(), heightPx * 0.5f)
                        lineTo(60.dp.toPx(), heightPx)
                        lineTo(paddingFromLeft.toPx(), heightPx)
                        lineTo(paddingFromLeft.toPx(), heightPx * 0.5f)

                        // Сумасшедший хвост
                        moveTo(paddingFromLeft.toPx(), heightPx * 0.5f)
                        lineTo(60.dp.toPx(), heightPx)
                        lineTo(2.dp.toPx(), heightPx)
                        quadraticTo(
                            x1 = 2.dp.toPx() + 20.dp.toPx(),
                            y1 = heightPx - 5.dp.toPx(),
                            x2 = paddingFromLeft.toPx(),
                            y2 = heightPx - 15.dp.toPx() - 5.dp.toPx(),
                        )
                    }
                    drawPath(
                        path = path,
                        color = backgroundColor,
                        style = Fill
                    )
                }
            } else Modifier )
            .padding(start = paddingFromLeft)
            .clickable(onClick = onMessageClick)
    ) {
        Box(
            modifier = Modifier
                .weight(0.8f)
                .width(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .background(
                        color = backgroundColor,
                        shape = cornerRoundShape
                    )
                    .padding(5.dp)
            ) {
                Column {
                    username?.let {
                        Text(
                            text = it,
                            style = usernameTextStyle,
                            color = usernameTextColor,
                        )
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = text,
                            modifier = Modifier
                                .wrapContentWidth(),
                            style = messageBodyTextStyle,
                            color = messageTextColor,
                        )

                        Box(
                            modifier = Modifier
                                .padding(
                                    start = 4.dp,
                                    top = 4.dp
                                )
                        ) {
                            Text(
                                text = time,
                                style = timeTextStyle,
                                color = timeTextColor,
                            )
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.weight(0.2f))
    }
}

@Preview(showBackground = true)
@Composable
fun OtherMessagePreview() {
    Surface {
        OthersMessage(
            "Got something",
            "Jul 18, 2025",
            true,
            "Mr. Bebra"
        )
    }
}
