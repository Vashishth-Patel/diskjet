package com.vashishth.diskschedulingalgos.Algos

//fun FCFS(head : Int,
//         queue:List<Int>
//): Int{
//    var seek:Int = 0
//    var diff:Int = 0
//
//    for (j in 1..queue.size-1){
//            diff = Math.abs(queue[j]-queue[j-1])
//            seek += diff
//    }
//    return seek
//}

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
//    println(
//        "Total number of " +
//                "seek operations = " +
//                seek_count
//    )
    return seek_count
}

fun fcfsSequence(arr : ArrayList<Int>) : ArrayList<Int>{
    return arr
}