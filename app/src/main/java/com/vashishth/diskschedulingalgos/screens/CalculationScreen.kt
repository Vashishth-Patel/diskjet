package com.vashishth.diskschedulingalgos.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.Algos.*
import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.component.*
import com.vashishth.diskschedulingalgos.model.BottomNavItem
import com.vashishth.diskschedulingalgos.navigation.Screen
import com.vashishth.diskschedulingalgos.ui.theme.ButtonBlue
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.LineChartData.*
import me.bytebeats.views.charts.line.LineChartData.Point
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer

var arrangedList by mutableStateOf(listOf<Int>())
var data =  mutableStateOf("PLEASE CALCULATE FIRST")
val openDialog =  mutableStateOf(false)

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
            color = Color.Black
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
    var show = remember {
        mutableStateOf(false)
    }
    var enable = remember {
        mutableStateOf(true)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = Color.Black,
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
//TODO//
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                initialTrack(enabled = enable.value) {
                    if(it.toInt() < tailTrack) {
                        initialTrack = it.toInt()
                        inputlist = listOf()
                        arrayList.clear()
                    }else{
                        //TODO//
                        Toast.makeText(
                            context,
                            "initial track must be less than tail track",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                tailTrack(enabled = enable.value) {
                        tailTrack = it.toInt()
                        inputlist = listOf()
                        arrayList.clear()
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            //TODO
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

            Spacer(modifier = Modifier.height(2.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//trial
                //TODO trial sms
                Row(modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically) {

                    Button(onClick = {
                        if (inputlist.isNotEmpty()) {
                            arrayList.clear()
                            var i = inputlist.lastIndex
                            inputlist = inputlist - inputlist[i]
                            if (inputlist.isNotEmpty()) {
                                inputlist.forEach {
                                    arrayList.add(it)
                                }
                            }
                        }
                    }, title = "POP")

                    //TODO SMS SENDING
                    Card(modifier = Modifier.width(170.dp).padding(all = 10.dp).height(50.dp),
                        backgroundColor = ButtonBlue,
                        elevation = 4.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp))) {
                        if (openDialog.value) {
                            alert(openDialog.value)
                        }
                        Row(
                            modifier = Modifier.clickable(
                                onClick = { openDialog.value = true }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("GET DATA", modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Total Seek Movements :")
                    Text(text = "${seektime.last()}")
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Seek Sequence :", modifier = Modifier.padding(10.dp))
                    LazyRow(state = rememberLazyListState()) {
                        if(arrangedList.isNotEmpty()) {
                            items(
                                arrangedList
                            ) {
                                queueItem(it)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

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
                            data = mutableStateOf("YOU HAVE SELECTED :- FCFS \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime[1]}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                            Log.d("fcfs", fcfsSequence(arrayList).toString())
                        }
                        if (selectedAlgo.equals("SSTF")) {
                            //SSTF
                            seektime = seektime + shortestSeekTimeFirst(arrayList, initialTrack)
                            arrangedList = sstfSequence()
                            data = mutableStateOf("YOU HAVE SELECTED :- SSTF \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime.last()}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                        }
                        if (selectedAlgo.equals("SCAN L-R")) {
                            //SCAN
                            seektime = seektime + SCAN(arrayList, initialTrack, tailtrack)
                            arrangedList = scanSequence()
                            data = mutableStateOf("YOU HAVE SELECTED :- SCAN L-R \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime[1]}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                        }
                        if (selectedAlgo.equals("C-SCAN L-R")) {
                            //C-Scan
                            seektime = seektime + CSCAN(arrayList, initialTrack, tailtrack)
                            arrangedList = cscanSequence()
                            data = mutableStateOf("YOU HAVE SELECTED :- C-SCAN L-R \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime[1]}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                        }
                        if (selectedAlgo.equals("C-SCAN R-L")){
                            seektime = seektime + CSCANRL(arrayList,initialTrack,tailTrack)
                            arrangedList = cscanSequenceRl()
                            data = mutableStateOf("YOU HAVE SELECTED :- C-SCAN R-L \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime[1]}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                        }
                        if(selectedAlgo.equals("SCAN R-L")){
                            seektime = seektime + SCAN_RL(arrayList,initialTrack,tailTrack)
                            arrangedList = scanSequenceRl()
                            data = mutableStateOf("YOU HAVE SELECTED :- SCAN R-L \n" +
                                    "YOU HAVE ENTERED :- ${arrayList.toString()}\n" +
                                    "TOTAL SEEK TIME IS :- ${seektime[1]}\n" +
                                    "SEEK SEQUENCE ACCORDING TO SELECTED ALGO :- ${arrangedList.toString()}")
                        }
                    },
                    title = "Calculate"
                )
            }
            //TODO
//            SimpleRadioButtonComponent(show = show.value)
            //TODO
            Spacer(modifier = Modifier.height(10.dp))
            if (arrangedList.isNotEmpty() && arrangedList.size > 1) {
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
            .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
            .background(Color.White, shape = RoundedCornerShape(5.dp)),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            drawLabelEvery = 2,
            labelTextSize = 8.sp,
            labelValueFormatter = { value -> value.toInt().toString() }
        ),
        //TODO//
        horizontalOffset = 4f
    )
}

// Testing


@Composable
fun alert(show:Boolean){

    var number by remember { mutableStateOf("") }
    val context = LocalContext.current

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            text = {
                Column() {
//                    TextField(modifier = Modifier,
//                        value = number,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                        onValueChange = { number = it }
//                    )
                    OutlinedTextField(value = number, onValueChange = {number = it.filter { it.isDigit() }},
                        label = { Text(text = "Enter Phone Number") },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 18.sp,
                            color = MaterialTheme.colors.onBackground),
                        modifier = Modifier,
                        enabled = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    )

                }
            },
            buttons = {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        modifier = Modifier.width(120.dp).padding(start = 0.dp),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Button(
                        modifier = Modifier.width(120.dp).padding(end = 0.dp),
                        onClick = {
                            if (number.isNotEmpty()) {
                                sendSMS(number, data.value, context)
                                openDialog.value = false
                            } else {
                                Toast.makeText(
                                    context,
                                    "Entere a valid phone number",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text("GET DATA")
                    }
                }
            }
        )
    }
}

    fun sendSMS(phoneNumber: String, message: String, context: Context) {
        val sentPI: PendingIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_SENT"), 0)
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
    }





