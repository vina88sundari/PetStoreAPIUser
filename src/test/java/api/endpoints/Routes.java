package api.endpoints;

public class Routes {
	
	//create-user =https://petstore.swagger.io/v2/user
	//get user =https://petstore.swagger.io/v2/user/{username}
	//delete user=https://petstore.swagger.io/v2/user/{username}
	//update user=https://petstore.swagger.io/v2/user/{username}
	
	public static String baseURL="https://petstore.swagger.io/v2";
	public static String createuser=baseURL+"/user";
	public static String getuser=baseURL+"/user/{username}";
	public static String updateuser=baseURL+"/user/{username}";
	public static String delete=baseURL+"/user/{username}";

}
