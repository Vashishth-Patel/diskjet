package com.vashishth.diskschedulingalgos.Algos

class node {
    // represent difference between
    // head position and track number
    var distance = 0

    // true if track has been accessed
    var accessed = false
}

var sstfSequence = ArrayList<Int>()

//Time Complexity - o(n^2)
fun shortestSeekTimeFirst(
    request: ArrayList<Int>,
    head: Int
):Int {
    var head = head
    if (request.size == 0) return 0

    // create array of objects of class node
    val diff = arrayOfNulls<node>(request.size)


    //Time complexity - only one for loop o(n)
    // initialize array
    for (i in diff.indices) diff[i] = node()

    // count total number of seek operation
    var seek_count = 0

    // stores sequence in which disk access is done
    val seek_sequence = IntArray(request.size + 1)

    //only one for loop time complexity will be o(n)
    for (i in request.indices) {
        seek_sequence[i] = head
        calculateDifference(request, head, diff)
        val index = findMin(diff)
        diff[index]!!.accessed = true

        // increase the total count
        seek_count += diff[index]!!.distance

        // accessed track is now new head
        head = request[index]
    }

    // for last accessed track
    seek_sequence[seek_sequence.size - 1] = head

    sstfSequence.clear()

    //onr for each loop time complexity will be o(n)
    if (sstfSequence.isEmpty()){
        seek_sequence.forEach {
            sstfSequence.add(it)
        }
    }
    return seek_count
}

//calculates difference
//o(n)
fun calculateDifference(
    queue: ArrayList<Int>,
    head: Int, diff: Array<node?>
) {
    for (i in diff.indices) diff[i]!!.distance = Math.abs(queue[i] - head)
}

//time complexity o(n)
fun findMin(diff: Array<node?>): Int {
    var index = -1
    var minimum = Int.MAX_VALUE
    for (i in diff.indices) {
        if (!diff[i]?.accessed!! && minimum > diff[i]!!.distance) {
            minimum = diff[i]!!.distance
            index = i
        }
    }
    return index
}

//returns sorted arr based on sstf algorithm
fun sstfSequence() : ArrayList<Int> {
    return sstfSequence
}