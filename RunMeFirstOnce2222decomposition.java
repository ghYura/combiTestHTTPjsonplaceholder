//<<Init--------------------------------------------------------------------------------------------Decomposition part>>
import org.testng.Assert;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RunMeFirstOnce2222decomposition {
    public static String FW_PATH_FILES_TO;
    public static int FW_VAR = 0;
    public static int FW_CUSTOM_VAR = 0;
    public static int FW_EXIT_CODE = 0;
    public static String FW_ARGS;
    public static HttpResponse response = null;
    public static List list = new LinkedList();
    public static int stepCounter = 0, subItemNo = 0;
    public static String tcID = "TC" + "1_0_0";
    public final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    enum Color {
        RESET("\033[0m"),
        RED_BOLD_BRIGHT("\033[1;91m"),
        RED_BACKGROUND_BRIGHT("\033[0;101m"),
        GREEN_BACKGROUND("\u001B[42m");
        private final String code;

        Color(String code) {
            this.code = code;
        }
        public String toString() {
            return code;
        }
    }
    private HttpResponse sendGet(String string, Map data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(string)).setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private HttpResponse sendPost(String string, Map data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().POST(buildFormDataFromMap2(data)).uri(URI.create(string)).setHeader("Content-Type", "application/json; charset=UTF-8").header("Accept", "application/json").build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private HttpResponse sendPut(String string, Map data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().PUT(buildFormDataFromMap2(data)).uri(URI.create(string)).setHeader("Content-Type", "application/json; charset=UTF-8").header("Accept", "application/json").build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private HttpResponse sendPatch(String string, Map data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().method("PATCH", buildFormDataFromMap2(data)).uri(URI.create(string)).setHeader("Content-Type", "application/json; charset=UTF-8").header("Accept", "application/json").build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private HttpResponse sendDelete(String string) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(string)).setHeader("Content-Type", "application/json; charset=UTF-8").header("Accept", "application/json").build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private static HttpRequest.BodyPublisher buildFormDataFromMap2(Map data) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (Object entry : data.entrySet()) {
            if (builder.length() > 1) {
                builder.append(",");
            }
            builder.append("\"").append(((Map.Entry) entry).getKey().toString()).append("\"");
            builder.append(" : ");
            builder.append((((Map.Entry) entry).getValue() instanceof Number) ? "" : "\"").append(((Map.Entry) entry).getValue().toString()).append((((Map.Entry) entry).getValue() instanceof Number) ? "" : "\"");
        }
        builder.append("}");
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    private static void passPrinter(String printGivenHereString) {
        System.out.print(Color.GREEN_BACKGROUND + " " + Color.RESET);
        System.out.print(printGivenHereString);
        System.out.println(Color.RESET);
        System.out.println(Color.GREEN_BACKGROUND + " " + Color.RESET + "STR:" + list.toString().replaceAll("\\[|\\]|,\\s?(?=\\d{1,}\\))", "\n" + Color.GREEN_BACKGROUND + " " + Color.RESET));
    }
    private static void failPrinter(String printGivenHereString) {
        System.out.print(Color.RED_BACKGROUND_BRIGHT + " " + Color.RESET);
        System.out.println(printGivenHereString);
        System.out.println(Color.RED_BACKGROUND_BRIGHT + " " + Color.RESET + "STR:" + list.toString().replaceAll("\\[|\\]|,\\s?(?=\\d{1,}\\))", "\n" + Color.RED_BACKGROUND_BRIGHT + " " + Color.RESET));
    }
    private static void verification(HttpResponse response) {
        subItemNo++;
        try {
            Assert.assertEquals(response.statusCode(), 200, "Msg: response.statusCode() = '" + response.statusCode() + "'");
        } catch (AssertionError asErr) {
            FW_EXIT_CODE = 2;
            FW_CUSTOM_VAR = 2;
            System.out.println(Color.RED_BOLD_BRIGHT + tcID + "." + subItemNo + "." + FW_CUSTOM_VAR + Color.RESET);
            failPrinter(asErr.getMessage() + "\nResponse body:\n" + response.body().toString().replaceAll("\n\r|\r\n|\n|\r|\\s{2,}", ""));
        } finally {
            try {
                Assert.assertFalse(((String) response.body()).matches("([Ee][Rr][Rr][Oo][Rr])|([Ff][Aa][Ii][Ll])"), "response.body():\n" + response.body());
            } catch (AssertionError asErr) {
                FW_EXIT_CODE = 3;
                FW_CUSTOM_VAR = 3;
                System.out.println(Color.RED_BOLD_BRIGHT + tcID + "." + subItemNo + "." + FW_CUSTOM_VAR + Color.RESET);
                failPrinter(asErr.getMessage());
                failPrinter(response.body().toString().replaceAll("\n\r|\r\n|\n|\r|\\s{2,}", ""));
            } finally {
                try {
                    Assert.assertTrue(((String) response.body()).contains("{"), "'{'-symbol not found in the response body");
                    if (FW_CUSTOM_VAR == 0) {
                        passPrinter(tcID + "." + subItemNo + "." + FW_CUSTOM_VAR + " " + response.statusCode() + " " + response.body().toString().replaceAll("\n\r|\r\n|\n|\r|\\s{2,}", ""));
                    }
                } catch (AssertionError asErr) {
                    FW_EXIT_CODE = 4;
                    FW_CUSTOM_VAR = 4;
                    System.out.println(Color.RED_BOLD_BRIGHT + tcID + "." + subItemNo + "." + FW_CUSTOM_VAR + Color.RESET);
                    failPrinter(asErr.getMessage());
                    failPrinter(response.body().toString().replaceAll("\n\r|\r\n|\n|\r|\\s{2,}", ""));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        RunMeFirstOnce2222decomposition obj = new RunMeFirstOnce2222decomposition();
        String id = "1", name = "1", salary = "1", age = "1";
        String string = "", string2 = "";
        Map data = new LinkedHashMap();
//<<dataPut-----------------------------------------------------------------------------------------Decomposition part>>
        data.put("name", name);
        data.put("salary", salary);
        data.put("id", id);
        data.put("age", age);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string + "' data: " + data.toString());
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest----------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string + "' data: " + data.toString());
        verification(response);
//<<------------------------------------------------------------------------------------------------Decomposition part>>
//<<string2string-----------------------------------------------------------------------------------Decomposition part>>
        string2 = string;
//<<------------------------------------------------------------------------------------------------Decomposition part>>
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr------------------------------------------------------------------------------------------Decomposition part>>
        string = "https://jsonplaceholder.typicode.com/posts";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<suddenDelete------------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendDelete(string2);
                                                                                                                        list.add(++stepCounter + ") HTTP DELETE: '" + string2 + "'");
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/albums/1/photos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/comments?postId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPatch(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PATCH: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/posts";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPost(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP POST: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/todos";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendGet(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP GET: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/posts/1/comments";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<urlStr2-----------------------------------------------------------------------------------------Decomposition part>>
        string2 = "https://jsonplaceholder.typicode.com/users/1/albums";
//<<objSendRequest2---------------------------------------------------------------------------------Decomposition part>>
        response = obj.sendPut(string2, data);
                                                                                                                        list.add(++stepCounter + ") HTTP PUT: '" + string2 + "' data: " + data.toString());
        verification(response);
//<<ending------------------------------------------------------------------------------------------Decomposition part>>
    }
}