package servidor.impresora;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.print.*;
import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EchoGetHandler implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {
        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);

        // send response
        String response = "";
        for (String key : parameters.keySet())
            response += key + " = " + parameters.get(key) + "n";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());

        os.close();

    }

    public static void parseQuery(String query, Map<String, Object> parameters) throws IOException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }


                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                    System.out.println(value);





                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List values = (List) obj;
                        values.add(value);
                        System.out.println(values);


                    } else if (obj instanceof String) {
                        List values = new ArrayList();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                        System.out.println(parameters);

                    }
                } else {
                    parameters.put(key, value);
                    System.out.println(parameters);

                    var myObj = new File("ticket" + parameters.get("nroTicket") + ".xml");
                    FileWriter objeto = new FileWriter("Ticket" + parameters.get("nroTicket") + ".xml");
                    objeto.write("<Root><leyendas><alto>10</alto><ancho>15</ancho><texto>Bienvenido a Banco Galicia</texto><fontSize>14</fontSize><posicionX>25</posicionX><posicionY>0</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>Usted serÃƒÂ¡ atendido con el nÃƒÂºmero </texto><fontSize>12</fontSize><posicionX>10</posicionX><posicionY>90</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>La Compra - Venta de Moneda extranjera por sucursal es hasta el cierre de atenciÃƒÂ³n al pÃƒÂºblico. PodÃƒÂ©s realizarlo hasta las 21hs a travÃƒÂ©s de la App Galicia</texto><fontSize>10</fontSize><posicionX>5</posicionX><posicionY>200</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><banners><alto>42</alto><ancho>165</ancho><posicionX>60</posicionX><posicionY>30</posicionY><PathImagen>C:Impresor32BitsImagenAImprimir.JPG</PathImagen></banners><leyendas><alto>10</alto><ancho>15</ancho><texto> " +parameters.get("nroTicket") + "</texto><fontSize>28</fontSize><posicionX>100</posicionX><posicionY>130</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas><leyendas><alto>10</alto><ancho>15</ancho><texto>11/4/2019</texto><fontSize>10</fontSize><posicionX>110</posicionX><posicionY>290</posicionY><fontFamily/><fontstyle>0</fontstyle></leyendas></Root>");
                    objeto.close();

                    System.out.println("File created: " + myObj.getName());


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

            }

            }

        }

    }

}