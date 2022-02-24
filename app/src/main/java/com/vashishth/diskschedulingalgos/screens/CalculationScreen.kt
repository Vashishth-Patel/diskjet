package com.vashishth.diskschedulingalgos.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.Algos.*
import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.component.BottomNavigationBar
import com.vashishth.diskschedulingalgos.component.Button
import com.vashishth.diskschedulingalgos.component.DropdownMenu
import com.vashishth.diskschedulingalgos.component.inputField
import com.vashishth.diskschedulingalgos.model.BottomNavItem
import com.vashishth.diskschedulingalgos.navigation.Screen
import com.vashishth.diskschedulingalgos.ui.theme.ButtonBlue
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.LineChartData.*
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer

var arrangedList by mutableStateOf(listOf<Int>())

@Composable
fun CalculationScreen(navController: NavController) {

    Scaffold(bottomBar = {
        BottomNavigationBar(items = listOf(
            BottomNavItem(
                name = "Home",
                route = Screen.HomeScreen.route,
                Icon = Icons.Default.Home
            ),
            BottomNavItem(
                name = "Algos",
                route = Screen.AlgoListScreen.route,
                Icon = Icons.Default.Code
            ),
            BottomNavItem(
                name = "Calculation",
                route = Screen.CalculationScreen.route,
                Icon = Icons.Default.Calculate
            )
        ),
            navController = navController,
            onItemClick = {
                navController.navigate(it.route)
            })
    }
    ) { contentpaddingvalues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                InputCard(contentPaddingValues = contentpaddingvalues)
            }
        }
    }
}

val items = listOf(
    "FCFS",
    "SSTF",
    "SCAN",
    "C-SCAN"
)
var selectedAlgo: String = ""

var seektime by mutableStateOf(listOf<Int>(0))
var inputlist by mutableStateOf(listOf<Int>())
var arrayList = ArrayList<Int>()
//var points by mutableStateOf(listOf<LineChartData.Point>())


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputCard(onValChange: (String) -> Unit = {}, contentPaddingValues: PaddingValues) {
    val context = LocalContext.current
    var initialTrack: Int = 50
    var tailTrack: Int = 200
    var expanded = remember {
        mutableStateOf(false)
    }
    var enable = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = colorResource(id = R.color.lightblack),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(state = rememberLazyListState()) {
                items(inputlist) {
                    queueItem(num = it)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                initialTrack(enabled = enable.value) {
                }
                tailTrack(enabled = enable.value) {
                }
            }
            CalciScreen(context = context, tailTrack = tailTrack, initialTrack = initialTrack)
            Spacer(modifier = Modifier.height(10.dp))
            if (arrangedList.isNotEmpty()) {
                lineChartView()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun DiskValue(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {
    val valueState = remember {
        mutableStateOf("")
    }
    val validState = remember(valueState.value) {
        valueState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    inputField(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        valueState = valueState,
        labelId = "Enter Disk Queue",
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            //TODO - onvaluechange
            onValChange(valueState.value.trim())
            valueState.value = ""
            keyboardController?.hide()
        }
    )

}

@ExperimentalComposeUiApi
@Composable
fun initialTrack(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onValChange: (String) -> Unit = {}
) {
    val starthead = remember {
        mutableStateOf("50")
    }
    val validState = remember(starthead.value) {
        starthead.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    inputField(
        valueState = starthead,
        labelId = "Initial Track",
        enabled = enabled,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            //TODO - onvaluechange
            onValChange(starthead.value.trim())

            keyboardController?.hide()
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun tailTrack(modifier: Modifier = Modifier, enabled: Boolean, onValChange: (String) -> Unit = {}) {
    val tailTrack = remember {
        mutableStateOf("200")
    }
    val validState = remember(tailTrack.value) {
        tailTrack.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    inputField(
        valueState = tailTrack,
        labelId = "Tail Track",
        enabled = enabled,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            //TODO - onvaluechange
            onValChange(tailTrack.value.trim())

            keyboardController?.hide()
        }
    )
}

@Composable
fun queueItem(num: Int = 1) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .width(30.dp),
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        color = colorResource(id = R.color.purple_200)
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "$num")
        }

    }
}

@Composable
fun TestDropdownMenu() {
    var selectedIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    val buttonTitle = com.vashishth.diskschedulingalgos.component.items[selectedIndex]
    DropdownMenu(
        colorSelected = ButtonBlue,
        colorBackground = colorResource(id = R.color.blue),
        expanded = expanded,
        selectedIndex = selectedIndex,
        items = com.vashishth.diskschedulingalgos.component.items,
        onSelect = { index ->
            selectedIndex = index
            expanded = false
        },
        onDismissRequest = {
            expanded = false
        }) {
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                expanded = true
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBlue,
                contentColor = Color.White
            )
        ) {
            selectedAlgo = buttonTitle
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = buttonTitle,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow")
            }
        }
    }
}

@Composable
fun lineChartView() {
    var points = listOf<Point>()
    arrangedList.forEach {
        points += Point(it.toFloat(), "")
    }
    LineChart(
        lineChartData =
        LineChartData(
            points = points
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(10.dp)
            .background(Color.White, shape = RoundedCornerShape(5.dp)),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer()
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalciScreen(context: Context, tailTrack: Int, initialTrack: Int) {

    DiskValue() {
        if (inputlist.contains(it.toInt())) {
            Toast.makeText(
                context,
                "Element is already present in queue",
                Toast.LENGTH_SHORT
            ).show()
        } else if (it.toInt() < 0 || it.toInt() > tailTrack) {
            Toast.makeText(
                context,
                "Entered element is not in range defined",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            inputlist = inputlist + it.toInt()
            arrayList.add(it.toInt())
            Log.d("input", "$it")
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total Seek Movements :")
            Text(text = "${seektime.last()}")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Seek Sequence :", modifier = Modifier.padding(10.dp))
            LazyRow(state = rememberLazyListState()) {
                items(arrangedList) {
                    queueItem(it)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TestDropdownMenu()
        Button(
            onClick = {
                var tailtrack = tailTrack
                if (selectedAlgo.equals("FCFS")) {
                    //  FCFS
                    seektime = seektime + FCFS(arrayList, initialTrack)
                    arrangedList = fcfsSequence(arrayList)
                    Log.d("fcfs", fcfsSequence(arrayList).toString())
                }
                if (selectedAlgo.equals("SSTF")) {
                    //SSTF
                    seektime = seektime + shortestSeekTimeFirst(arrayList, initialTrack)
                    arrangedList = sstfSequence(arrayList, initialTrack)
                    Log.d("sstf", sstfSequence(arrayList, initialTrack).toString())
                }
                if (selectedAlgo.equals("SCAN")) {
                    //SCAN
                    seektime = seektime + SCAN(arrayList, initialTrack, tailtrack)
                    arrangedList = scanSequence()
                    Log.d(
                        "scan",
                        scanSequence().toString()
                    )
                }
                if (selectedAlgo.equals("C-SCAN")) {
                    //C-Scan
                    seektime = seektime + CSCAN(arrayList, initialTrack, tailtrack)
                    arrangedList = cscanSequence(arrayList, initialTrack, tailtrack)
                    Log.d(
                        "cscan",
                        cscanSequence(arrayList, initialTrack, tailTrack).toString()
                    )
                }
            },
            title = "Calculate"
        )
    }
}


// Testing




