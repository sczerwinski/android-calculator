package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Processor

class BackOperation : Operation {

	override fun push() {
		Processor.back()
	}
}