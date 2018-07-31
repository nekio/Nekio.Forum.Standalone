package nekio.forum.standalone.host;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/31
 *
 * Manager Builder
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nekio.forum.standalone.P;
import nekio.library.common.contracts.IFactory;
import nekio.library.common.contracts.component.IManager;
import nekio.library.framework.dp.factory.GenericFactory;
import nekio.library.log.Logger;
// </editor-fold>

public class ManagersBuilder {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    public static Map<String, IManager> managers;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Managers Behaviours">
    public static void init(List<Class> managersClass) {
        init(managersClass, false);
    }

    public static void init(List<Class> managersClass, boolean startAll) {
        Logger.brace("Nekio.Forum.Standalone.ManagerBuilder.init()", "#");

        managers = new HashMap<>();
        IFactory<IManager> factory = null;

        String pathClass = null;
        for (Class clazz : managersClass) {
            try {
                pathClass = clazz.getCanonicalName();

                P.Log.i("\nPath class recognized: " + pathClass);

                factory = new GenericFactory<>(clazz);
                IManager manager = factory.create();

                if (startAll) {
                    start(manager);
                }

                managers.put(pathClass, manager);
            } catch (Exception e) {
                P.Log.x(e);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Single Manager Behaviours">
    public static void test(IManager manager) {
        start(manager);
        manager.restart();
        stop(manager);
    }

    public static void start(IManager manager) {
        manager.activate();
        manager.initialize();
    }

    public static void stop(IManager manager) {
        manager.uninitialize();
        manager.deactivate();
        manager.dispose();
    }
    // </editor-fold>
}
