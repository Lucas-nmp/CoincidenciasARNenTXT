package coincidenciasarnentxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Programa para la búsqueda de cadenas de ARN iguales en un archivo TXT proporcionado. 
 * Las cadenas tienen que estar en diferentes líneas dentro del TXT 
 * En este programa voy a crear el archivo TXT y llenarlo con 12 letras seleccionadas aleatoriamente de entre las letras A, C, G y U
 * Es un programa que pidió un compañero de clase y me gustó mucho como para practicar
 *
 * 
 * @author Lucas
 */

public class CoincidenciasARNenTXT {

    static List <String> paraInsertar;
    
    
    public void crearLlenarTXT() {
        
         String letrasPermitidas[] = {"A", "C", "G", "U"};
         String ARN = "", escribir = "";
         
         
         try {
            String ruta = "Cadenas de ARN.txt";
            File file = new File(ruta);
            if(!file.exists()){
                file.createNewFile();
            }
             FileWriter fw = new FileWriter(file);
             try (BufferedWriter bw = new BufferedWriter(fw)) {
                 for(int i=0; i<1000; i++){
                     for(int x=0; x<12; x++) {
                         ARN += letrasPermitidas[(int) Math.floor(Math.random()*3)];
                     }
                     ARN += "\n";
                     escribir = ARN;
                 }
                 bw.write(escribir);
             }
            
            
        } catch (IOException e) {
        } 
    }
    
    private static <Tipo>Set<Tipo> buscarDuplicados(List<Tipo> list) {
        
        Set <Tipo> set = new HashSet<>();
        for (Tipo i : list) {
            if (Collections.frequency(list, i)>1) {
                set.add(i);
            }
        }
        return set;
    }
    
    public void leerTXT() {
        
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        
        try {
            archivo = new File("Cadenas de ARN.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            String linea;
            paraInsertar = new ArrayList<>();
            while((linea=br.readLine())!=null) {
                paraInsertar.add(linea);
            
        }
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != fr){
                    fr.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
    public static void main(String[] args) {
        CoincidenciasARNenTXT arn = new CoincidenciasARNenTXT();
        arn.crearLlenarTXT();
        arn.leerTXT();
        Set<String> duplicados = buscarDuplicados(paraInsertar);
        System.out.println(duplicados); // imprime por consola los ARN duplicados en el TXT, es mejor crear otro archivo con los resultados
        
        
    }
    
}
