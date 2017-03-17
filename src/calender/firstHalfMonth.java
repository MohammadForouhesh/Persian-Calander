/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.Serializable;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class firstHalfMonth extends Month implements Serializable{
    
    /**
     * constructor firstHalfMonth
     * @param solarNumber parameter
     * @param numDayName parameter
     */
    public firstHalfMonth(int solarNumber , int numDayName) {
        super(solarNumber , 31 , numDayName);
    }
    
}
