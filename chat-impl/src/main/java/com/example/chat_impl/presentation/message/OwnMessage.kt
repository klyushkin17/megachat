package com.example.chat_impl.presentation.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.chatTheme.ChatColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OwnMessage(
    text: String,
    time: String,
    isLast: Boolean,
    onMessageClick: (id: String) -> Unit = {},
    color: Color = ChatColors.defaultOwnMessageColor,
    cornerRadius: Dp = 10.dp,
    paddingFromRight: Dp = 12.dp,
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

                        // Извечный прямой правый нижний угол
                        moveTo(widthPx - paddingFromRight.toPx(), heightPx * 0.5f)
                        lineTo(widthPx - 70.dp.toPx(), heightPx)
                        lineTo(widthPx - paddingFromRight.toPx(), heightPx)
                        lineTo(widthPx - paddingFromRight.toPx(), heightPx * 0.5f)

                        // Сумасшедший хвост
                        moveTo(widthPx - paddingFromRight.toPx(), heightPx * 0.5f)
                        lineTo(widthPx - 70.dp.toPx(), heightPx)
                        lineTo(widthPx - 2.dp.toPx(), heightPx)

                        quadraticTo(
                            x1 = widthPx - 2.dp.toPx() - 20.dp.toPx(),
                            y1 = heightPx - 5.dp.toPx(),
                            x2 = widthPx - paddingFromRight.toPx(),
                            y2 = heightPx - 15.dp.toPx() - 5.dp.toPx(),
                        )
                    }
                    drawPath(
                        path = path,
                        color = color,
                        style = Fill
                    )
                }
            } else Modifier )
            .padding(end = paddingFromRight)
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Box(
            modifier = Modifier
                .weight(0.8f)
                .width(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(
                        color = color,
                        shape = RoundedCornerShape(cornerRadius)
                    )
                    .padding(5.dp)
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .wrapContentWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .padding(
                                start = 4.dp,
                                top = 4.dp
                            )
                    ) {
                        Text(text = time)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OwnMessagePreview() {
    Surface {
        OwnMessage(
            "Got something for ya",
            "Jul 18, 2025",
            false,
            onMessageClick = {},
        )
    }
}