package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.done
import muktokowlomcmp.composeapp.generated.resources.select_date
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.MAX
import network.chaintech.kmp_date_time_picker.utils.MIN
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults
import network.chaintech.kmp_date_time_picker.utils.now
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WheelDatePickerDialog(onDismissRequest: () -> Unit, onDateSelected: (LocalDate) -> Unit) {
    WheelDatePickerView(
        modifier = Modifier,
    showDatePicker = true,
    title = stringResource(Res.string.select_date),
    doneLabel = stringResource(Res.string.done),
    titleStyle = LocalTextStyle.current,
    doneLabelStyle = LocalTextStyle.current,
    startDate = LocalDate.now(),
    minDate = LocalDate.MIN(),
    maxDate = LocalDate.MAX(),
    yearsRange= IntRange(1922, 2122),

    height = 150.dp,
    rowCount = 3,
    showShortMonths = false,
    showMonthAsNumber = false,
    selectedDateTextStyle = MaterialTheme.typography.titleMedium.copy(
    color = LocalContentColor.current,
    fontSize = 15.sp
    ),
    defaultDateTextStyle = MaterialTheme.typography.titleSmall.copy(
    color = Color.Black,
    fontSize = 18.sp
    ),
    hideHeader = false,
    customMonthNames = null,
    containerColor = Color.White,
    shape = RoundedCornerShape(10.dp),
    dateTimePickerView = DateTimePickerView.BOTTOM_SHEET_VIEW,
    selectorProperties = WheelPickerDefaults.selectorProperties(),
    dragHandle = { BottomSheetDefaults.DragHandle() },
    onDoneClick = {localDate ->
        onDateSelected.invoke(localDate)
    },
    onDateChangeListener = {},
    onDismiss = {onDismissRequest.invoke()}
    )

}

@Composable
@Preview
fun WheelDatePickerDialogPreview() {
    WheelDatePickerDialog(
        onDismissRequest = {},
        onDateSelected = {}
    )
}