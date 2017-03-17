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
public class secondHalfMonth extends Month implements Serializable{
    
    /**
     * constructor for secondHalfMonth
     * @param solarNumber parameter
     * @param numDayName parameter
     */
    public secondHalfMonth(int solarNumber , int numDayName) {
        super(solarNumber , 30 , numDayName);
    }

}
