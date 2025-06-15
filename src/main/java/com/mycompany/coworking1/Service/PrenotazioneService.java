package com.mycompany.coworking1.Service;

import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author 39327
 */
public interface PrenotazioneService {
    
    public List<Integer> getMonthReservationCounts(String officeId, int year);
    
    public List<Integer> getMonthlyRevenue(String officeId, int year, int priceforreservation);
    
}
