# simple-rpc-demo
This is a demo project that shows how a simple basic RPC framework works.  
The project is divided into three modules. 
* **Server**  
Providing a simple service of calculating sum of two ints.
* **Client**  
Calling service to calculate sum of two ints.
* **Framework**  
Serving as a simple basic RPC framework for registering and invoking service.

## Getting started

Git and maven are prerequisites for getting and making the project.

1. **Check out the project**

    ```shell
    git clone git@github.com:MottoX/simple-rpc-demo.git
    ```
        
2. **Compile and package the project**

    ```shell
    sh build.sh
    ```
        
3. **Run JAR of server**

    ```shell
    sh start_server.sh
    ```
    
4. **Run JAR of client**

    ```shell
    sh start_client.sh
    ```

Then, you can view information that is being printed on consoles of client and server.
