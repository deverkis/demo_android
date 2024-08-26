import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.kt66.codelabs1.R
import cn.kt66.codelabs1.data.Dog
import cn.kt66.codelabs1.data.dogs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Unit3Test35() {
    Scaffold(
        topBar = {
            WoofTopAppBar2()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(dogs) {
                DogItem2(
                    dog = it,
                    //LazyColumn。 对内边距使用以下界面规范。
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of dogs.
 */

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogItem2(
    dog: Dog, modifier: Modifier = Modifier
) {
    //卡片高度为 2dp
    //探索不同的内边距和尺寸选项；根据 Material 3 准则，内边距应以 4dp 为增量。
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        //列表项的高度为 72dp，内边距为 16dp
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 72.dp)
                .padding(16.dp)
        ) {
            //文字占满, 图片靠右
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = dog.name),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = dog.hobbies),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //图片和文字之间的间距为 16dp
            Spacer(Modifier.width(16.dp))
            //图片的裁剪半径为 8dp
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                DogIcon2(DogIcon2 = dog.imageResourceId)
            }
        }
    }
}

/**
 * Composable that displays a photo of a dog.
 *
 * @param DogIcon2 is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogIcon2(
    @DrawableRes DogIcon2: Int, modifier: Modifier = Modifier
) {
    Image(
        contentScale = ContentScale.FillWidth,//添加 ContentScale 和 Crop 属性，这会根据显示大小裁剪图片
        painter = painterResource(DogIcon2),
        alignment = Alignment.TopCenter,

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogInformation2(
    @StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar2(modifier: Modifier = Modifier) {
    //Scaffold 是一种布局，可为各种组件和屏幕元素（如 Image、Row 或 Column）提供槽。
    //Scaffold 支持 contentWindowInsets 参数
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge
        )
    })

}
