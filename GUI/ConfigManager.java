package GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Handle tasks concerning reading and writing config files
 */
public class ConfigManager {

    private Config parseConfig(File file) throws Exception {
        Map<String, String> configMap = new HashMap<>();
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String[] s = scanner.nextLine().split(":");
            if (s.length > 0) {
                configMap.put(s[0].trim(), s[1].trim());
            }
        }

        if (!configMap.containsKey(Constants.CONFIG_KEY_IV)) {
            throw new Exception("Config file does not contains IV");
        }

        Config config = new Config();
        config.setIV(Utils.stringToByteArray(configMap.get(Constants.CONFIG_KEY_IV)));

        return config;
    }

    public Config getConfig() {
        File file = new File(Constants.CONFIG_FILE_PATH);
        Config config;
        if (file.isFile()) {
            try {
                config = parseConfig(file);
            } catch (Exception e) {
                config = ConfigManager.newConfig();
                saveConfig(config);
            }
        } else {
            config = ConfigManager.newConfig();
            saveConfig(config);
        }
        return config;
    }

    public void saveConfig(Config config) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s:%s\n", Constants.CONFIG_KEY_IV, Utils.byteToHexString(config.getIV())));

        try {
            File file = new File(Constants.CONFIG_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config newConfig() {
        Random random = new Random();
        byte[] seed = new byte[Constants.NONCE_LENGTH / 8];
        random.nextBytes(seed);

        Config c = new Config();
        c.setIV(seed);

        return c;
    }

}
