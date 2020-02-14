/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.impresora;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author m.parra.davila
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        int nroticket = 0;
int port = 9000;
HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
System.out.println("server started at " + port);
//server.createContext("/", new RootHandler());
server.createContext("/echoHeader", new EchoHeaderHandler());
server.createContext("/echoGet", new EchoGetHandler());
//server.createContext("/echoPost", new EchoPostHandler());
server.setExecutor(null);
server.start();
    }
    
}


 
class RootHandler implements HttpHandler {

    //double nroticket=  Math.random();
         @Override
         public void handle(HttpExchange he) throws IOException {
             String response = "Server start success if you see this message" + " Port: " + 9000;
             try {
                 double nroticket = Math.random();

                 var myObj = new File("filename" + nroticket + ".xml");
                 FileWriter objeto = new FileWriter("Camarones" + nroticket + ".xml");
                 // try (FileWriter objeto = new FileWriter (myObj)) {
                 objeto.write("<Root><leyendas><alto>10</alto><ancho>15</ancho><texto>Bienvenido a Banco Galicia</texto><fontSize>14</fontSize><posicionX>25</posicionX><posicionY>0</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>Usted serÃƒÂ¡ atendido con el nÃƒÂºmero" + nroticket + "</texto><fontSize>12</fontSize><posicionX>10</posicionX><posicionY>90</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>La Compra - Venta de Moneda extranjera por sucursal es hasta el cierre de atenciÃƒÂ³n al pÃƒÂºblico. PodÃƒÂ©s realizarlo hasta las 21hs a travÃƒÂ©s de la App Galicia</texto><fontSize>10</fontSize><posicionX>5</posicionX><posicionY>200</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><banners><alto>42</alto><ancho>165</ancho><posicionX>60</posicionX><posicionY>30</posicionY><PathImagen>C:Impresor32BitsImagenAImprimir.JPG</PathImagen></banners><leyendas><alto>10</alto><ancho>15</ancho><texto>D002</texto><fontSize>28</fontSize><posicionX>100</posicionX><posicionY>130</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>11/4/2019</texto><fontSize>10</fontSize><posicionX>110</posicionX><posicionY>290</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas></Root>");
                 objeto.close();
                 // }
     /* if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }*/



             /*    System.out.println("File created: " + myObj.getName());


                 String texto = "Esto es lo que va a la impresora";

                 PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                 DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                 DocPrintJob docPrintJob = printService.createPrintJob();
                 Doc doc = new SimpleDoc(texto.getBytes(), flavor, null);
                 try {
                     docPrintJob.print(doc, null);
                 } catch (PrintException e) {
                     // TODO Auto-generated catch block
                 }

                 he.sendResponseHeaders(200, response.length());
                 try (OutputStream os = he.getResponseBody()) {
                     os.write(response.getBytes());
                 }

*/
             } catch (IOException e) {
                 System.out.println("An error occurred.");
             }


         }


}
