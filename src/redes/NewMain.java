/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author luisg
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {

        int x=0;
        String s="";
        String titulo="";
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Dame la cantidad de hosts a subnetear:");
        x = Integer.parseInt(r.readLine());
        System.out.println("Dame la ip de red inicial:");
        s = r.readLine();
        System.out.println("Dame el nombre del archivo de excel a crear:");
        titulo = r.readLine();
        subneteo obj = new subneteo(x, s, titulo);
        
        obj.crearExcel();

    }

    

}
