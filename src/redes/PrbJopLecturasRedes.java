/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import Lecturas.*;
import java.io.IOException;

/**
 *
 * @author luisg
 */
public class PrbJopLecturasRedes
{
 
    public static void main(String[] args) throws IOException
    {
        Lecturas l = new Lecturas();
        int x = l.leerInt("dame la cantidad de host´s a subnetear:");
        String s=l.leerStrings("dame la ip de red inicial:");
        String titulo=l.leerStrings("dame el nombre del archivo a crear");
        subneteo subnet = new subneteo(x, s, titulo);
        subnet.crearExcel();

    
    }

}
