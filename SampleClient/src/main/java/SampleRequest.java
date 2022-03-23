import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SampleRequest {

    public static void main(String[] args) throws IOException {

        URL url = new URL("http://185.194.217.213:8080/api/user/login?key=dubsky&username=marck&password=pwpwpw");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(6666);
        connection.setReadTimeout(6666);
        int result = connection.getResponseCode();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String ausgabe;
        StringBuffer ausgabeP = new StringBuffer();
        while((ausgabe = input.readLine()) != null) {
            ausgabeP.append(ausgabe);
        }
        input.close();
        connection.disconnect();
        System.out.println(ausgabeP);
        if(ausgabeP.length() > 5) {
            //ERFOLGREICH
            System.out.println("Angemeldet");
        } else {
            System.out.println("Falsche Daten");
        }

    }

}
