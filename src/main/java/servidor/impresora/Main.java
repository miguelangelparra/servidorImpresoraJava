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
server.createContext("/", new RootHandler());
//server.createContext("/echoHeader", new EchoHeaderHandler());
//server.createContext("/echoGet", new EchoGetHandler());
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

      var myObj  = new File("filename"+nroticket+".xml");
                           FileWriter objeto = new FileWriter ("Camarones"+nroticket+".xml");
                   // try (FileWriter objeto = new FileWriter (myObj)) {
                         objeto.write("Este es el ticket Nro: " + nroticket);
                         objeto.close();
                    // }
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
                 
                  
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
         }

      
 

}
