/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.ma1312gae;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tyaa.ma1312gae.dao.DAO;
import org.tyaa.ma1312gae.entity.Achievement;
import org.tyaa.ma1312gae.model.Result;

/**
 *
 * @author student
 */
@WebServlet(name = "AchievementServlet", urlPatterns = {"/achievements"})
public class AchievementServlet extends HttpServlet {
	
	static {
		
		ObjectifyService.register(Achievement.class);
	}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("deprecation")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Gson gson = new Gson();

        String action = "";

        if (request.getParameterMap().containsKey("action")) {

            action = request.getParameter("action");
        }

        switch (action) {

            case "signin": {

                String loginString = "no login";
                String passwordString = "no password";

                if (request.getParameterMap().containsKey("login")
                        && request.getParameterMap().containsKey("password")) {

                    loginString = request.getParameter("login");
                    passwordString = request.getParameter("password");
                }

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */

                    //Создали пустой список
                    ArrayList<String> data = new ArrayList<>();
                    //Добавили в него имя и пароль
                    data.add(loginString);
                    data.add(passwordString);
                    //Предали список в объект Result
                    Result result = new Result(data);
                    //Превратили объект Result в json-строку
                    //и отправили на клиент
                    out.println(gson.toJson(result));
                }
                break;
            }
            case "create": {

                if (request.getParameterMap().containsKey("title")
                        && request.getParameterMap().containsKey("content")) {

                	final String titleString = request.getParameter("title");
                	final String contentString = request.getParameter("content");
                    
                    try (PrintWriter out = response.getWriter()) {
                        
                        //Global.achievements.add(achievement);
                    	
                    	String errorString = "";
                    	
                        ObjectifyService.run(new VoidWork() {
                		    public void vrun() {
                		    	//try {
    								DAO.createAchievement(titleString, contentString);
    							//} catch (Exception ex) {
    								
    								//out.print(gson.toJson(new Result(ex.getMessage())));
    								//errorString = ex.getMessage();
    							//}
                		    }
                		});

                        ArrayList<String> data = new ArrayList<>();
                        //Добавили в него имя и пароль
                        data.add("created");
                        //data.add(passwordString);
                        //Предали список в объект Result
                        Result result = new Result(data);
                        //Превратили объект Result в json-строку
                        //и отправили на клиент
                        out.println(gson.toJson(result));
                    }
                }
                break;
            }
            case "all-achievements": {

                try (PrintWriter out = response.getWriter()) {
                	
                	try {
                		
                		List<Achievement> achievements = new ArrayList<>();
                		
                		ObjectifyService.run(new VoidWork() {
                		    public void vrun() {
                		    	//try {
									DAO.getAllAchievements(achievements);
								//} catch (Exception ex) {
									
									//out.print(gson.toJson(new Result("error")));
								//}
                		    }
                		});
                		//Предали список в объект Result
                        //Result result = new Result(Global.achievements);
                		Result result = new Result(achievements);
                		//Превратили объект Result в json-строку
                        //и отправили на клиент
                        out.println(gson.toJson(result));
                	} catch (Exception ex) {
                		
                        //out.print(gson.toJson(new Result(ex.getMessage())));
                        out.print(gson.toJson(new Result("error")));
                	}
                	
                    break;
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
