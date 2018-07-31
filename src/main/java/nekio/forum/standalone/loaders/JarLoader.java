package nekio.forum.standalone.loaders;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/31
 *
 * JAR Loader
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.ArrayList;
import java.util.List;
import nekio.forum.standalone.P;
import nekio.library.common.contracts.component.IManager;
import nekio.library.framework.adapters.ComponentAdapter;
import nekio.library.framework.component.Manager;
import nekio.library.log.Logger;
import nekio.library.utils.reflection.Reflect;
// </editor-fold>

public class JarLoader extends Reflect{    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public JarLoader(){
        Logger.brace("Nekio.Forum.Standalone.JarLoader()", "#");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">    
    public List<Class> getManagerClasses(List<Class> classes){
        List<Class> managerClasses = new ArrayList<>();
        
        for(Class clazz : classes){
            if(isManager(clazz)){
                P.Log.i("Manager Found: " + clazz.getCanonicalName());
                managerClasses.add(clazz);
            }
        }
        
        return managerClasses;
    }
    
    public boolean isManager(Class clazz){
        boolean result = false;
        
        Class superclass = clazz.getSuperclass();
        Object instance = null;
        Object superInstance = null;
        try {
            instance = clazz.newInstance(); 
            superInstance = superclass.newInstance(); 
            
            if(instance instanceof IManager){
                result = true;
            }else if(superInstance instanceof IManager){
                result = true;
            }
        } catch (Exception e) {
            // Exceptions for abstract classes and interfaces
        }
        
        return result;
    }
    // </editor-fold>
}