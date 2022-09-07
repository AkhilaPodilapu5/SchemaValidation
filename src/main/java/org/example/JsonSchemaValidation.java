package org.example;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class JsonSchemaValidation {
    public static void main(String[] args) throws FileNotFoundException {
        //json schema
        File schemaFile = new File("Json-schema-example.json");

        JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
        JSONObject jsonSchema = new JSONObject(schemaData);

        //json data
        File jsonData = new File("data.json");
        JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonData));
        JSONObject jsonObject = new JSONObject(jsonDataFile);

        //validate schema
        JSONArray users = (JSONArray) jsonObject.get("Users");
        Schema schemaValidator = SchemaLoader.load(jsonSchema);
        for(Object o: users) {
            if(o instanceof JSONObject) {
                JSONObject jsonUser = (JSONObject )o;
                schemaValidator.validate(jsonUser);

                //use json data

                System.out.println("-------------------------------------");
                System.out.println(jsonUser);
                System.out.println("-------------------------------------");
            }
        }

    }
}