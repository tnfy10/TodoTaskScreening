package hongsunghwi.todotaskscreening.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import hongsunghwi.todotaskscreening.R

val notoSansKr = FontFamily(
    Font(R.font.noto_sans_kr_thin, FontWeight.W100),
    Font(R.font.noto_sans_kr_extra_light, FontWeight.W200),
    Font(R.font.noto_sans_kr_light, FontWeight.W300),
    Font(R.font.noto_sans_kr_regular, FontWeight.W400),
    Font(R.font.noto_sans_kr_medium, FontWeight.W500),
    Font(R.font.noto_sans_kr_semi_bold, FontWeight.W600),
    Font(R.font.noto_sans_kr_bold, FontWeight.W700),
    Font(R.font.noto_sans_kr_extra_bold, FontWeight.W800),
    Font(R.font.noto_sans_kr_black, FontWeight.W900),
)

private val notoSansKrStyle = TextStyle(
    fontFamily = notoSansKr
)

val Typography = TodoTypography(
    titleBold = notoSansKrStyle.copy(
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    bodyRegular1 = notoSansKrStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em,
    ),
    bodyRegular2 = notoSansKrStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    labelBold = notoSansKrStyle.copy(
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    labelRegular = notoSansKrStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    )
)

@Immutable
data class TodoTypography(
    val titleBold: TextStyle,
    val bodyRegular1: TextStyle,
    val bodyRegular2: TextStyle,
    val labelBold: TextStyle,
    val labelRegular: TextStyle
)

val LocalTypography = staticCompositionLocalOf {
    TodoTypography(
        titleBold = notoSansKrStyle,
        bodyRegular1 = notoSansKrStyle,
        bodyRegular2 = notoSansKrStyle,
        labelBold = notoSansKrStyle,
        labelRegular = notoSansKrStyle
    )
}