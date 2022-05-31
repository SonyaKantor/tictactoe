/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.CallableStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    @Override
    // This function handles all the options of user-side requests( JS) and sends XML-formatted
    //answers back to user. Each if-case covers a different action from user
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        final int PLAYER1 = 1;
        final int PLAYER2 = 2;
        Socket firstPlayer = null;
        Socket secondPlayer = null;
        String str = "";
        final String X = "X";
        final String O = "O";
        String figure = null;
        int[][] field = {{0,0,0},{0,0,0},{0,0,0}};
        // This action shows books on a main page
        if (action.equals("start")) {
                try {
                     ServerSocket serverSocket = new ServerSocket(8000);
                     if(firstPlayer==null){
                        firstPlayer = serverSocket.accept(); 
                        figure = X;
                     }
                     else{
                        secondPlayer = serverSocket.accept();
                        figure = O;
                     }
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                resp.setContentType("text/xml");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write("<start><figure>" + figure + "</figure></start>");
        }
          else{
             char temp = action.charAt(0);
             int Xcell = Character.getNumericValue(temp);
             temp = action.charAt(1);
             int Ycell = Character.getNumericValue(temp);
             field[Xcell][Ycell] = 1;
             if(win(field)){
                resp.setContentType("text/xml");
                resp.setHeader("Cache-control", "no-cache");
                resp.getWriter().write("<start><figure>" + "won" + "</figure></start>"); 
             }
          }
         
    }
    public boolean win(int[][] field){
        if((field[0][0]==1) && (field[0][1]==1) && (field[0][2]==1))
            return true;
        else if((field[0][0]==1) && (field[1][0]==1) && (field[2][0]==1))
            return true;    
        else if((field[1][0]==1) && (field[1][1]==1) && (field[1][2]==1))
            return true;
        else if((field[0][1]==1) && (field[1][1]==1) && (field[2][1]==1))
            return true; 
        else if((field[2][0]==1) && (field[2][1]==1) && (field[2][2]==1))
            return true;
        else if((field[0][2]==1) && (field[1][2]==1) && (field[2][2]==1))
            return true; 
        else if((field[0][0]==1) && (field[1][1]==1) && (field[2][2]==1))
            return true;
        else if((field[2][0]==1) && (field[1][1]==1) && (field[0][2]==1))
            return true; 
        else 
            return false;
    }
}