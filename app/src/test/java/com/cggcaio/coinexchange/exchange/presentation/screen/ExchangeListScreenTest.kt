package com.cggcaio.coinexchange.exchange.presentation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeList
import com.cggcaio.coinexchange.constants.ObjectsTest.exchange_1
import com.cggcaio.coinexchange.constants.ObjectsTest.exchange_2
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.EMPTY_LIST
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.ERROR
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.LOADING
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExchangeListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState_ShowsShimmerEffect() {
        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = mutableStateOf(LOADING)
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = mutableStateOf(null)
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {}
                    },
                goToDetailScreen = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "CustomShimmer").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "EmptyListView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "ExchangeListView").assertDoesNotExist()
    }

    @Test
    fun testErrorState() {
        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = mutableStateOf(ERROR)
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = mutableStateOf(null)
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {}
                    },
                goToDetailScreen = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertExists()
        composeTestRule.onNodeWithTag(testTag = "CustomShimmer").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "EmptyListView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "ExchangeListView").assertDoesNotExist()
    }

    @Test
    fun testEmptyListState() {
        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = mutableStateOf(EMPTY_LIST)
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = mutableStateOf(emptyList())
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {}
                    },
                goToDetailScreen = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "EmptyListView").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "CustomShimmer").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "ExchangeListView").assertDoesNotExist()
    }

    @Test
    fun testSuccessState() {
        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = mutableStateOf(ExchangeListStatusEnum.SUCCESS)
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = mutableStateOf(exchangeList)
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {}
                    },
                goToDetailScreen = {},
            )
        }

        composeTestRule.onNodeWithText(text = exchange_1.name).assertExists()
        composeTestRule.onNodeWithText(text = exchange_2.name).assertExists()
        composeTestRule.onNodeWithText(text = exchange_1.id).assertExists()
        composeTestRule.onNodeWithText(text = exchange_2.id).assertExists()
        composeTestRule.onNodeWithText(text = exchange_1.volume1DayUsd).assertExists()
        composeTestRule.onNodeWithText(text = exchange_2.volume1DayUsd).assertExists()
        composeTestRule.onNodeWithTag(testTag = "ExchangeListView").assertExists()
        composeTestRule.onNodeWithTag(testTag = "EmptyListView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "CustomShimmer").assertDoesNotExist()
    }

    @Test
    fun testFilterExchanges() {
        val filteredList = mutableStateOf(exchangeList)
        val listStatus = mutableStateOf(ExchangeListStatusEnum.SUCCESS)

        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = listStatus
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = filteredList
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {
                            filteredList.value = exchangeList.filter { it.name.contains(query, ignoreCase = true) }
                            if (filteredList.value.isEmpty()) listStatus.value = EMPTY_LIST
                        }
                    },
                goToDetailScreen = {},
            )
        }
        composeTestRule.onNodeWithTag(testTag = "CustomTextField").performTextInput(exchange_1.name)
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag(testTag = "ExchangeNameText", useUnmergedTree = true)
            .assertTextEquals(exchange_1.name)
        composeTestRule.onNodeWithText(text = exchange_2.name).assertDoesNotExist()

        composeTestRule.onNodeWithTag(testTag = "CustomTextField").performTextInput(text = "xxx")
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(testTag = "ExchangeNameText", useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "ExchangeListView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "EmptyListView").assertExists()
    }

    @Test
    fun testNavigateToDetailScreen() {
        var navigateToDetailCalled = false

        composeTestRule.setContent {
            ExchangeListScreen(
                exchangeViewModel =
                    object : BaseExchangeListViewModel {
                        override val listStatus: State<ExchangeListStatusEnum>
                            get() = mutableStateOf(ExchangeListStatusEnum.SUCCESS)
                        override val visibleExchanges: State<List<Exchange>?>
                            get() = mutableStateOf(exchangeList)
                        override val error: State<String?>
                            get() = mutableStateOf(null)

                        override fun getExchanges() {}

                        override fun filterExchanges(query: String) {}
                    },
                goToDetailScreen = { id ->
                    navigateToDetailCalled = true
                },
            )
        }

        composeTestRule.onNodeWithText("Binance").performClick()

        assert(navigateToDetailCalled)
    }
}
