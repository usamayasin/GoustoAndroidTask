package com.app.goustotask.data.usecase

import android.content.res.Resources
import android.util.DisplayMetrics
import javax.inject.Inject

class GetScreenSizeInfoUsecase @Inject constructor(
) {

    fun getScreenDisplayMetric():DisplayMetrics = Resources.getSystem().displayMetrics

}
