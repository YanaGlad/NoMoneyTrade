package internal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nomoneytrade.R
import com.example.nomoneytrade.showcase.ProductPreview

@Composable
fun ProductListItem(productPreview: ProductPreview, onClick: ()-> Unit) {
    //TODO загрузка по url
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(top = 16.dp, end = 8.dp, start = 8.dp)
            .clickable(
                onClick = {
                    onClick()
                }
            )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.stub),
                modifier = Modifier
                    .width(140.dp)
                    .align(Alignment.CenterVertically)
                    .padding(top = 10.dp, start = 5.dp, bottom = 10.dp),
                contentDescription = "profile photo",
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(start = 6.dp, top = 5.dp)) {
                Text(
                    text = productPreview.title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = productPreview.tags.getTags(),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                )

                Text(
                    text = productPreview.description.getModifiedText(),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                )
            }
        }
    }
}


private fun List<String>.getTags(): String {
    var result = ""
    this.forEach {
        result += "$it "
    }
    return result
}

private fun String.getModifiedText(): String {
    return if (length > 75)
        substring(0, 70) + "..."
    else
        this
}
