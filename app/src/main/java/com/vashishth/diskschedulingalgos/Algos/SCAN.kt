package com.vashishth.diskschedulingalgos.Algos

import java.util.*
import kotlin.collections.ArrayList

var scanSequence = ArrayList<Int>()


//time complexity o(nlogn)
fun SCAN(arr: ArrayList<Int>, head: Int, tail :Int) : Int {
    var head = head
    var direction = "right"
    var seek_count = 0
    var distance: Int
    var cur_track: Int
    val left = Vector<Int>()
    val right = Vector<Int>()
    val seek_sequence = Vector<Int>()

    // appending end values
    // which has to be visited
    // before reversing the direction
    if (direction === "left") left.add(0) else if (direction === "right") right.add(tail - 1)
    for (i in 0 until arr.size) {
        if (arr[i] < head) left.add(arr[i])
        if (arr[i] > head) right.add(arr[i])
    }

    // sorting left and right vectors
    Collections.sort(left)
    Collections.sort(right)

    // run the while loop two times.
    // one by one scanning right
    // and left of the head
    var run = 2
    while (run-- > 0) {
        if (direction === "left") {
            for (i in left.size - 1 downTo 0) {
                cur_track = left[i]

                // appending current track to seek sequence
                seek_sequence.add(cur_track)

                // calculate absolute distance
                distance = Math.abs(cur_track - head)

                // increase the total count
                seek_count += distance

                // accessed track is now the new head
                head = cur_track
            }
            direction = "right"
        } else if (direction === "right") {
            for (i in 0 until right.size) {
                cur_track = right[i]

                // appending current track to seek sequence
                seek_sequence.add(cur_track)

                // calculate absolute distance
                distance = Math.abs(cur_track - head)

                // increase the total count
                seek_count += distance

                // accessed track is now new head
                head = cur_track
            }
            direction = "left"
        }
    }
    scanSequence.clear()
    seek_sequence.forEach {
        scanSequence.add(it)
    }
    return seek_count
}

fun scanSequence():ArrayList<Int>{
    return scanSequence
}




