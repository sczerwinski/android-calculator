package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import org.jetbrains.anko.PropertyWithoutGetterException
import pl.info.czerwinski.android.calculator.R

open class GridLayout : ViewGroup {

	var colsCount = 1
	var rowsCount = 1

	var horizontalPadding: Int
		get() = throw PropertyWithoutGetterException("horizontalPadding")
		set(value) = setPadding(value, paddingTop, value, paddingBottom, value, internalPaddingVertical)

	var verticalPadding: Int
		get() = throw PropertyWithoutGetterException("verticalPadding")
		set(value) = setPadding(paddingLeft, value, paddingRight, value, internalPaddingHorizontal, value)

	var internalPaddingHorizontal = 0
	var internalPaddingVertical = 0

	constructor(context: Context?) : this(context, null, 0)
	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		if (attrs != null) {
			initFromAttributes(attrs)
		}
	}

	private fun initFromAttributes(attrs: AttributeSet) {
		val attributes = context.obtainStyledAttributes(attrs, R.styleable.GridLayout)
		for (attribute in (0..attributes.indexCount - 1).map { attributes[it] }) {
			when (attribute) {
				R.styleable.GridLayout_colsCount -> colsCount = attributes.getInteger(attribute, 1)
				R.styleable.GridLayout_rowsCount -> rowsCount = attributes.getInteger(attribute, 1)
				R.styleable.GridLayout_horizontalPadding -> internalPaddingHorizontal = attributes.getDimensionPixelSize(attribute, 0)
				R.styleable.GridLayout_verticalPadding -> internalPaddingVertical = attributes.getDimensionPixelSize(attribute, 0)
			}
		}
		attributes.recycle()
	}

	override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
		val innerPaddingsWidth = internalPaddingHorizontal * (colsCount - 1)
		val innerPaddingsHeight = internalPaddingVertical * (rowsCount - 1)

		val childWidth = (measuredWidth - paddingLeft - paddingRight - innerPaddingsWidth) / colsCount
		val childHeight = (measuredHeight - paddingTop - paddingBottom - innerPaddingsHeight) / rowsCount

		val layoutLeft = (measuredWidth - childWidth * colsCount - innerPaddingsWidth) / 2
		val layoutTop = (measuredHeight - childHeight * rowsCount - innerPaddingsHeight) / 2

		for ((index, child) in (0..childCount - 1).map { Pair(it, getChildAt(it)) }) {
			val x = index % colsCount
			val y = index / colsCount

			val childLeft = layoutLeft + x * (childWidth + internalPaddingHorizontal)
			val childRight = childLeft + childWidth
			val childTop = layoutTop + y * (childHeight + internalPaddingVertical)
			val childBottom = childTop + childHeight

			child.measure(exactMeasureSpec(childWidth), exactMeasureSpec(childHeight))
			child.layout(childLeft, childTop, childRight, childBottom)
		}
	}

	private fun exactMeasureSpec(size: Int) = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)

	fun setPadding(left: Int, top: Int, right: Int, bottom: Int, horizontal: Int, vertical: Int) {
		setPadding(left, top, right, bottom)
		internalPaddingHorizontal = horizontal
		internalPaddingVertical = vertical
	}
}
