This README contains the details of the Project members for 'Doodl' and the instructions to execute the java program correctly.

Team Members:
1. Soham Chakraborty(22053198)
2. Trideb Dhar(22053211)
3. Shreyansh(22053196)
4. Shreya Mishra(22053195)
5. Simran Singh(22054089)

You need to have the following installed in your system to run into your VScode editor(on any other editor/IDE of your choice):
1. javaFX .jar files
2. JDK version 17.0.7
3. Python version 3.9.19
4. PyTorch
5. pip modules(mentioned below)

Extensions:
6. Extension Pack for Java v0.25.20240325

1) How to load javaFX .jar files into your project?
(a)->open your project file with help of "open with code" from the open options
->then enable the extension for the java project file
->after allowing the extension you will be able to see the java project area in the bottom left side of vs code 
->goto the bottom and scroll down to get the plus button in the "referrenced libraries" option 
->click on the plus button and navigate to the lib folder in the javafx sdk folder
->select all the jar files and add them 
(b)->now go to the "run" option the top left menubar of your vs code and click on the open configuration
->there in the configurations in the second block , below "request" add this 
 "vmArgs": "--module-path \"C:/add/your/lib-folder-location\" --add-modules javafx.controls,javafx.fxml"{note:dont forfet to put comma}
->now save the file and go to app.java, you will find the run button above the main class . RUN IT!!!!

2) How to download PyTorch into your system? (run these commands in your terminal)
   a) If you want CPU support:
   -> pip install torch torchvision torchaudio
   b) If you have a specific CUDA version and want GPU support, you can specify it in the command:
   -> pip install torch==1.10.0+cu113 torchvision==0.11.1+cu113 torchaudio==0.10.0+cu113 -f https://download.pytorch.org/whl/torch_stable.html

3) Pip Modules:
  ->pip install requests pillow transformers
