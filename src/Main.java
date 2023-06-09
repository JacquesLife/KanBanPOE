/*
this is the main class of the project it will create a worker class object and call the performLoginOrRegister method
this is done to save memory and to make the code more readable
 */

public class Main {

    public static void main(String[] args) {
        // create a worker class object and call the performLoginOrRegister method
        WorkerClass workerClass = new WorkerClass();
        workerClass.performLoginOrRegister();
        workerClass.performOperations();

    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//