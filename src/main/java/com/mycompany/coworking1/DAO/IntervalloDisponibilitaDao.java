/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.coworking1.DAO;

import com.mycompany.coworking1.Model.entity.EIntervalliDisponibilita;
import com.mycompany.coworking1.Model.entity.EUfficio;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IntervalloDisponibilitaDao {

        public List<EIntervalliDisponibilita> getIntervalliByOffice(EUfficio ufficio);
}
