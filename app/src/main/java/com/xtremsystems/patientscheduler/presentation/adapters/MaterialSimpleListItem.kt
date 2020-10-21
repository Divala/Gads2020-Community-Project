package com.xtremsystems.patientscheduler.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import android.util.TypedValue
import com.afollestad.materialdialogs.util.DialogUtils

class MaterialSimpleListItem(private val builder: Builder) {

    fun getIcon(): Drawable? {
        return builder.icon
    }

    fun getContent(): CharSequence? {
        return builder.content
    }

    fun getIconPadding(): Int {
        return builder.iconPadding
    }

    @ColorInt
    fun getBackgroundColor(): Int {
        return builder.backgroundColor
    }

    fun getId(): Long {
        return builder.id
    }

    fun getTag(): Any? {
        return builder.tag
    }

    override fun toString(): String {
        return if (getContent() != null) {
            getContent()!!.toString()
        } else {
            "(no content)"
        }
    }

    class Builder(private val context: Context) {
        var icon: Drawable? = null
        lateinit var content: CharSequence
        var id: Long = 0

        internal var iconPadding: Int = 0
        internal var backgroundColor: Int = 0
        internal var tag: Any? = null

        init {
            backgroundColor = Color.parseColor("#BCBCBC")
        }

        fun icon(icon: Drawable?): Builder {
            this.icon = icon
            return this
        }

        fun icon(@DrawableRes iconRes: Int): Builder {
            return icon(ContextCompat.getDrawable(context, iconRes))
        }

        fun iconPadding(@IntRange(from = 0, to = Integer.MAX_VALUE.toLong()) padding: Int): Builder {
            this.iconPadding = padding
            return this
        }

        fun iconPaddingDp(@IntRange(from = 0, to = Integer.MAX_VALUE.toLong()) paddingDp: Int): Builder {
            this.iconPadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                paddingDp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
            return this
        }

        fun iconPaddingRes(@DimenRes paddingRes: Int): Builder {
            return iconPadding(context.resources.getDimensionPixelSize(paddingRes))
        }

        fun content(content: CharSequence): Builder {
            this.content = content
            return this
        }

        fun content(@StringRes contentRes: Int): Builder {
            return content(context.getString(contentRes))
        }

        fun backgroundColor(@ColorInt color: Int): Builder {
            this.backgroundColor = color
            return this
        }

        fun backgroundColorRes(@ColorRes colorRes: Int): Builder {
            return backgroundColor(DialogUtils.getColor(context, colorRes))
        }

        fun backgroundColorAttr(@AttrRes colorAttr: Int): Builder {
            return backgroundColor(DialogUtils.resolveColor(context, colorAttr))
        }

        fun id(id: Long): Builder {
            this.id = id
            return this
        }

        fun tag(tag: Any?): Builder {
            this.tag = tag
            return this
        }

        fun build(): MaterialSimpleListItem {
            return MaterialSimpleListItem(this)
        }

    }
}