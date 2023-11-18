package com.tmu.studybudd.view.home.student.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tmu.studybudd.util.TimeStampUtils

@Composable
fun ReceiverMessage(sender: String, text: String, timeStamp: Long) {
    Column {
        Text(text = "$sender: ")
        Row(
            Modifier.padding(end = 64.dp).background(Color(0xFFFFFFFF)).wrapContentSize()
                .padding(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = text, Modifier.weight(1f))
            Text(text = TimeStampUtils.formatTimestampToDateString(timeStamp), fontSize = 8.sp)
        }
    }
}

@Composable
fun SenderMessage(text: String, timeStamp: Long) {
    Column {
        Text(
            modifier = Modifier.padding(start = 64.dp),
            text = "You"
        )
        Row(
            Modifier.padding(start = 64.dp).background(
                Color(0xFFDBF1C1)
            ).wrapContentSize().padding(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = text, Modifier.weight(1f))
            Text(text = TimeStampUtils.formatTimestampToDateString(timeStamp), fontSize = 8.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0x983382)
@Composable
fun ReceiverMessagePreview() {
    SenderMessage(text = "asdfsadf", System.currentTimeMillis())
}
