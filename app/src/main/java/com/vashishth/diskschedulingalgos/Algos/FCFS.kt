package com.vashishth.diskschedulingalgos.Algos

fun FCFS(arr: ArrayList<Int>, head: Int): Int {
    var head = head
    var seek_count = 0
    var distance: Int
    var cur_track: Int
    for (i in 0 until arr.size) {
        cur_track = arr[i]
        // calculate absolute distance
        distance = Math.abs(cur_track - head)
        // increase the total count
        seek_count += distance
        // accessed track is now new head
        head = cur_track
    }
    return seek_count
}

fun fcfsSequence(arr : ArrayList<Int>) : ArrayList<Int>{
    return arr
}