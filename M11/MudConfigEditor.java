/*
Clint Scott
CSD 420
M11 – Jackson JSON API Example for Fallen Night MUD
10/12/2025

The following example was adapted from examples provided in the Jackson 
documentation and tutorials (https://www.baeldung.com/jackson). 
It was modified to demonstrate how the Fallen Night MUD could handle 
JSON-based configuration files.
*/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;

public class MudConfigEditor {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File("fallen_night_config.json");

        try {
            // Check if the file exists; create one if missing
            if (!configFile.exists()) {
                System.out.println("No configuration found — creating default fallen_night_config.json...");
                ObjectNode defaultConfig = mapper.createObjectNode();
                defaultConfig.put("serverName", "Fallen Night");
                defaultConfig.put("port", 4000);
                defaultConfig.put("maxPlayers", 100);
                defaultConfig.put("motd", "Welcome to Fallen Night, a world reborn!");
                mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, defaultConfig);
            }

            // Load existing configuration
            JsonNode root = mapper.readTree(configFile);

            // Display current server information
            System.out.println("Server: " + root.path("serverName").asText("Unknown Server"));
            System.out.println("MOTD: " + root.path("motd").asText("No message of the day found."));

            // Modify live configuration
            ((ObjectNode) root).put("motd", "Welcome back to the world of Fallen Night!");
            ((ObjectNode) root).put("lastUpdated", System.currentTimeMillis());

            // Save updated configuration
            mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, root);
            System.out.println("Configuration updated successfully.");

        } catch (IOException e) {
            System.err.println("Error handling configuration: " + e.getMessage());
        }
    }
}