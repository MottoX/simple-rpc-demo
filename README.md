# simple-rpc-demo
This is a demo project that shows how a simple basic RPC framework works.  
There are three basic modules in the project. The service implemented in server module is a simple service for calculating the sum of two int numbers. The framework module works as a simple basic RPC framework for registering and invoking service.

## Getting started

Git and maven are prerequisites for getting and making the project.

1. **Check out the project**

		$ git clone git@github.com:MottoX/simple-rpc-demo.git

2. **Compile and package the project**

		$ sh build.sh

3. **Run JAR of server**

		$ sh start_server.sh

4. **Run JAR of client**

		$ sh start_client.sh


Then, you can view what is being printed on consoles.
