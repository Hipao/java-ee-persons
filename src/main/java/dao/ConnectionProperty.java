package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс, читающий конфигурационный файл config/config.properties из classpath
 * и предоставляющий доступ к параметрам подключения к БД по ключу.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class ConnectionProperty {

    public static final String CONFIG_NAME = "config.properties";

    public static final Properties PROPERTY_CONFIG = new Properties();

    static {
        try (InputStream is = ConnectionProperty.class.getClassLoader()
                .getResourceAsStream("config/" + CONFIG_NAME)) {
            if (is == null) {
                throw new IOException("Resource config/" + CONFIG_NAME + " not found on classpath");
            }
            PROPERTY_CONFIG.load(is);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionProperty() {
        // utility class
    }

    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}
