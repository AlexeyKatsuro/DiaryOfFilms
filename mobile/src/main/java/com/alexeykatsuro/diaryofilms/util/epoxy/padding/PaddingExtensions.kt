package com.alexeykatsuro.diaryofilms.util.epoxy.padding

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.Px
import androidx.core.view.setPadding

@Px
fun Context.dpToPx(@Dimension(unit = Dimension.DP) dp: Int): Int {
    return TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            resources.displayMetrics
        ).toInt()
}

@Px
fun Context.resToPx(@DimenRes itemSpacingRes: Int): Int {
    return resources.getDimensionPixelOffset(itemSpacingRes)
}


fun View.setViewPadding(padding: PaddingEpx?) {
    if (padding == null) {
        val px = context.dpToPx(0)
        setPadding(px)
    } else {
        val paddingPx = padding.toPxType(context)
        setPadding(
            resolvePadding(paddingPx.left, paddingLeft),
            resolvePadding(paddingPx.top, paddingTop),
            resolvePadding(paddingPx.right, paddingRight),
            resolvePadding(paddingPx.bottom, paddingBottom)
        )
    }
}

private fun resolvePadding(new: Int, old: Int): Int {
    return if (new != PaddingEpx.NO_VALUE_SET) new else old
}

private inline fun PaddingEpx.mapBy(mapper: (Int) -> (Int)): PaddingEpx {
    return PaddingEpx(
        if (left != PaddingEpx.NO_VALUE_SET) mapper(left) else PaddingEpx.NO_VALUE_SET,
        if (top != PaddingEpx.NO_VALUE_SET) mapper(top) else PaddingEpx.NO_VALUE_SET,
        if (right != PaddingEpx.NO_VALUE_SET) mapper(right) else PaddingEpx.NO_VALUE_SET,
        if (bottom != PaddingEpx.NO_VALUE_SET) mapper(bottom) else PaddingEpx.NO_VALUE_SET
    )
}

fun PaddingEpx.toPxType(context: Context): PaddingEpx = when (paddingType) {
    PaddingEpx.PaddingType.DP -> mapBy { context.dpToPx(it) }
    PaddingEpx.PaddingType.RESOURCE -> mapBy { context.resToPx(it) }
    PaddingEpx.PaddingType.PX -> this
}

fun PaddingEpx.Companion.verticalDp(@Dimension(unit = Dimension.DP) dp: Int) =
    PaddingEpx(NO_VALUE_SET, dp, NO_VALUE_SET, dp, PaddingEpx.PaddingType.DP)

fun PaddingEpx.Companion.verticalRes(@DimenRes res: Int) =
    PaddingEpx(NO_VALUE_SET, res, NO_VALUE_SET, res, PaddingEpx.PaddingType.RESOURCE)

fun PaddingEpx.Companion.horizontalDp(@Dimension(unit = Dimension.DP) dp: Int) =
    PaddingEpx(dp, NO_VALUE_SET, dp, NO_VALUE_SET, PaddingEpx.PaddingType.DP)

fun PaddingEpx.Companion.horizontalRes(@DimenRes res: Int) =
    PaddingEpx(res, NO_VALUE_SET, res, NO_VALUE_SET, PaddingEpx.PaddingType.RESOURCE)
