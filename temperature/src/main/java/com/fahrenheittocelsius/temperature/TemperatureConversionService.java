package com.fahrenheittocelsius.temperature;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.net.URL;

@Service
public class TemperatureConversionService {
    private final String NAMESPACE_URI = "https://www.w3schools.com/xml/";
    private final String ENDPOINT_URL = "https://www.w3schools.com/xml/tempconvert.asmx";

    public double convertCelsiusToFahrenheit(double celsius) {
        try {
            // Create a SOAP connection factory
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            // Create a SOAP connection from the SOAP connection factory
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Create a URL object for the endpoint
            URL endpointUrl = new URL(ENDPOINT_URL);

            // Send the SOAP request and get the response
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(celsius), endpointUrl);

            // Extract the Fahrenheit temperature from the SOAP response
            double fahrenheit = extractFahrenheitFromResponse(soapResponse);

            // Close the SOAP connection
            soapConnection.close();

            return fahrenheit;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SOAPMessage createSOAPRequest(double celsius) throws SOAPException {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("ns", NAMESPACE_URI);

        SOAPBody soapBody = soapEnvelope.getBody();
        QName celsiusToFahrenheitQName = new QName(NAMESPACE_URI, "CelsiusToFahrenheit", "ns");
        SOAPBodyElement celsiusToFahrenheitElement = soapBody.addBodyElement(celsiusToFahrenheitQName);

        QName celsiusQName = new QName(NAMESPACE_URI, "Celsius", "ns");
        SOAPElement celsiusElement = celsiusToFahrenheitElement.addChildElement(celsiusQName);
        celsiusElement.setValue(String.valueOf(celsius));

        return soapMessage;
    }

    private double extractFahrenheitFromResponse(SOAPMessage soapResponse) throws SOAPException {
        SOAPBody soapResponseBody = soapResponse.getSOAPBody();
        Node fahrenheitNode = soapResponseBody.getElementsByTagNameNS(NAMESPACE_URI, "Fahrenheit").item(0);
        String fahrenheitString = fahrenheitNode.getTextContent();
        return Double.parseDouble(fahrenheitString);
    }
}






//package com.fahrenheittocelsius.temperature;
//import org.springframework.stereotype.Service;
//
//import javax.xml.namespace.QName;
//import javax.xml.soap.*;
//import java.net.URL;
//
//@Service
//public class TemperatureConversionService {
//    private final String NAMESPACE_URI = "https://www.w3schools.com/xml/";
//    private final String ENDPOINT_URL = "https://www.w3schools.com/xml/tempconvert.asmx";
//
//    public double convertCelsiusToFahrenheit(double celsius) {
//        try {
//            // Create a SOAP connection factory
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//
//            // Create a SOAP connection from the SOAP connection factory
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//
//            // Create a URL object for the endpoint
//            URL endpointUrl = new URL(ENDPOINT_URL);
//
//            // Send the SOAP request and get the response
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(celsius), endpointUrl);
//
//            // Extract the Fahrenheit temperature from the SOAP response
//            double fahrenheit = extractFahrenheitFromResponse(soapResponse);
//
//            // Close the SOAP connection
//            soapConnection.close();
//
//            return fahrenheit;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private SOAPMessage createSOAPRequest(double celsius) throws SOAPException {
//        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//        soapEnvelope.addNamespaceDeclaration("ns", NAMESPACE_URI);
//
//        SOAPBody soapBody = soapEnvelope.getBody();
//        QName celsiusToFahrenheitQName = new QName(NAMESPACE_URI, "CelsiusToFahrenheit", "ns");
//        SOAPBodyElement celsiusToFahrenheitElement = soapBody.addBodyElement(celsiusToFahrenheitQName);
//
//        QName celsiusQName = new QName(NAMESPACE_URI, "Celsius", "ns");
//        SOAPElement celsiusElement = celsiusToFahrenheitElement.addChildElement(celsiusQName);
//        celsiusElement.setValue(String.valueOf(celsius));
//
//        return soapMessage;
//    }
//
//    private double extractFahrenheitFromResponse(SOAPMessage soapResponse) throws SOAPException {
//        SOAPBody soapResponseBody = soapResponse.getSOAPBody();
//        Node fahrenheitNode = soapResponseBody.getElementsByTagName("Fahrenheit").item(0);
//        String fahrenheitString = fahrenheitNode.getTextContent();
//        return Double.parseDouble(fahrenheitString);
//    }
//}


