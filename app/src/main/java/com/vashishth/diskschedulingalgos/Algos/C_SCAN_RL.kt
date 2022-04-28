package com.vashishth.diskschedulingalgos.Algos

import java.util.*
import kotlin.collections.ArrayList

var csanSequenceRl = ArrayList<Int>()
//time complexity nlog(n)
fun CSCANRL(arr: ArrayList<Int>, head: Int,tail : Int) : Int{
    var head = head
    var seek_count = 0
    var distance: Int
    var cur_track: Int
    val left: Vector<Int> = Vector<Int>()
    val right: Vector<Int> = Vector<Int>()
    val seek_sequence: Vector<Int> = Vector<Int>()

    // Appending end values which has
    // to be visited before reversing
    // the direction
    left.add(0)
    right.add(tail - 1)

    //time complexity is O(n)
    for (i in 0 until arr.size) {
        if (arr[i] < head) left.add(arr[i])
        if (arr[i] > head) right.add(arr[i])
    }

    // Sorting left and right vectors
    Collections.sort(left)
    Collections.reverse(left)
    Collections.sort(right)
    Collections.reverse(right)


    //time complexity is O(n)

    for (i in 0 until left.size) {
        cur_track = left.get(i)

        // Appending current track to seek sequence
        seek_sequence.add(cur_track)

        // Calculate absolute distance
        distance = Math.abs(cur_track - head)

        // Increase the total count
        seek_count += distance

        // Accessed track is now new head
        head = cur_track
    }

    // Once reached the left end
    // jump to the end.
    head = tail

    // adding seek count for head returning from 199 to
    // 0
    seek_count += tail - 1

    // Now service the requests again
    // which are left.
    //time complexity is O(n)
    for (i in 0 until right.size) {
        cur_track = right.get(i)

        // Appending current track to
        // seek sequence
        seek_sequence.add(cur_track)

        // Calculate absolute distance
        distance = Math.abs(cur_track - head)

        // Increase the total count
        seek_count += distance

        // Accessed track is now the new head
        head = cur_track
    }
    csanSequenceRl.clear()
    seek_sequence.forEach {
        csanSequenceRl.add(it)
    }

    return seek_count
}

fun cscanSequenceRl() : ArrayList<Int>{
    return csanSequenceRl
}