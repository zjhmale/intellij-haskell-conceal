package zjhmale.hc.setting

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurationException
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Created by zjh on 16/3/22.
 */
class HCConfigurable : Configurable {
    private var settingsForm: HCSettingsForm? = null

    override fun createComponent(): JComponent? {
        settingsForm = settingsForm ?: HCSettingsForm()
        return settingsForm?.component
    }

    override fun isModified() = settingsForm?.isModified ?: false

    @Throws(ConfigurationException::class)
    override fun apply() {
        val settings = HCSettings.getInstance()

        settings.turnOnPi = settingsForm?.prettyPiCheckBox?.isSelected ?: true
        settings.turnOnTau = settingsForm?.prettyTauCheckBox?.isSelected ?: true
        settings.turnOnIsSubsetOf = settingsForm?.prettyIsSubsetOfCheckBox?.isSelected ?: true
        settings.turnOnElem = settingsForm?.prettyElemCheckBox?.isSelected ?: true
        settings.turnOnNotElem= settingsForm?.prettyNotElemCheckBox?.isSelected ?: true
        settings.turnOnUnion= settingsForm?.prettyUnionCheckBox?.isSelected ?: true
        settings.turnOnIntersect= settingsForm?.prettyIntersectCheckBox?.isSelected ?: true
        settings.turnOnDiv= settingsForm?.prettyDivCheckBox?.isSelected ?: true
        settings.turnOnSqrt= settingsForm?.prettySqrtCheckBox?.isSelected ?: true
        settings.turnOnTypeSig= settingsForm?.prettyTypeSigCheckBox?.isSelected ?: true
        settings.turnOnForall= settingsForm?.prettyForallCheckBox?.isSelected ?: true
        settings.turnOnComp= settingsForm?.prettyCompCheckBox?.isSelected ?: true
        settings.turnOnArrowType= settingsForm?.prettyArrowTypeCheckBox?.isSelected ?: true
        settings.turnOnBind= settingsForm?.prettyBindCheckBox?.isSelected ?: true
        settings.turnOnTypeConstraint= settingsForm?.prettyTypeConstraintCheckBox?.isSelected ?: true
        settings.turnOnEqual= settingsForm?.prettyEqualCheckBox?.isSelected ?: true
        settings.turnOnNotEqual= settingsForm?.prettyNotEqualCheckBox?.isSelected ?: true
        settings.turnOnAnd= settingsForm?.prettyAndCheckBox?.isSelected ?: true
        settings.turnOnOr= settingsForm?.prettyOrCheckBox?.isSelected ?: true
        settings.turnOnNot= settingsForm?.prettyNotCheckBox?.isSelected ?: true
        settings.turnOnGT= settingsForm?.prettyGTCheckBox?.isSelected ?: true
        settings.turnOnLT= settingsForm?.prettyLTCheckBox?.isSelected ?: true
        settings.turnOnMZero = settingsForm?.prettyMZeroCheckBox?.isSelected ?: true
        settings.turnOnMEmpty = settingsForm?.prettyMEmptyCheckBox?.isSelected ?: true
        settings.turnOnSum = settingsForm?.prettySumCheckBox?.isSelected ?: true
        settings.turnOnProduct = settingsForm?.prettyProductCheckBox?.isSelected ?: true
        settings.turnOnLambda = settingsForm?.prettyLambdaCheckBox?.isSelected ?: true
        settings.turnOnLet = settingsForm?.prettyLetCheckBox?.isSelected ?: true
        settings.turnOnWhere = settingsForm?.prettyWhereCheckBox?.isSelected ?: true
        settings.turnOnIdx = settingsForm?.prettyIdxCheckBox?.isSelected ?: true
        settings.turnOnRange = settingsForm?.prettyRangeCheckBox?.isSelected ?: true
        settings.turnOnUndefined = settingsForm?.prettyUndefinedCheckBox?.isSelected ?: true
    }

    override fun reset() {
        settingsForm?.reset()
    }

    override fun disposeUIResources() {
        settingsForm = null
    }

    @Nls
    override fun getDisplayName() = "Haskell Conceal"

    override fun getHelpTopic() = null
}