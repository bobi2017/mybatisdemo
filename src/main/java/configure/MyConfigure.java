package configure;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConfigure {

    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    public Connection loadDataSource(String source) {
        InputStream inputStream = null;
        try {
            inputStream = loader.getResourceAsStream(source);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            return getDataSource(root);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private Connection getDataSource(Element root) {
        if ("datasouce".equals(root.getName())) {
            System.out.println("xml错误");
            return null;
        }
        String url = null;
        String driverClassName = null;
        String username = null;
        String password = null;
        for (Object obj : root.elements("property")) {
            if (obj instanceof  Element) {
                Element element = (Element) obj;
                String value = this.getValue(element);
                String name = element.attributeValue("name");
                switch (name) {
                    case "url":
                        url = value;
                        break;
                    case "driverClassName" :
                        driverClassName = value;
                        break;
                    case "username" :
                        username = value;
                        break;
                    case "password":
                        password = value;
                        break;
                    default:
                        break;
                }
            }
        }
        Connection connection ;
        try {
            connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String getValue(Element element) {
        return  element.hasContent() ? element.getText() : element.attributeValue("value");
    }

    public static void main(String[] args) {
        MyConfigure myConfiguration = new MyConfigure();
        Connection connection =  myConfiguration.loadDataSource("sql.xml");
        System.out.println(connection);
    }

}
