
package com.mycompany.coworking1.Service;
import com.mycompany.coworking1.Model.entity.EProfilo;

/**
 *
 * @author 39327
 */
public interface ProfiloService {
    
    
    boolean isRegister(EProfilo user);
    EProfilo login(String username, String password);
}

