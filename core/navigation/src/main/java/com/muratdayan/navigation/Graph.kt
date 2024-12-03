package com.muratdayan.navigation

sealed class Graph(val graph: String) {
    data object AuthGraph : Graph("auth_graph")
}