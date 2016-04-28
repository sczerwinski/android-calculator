package pl.info.czerwinski.android.calculator.processor

abstract class QueuedOperation : Operation {

	override fun push() {
		Processor.operations.add(this)
	}
}