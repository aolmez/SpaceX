package org.faruk.spacex.model

interface BaseListModel {

    val type: Int

    companion object {
        val TYPE_EMPTY = 100
        val TYPE_REGULAR = 101
    }

}