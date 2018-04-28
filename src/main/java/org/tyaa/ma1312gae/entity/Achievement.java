/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.ma1312gae.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 *
 * @author student
 */
@Entity
public class Achievement {
    
	@Id
    public Long id;
	@Index
    public String title;
    public String content;
    public String picture;
    
    //private static Long lastId = 1L;
    public Achievement() {}

    public Achievement(String title, String content, String picture) {
        
        //this.id = lastId;
        this.title = title;
        this.content = content;
        this.picture = picture;
        //lastId++;
    }
}
