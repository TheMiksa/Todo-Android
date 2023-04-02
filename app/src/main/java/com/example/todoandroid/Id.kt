package com.example.todoandroid

import kotlin.random.Random

class Id {

    companion object {
        private const val MIN_ID = 1
        private const val MAX_ID: Int = Int.MAX_VALUE // 2_147_483_647
        private val keySet = mutableSetOf<Pair<String, MutableList<Int>>>()

        val getId: () -> Int = { Random.nextInt(MIN_ID, MAX_ID)}
        val getUniqId: (existingIds: MutableList<Int>) -> Int = {
            var memorySaver = 0
            var id = getId()

            while (it.find { existingId -> existingId == id } != null || memorySaver > 1000) {
                id = getId()
                memorySaver++
            }
            if (memorySaver > 1000) {
                println("TodoList -> getUniqId (memorySaver) --- cannot find uniq id.")
            }
            id
        }

        val getQniqIbyGroup: (key: String) -> Int = { key ->
            val pair = keySet.find { pair -> pair.first == key }

            if (pair == null) {
                val newId = getId()
                keySet.add(Pair(key, mutableListOf(newId)))
                newId
            } else {
                val idList = pair.second
                val newId = getUniqId(idList)
                idList.add(newId)

                keySet.removeIf { it.first == key }
                keySet.add(Pair(key, idList))
                newId
            }
        }
    }
}