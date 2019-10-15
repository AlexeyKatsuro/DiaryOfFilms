package com.alexeykatsuro.inputfromutil.validation

typealias Condition = () -> Boolean

internal typealias ConditionList = List<Condition>

/** @author Aidan Follestad (@afollestad) */
internal class ConditionStack {
  private val stack = mutableListOf<Condition>()

  /** Pushes a condition into the stack. */
  fun push(condition: Condition) {
    stack.add(condition)
  }

  /** Pops the stack. */
  fun pop(): Condition {
    val top = peek()
    stack.removeAt(stack.size - 1)
    return top
  }

  /** Peeks the top item on the stack. */
  fun peek() = stack[stack.size - 1]

  /** Converts the current state of the stack to a immutable List. */
  fun asList(): List<Condition> = stack.toList()
}

/** Returns true if all conditions in the List return true. */
internal fun ConditionList?.isAllMet(): Boolean {
  return if (this == null || isEmpty()) {
    true
  } else {
    all { it() }
  }
}
