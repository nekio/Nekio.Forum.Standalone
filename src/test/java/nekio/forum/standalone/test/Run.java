package nekio.forum.standalone.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/31
 *
 * Class for test/run the main project
 * Nekio.Forum.Standalone
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.ArrayList;
import java.util.List;
import nekio.forum.standalone.host.ManagersProcessor;
import nekio.library.common.Global;
import nekio.library.common.contracts.component.IManager;
import nekio.library.framework.component.Manager;
// </editor-fold>

public class Run {
    // <editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String[] args) {
        ManagersProcessor managersProcessor = new ManagersProcessor();

        // Group
        List<String> managersNames = new ArrayList<>();
        managersNames.add("nekio.dummy.manager.host.DummyManager");
        
        runStatic(managersProcessor, managersNames);
        //runNonStatic(managersProcessor, managersNames);
        
        // Individual
        /*
        IManager manager = ManagersProcessor.getManager(managersProcessor, "nekio.dummy.manager.host.DummyManager");
        manager.activate();
        manager.initialize();
        manager.restart();
        manager.uninitialize();
        manager.deactivate();
        */
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static">
    private static void runStatic(ManagersProcessor managersProcessor, List<String> managersNames){
        for(String managerName : managersNames){
            testStatic(managersProcessor, managerName);
        }
    }
    
    private static void runNonStatic(ManagersProcessor managersProcessor, List<String> managersNames){
        List<IManager> managers = ManagersProcessor.getManagers(managersProcessor, managersNames);
        for(IManager manager : managers){
            testNonStatic(managersProcessor, manager);
        }
    }
    
    private static void testStatic(ManagersProcessor managers, String managerName){
        ManagersProcessor.processIndividual(managers, managerName);
        ManagersProcessor.killIndividual(managers, managerName); 
    }
    
    private static void testNonStatic(ManagersProcessor managers, IManager manager){
        managers.proccess(manager);
        managers.kill(manager);
    }
    // </editor-fold>
}
