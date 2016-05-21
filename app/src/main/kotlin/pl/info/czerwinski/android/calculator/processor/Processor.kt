package pl.info.czerwinski.android.calculator.processor

import pl.info.czerwinski.android.calculator.processor.operations.BinaryOperation
import pl.info.czerwinski.android.calculator.processor.operations.Operation
import pl.info.czerwinski.android.calculator.processor.operations.UnaryOperation

object Processor {

	private val operations: MutableList<Operation> = mutableListOf()

	operator fun get(index: Int) = operations.get(index)

	fun push(operation: Operation) {
		when (operation) {
			is UnaryOperation -> operations.add(operation)
			is BinaryOperation -> {
				if (operations.last() is BinaryOperation) {
					operations.removeAt(operations.lastIndex)
				}
				operations.add(operation)
			}
		}
	}

	fun clear() {
		operations.clear()
	}

	fun size() = operations.size

	fun back() {
		if (operations.isNotEmpty()) {
			operations.removeAt(operations.lastIndex)
		}
	}

	fun calculate() {
		var calculationChain = operations
				.fold(Pair(listOf(Value.EMPTY), emptyList<BinaryOperation>())) { lists, next ->
					when (next) {
						is UnaryOperation -> Pair(lists.first.dropLast(1) + next(lists.first.last()), lists.second)
						is BinaryOperation -> Pair(lists.first + Value.EMPTY, lists.second + next)
						else -> lists
					}
				}
				.let {
					it.first.zip(it.second + BinaryOperation("=", -1) { x, y -> x })
				}
		clear()
		var precedence = 0
		while (calculationChain.size > 1) {
			calculationChain = calculationChain.drop(1).fold(calculationChain.take(1)) { chain, next ->
				if (chain.last().second.precedence == precedence)
					chain.dropLast(1) + Pair(chain.last().second(chain.last().first, next.first), next.second)
				else
					chain + next
			}
			precedence++
		}
		operations.add(calculationChain.first().first.toOperation())
	}

	override fun toString(): String {
		val builder = StringBuilder()
		operations.fold(listOf<Any>(Value.EMPTY)) { list, next ->
			when (next) {
				is UnaryOperation -> list.dropLast(1) + next(list.last() as Value)
				is BinaryOperation -> list + next + Value.EMPTY
				else -> list
			}
		}.map { builder.append(it) }
		return builder.toString()
	}
}
