package naming;

import java.io.IOException;

public class NamingServer {
	private static int port = 1414;
	
	public static void main(String[] args) throws IOException, Throwable {
		NamingInvoker invoker = new NamingInvoker();
		invoker.invoke(port);
	}
}
