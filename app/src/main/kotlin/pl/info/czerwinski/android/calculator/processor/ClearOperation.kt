package pl.info.czerwinski.android.calculator.processor

class ClearOperation : Operation {

	override fun push() {
		Processor.clear()
	}
}
