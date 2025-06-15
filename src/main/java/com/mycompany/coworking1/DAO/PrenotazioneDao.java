/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.coworking1.DAO;

import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.EUfficio;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 39327
 */
public interface PrenotazioneDao {
    
    public long getActiveReservationsByOfficeDateSlot(EUfficio office, LocalDate date, String fascia);
    
    public List<EPrenotazione> getReservationbyUser(EProfilo User);
     
    public List<EPrenotazione> getReservationbyoffice(EUfficio office);
     
    public List<LocalDate> findReservationDatesByOfficeId(String officeId, int year) ;
    
    public List<EPrenotazione> getPrenotazioniByLocatore(String locatoreId) ;
    
    public List<Object[]> getEntrateMensili(String locatoreId);
}
