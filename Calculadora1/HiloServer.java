/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;

/**
 *
 * @author raimond
 */
class HiloServer extends Thread {
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Socket Middleware;
  

    public HiloServer(Socket sc, DataInputStream entrada, DataOutputStream salida) {
        
        this.Middleware = sc;
        this.entrada = entrada;
        this.salida = salida;
        
    }

    @Override
    public void run() {
        
            try {
                String operacion_a_resolver;
                
                
                String operando1 = "";
                String operando2 = "";
                
                double operador1 = 0;
                double operador2 = 0;
                
                int banderaOperacion = 0;
                
                operacion_a_resolver = entrada.readUTF();
                System.out.println(operacion_a_resolver);
                
                int check = 0;
                for (int i = 0; i < operacion_a_resolver.length(); i++) {

                    if (operacion_a_resolver.charAt(i) == '*') {
                        check = 1;
                        banderaOperacion = i;
                        break;
                    }
                    if (operacion_a_resolver.charAt(i) == '/') {
                        check = 2;
                        banderaOperacion = i;
                        break;
                    }
                    if (operacion_a_resolver.charAt(i) == '+') {
                        check = 3;
                        banderaOperacion = i;
                        break;
                    }
                    if (operacion_a_resolver.charAt(i) == '-') {
                        check = 4;
                        banderaOperacion = i;
                        break;
                    }
                    operando1 = operando1 + operacion_a_resolver.charAt(i);
                }
                for (int i = banderaOperacion + 1; i < operacion_a_resolver.length(); i++) {
                    operando2 = operando2 + operacion_a_resolver.charAt(i);
                }
                operador1 = parseDouble(operando1);
                System.out.println(operador1);
                operador2 = parseDouble(operando2);
                System.out.println(operador2);
                double resultado = 0;
                
                switch (check) {
                    case 1:
                        resultado = (operador1 * operador2);
                        String resultado1 = String.valueOf(resultado);
                        salida.writeUTF(resultado1);
                        break;
                    case 2:
                        resultado = (operador1 / operador2);
                        String resultado2 = String.valueOf(resultado);
                        salida.writeUTF(resultado2);
                        break;
                    case 3:
                        resultado = (operador1 + operador2);
                        String resultado3 = String.valueOf(resultado);
                        salida.writeUTF(resultado3);
                        break;
                    case 4:
                        resultado = (operador1 - operador2);
                        String resultado4 = String.valueOf(resultado);
                        salida.writeUTF(resultado4);
                        break;
                    default:
                        System.out.println("Debe introducir una operacion");
                }
            } catch (IOException ex) {
                
            }
        }   
}
