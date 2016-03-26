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
        @Storage(id = "clojureprettysymbol_config", file = "$APP_CONFIG$/clojureprettysymbol_application.xml")
})
public class HCSettings implements PersistentStateComponent<HCSettings> {
    public boolean turnOnFn = true;
    public boolean turnOnPartial = true;
    public boolean turnOnDef = true;
    public boolean turnOnDefn = true;
    public boolean turnOnLet = true;
    public boolean turnOnLetfn = true;
    public boolean turnOnDoseq = true;
    public boolean turnOnComp = true;
    public boolean turnOnThreadFirst = true;
    public boolean turnOnThreadLast = true;
    public boolean turnOnNotEqual = true;
    public boolean turnOnGT = true;
    public boolean turnOnLT = true;
    public boolean turnOnAnd = true;
    public boolean turnOnOr = true;
    public boolean turnOnNot = true;
    public boolean turnOnLambda = true;
    public boolean turnOnSet = true;
    public boolean turnOnEmptySet = true;
    public boolean turnOnSetUnion = true;
    public boolean turnOnSetDifference = true;
    public boolean turnOnSetIntersection = true;

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
