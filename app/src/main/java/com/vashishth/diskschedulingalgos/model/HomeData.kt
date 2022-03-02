package com.vashishth.diskschedulingalgos.model

import androidx.compose.ui.graphics.Color
import com.vashishth.diskschedulingalgos.ui.theme.*

data class HomeData(
    val title:String,
    val Info:String,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)

fun getHomeData():List<HomeData>{
    return listOf(
        HomeData(
            title = "Disk Scheduling Algorithms",
            Info = "As we know, a process needs two type of time, CPU time and IO time. For I/O, it requests the Operating system to access the disk.\n" +
                    "\n" +
                    "However, the operating system must be fare enough to satisfy each request and at the same time, operating system must maintain the efficiency and speed of process execution.\n" +
                    "\n" +
                    "The technique that operating system uses to determine the request which is to be satisfied next is called disk scheduling.",
            lightColor = LightGreen1,
            mediumColor = LightGreen2,
            darkColor = LightGreen3
        ),
        HomeData(
            title = "Types Of Disk Scheduling Algos",
            Info = "The list of various disks scheduling algorithm is given below. Each algorithm is carrying some advantages and disadvantages. The limitation of each algorithm leads to the evolution of a new algorithm.\n" +
                                "\n" +
                                "FCFS scheduling algorithm\n" +
                                "SSTF (shortest seek time first) algorithm\n" +
                                "SCAN scheduling\n" +
                                "C-SCAN scheduling\n" +
                                "LOOK Scheduling\n" +
                                "C-LOOK scheduling",
            lightColor = BlueViolet1,
            mediumColor = BlueViolet2,
            darkColor = BlueViolet3
        )

    )
}
