package com.vashishth.diskschedulingalgos.model

import androidx.compose.ui.graphics.Color
import com.vashishth.diskschedulingalgos.ui.theme.*

data class Algorithm(
    val title: String,
    val about:String,
    val advantage:String,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
    val disadvantage:String
)

fun getAlgos():List<Algorithm>{
    return listOf(
        Algorithm(title = "FCFS",
        about = "It is the simplest Disk Scheduling algorithm. It services the IO requests in the order in which they arrive. There is no starvation in this algorithm, every request is serviced.",
        advantage = "1. Every request gets a fair chance\n" +
                "2. No indefinite postponement",
            disadvantage = "1 .Does not try to optimize seek time\n" +
                    "2. May not provide the best possible service",
            lightColor = BlueViolet1,
            mediumColor = BlueViolet2,
            darkColor = BlueViolet3
            ),
        Algorithm(title = "SSTF",
        about = "Shortest seek time first (SSTF) algorithm selects the disk I/O request which requires the least disk arm movement from its current position regardless of the direction. It reduces the total seek time as compared to FCFS.\n" +
                "\n" +
                "It allows the head to move to the closest track in the service queue.",
        advantage = "1. Average Response Time decreases\n" +
                "2. Throughput increases",
        disadvantage = "1. It may cause starvation for some requests.\n" +
                "2. Switching direction on the frequent basis slows the working of algorithm.\n" +
                "3. It is not the most optimal algorithm.",
            lightColor = LightGreen1,
            mediumColor = LightGreen2,
            darkColor = LightGreen3),
        Algorithm(title = "SCAN",
        about = "It is also called as Elevator Algorithm. In this algorithm, the disk arm moves into a particular direction till the end, satisfying all the requests coming in its path,and then it turns backand moves in the reverse direction satisfying requests coming in its path.\n" +
                "\n" +
                "It works in the way an elevator works, elevator moves in a direction completely till the last floor of that direction and then turns back.",
        advantage = "1. High throughput\n" +
                "2. Low variance of response time\n" +
                "3. Average response time",
            disadvantage = "1. Long waiting time for requests for locations just visited by disk arm",
            lightColor = OrangeYellow1,
            mediumColor = OrangeYellow2,
            darkColor = OrangeYellow3),
        Algorithm(title = "CSCAN",
        about = "In C-SCAN algorithm, the arm of the disk moves in a particular direction servicing requests until it reaches the last cylinder, then it jumps to the last cylinder of the opposite direction without servicing any request then it turns back and start moving in that direction servicing the remaining requests.",
        advantage = "1. Provides more uniform wait time compared to SCAN",
        disadvantage = "1. It causes more seek movements as compared to SCAN Algorithm.\n" +
                "2. It causes the head to move till the end of the disk even if there are no requests to be serviced.",
            lightColor = Beige1,
            mediumColor = Beige2,
            darkColor = Beige3),
    )
}

