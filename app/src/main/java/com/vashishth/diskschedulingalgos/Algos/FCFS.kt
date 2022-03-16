package com.vashishth.diskschedulingalgos.Algos

//calculates seek count for fcfs algorithm
fun FCFS(arr: ArrayList<Int>, head: Int): Int {
    var head = head
    var seek_count = 0
    var distance: Int
    var current_track: Int
    //only one for loop
    //O(n) - Time Complexity
    for (i in 0 until arr.size) {
        current_track = arr[i]
        // calculate absolute distance
        distance = Math.abs(current_track - head)
        // increase the total count
        seek_count += distance
        // accessed track is now new head
        head = current_track
    }
    return seek_count
}

//returns sorted list based on fcfs algorithm
fun fcfsSequence(arr : ArrayList<Int>) : ArrayList<Int>{
    return arr
}