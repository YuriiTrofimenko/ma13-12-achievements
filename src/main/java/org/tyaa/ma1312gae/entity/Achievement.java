/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.ma1312gae.entity;

/**
 *
 * @author student
 */
public class Achievement {
    
    public Long id;
    public String title;
    public String content;
    public String picture;
    
    private static Long lastId = 1L;

    public Achievement(String title, String content, String picture) {
        
        this.id = lastId;
        this.title = title;
        this.content = content;
        this.picture = picture;
        lastId++;
    }
}
