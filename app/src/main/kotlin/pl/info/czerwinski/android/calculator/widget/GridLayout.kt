package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import pl.info.czerwinski.android.calculator.R

open class GridLayout : ViewGroup {

	var colsCount = 1
	var rowsCount = 1

	var paddingHorizontal = 0
	var paddingVertical = 0

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
				R.styleable.GridLayout_horizontalPadding -> paddingHorizontal = attributes.getDimensionPixelSize(attribute, 0)
				R.styleable.GridLayout_verticalPadding -> paddingVertical = attributes.getDimensionPixelSize(attribute, 0)
			}
		}
		attributes.recycle()
	}

	override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
		val innerPaddingsWidth = paddingHorizontal * (colsCount - 1)
		val innerPaddingsHeight = paddingVertical * (rowsCount - 1)

		val childWidth = (measuredWidth - paddingLeft - paddingRight - innerPaddingsWidth) / colsCount
		val childHeight = (measuredHeight - paddingTop - paddingBottom - innerPaddingsHeight) / rowsCount

		val layoutLeft = (measuredWidth - childWidth * colsCount - innerPaddingsWidth) / 2
		val layoutTop = (measuredHeight - childHeight * rowsCount - innerPaddingsHeight) / 2

		for ((index, child) in (0..childCount - 1).map { Pair(it, getChildAt(it)) }) {
			val x = index % colsCount
			val y = index / colsCount

			val childLeft = layoutLeft + x * (childWidth + paddingHorizontal)
			val childRight = childLeft + childWidth
			val childTop = layoutTop + y * (childHeight + paddingVertical)
			val childBottom = childTop + childHeight

			child.measure(exactMeasureSpec(childWidth), exactMeasureSpec(childHeight))
			child.layout(childLeft, childTop, childRight, childBottom)
		}
	}

	private fun exactMeasureSpec(size: Int) = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
}
