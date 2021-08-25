/*
 *
 * Treexl (Tree extensible expression language).
 * Copyright Ted Colvin (tedcolvin@outlook.com).
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the LICENSE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 */

package org.treexl.visitors

import org.treexl.AbstractVisitor
import org.treexl.Expression
import org.treexl.Identifier
import org.treexl.Literal
import org.treexl.Parameter
import org.treexl.Visitor

object TypedVisitor {
    inline fun <reified T : Expression> visitor(crossinline collector: (T) -> Unit): Visitor {
        return object : AbstractVisitor() {
            init {
                val kClass = T::class

                if (kClass != Literal::class && kClass != Identifier::class && kClass != Parameter::class) {
                    throw UnsupportedOperationException("Unsupported expression type ${kClass}.")
                }
            }

            fun handle(expression: Expression) {
                if (expression is T) collector(expression)
            }

            override fun visit(expression: Literal<*>) {
                handle(expression)
            }

            override fun visit(expression: Identifier) {
                handle(expression)
            }

            override fun visit(expression: Parameter) {
                handle(expression)
            }
        }
    }
}