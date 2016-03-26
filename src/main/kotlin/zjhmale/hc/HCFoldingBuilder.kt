package zjhmale.hc

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import zjhmale.hc.setting.HCSettings
import java.util.*
import java.util.regex.Pattern

/**
 * Created by zjh on 16/3/22.
 */
class HCFoldingBuilder : FoldingBuilder {
    private val symbolPattern = Pattern.compile(
            "pi|tau|undefined|`elem`|`notElem`|`isSubsetOf`|`union`|`intersect`|`div`|sqrt|<=|\\\\|::|!!|\\.\\.|forall|\\.|->|<-|=>|==|/=|&&|\\|\\||not|>=|<=|mzero|mempty|sum|product|let|where"
    )

    private val prettySymbolMaps = hashMapOf(
            "pi" to "π",
            "tau" to "τ",
            "undefined" to "⊥",
            "`elem`" to "∈",
            "`notElem`" to "∉",
            "`isSubsetOf`" to "⊆",
            "`union`" to "⋃",
            "`intersect`" to "⋂",
            "`div`" to "÷",
            "sqrt" to "√",
            "::" to "∷",
            "forall" to "∀",
            "." to "∘",
            "->" to "→",
            "<-" to "←",
            "=>" to "⇒",
            "==" to "≡",
            "/=" to "≢",
            "&&" to "∧",
            "||" to "∨",
            "not" to "¬",
            ">=" to "≥",
            "<=" to "≤",
            "mzero" to "∅",
            "mempty" to "∅",
            "sum" to "∑",
            "product" to "∏",
            "let" to "⊢",
            "where" to "∵",
            "\\" to "λ",
            "!!" to "‼",
            ".." to "…"
    )

    private val constants = arrayOf("pi", "tau", "undefined")
    private val setOperators = arrayOf("`elem`", "`notElem`", "`isSubsetOf`", "`union`", "`intersect`")
    private val arithOprators = arrayOf("`div`", "sqrt", "sum", "product")
    private val logicOperators = arrayOf("==", "/=", "&&", "||", "not", ">=", "<=", "forall")
    private val listOperators = arrayOf("!!", "..")
    private val functionSymbols = arrayOf(".", "\\")
    private val controlFlowSymbols = arrayOf("let", "where")
    private val typeSymbols = arrayOf("::", "->", "=>")
    private val monadSymbols = arrayOf("mzero", "mempty", "<-")

    private val settings = HCSettings.getInstance()

    private fun isToggleOn(key: String): Boolean {
        return (key == "pi" && settings.turnOnPi)
                || (key == "tau" && settings.turnOnTau)
                || (key == "undefined" && settings.turnOnTau)
                || (key == "`elem`" && settings.turnOnElem)
                || (key == "`notElem`" && settings.turnOnNotElem)
                || (key == "isSubsetOf`" && settings.turnOnIsSubsetOf)
                || (key == "`union`" && settings.turnOnUnion)
                || (key == "`intersect`" && settings.turnOnIntersect)
                || (key == "`div`" && settings.turnOnDiv)
                || (key == "sqrt" && settings.turnOnSqrt)
                || (key == "::" && settings.turnOnTypeSig)
                || (key == "forall" && settings.turnOnForall)
                || (key == "." && settings.turnOnComp)
                || (key == "->" && settings.turnOnArrowType)
                || (key == "<-" && settings.turnOnBind)
                || (key == "=>" && settings.turnOnTypeConstraint)
                || (key == "==" && settings.turnOnEqual)
                || (key == "/=" && settings.turnOnNotEqual)
                || (key == "&&" && settings.turnOnAnd)
                || (key == "||" && settings.turnOnOr)
                || (key == "not" && settings.turnOnNot)
                || (key == ">=" && settings.turnOnGT)
                || (key == "<=" && settings.turnOnLT)
                || (key == "mzero" && settings.turnOnMZero)
                || (key == "mempty" && settings.turnOnMEmpty)
                || (key == "sum" && settings.turnOnSum)
                || (key == "product" && settings.turnOnProduct)
                || (key == "let" && settings.turnOnLet)
                || (key == "where" && settings.turnOnWhere)
                || (key == "\\" && settings.turnOnLambda)
                || (key == "!!" && settings.turnOnIdx)
                || (key == ".." && settings.turnOnRange)
    }

    override fun buildFoldRegions(node: ASTNode, document: Document): Array<out FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val text = node.text
        val matcher = symbolPattern.matcher(text)

        while (matcher.find()) {
            var key = text.substring(matcher.start(), matcher.end())
            val nodeRange = node.textRange
            var rangeStart = nodeRange.startOffset + matcher.start()
            var rangeEnd = nodeRange.startOffset + matcher.end()
            if (key.startsWith("(")) {
                rangeStart += 1
            }

            if (rangeStart > nodeRange.startOffset && rangeEnd < nodeRange.endOffset) {
                val nextChar = text.substring(rangeEnd, rangeEnd + 1)
                val prevChar = text.substring(rangeStart - 1, rangeStart)

                val shouldFold = if ((constants + setOperators + logicOperators + controlFlowSymbols + typeSymbols + monadSymbols).contains(key)) {
                    if (key == "undefined") {
                        prevChar == " " && (nextChar == " " || nextChar == "\n")
                    } else {
                        prevChar == " " && nextChar == " "
                    }
                } else if (arithOprators.contains(key)) {
                    if (key == "`div`") {
                        prevChar == " " && nextChar == " "
                    } else {
                        (prevChar == " " || prevChar == "(") && nextChar == " "
                    }
                } else if (functionSymbols.contains(key)) {
                    if (key == ".") {
                        prevChar == " " && nextChar == " "
                    } else {
                        prevChar == " " || prevChar == "("
                    }
                } else {
                    listOperators.contains(key)
                }

                if (shouldFold && isToggleOn(key)) {
                    val pretty = prettySymbolMaps[key] ?: return arrayOf<FoldingDescriptor>()
                    val range = TextRange.create(rangeStart, rangeEnd)
                    descriptors.add(HCFoldingDescriptor(node, range, null, pretty, true))
                }
            }
        }
        return descriptors.toArray<FoldingDescriptor>(arrayOfNulls<FoldingDescriptor>(descriptors.size))
    }

    override fun getPlaceholderText(node: ASTNode) = null

    override fun isCollapsedByDefault(node: ASTNode) = true
}