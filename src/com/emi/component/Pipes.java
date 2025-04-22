
package com.emi.component;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;


public class Pipes {


    int width=64;
    int height=512;
    int x=360;
    int y=0;
    private Image img;
    boolean passed =false;
    /**
     * @return the Size
     */
    Pipes(Image img){
        this.img=img;
                
    }
 

    /**
     * @param Size the Size to set


    /**
     * @return the Location
     */


    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
    }
}
