package json;

import helpers.Log;
import lombok.Data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private static String configFilePath = "src/main/resources/config.json";

    public static JsonObject readConfigFile(String configFilePath) {
        try {
            FileReader reader = new FileReader(configFilePath);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            Log.error(e.getMessage());
            return null;
        }
    }

    public static ConfigObject configObject() {
        Gson gson = new Gson();
        JsonObject configFile = readConfigFile(configFilePath);
        JsonObject webConfig = configFile.get("choTot").getAsJsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("browser", webConfig.get("browser").getAsString());
        jsonObject.addProperty("urlHome", webConfig.get("urlHome").getAsString());
        jsonObject.addProperty("waitTime", webConfig.get("wait").getAsString());

        ConfigObject configObject = gson.fromJson(jsonObject, ConfigObject.class);
        return configObject;
    }

    @Data
    public static class ConfigObject {
        private String browser;
        private String urlHome;

        public int getWaitTime() {
            return waitTime;
        }

        public void setWaitTime(int waitTime) {
            this.waitTime = waitTime;
        }

        private int waitTime;

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public String getUrlHome() {
            return urlHome;
        }

        public void setUrlHome(String urlHome) {
            this.urlHome = urlHome;
        }
    }

    public static void main(String[] args) {
        ConfigObject configObject = new ConfigObject();
        System.out.println(configObject.getBrowser());
        JsonObject js = readConfigFile(configFilePath);
        JsonObject webConfig = js.get("choTot").getAsJsonObject();
        System.out.println(webConfig.get("browser").getAsString());
    }
}
