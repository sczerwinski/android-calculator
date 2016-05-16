package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Processor

abstract class QueuedOperation : Operation {

	override fun push() {
		Processor.operations.add(this)
	}
}