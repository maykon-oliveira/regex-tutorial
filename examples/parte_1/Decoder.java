package testes;

import org.json.simple.JSONObject;

/**
 * @author Maykon Oliveira
 */
public class Decoder {

    public static void main(String[] args) {
        
        /* -------------------------------------------------------
         * TESTE 1 
         * cria um JSONObject para armazenar dados de um filme
         * -------------------------------------------------------*/
        
        // instancia um novo objeto JOSNOBJECT
        JSONObject json_obj = new JSONObject();
        
        //preenche o objeto com os campos: titulo, ano e genero
        json_obj.put("titulo", "JSON x XML: a Btalaha Final");
        json_obj.put("ano", 2012);
        json_obj.put("genero", "Ação");
        
        // serializa para uma string e imprime
        String json_obj_string = json_obj.toJSONString();
        System.out.println("objeto original -> " + json_obj_string);
        System.out.println("");
        
        // altera o titulo e imprime a nova configuração do objeto
        json_obj.put("titulo", "JSON x XML> o Confronto das Linguagens");
        json_obj_string = json_obj.toJSONString();
        System.out.println("objeto modificado -> " + json_obj_string);
        System.out.println("");
        
        // recupera campo por campo com o método get() e imprime cada um
        String titulo = (String) json_obj.get("titulo");
        Integer ano = (Integer) json_obj.get("ano");
        String genero = (String) json_obj.get("genero");
        
        System.out.println("titulo: " + titulo);
        System.out.println("ano: " + ano);
        System.out.println("genero: " + genero);	
    }
    
}
