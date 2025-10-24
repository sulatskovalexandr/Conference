package com.example.partnerkintestapp.data.paging

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.partnerkintestapp.data.repository.ConferenceRepository
import java.text.SimpleDateFormat
import java.util.Calendar

class ConferencePagingSource(private val repository: ConferenceRepository) :
    PagingSource<Int, ConferenceListItem>() {


    override fun getRefreshKey(state: PagingState<Int, ConferenceListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ConferenceListItem> {
        var countMonthOnPage = 0
        return try {
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var currentMonthDate = ""
            val result = mutableListOf<ConferenceListItem>()
            val calendarPrev = Calendar.getInstance()
            val calendarNext = Calendar.getInstance()

            val page = params.key ?: 1
            val perPage = 5

            val response = repository.getConferences()
//                if (page == 1) pagingData1 else pagingData2
            val data = response.data
            val error = response.error
            if (data != null) {
                val conferences = data.result

                conferences.forEach { (_, conference) ->

                    if (currentMonthDate.isEmpty()) {
                        calendarPrev.time = sdf.parse(conference.startDate) ?: error("unknown date")
                        currentMonthDate = conference.startDate

                        result.add(ConferenceListItem.MonthItem(currentMonthDate))
                        countMonthOnPage++
                    } else {
                        calendarPrev.time = sdf.parse(currentMonthDate) ?: error("unknown date")
                        calendarNext.time = sdf.parse(conference.startDate) ?: error("unknown date")

                        val prevMonth = calendarPrev.get(Calendar.MONTH)
                        val nextMonth = calendarNext.get(Calendar.MONTH)

                        if (prevMonth != nextMonth) {
                            currentMonthDate = conference.startDate
                            result.add(ConferenceListItem.MonthItem(currentMonthDate))
                            countMonthOnPage++
                        }
                    }

                    result.add(
                        ConferenceListItem.ConferenceItem(
                            id = conference.id,
                            name = conference.name,
                            format = conference.format,
                            status = conference.status,
                            statusTitle = conference.statusTitle,
                            url = conference.url,
                            image = conference.image,
                            rating = conference.rating.orEmpty(),
                            startDate = conference.startDate,
                            endDate = conference.endDate,
                            oneDay = conference.oneDay,
                            customDate = conference.customDate.orEmpty(),
                            countryId = conference.countryId,
                            country = conference.country,
                            cityId = conference.cityId,
                            city = conference.city,
                            categories = conference.categories,
                            typeId = conference.typeId,
                            type = conference.type,
                        )
                    )
                }
                val nextKey = if (perPage > data.pagination.pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1

                LoadResult.Page(data = result, prevKey = prevKey, nextKey = nextKey)
            } else if (error != null) {
                LoadResult.Error(Exception(error.message))
            } else {
                error("data and error is null")
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}

enum class StatusTitle(val status: String, val title: String) {
    PUBLISHED(status = "publish", title = "Опубликована"),
    CANCELED(status = "canceled", title = "Отменена")
}
