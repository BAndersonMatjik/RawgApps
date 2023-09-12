package com.dev.rawgapps.feature.game

import androidx.recyclerview.widget.ListUpdateCallback

//unwrap data
//https://stackoverflow.com/questions/65112403/android-paging-3-get-list-of-data-from-pagingdatat-object
class TestListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}