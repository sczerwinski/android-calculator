package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Processor

class ClearOperation : Operation {

	override fun push() {
		Processor.clear()
	}
}
