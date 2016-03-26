package zjhmale.hc.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by zjh on 2016/2/16.
 */

@State(name = "HCSettings", storages = {
        @Storage(id = "haskellconceal_config", file = "$APP_CONFIG$/haskellconceal_application.xml")
})
public class HCSettings implements PersistentStateComponent<HCSettings> {
    public boolean turnOnPi = true;
    public boolean turnOnTau = true;
    public boolean turnOnElem = true;
    public boolean turnOnNotElem = true;
    public boolean turnOnIsSubsetOf = true;
    public boolean turnOnUnion = true;
    public boolean turnOnIntersect = true;
    public boolean turnOnDiv = true;
    public boolean turnOnSqrt = true;
    public boolean turnOnTypeSig = true;
    public boolean turnOnForall = true;
    public boolean turnOnComp = true;
    public boolean turnOnArrowType = true;
    public boolean turnOnBind = true;
    public boolean turnOnTypeConstraint = true;
    public boolean turnOnEqual = true;
    public boolean turnOnNotEqual = true;
    public boolean turnOnAnd = true;
    public boolean turnOnOr = true;
    public boolean turnOnNot = true;
    public boolean turnOnGT = true;
    public boolean turnOnLT = true;
    public boolean turnOnMZero = true;
    public boolean turnOnMEmpty = true;
    public boolean turnOnSum = true;
    public boolean turnOnProduct = true;
    public boolean turnOnLet = true;
    public boolean turnOnWhere = true;
    public boolean turnOnLambda = true;
    public boolean turnOnIdx = true;
    public boolean turnOnRange = true;

    @Nullable
    @Override
    public HCSettings getState() {
        return this;
    }

    @Override
    public void loadState(HCSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static HCSettings getInstance() {
        return ServiceManager.getService(HCSettings.class);
    }
}
