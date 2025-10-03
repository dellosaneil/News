package com.thelazybattley.feature.util

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.core.R

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var value by rememberSaveable { mutableStateOf("") }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .height(height = 48.dp),
        value = value,
        onValueChange = {
            value = it
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 4.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xff4E4B66),
                        shape = RoundedCornerShape(size = 4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                Spacer(modifier = Modifier.width(width = 8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        innerTextField()
                        Text(text = stringResource(id = com.thelazybattley.feature.R.string.search))
                    } else {
                        innerTextField()
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        singleLine = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSearchBar() {
    SearchBar(modifier = Modifier)
}
