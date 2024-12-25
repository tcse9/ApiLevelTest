package com.ovi.apileveltest.data.dto

import com.google.gson.annotations.SerializedName

data class FakeResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("has_previous")
	val hasPrevious: Boolean? = null,

	@field:SerializedName("total_page")
	val totalPage: Int? = null,

	@field:SerializedName("has_next")
	val hasNext: Boolean? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("error")
	val error: Error? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("arrival_time")
	val arrivalTime: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("tag")
	val tag: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("related_hadith")
	val relatedHadith: List<String?>? = null
)