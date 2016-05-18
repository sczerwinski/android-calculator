package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Processor

class BackOperation : Operation {
	override fun push() {
		Processor.back()
	}
}

class ClearOperation : Operation {
	override fun push() {
		Processor.clear()
	}
}

class ExecOperation : Operation {
	override fun push() {
		Processor.calculate();
	}
}
