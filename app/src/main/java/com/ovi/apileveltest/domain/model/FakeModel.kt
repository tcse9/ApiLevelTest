package com.ovi.apileveltest.domain.model

import com.google.gson.annotations.SerializedName

data class FakeModel(
    val description: String = "",
    val source: String = "",
    val title: String = "",
)