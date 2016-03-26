package zjhmale.cps

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import zjhmale.cps.setting.CPSSettings
import java.util.*
import java.util.regex.Pattern

/**
 * Created by zjh on 16/3/22.
 */
class CPSFoldingBuilder : FoldingBuilder {
    private val symbolPattern = Pattern.compile(
            "pi|tau|`elem`|`notElem`|`isSubsetOf`|`union`|`intersect`|`div`|sqrt|<=|\\\\|::|!!|\\.\\.|forall|\\.|->|<-|=>|==|/=|&&|\\|\\||not|>=|<=|mzero|mempty|sum|product|let|where"
    )

    private val prettySymbolMaps = hashMapOf(
            "pi" to "π",
            "tau" to "τ",
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

    private val constants = arrayOf("pi", "tau")
    private val setOperators = arrayOf("`elem`", "`notElem`", "`isSubsetOf`", "`union`", "`intersect`")
    private val arithOprators = arrayOf("`div`", "sqrt", "sum", "product")
    private val logicOperators = arrayOf("==", "/=", "&&", "||", "not", ">=", "<=", "forall")
    private val listOperators = arrayOf("!!", "..")
    private val functionSymbols = arrayOf(".", "\\")
    private val controlFlowSymbols = arrayOf("let", "where")
    private val typeSymbols = arrayOf("::", "->", "=>")
    private val monadSymbols = arrayOf("mzero", "mempty", "<-")

    override fun buildFoldRegions(node: ASTNode, document: Document): Array<out FoldingDescriptor> {
        val settings = CPSSettings.getInstance()
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

                val shouldFold = if ((constants + setOperators + logicOperators + controlFlowSymbols + typeSymbols).contains(key)) {
                    prevChar == " " && nextChar == " "
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
                } else if (monadSymbols.contains(key)) {
                    if (key == "<-") {
                        prevChar == " " && (nextChar == " " || nextChar == "\n")
                    } else {
                        prevChar == " " && nextChar == " "
                    }
                } else {
                    listOperators.contains(key)
                }

                if (shouldFold) {
                    val pretty = prettySymbolMaps[key] ?: return arrayOf<FoldingDescriptor>()
                    val range = TextRange.create(rangeStart, rangeEnd)
                    descriptors.add(CPSFoldingDescriptor(node, range, null, pretty, true))
                }
            }
        }
        return descriptors.toArray<FoldingDescriptor>(arrayOfNulls<FoldingDescriptor>(descriptors.size))
    }

    override fun getPlaceholderText(node: ASTNode) = null

    override fun isCollapsedByDefault(node: ASTNode) = true
}