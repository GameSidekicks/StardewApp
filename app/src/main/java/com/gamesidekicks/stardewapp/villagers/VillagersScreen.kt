package com.gamesidekicks.stardewapp.villagers

import com.gamesidekicks.stardewapp.DefaultTopAppBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gamesidekicks.data.domain.model.Villager
import com.gamesidekicks.stardewapp.R
import com.gamesidekicks.stardewapp.ui.theme.StardewAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillagersScreen(
    openDrawer: () -> Unit,
    viewModel: VillagersVM = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                openDrawer = openDrawer,
                titleRes = R.string.villagers,
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        VillagerList(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            villagers = uiState.items
        )
    }
}

@Composable
fun VillagerList(villagers: List<Villager>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(villagers) { villagers ->
            VillagerItem(villagers)
        }
    }
}

@Composable
fun VillagerItem(villager: Villager) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                top = dimensionResource(R.dimen.list_item_padding_vertical),
                bottom = dimensionResource(R.dimen.list_item_padding_vertical),
                start = dimensionResource(R.dimen.list_item_padding_start),
                end = dimensionResource(R.dimen.list_item_padding_end),
            )
            .clickable { TODO() }
    ) {
        AsyncImage(
            model = villager.image,
            placeholder = painterResource(R.drawable.ic_sample_wizard),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.avatar_image_size))
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colorScheme.inversePrimary)
        )
        Text(
            text = villager.name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = dimensionResource(R.dimen.horizontal_margin)
                )
        )
        Text(
            text = villager.birthday,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall,
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
        VillagerList(
            modifier = Modifier,
            villagers = listOf(
                Villager(
                    id = "1",
                    image = "https://stardewvalleywiki.com/mediawiki/images/thumb/0/04/Alex.png/80px-Alex.png",
                    name = "Alex",
                    birthday = "Summer 13"
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
            VillagerItem(
                Villager(
                    id = "1",
                    image = "https://stardewvalleywiki.com/mediawiki/images/thumb/0/04/Alex.png/80px-Alex.png",
                    name = "Alex",
                    birthday = "Summer 13"
                )
            )
        }
    }
}