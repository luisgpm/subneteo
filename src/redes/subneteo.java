/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import Lecturas.Lecturas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author luisg
 */
public class subneteo
{
    Lecturas leer = new Lecturas();

    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    // son variables de la ip de red
    int a;
    int b;
    int c;
    int d;

    int cantidadHost = 0; // es el numero de filas que se van a calcular
    String ipRedInicial = ""; // es la ip de red que te da inicialmente
    String titulo = "";
    String ipRed[];
    int host[];
    int rango[];
    String bits[]; // son los bits que se calculan
    String ipBoadcast[];//son las broadcast que se calculan
    String ipRedInicialArr[];
    String ipRedFinalArr[];
    int aux[];
    String masc[];

    public subneteo(int cantidadHost, String ipRedInicial, String titulo)
    {
        this.titulo = titulo;
        this.cantidadHost = cantidadHost;
        this.ipRedInicial = ipRedInicial;
        ipRed = new String[cantidadHost]; // son las ip de red resultantes
        host = new int[cantidadHost]; // son los host que te da inicialmente
        rango = new int[cantidadHost]; // son los rangos que se calculan
        bits = new String[cantidadHost]; // son los bits que se calculan
        ipBoadcast = new String[cantidadHost];
        ipRed[0] = ipRedInicial;
        ipRedInicialArr = new String[cantidadHost];
        ipRedFinalArr = new String[cantidadHost];
        aux = new int[cantidadHost];
        masc = new String[cantidadHost];
    }

    public int getNumHost()
    {
        return cantidadHost;
    }

    public void setNumHost(int numHost)
    {
        this.cantidadHost = numHost;
    }

    public void arrHost() throws IOException // pide los host que tienes inicialmente
    {
        for (int i = 0; i < cantidadHost; i++)
        {
            host[i]= leer.leerInt("dame el"+i+" #host");
        }
    }

    public void rango() // calcula el rango
    {
        for (int i = 0; i < host.length; i++)
        {
            if (host[i] + 2 > 1)
            {
                if (host[i] + 2 > 2)
                {
                    if (host[i] + 2 > 4)
                    {
                        if (host[i] + 2 > 8)
                        {
                            if (host[i] + 2 > 16)
                            {
                                if (host[i] + 2 > 32)
                                {
                                    if (host[i] + 2 > 64)
                                    {
                                        if (host[i] + 2 > 128)
                                        {
                                            if (host[i] + 2 > 256)
                                            {

                                                if (host[i] + 2 > 512)
                                                {
                                                    if (host[i] > 1024)
                                                    {
                                                        if (host[i] > 2048)
                                                        {
                                                            rango[i] = 255;
                                                            bits[i] = "2^12";
                                                            aux[i] = 15;

                                                        } else
                                                        {
                                                            rango[i] = 255;
                                                            bits[i] = "2^11";
                                                            aux[i] = 7;
                                                        }
                                                    } else
                                                    {
                                                        rango[i] = 255;
                                                        bits[i] = "2^10";
                                                        aux[i] = 3;
                                                    }
                                                } else
                                                {
                                                    rango[i] = 255;
                                                    aux[i] = 1;
                                                    bits[i] = "2^9";

                                                }
                                            } else
                                            {
                                                rango[i] = 255;
                                                bits[i] = "2^8";
                                            }

                                        } else
                                        {
                                            rango[i] = 127;
                                            bits[i] = "2^7";
                                        }
                                    } else
                                    {
                                        rango[i] = 63;
                                        bits[i] = "2^6";
                                    }
                                } else
                                {
                                    rango[i] = 31;
                                    bits[i] = "2^5";
                                }
                            } else
                            {
                                rango[i] = 15;
                                bits[i] = "2^4";
                            }
                        } else
                        {
                            rango[i] = 7;
                            bits[i] = "2^3";
                        }
                    } else
                    {
                        rango[i] = 3;
                        bits[i] = "2^2";
                    }
                } else
                {
                    rango[i] = 1;
                    bits[i] = "2^1";
                }
            }
        }
    }

    public void ipRed(int w) // maneja la ip de red, no calcula nada
    {
        String seccion = "";

        int x = 0;
        int rr = 0;

        for (int i = 0; i < ipRed[w].length() - 1; i++)
        {
            if (ipRed[w].charAt(i) == '.')
            {
                for (int j = x; j < i; j++)
                {
                    seccion += ipRed[w].charAt(j);
                }
                if (rr == 0)
                {
                    a = Integer.parseInt(seccion);
                    rr++;
                    x = i + 1;
                    seccion = "";
                } else
                {
                    if (rr == 1)
                    {
                        b = Integer.parseInt(seccion);
                        rr++;
                        x = i + 1;
                        seccion = "";
                    } else
                    {
                        if (rr == 2)
                        {
                            c = Integer.parseInt(seccion);
                            rr++;
                            x = i + 1;
                            seccion = ipRed[w].substring(i + 1, ipRed[w].length());
                            d = Integer.parseInt(seccion);
                        }
                    }
                }
            }
        }
    }

    public void ipRedBroadcast()
    {
        for (int i = 0; i < cantidadHost; i++)
        {
            ipRed(i);
            ipRedInicialArr[i] = a + "." + b + "." + c + "." + (d + 1);
            if (aux[i] > 0)
            {

                d = 255;
                masc[i] = "255.255." + (255 - aux[i]) + "." + 0;
                ipBoadcast[i] = a + "." + b + "." + (c + aux[i]) + "." + d;
                ipRedFinalArr[i] = a + "." + b + "." + ((c + aux[i])) + "." + (d - 1);

                if ((i + 1) < cantidadHost)
                {
                    ipRed[i + 1] = a + "." + b + "." + ((c + aux[i]) + 1) + "." + 0;
                }

            } else
            {
                masc[i] = "255.255.255"   + "." + (255-rango[i]);

                d = rango[i] + d;
                if (d > 255)
                {
                    int x = d - 255;
                    ipBoadcast[i] = a + "." + b + "." + (c + 1) + "." + x;
                    ipRedFinalArr[i] = a + "." + b + "." + (c + 1) + "." + (x - 1);
                    if ((i + 1) < cantidadHost)
                    {
                        ipRed[i + 1] = a + "." + b + "." + (c + 1) + "." + (x + 1);
                    }
                } else
                {
                    if (d == 255)
                    {
                        ipBoadcast[i] = a + "." + b + "." + c + "." + d;
                        ipRedFinalArr[i] = a + "." + b + "." + c + "." + (d - 1);

                        if ((i + 1) < cantidadHost)
                        {
                            ipRed[i + 1] = a + "." + b + "." + (c + 1) + "." + 0;
                        }
                    } else
                    {
                        ipBoadcast[i] = a + "." + b + "." + c + "." + d;
                        ipRedFinalArr[i] = a + "." + b + "." + c + "." + (d - 1);
                        if ((i + 1) < cantidadHost)
                        {
                            ipRed[i + 1] = a + "." + b + "." + c + "." + (d + 1);
                        }
                    }
                }
            }

        }
    }
    
    public void crearExcel() throws IOException
    {
        String  ss = titulo+".xlsx";
        arrHost();
        rango();
        ipRedBroadcast();
        Workbook libro = new XSSFWorkbook();
        Sheet sheet = libro.createSheet("subneteo");
        
        Row titulo = sheet.createRow(0);
        titulo.createCell(0).setCellValue("nombre");
        titulo.createCell(1).setCellValue("#host");
        titulo.createCell(2).setCellValue("#bits");
        titulo.createCell(3).setCellValue("#rango");
        titulo.createCell(4).setCellValue("Ip Red");
        titulo.createCell(5).setCellValue("Ip inicial");
        titulo.createCell(6).setCellValue("Ip final");
        titulo.createCell(7).setCellValue("Ip broadcast");
        titulo.createCell(8).setCellValue("Ip mascara");
        titulo.createCell(9).setCellValue("cifra");
        for (int i = 0; i < cantidadHost; i++)
        {
            Row fila = sheet.createRow(i+1);
            fila.createCell(1).setCellValue(host[i]);
            fila.createCell(2).setCellValue(bits[i]);
            fila.createCell(3).setCellValue(rango[i]);
            fila.createCell(4).setCellValue(ipRed[i]);
            fila.createCell(5).setCellValue(ipRedInicialArr[i]);
            fila.createCell(6).setCellValue(ipRedFinalArr[i]);
            fila.createCell(7).setCellValue(ipBoadcast[i]);
            fila.createCell(8).setCellValue(masc[i]);
            
        }

        try
        {
            String destino = System.getProperty("user.home")+"\\Desktop\\"+ss;
            FileOutputStream fos = new FileOutputStream(destino);
            libro.write(fos);
            fos.close();
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
