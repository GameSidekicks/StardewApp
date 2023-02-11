package com.gamesidekicks.stardewapp.gifts

import com.gamesidekicks.stardewapp.DefaultTopAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamesidekicks.data.domain.model.Gift
import com.gamesidekicks.data.domain.model.ItemCollection
import com.gamesidekicks.stardewapp.R
import com.gamesidekicks.stardewapp.ui.theme.StardewAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftsScreen(
    openDrawer: () -> Unit,
    //viewModel: TasksViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { DefaultTopAppBar(openDrawer = openDrawer, R.string.gifts) },
        content = {
            GiftList(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
                gifts = mapOf(
                    ItemCollection.COOKING to listOf(
                        Gift(
                            painterResource(R.drawable.ic_sample_cooking),
                            "Fried Egg",
                            ItemCollection.COOKING
                        )
                    ),
                    ItemCollection.MINERALS to listOf(
                        Gift(
                            painterResource(R.drawable.ic_sample_mineral),
                            "Ruby",
                            ItemCollection.MINERALS
                        )
                    )
                )
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GiftList(gifts: Map<ItemCollection, List<Gift>>, modifier: Modifier) {
    Column(modifier = modifier) {
        LazyColumn {
            gifts.forEach { (collection, gifts) ->
                stickyHeader {
                    GiftHeader(collection)
                }
                items(gifts) { gift ->
                    GiftItem(gift)
                }
            }
        }
    }
}

@Composable
fun GiftHeader(collection: ItemCollection) {
    Text(
        collection.name,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(
                top = dimensionResource(R.dimen.list_item_padding_vertical),
                bottom = dimensionResource(R.dimen.list_item_padding_vertical),
                start = dimensionResource(R.dimen.list_item_padding_start),
                end = dimensionResource(R.dimen.list_item_padding_end),
            )
    )
}

@Composable
fun GiftItem(gift: Gift) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.horizontal_margin),
                vertical = dimensionResource(R.dimen.list_item_padding_vertical),
            )
            .clickable { TODO() }
    ) {
        Image(
            painter = gift.icon,
            contentDescription = null,
            Modifier.padding(4.dp)
        )
        Text(
            text = gift.name,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.horizontal_margin)
                )
        )
    }
}

// PREVIEWS ----------------------------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StardewAppTheme {
        GiftList(
            modifier = Modifier,
            gifts = mapOf(
                ItemCollection.COOKING to listOf(
                    Gift(
                        painterResource(R.drawable.ic_sample_cooking),
                        "Fried Egg",
                        ItemCollection.COOKING
                    )
                ),
                ItemCollection.MINERALS to listOf(
                    Gift(
                        painterResource(R.drawable.ic_sample_mineral),
                        "Ruby",
                        ItemCollection.MINERALS
                    )
                )
            )
        )
    }
}

@Preview
@Composable
private fun EmptyListPreview() {
    TODO()
}

@Preview
@Composable
private fun CollectionItemPreview() {
    StardewAppTheme {
        Surface {
            GiftItem(
                Gift(
                    painterResource(R.drawable.ic_sample_cooking),
                    "Fried Egg",
                    ItemCollection.COOKING
                )
            )
        }
    }
}