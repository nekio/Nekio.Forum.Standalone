package nekio.forum.standalone.host;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/31
 *
 * Managers Processor
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import nekio.forum.standalone.P;
import nekio.forum.standalone.loaders.JarLoader;
import nekio.library.common.contracts.component.IManager;
import nekio.library.common.contracts.ws.IWebservicePublisher;
import nekio.library.log.Logger;
import nekio.library.utils.helpers.DataHelper;
// </editor-fold>

public class ManagersProcessor {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Map<String, IManager> managers;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ManagersProcessor(){
        Logger.brace("Nekio.Forum.Standalone.ManagersProcessor()", "#");
        loadManagers();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public IManager get(String key) {
        return managers.get(key);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static List<IManager> getManagers(ManagersProcessor managers, List<String> strings){
        List<IManager> result = new ArrayList<>();
        
        for(String string : strings){
            try {
                result.add(getManager(managers, string));
            } catch (Exception e) {
                P.Log.e("String not value = " + string);
                P.Log.x(e);
            }
        }
        
        return result;
    }
    
    public static IManager getManager(ManagersProcessor managers, String canonicalName){
        IManager manager = (IManager)managers.get(canonicalName);
        
        return manager;
    }
    
    public static void testAll(ManagersProcessor managers){
        managers.testAll();
    }
    
    public static void testIndividual(ManagersProcessor managers, String canonicalName){
        IManager manager = getManager(managers, canonicalName);
        
        managers.test(manager);
    }
    
    public static void processAll(ManagersProcessor managers){
        managers.proccessAll();
    }
    
    public static void processIndividual(ManagersProcessor managers, String canonicalName){
        IManager manager = getManager(managers, canonicalName);
        
        managers.proccess(manager);
    }
    
    public static void killAll(ManagersProcessor managers){
        managers.killAll();
    }
    
    public static void killIndividual(ManagersProcessor managers, String canonicalName){
        IManager manager = getManager(managers, canonicalName);
        
        managers.kill(manager);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Exposed Behaviours">
    public void testAll(){
        for(Entry<String, IManager> map : managers.entrySet()){
            P.Log.i("\nTest All Automatic test for " + map.getKey());
            test(map.getValue());
            
            DataHelper.wait(1);
        }
    }
    
    public void test(IManager manager){
        proccess(manager);
        kill(manager);
    }
    
    public void proccessAll(){
        for(Entry<String, IManager> map : managers.entrySet()){
            P.Log.i("\nProccess All Automatic proccess for " + map.getKey());
            proccess(map.getValue());
            
            DataHelper.wait(1);
        }
    }
    
    public void proccess(IManager manager){
        Logger.brace("ManagersProcessor - proccess()", "*");
        
        ManagersBuilder.start(manager);
        manager.publish();
    }
    
    public void killAll(){
        for(Entry<String, IManager> map : managers.entrySet()){
            P.Log.i("\nKill All Automatic proccess for " + map.getKey());
            kill(map.getValue());
            
            DataHelper.wait(1);
        }
    }
    
    public void kill(IManager manager){
        Logger.brace("ManagersProcessor - kill()", "*");
        
        IWebservicePublisher ws = manager.getWebservicePublisher();
        if(ws == null){
            P.Log.i("No Webservice detected for this Plugin");
        }else{
            ws.dispose();
        }
        
        ManagersBuilder.stop(manager);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Behaviours">
    private void loadManagers(){
        JarLoader jarLoader = new JarLoader();
        
        List<String> binaryFilenames = jarLoader.getBinaryFilenames();
        List<Class> classes = jarLoader.getBinaryClasses(binaryFilenames);
        List<Class> managerClasses = jarLoader.getManagerClasses(classes);
        jarLoader = null;
        
        ManagersBuilder.init(managerClasses);
        managers = ManagersBuilder.managers;
    }
    // </editor-fold>
}
