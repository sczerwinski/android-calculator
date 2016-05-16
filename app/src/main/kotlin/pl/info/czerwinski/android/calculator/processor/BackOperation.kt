package pl.info.czerwinski.android.calculator.processor

class BackOperation : Operation {

	override fun push() {
		Processor.back()
	}
}