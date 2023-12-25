package com.vinaybyte.sample.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.vinaybyte.sample.Constants.IMAGE_URL
import com.vinaybyte.sample.R
import com.vinaybyte.sample.data.model.MovieItem
import com.vinaybyte.sample.ui.theme.AppTheme

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@Composable
fun ListItemsView(
    modifier: Modifier = Modifier,
    item: MovieItem,
    onItemClick: (MovieItem) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = {
                    onItemClick(item)
                }
            ),
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (image, title, content) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start)
                    }
                    .height(64.dp)
                    .width(64.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp)),
                model = "$IMAGE_URL${item.posterPath}",
                contentDescription = item.title,
                placeholder = painterResource(id = R.drawable.img),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    width = Dimension.fillToConstraints
                },
                text = item.title,
                maxLines = 1,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall

            )
            Text(
                modifier = Modifier
                    .constrainAs(content) {
                        start.linkTo(image.end, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                        top.linkTo(title.bottom)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                text = item.overview,
                maxLines = 2,
                style = MaterialTheme.typography.bodySmall,
            )
        }

    }
}

@Composable
@Preview(name = "HomeItemsView Light")
private fun ListItemsViewPreviewLight() {
    AppTheme(darkTheme = false) {
        ListItemsView(
            item = MovieItem.mock(),
            onItemClick = { }
        )
    }
}
