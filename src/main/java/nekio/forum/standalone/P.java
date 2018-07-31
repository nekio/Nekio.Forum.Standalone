package nekio.forum.standalone;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/31
 *
 * Nekio.Forum.Standalone
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.Global;
import nekio.library.common.Track;
import nekio.library.common.model.component.ComponentConfigurations;
import nekio.library.log.Logger;
// </editor-fold>

public class P {
    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final ComponentConfigurations CONFIGURATIONS = new ComponentConfigurations(
        "Forum",
        "Nekio.Forum.Standalone",
        "NekioForumStandalone"
    );
    
    public final static String ID = P.CONFIGURATIONS.getComponentId();
    public final static String PROJECT = P.CONFIGURATIONS.getComponentProjectName();
    public final static String COMPONENT = P.CONFIGURATIONS.getComponentName();
    public final static String DEVELOPER = P.CONFIGURATIONS.getComponentDeveloper();
    public final static String EDITION = P.CONFIGURATIONS.getComponentEdition();
    public final static String VERSION = P.CONFIGURATIONS.getComponentVersion();
    public final static String PROJECT_FOLDER = P.CONFIGURATIONS.getProjectFolder();
    public final static String FOLDER_CONFIGURATION_CLASS = P.CONFIGURATIONS.getConfigurationClassFolder();
    public final static String FOLDER_PROPERTIES = P.CONFIGURATIONS.getPropertiesFolder();
    public final static String FOLDER_LOG = P.CONFIGURATIONS.getLogFolder();
    public final static boolean IS_DEBUG = P.CONFIGURATIONS.isDebug();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Log Behaviours">
    public static class Log{
        public static void plain(String message){
            Logger.p(message);
        }
        
        public static void d(String message){
            Logger.d(Track.as(P.ID, message, Global.PROJECT_STACKTRACE_LEVEL));
        }
        
        public static void i(String message){
            Logger.i(Track.as(P.ID, message, Global.PROJECT_STACKTRACE_LEVEL));
        }
        
        public static void w(String message){
            Logger.w(Track.as(P.ID, message, Global.PROJECT_STACKTRACE_LEVEL));
        }
        
        public static void e(String message){
            Logger.e(Track.as(P.ID, message, Global.PROJECT_STACKTRACE_LEVEL));
        }
        
        public static void x(Exception e){
            Logger.x(e);
        }
    }
    // </editor-fold>
}