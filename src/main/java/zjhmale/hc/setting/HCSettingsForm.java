package zjhmale.hc.setting;

import javax.swing.*;

/**
 * Created by zjh on 16/2/27.
 */
public class HCSettingsForm {
    private JPanel appearancePanel;
    private JPanel panel;

    public JCheckBox prettyPiCheckBox;
    public JCheckBox prettyTauCheckBox;
    public JCheckBox prettyIsSubsetOfCheckBox;
    public JCheckBox prettyDivCheckBox;
    public JCheckBox prettySqrtCheckBox;
    public JCheckBox prettyTypeSigCheckBox;
    public JCheckBox prettyForallCheckBox;
    public JCheckBox prettyTypeConstraintCheckBox;
    public JCheckBox prettyEqualCheckBox;
    public JCheckBox prettyNotEqualCheckBox;
    public JCheckBox prettyAndCheckBox;
    public JCheckBox prettyOrCheckBox;
    public JCheckBox prettyNotCheckBox;
    public JCheckBox prettyGTCheckBox;
    public JCheckBox prettyLTCheckBox;
    public JCheckBox prettyElemCheckBox;
    public JCheckBox prettyNotElemCheckBox;
    public JCheckBox prettyUnionCheckBox;
    public JCheckBox prettyIntersectCheckBox;
    public JCheckBox prettyCompCheckBox;
    public JCheckBox prettyArrowTypeCheckBox;
    public JCheckBox prettyBindCheckBox;
    public JCheckBox prettyMZeroCheckBox;
    public JCheckBox prettyMEmptyCheckBox;
    public JCheckBox prettySumCheckBox;
    public JCheckBox prettyProductCheckBox;
    public JCheckBox prettyLetCheckBox;
    public JCheckBox prettyWhereCheckBox;
    public JCheckBox prettyLambdaCheckBox;
    public JCheckBox prettyIdxCheckBox;
    public JCheckBox prettyRangeCheckBox;
    public JCheckBox prettyUndefinedCheckBox;
    public JCheckBox prettyEmptyListCheckBox;

    private final HCSettings settings;

    public HCSettingsForm() {
        settings = HCSettings.getInstance();
    }

    public JComponent getComponent() {
        return panel;
    }

    public boolean isModified() {
        return prettyPiCheckBox.isSelected() != settings.turnOnPi
                || prettyTauCheckBox.isSelected() != settings.turnOnTau
                || prettyIsSubsetOfCheckBox.isSelected() != settings.turnOnIsSubsetOf
                || prettyElemCheckBox.isSelected() != settings.turnOnElem
                || prettyNotElemCheckBox.isSelected() != settings.turnOnNotElem
                || prettyUnionCheckBox.isSelected() != settings.turnOnUnion
                || prettyIntersectCheckBox.isSelected() != settings.turnOnIntersect
                || prettyDivCheckBox.isSelected() != settings.turnOnDiv
                || prettySqrtCheckBox.isSelected() != settings.turnOnSqrt
                || prettyTypeSigCheckBox.isSelected() != settings.turnOnTypeSig
                || prettyForallCheckBox.isSelected() != settings.turnOnForall
                || prettyCompCheckBox.isSelected() != settings.turnOnComp
                || prettyArrowTypeCheckBox.isSelected() != settings.turnOnArrowType
                || prettyBindCheckBox.isSelected() != settings.turnOnBind
                || prettyTypeConstraintCheckBox.isSelected() != settings.turnOnTypeConstraint
                || prettyEqualCheckBox.isSelected() != settings.turnOnEqual
                || prettyNotEqualCheckBox.isSelected() != settings.turnOnNotEqual
                || prettyAndCheckBox.isSelected() != settings.turnOnAnd
                || prettyOrCheckBox.isSelected() != settings.turnOnOr
                || prettyNotCheckBox.isSelected() != settings.turnOnNot
                || prettyGTCheckBox.isSelected() != settings.turnOnGT
                || prettyLTCheckBox.isSelected() != settings.turnOnLT
                || prettyMZeroCheckBox.isSelected() != settings.turnOnMZero
                || prettyMEmptyCheckBox.isSelected() != settings.turnOnMEmpty
                || prettySumCheckBox.isSelected() != settings.turnOnSum
                || prettyProductCheckBox.isSelected() != settings.turnOnProduct
                || prettyLambdaCheckBox.isSelected() != settings.turnOnLambda
                || prettyLetCheckBox.isSelected() != settings.turnOnLet
                || prettyWhereCheckBox.isSelected() != settings.turnOnWhere
                || prettyIdxCheckBox.isSelected() != settings.turnOnIdx
                || prettyRangeCheckBox.isSelected() != settings.turnOnRange
                || prettyUndefinedCheckBox.isSelected() != settings.turnOnUndefined
                || prettyEmptyListCheckBox.isSelected() != settings.turnOnEmptyList;
    }

    public void reset() {
        prettyPiCheckBox.setSelected(settings.turnOnPi);
        prettyTauCheckBox.setSelected(settings.turnOnTau);
        prettyIsSubsetOfCheckBox.setSelected(settings.turnOnIsSubsetOf);
        prettyElemCheckBox.setSelected(settings.turnOnElem);
        prettyNotElemCheckBox.setSelected(settings.turnOnNotElem);
        prettyUnionCheckBox.setSelected(settings.turnOnUnion);
        prettyIntersectCheckBox.setSelected(settings.turnOnIntersect);
        prettyDivCheckBox.setSelected(settings.turnOnDiv);
        prettySqrtCheckBox.setSelected(settings.turnOnSqrt);
        prettyTypeSigCheckBox.setSelected(settings.turnOnTypeSig);
        prettyForallCheckBox.setSelected(settings.turnOnForall);
        prettyCompCheckBox.setSelected(settings.turnOnComp);
        prettyArrowTypeCheckBox.setSelected(settings.turnOnArrowType);
        prettyBindCheckBox.setSelected(settings.turnOnBind);
        prettyTypeConstraintCheckBox.setSelected(settings.turnOnTypeConstraint);
        prettyEqualCheckBox.setSelected(settings.turnOnEqual);
        prettyNotEqualCheckBox.setSelected(settings.turnOnNotEqual);
        prettyAndCheckBox.setSelected(settings.turnOnAnd);
        prettyOrCheckBox.setSelected(settings.turnOnOr);
        prettyNotCheckBox.setSelected(settings.turnOnNot);
        prettyGTCheckBox.setSelected(settings.turnOnGT);
        prettyLTCheckBox.setSelected(settings.turnOnLT);
        prettyMZeroCheckBox.setSelected(settings.turnOnMZero);
        prettyMEmptyCheckBox.setSelected(settings.turnOnMEmpty);
        prettySumCheckBox.setSelected(settings.turnOnSum);
        prettyProductCheckBox.setSelected(settings.turnOnProduct);
        prettyLambdaCheckBox.setSelected(settings.turnOnLambda);
        prettyLetCheckBox.setSelected(settings.turnOnLet);
        prettyWhereCheckBox.setSelected(settings.turnOnWhere);
        prettyIdxCheckBox.setSelected(settings.turnOnIdx);
        prettyRangeCheckBox.setSelected(settings.turnOnRange);
        prettyUndefinedCheckBox.setSelected(settings.turnOnUndefined);
        prettyEmptyListCheckBox.setSelected(settings.turnOnEmptyList);
    }
}
