package com.app.goustotask.testUtil

import com.app.goustotask.data.model.*
import com.app.goustotask.data.remote.DataState


class MockTestUtil {
    companion object {

        fun getMockProductsError() = DataState.CustomMessages.BadRequest

        fun getMockProducts(count: Int): ProductDTO {
            val mockImageUrl =
                "https://production-media.gousto.co.uk/cms/product-image-landscape/YAddOns-WhiteWines-Borsao_013244-x750.jpg"
            val imagesHashMap = hashMapOf("src" to MyImage(mockImageUrl))
            val data = arrayListOf<Data>()
            val item = Data(
                id = "0009468c-33e9-11e7-b485-02859a19531d",
                title = "Borsao Macabeo",
                description = "A flavoursome Summer wine made from the indigenous Macabeo grape in northern Spain. A balanced, modern white with flavours of ripe peach, zesty lemon and nutty undertones, it leaves the palate with a clean and fruity finish.",
                price = "6.95",
                isForSale = false,
                ageRestricted = true,
                volume = 1000,
                zone = "Ambient",
                categories = arrayListOf<Category>(),
                images = imagesHashMap
            )

            for (i in 0 until count) {
                data.add(item)
            }

            return ProductDTO("ok", data)
        }

    }
}
