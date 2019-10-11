package com.alexeykatsuro.diaryofilms.util.epoxy.padding

import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.Px

/**
 * Used to specify individual padding values programmatically.
 *
 * @param left Left padding.
 * @param top Top padding.
 * @param right Right padding.
 * @param bottom Bottom padding.
 * decoration.
 * @param paddingType Unit / Type of the given paddings/ itemspacing.
 */
data class PaddingEpx(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val paddingType: PaddingType
) {


    enum class PaddingType {
        PX,
        DP,
        RESOURCE
    }


    /**
     * @param leftPx Left padding in pixels.
     * @param topPx Top padding in pixels.
     * @param rightPx Right padding in pixels.
     * @param bottomPx Bottom padding in pixels.
     * @param itemSpacingPx Space in pixels to add between each carousel item. Will be implemented
     * via an item decoration.
     */
    constructor(
        @Px leftPx: Int, @Px topPx: Int, @Px rightPx: Int, @Px bottomPx: Int
    ) : this(leftPx, topPx, rightPx, bottomPx, PaddingType.PX) {
    }

    companion object {


        const val NO_VALUE_SET = 0xFF0000

        /**
         * @param paddingRes Padding as dimension resource.
         * @param itemSpacingRes Space as dimension resource to add between each carousel item. Will be
         * implemented via an item decoration.
         */
        fun resource(@DimenRes paddingRes: Int): PaddingEpx {
            return PaddingEpx(
                paddingRes, paddingRes, paddingRes, paddingRes, PaddingType.RESOURCE
            )
        }

        /**
         * @param leftRes Left padding as dimension resource.
         * @param topRes Top padding as dimension resource.
         * @param rightRes Right padding as dimension resource.
         * @param bottomRes Bottom padding as dimension resource.
         * @param itemSpacingRes Space as dimension resource to add between each carousel item. Will be
         * implemented via an item decoration.
         */
        fun resource(
            @DimenRes leftRes: Int,
            @DimenRes topRes: Int,
            @DimenRes rightRes: Int,
            @DimenRes bottomRes: Int
        ): PaddingEpx {
            return PaddingEpx(
                leftRes, topRes, rightRes, bottomRes, PaddingType.RESOURCE
            )
        }

        /**
         * @param paddingDp Padding in dp.
         */
        fun dp(
            @Dimension(unit = Dimension.DP) paddingDp: Int
        ): PaddingEpx {
            return PaddingEpx(
                paddingDp,
                paddingDp,
                paddingDp,
                paddingDp,
                PaddingType.DP
            )
        }

        /**
         * @param leftDp Left padding in dp.
         * @param topDp Top padding in dp.
         * @param rightDp Right padding in dp.
         * @param bottomDp Bottom padding in dp.
         * an item decoration.
         */
        fun dp(
            @Dimension(unit = Dimension.DP) leftDp: Int,
            @Dimension(unit = Dimension.DP) topDp: Int,
            @Dimension(unit = Dimension.DP) rightDp: Int,
            @Dimension(unit = Dimension.DP) bottomDp: Int
        ): PaddingEpx {
            return PaddingEpx(leftDp, topDp, rightDp, bottomDp, PaddingType.DP)
        }
    }

}