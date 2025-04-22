
package com.emi.component;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;


public class bird {
     Point location;
     Dimension size;
    Image img;
    bird(Image img,Dimension size,Point location){
        this.img=img;
        this.size=size;
        this.location=location;
    }

    /**
     * @return the location
     */
    public  Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the size
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

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
