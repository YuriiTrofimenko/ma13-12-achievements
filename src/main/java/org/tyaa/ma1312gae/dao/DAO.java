package org.tyaa.ma1312gae.dao;

import java.util.List;
import com.googlecode.objectify.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

import org.tyaa.ma1312gae.entity.*;

public class DAO {

	//Получение всех  в виде списка
	public static void getAllAchievements(List<Achievement> _emptyOrdersList) throws Exception {
		
		_emptyOrdersList.addAll(ofy().load().type(Achievement.class).list());
	}
	
	public static void createAchievement(String _title, String _content) {
        
		Achievement achievement =
				new Achievement(_title, _content, "");
        
        ofy().save().entity(achievement).now();
	}
}
