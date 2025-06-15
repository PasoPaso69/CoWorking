/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Service.impl;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.ProfiloDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.Service.PrenotazioneService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 39327
 */
public class PrenotazioneServiceImpl implements PrenotazioneService {
   private PrenotazioneDao reservationDao;
    
    public PrenotazioneServiceImpl(PrenotazioneDao reservationDao){
        this.reservationDao=reservationDao;
    }
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Integer> getMonthReservationCounts(String officeId, int year) {
        List<Integer> counts = new ArrayList<>(Collections.nCopies(12, 0));
        List<LocalDate> bookings = reservationDao.findReservationDatesByOfficeId(officeId, year);

        for (LocalDate date : bookings) {
            int monthIndex = date.getMonthValue() - 1;
            counts.set(monthIndex, counts.get(monthIndex) + 1);
        }
        return counts;
    }
    @Override
    public List<Integer> getMonthlyRevenue(String officeId, int year, int priceforreservation) {
        List<Integer> counts = getMonthReservationCounts(officeId, year);
        return counts.stream().map(count -> count * priceforreservation).collect(Collectors.toList());
    }
}
