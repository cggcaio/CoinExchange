package com.cggcaio.coinexchange.exchange.presentation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails_1
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeDetailsViewModel
import com.cggcaio.core.constants.RequestStatusEnum
import com.cggcaio.core.constants.RequestStatusEnum.ERROR
import com.cggcaio.core.constants.RequestStatusEnum.LOADING
import com.cggcaio.core.constants.RequestStatusEnum.SUCCESS
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExchangeDetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState_ShowsShimmerEffect() {
        composeTestRule.setContent {
            ExchangeDetailsScreen(
                exchangeId = "BINANCE",
                detailsViewModel =
                    object : BaseExchangeDetailsViewModel {
                        override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = null)
                        override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = LOADING)
                        override val error: State<String?> get() = mutableStateOf(value = null)

                        override fun getExchangeDetails(id: String) {}
                    },
                goToBack = {},
                goToWeb = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "DetailsShimmer").assertExists()
        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "DetailsExchangeView").assertDoesNotExist()
    }

    @Test
    fun testErrorState() {
        composeTestRule.setContent {
            ExchangeDetailsScreen(
                exchangeId = "BINANCE",
                detailsViewModel =
                    object : BaseExchangeDetailsViewModel {
                        override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = null)
                        override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = ERROR)
                        override val error: State<String?> get() = mutableStateOf(value = null)

                        override fun getExchangeDetails(id: String) {}
                    },
                goToBack = {},
                goToWeb = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertExists()
        composeTestRule.onNodeWithTag(testTag = "DetailsShimmer").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "DetailsExchangeView").assertDoesNotExist()
    }

    @Test
    fun testSuccessState() {
        composeTestRule.setContent {
            ExchangeDetailsScreen(
                exchangeId = "BINANCE",
                detailsViewModel =
                    object : BaseExchangeDetailsViewModel {
                        override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = exchangeDetails_1)
                        override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = SUCCESS)
                        override val error: State<String?> get() = mutableStateOf(value = null)

                        override fun getExchangeDetails(id: String) {}
                    },
                goToBack = {},
                goToWeb = {},
            )
        }

        composeTestRule.onNodeWithText(text = exchangeDetails_1.name ?: "").assertExists()
        composeTestRule.onNodeWithText(text = "ID: ${exchangeDetails_1.exchangeId}").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.volume1HrsUsd ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.volume1DayUsd ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.volume1MthUsd ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.website ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataQuoteStart ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataQuoteEnd ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataOrderStart ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataOrderEnd ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataTradeStart ?: "").assertExists()
        composeTestRule.onNodeWithText(text = exchangeDetails_1.dataTradeEnd ?: "").assertExists()

        composeTestRule.onNodeWithTag(testTag = "ErrorView").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "DetailsShimmer").assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "DetailsExchangeView").assertExists()
    }

    @Test
    fun testSuccessState_clickGoBack() {
        var actionGoBack = false
        composeTestRule.setContent {
            ExchangeDetailsScreen(
                exchangeId = "BINANCE",
                detailsViewModel =
                    object : BaseExchangeDetailsViewModel {
                        override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = exchangeDetails_1)
                        override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = SUCCESS)
                        override val error: State<String?> get() = mutableStateOf(value = null)

                        override fun getExchangeDetails(id: String) {}
                    },
                goToBack = { actionGoBack = true },
                goToWeb = {},
            )
        }

        composeTestRule.onNodeWithTag(testTag = "ToolbarBackPress").performClick()
        assert(actionGoBack)
    }
}
