import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class lab10 extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Lab 10 Solution");

        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.CENTER);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new Insets(25, 25, 25, 25));

        Socket client = new Socket("localhost", 16789);
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());

        Button send = new Button("Send");
        Button exit = new Button("Exit");
        Label lbMes = new Label("Message: ");
        Label lbName = new Label("Username: ");
        TextField txtMes = new TextField();
        TextField txtName = new TextField();

        myGrid.add(lbMes, 0, 1);
        myGrid.add(txtMes, 1, 1);
        myGrid.add(lbName, 0, 2);
        myGrid.add(txtName, 1, 2);
        myGrid.add(send, 0, 3);
        myGrid.add(exit, 1, 3);

        send.setOnAction(e -> {
            if (client.isConnected()){
                try{
                    dout.writeUTF(txtName.getText() + ": " + txtMes.getText());
                    dout.flush();
                }
                catch (IOException e){
                    System.out.println("Error");
                }
            }
            else{
                try{
                    dout.close();
                    client.close();
                    System.exit(0);
                }
                catch (IOException e){
                    System.out.println("Error");
                }
            }
        });

        exit.setOnAction(e -> {
            try{
                dout.close();
                client.close();
                System.exit(0);
            }
            catch (IOException e){
                System.out.println("Error");
            }
        });

        Scene scene = new Scene(myGrid, 350, 350);

        primaryStage.setTitle("lab10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
