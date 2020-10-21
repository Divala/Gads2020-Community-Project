package com.xtremsystems.patientscheduler.helpers

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.xtremsystems.patientscheduler.R

/**
 * Created by mathewsdivala on 2/7/18.
 */

class QRCodeHintView : RelativeLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.qr_code_hinter_view_layout, this)
    }

}
