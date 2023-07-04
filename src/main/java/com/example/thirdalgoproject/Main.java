package com.example.thirdalgoproject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Main extends Application {

	static ArrayList<Building> Buildings;
	static Building sourceBuilding = null;
	static Building destinationBuilding = null;
	Pane root = new Pane();
	ComboBox<Label> source = new ComboBox<Label>();
	ComboBox<Label> dest = new ComboBox<Label>();
	static float mapHeight=600;
	static float mapWidth=1000;

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		Stage primaryStage = new Stage();

		Scene scene = new Scene(root, 1580, 715);//1580*715
		primaryStage.setTitle("Third Analysis of Algorithm Project");
		root.setStyle("-fx-background-color: #FFE4C4	;\r\n");
		initialize();
		Label names[] = new Label[Buildings.size()];
		Label s = new Label("Source:");
		s.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		s.setTextFill(Color.DARKRED);
		Label d = new Label("Destination:");
		d.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		d.setTextFill(Color.DARKRED);
		source.setStyle("-fx-background-radius:100;\r\n");
		dest.setStyle("-fx-background-radius:100;\r\n");
		for (int i = 0, j = 0; i < names.length; i++, j++) {
			names[i] = new Label();
			names[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
			names[i].setTextFill(Color.DARKRED );
			names[i].setText(Buildings.get(i).name);
			source.getItems().add(names[i]);
			names[j] = new Label();
			names[j].setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
			names[j].setTextFill(Color.DARKRED);
			names[j].setText(Buildings.get(j).name);
			dest.getItems().add(names[j]);
		}
		source.setTranslateX(1200);
		source.setTranslateY(50);
		source.setPrefSize(180, 50);
		dest.setTranslateX(1200);
		dest.setTranslateY(150);
		dest.setPrefSize(180, 50);
		s.setTranslateX(1020);
		s.setTranslateY(50);
		d.setTranslateX(1020);
		d.setTranslateY(150);


		source.setOnAction(e -> {
			sourceBuilding=Dijkstra.allNodes.get(source.getValue().getText());
			if(sourceBuilding!=null) {
			sourceBuilding.getTest().setStyle("-fx-background-color: #BC8F8F;\r\n" + "        -fx-background-radius:100;\r\n");
			}
		});
		dest.setOnAction(i->{
			destinationBuilding=Dijkstra.allNodes.get(dest.getValue().getText());
			if(destinationBuilding!=null) {
			destinationBuilding.getTest().setStyle("-fx-background-color: #BC8F8F;\r\n" + "        -fx-background-radius:100;\r\n");
			}
		});

		Button run = new Button("Run");
		run.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		run.setTranslateX(1100);
		run.setTranslateY(220);
		run.setMinWidth(200);
		run.setMinHeight(80);
		run.setMaxWidth(200);
		run.setMaxHeight(80);
		run.setAlignment(Pos.CENTER);
		run.setPrefSize(150, 60);
		run.setStyle("-fx-background-color: #FFB6C1;\r\n" + "        -fx-background-radius:50;\r\n");

		TextArea path = new TextArea();
		path.setTranslateX(1150);
		path.setTranslateY(320);
		path.setMinSize(270, 180);
		path.setMaxSize(270, 180);
		path.setEditable(false);
		
		Label p=new Label("Path:");
		p.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		p.setTextFill(Color.DARKRED);
		p.setTranslateX(1020);
		p.setTranslateY(320);

		TextField t1 = new TextField();
		t1.setTranslateX(1200);
		t1.setTranslateY(530);
		t1.setPrefSize(220, 50);
		t1.setStyle("        -fx-background-radius:100;\r\n");
		t1.setEditable(false);
		t1.setFont(new Font(20));
		
		Label t=new Label("Distance:");
		t.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		t.setTranslateX(1020);
		t.setTranslateY(530);
        t.setTextFill(Color.DARKRED);
		run.setOnAction(e -> {

			int v=0,w=0;
			for(int i=0;i<Buildings.size();i++) {
				if(sourceBuilding.getFullName().equals(Buildings.get(i).getFullName()))
					v=i;
				if(destinationBuilding.getFullName().equals(Buildings.get(i).getFullName()))
					w=i;
			}
			if (sourceBuilding != null && destinationBuilding != null) {//
				Dijkstra graph = new Dijkstra(Buildings, Buildings.get(v), Buildings.get(w));
				graph.generateDijkstra();
				drawPathOnMap(graph.pathTo(Buildings.get(w)));
				root.getChildren().add(group);
				path.setText(graph.getPathString());
				path.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
				t1.setText(graph.distanceString);
				t1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			}

		});
		
		Button reset=new Button("Reset");
		reset.setPrefSize(150, 60);
		reset.setAlignment(Pos.CENTER);
		reset.setTranslateX(1100);
		reset.setTranslateY(610);
		reset.setStyle("-fx-background-color: #FFB6C1;\r\n" + "        -fx-background-radius:50;\r\n");
		reset.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		
		reset.setOnAction(action ->{
			sourceBuilding.getTest().setStyle("-fx-background-color: #D8BFD8;\r\n" + "        -fx-background-radius:100;\r\n");
			destinationBuilding.getTest().setStyle("-fx-background-color: #D8BFD8;\r\n" + "        -fx-background-radius:100;\r\n");
			sourceBuilding=new Building();
			destinationBuilding=new Building();
			group.getChildren().clear();
			root.getChildren().remove(group);
			source.setValue(new Label(""));
			dest.setValue(new Label(""));
			path.setText(null);
			t1.setText(null);
		});

		root.getChildren().addAll(source, dest, run, path, t1, s, d,reset,p,t);
		primaryStage.setScene(scene);// set the scene
		primaryStage.show();
	}

	public void initialize() {
		Image image1 = new Image("file:///Users/hindsuleiman/Desktop/birzeit.jpeg");
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitHeight(mapHeight);
		imageView1.setFitWidth(mapWidth);
		imageView1.setVisible(true);
		root.getChildren().add(imageView1);
		for (int i = 0; i < Buildings.size(); i++) {

			Button b = new Button();
			Buildings.get(i).setTest(b);
			b.setUserData(Buildings.get(i));//get the name of bulding 
			b.setTranslateX(getX(Buildings.get(i).x));//get x
			b.setTranslateY(getY(Buildings.get(i).y));//get y

			b.setMinWidth(15);
			b.setMinHeight(15);
			b.setMaxWidth(15);
			b.setMaxHeight(15);

			// set the button color and radius using css
			b.setStyle("-fx-background-color: #90EE90;\r\n" + "        -fx-background-radius:100;\r\n");
			b.setOnAction(event -> {
				b.setStyle("-fx-background-color: #2E8B57;\r\n" + "        -fx-background-radius:100;\r\n");
				if (sourceBuilding == null) {
					sourceBuilding = (Building) b.getUserData();//get name of sours
					Label l = new Label();
					l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
					l.setTextFill(Color.DARKRED);
					l.setText(sourceBuilding.name);
					source.setValue(l);
				} else if (destinationBuilding == null && sourceBuilding != null) {
					destinationBuilding = (Building) b.getUserData();
					Label l = new Label();
					l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
					l.setTextFill(Color.DARKRED);
					l.setText(destinationBuilding.name);
					dest.setValue(l);
				}
			});

			Label lb = new Label(Buildings.get(i).name);
			//lb.setFont(new Font(10));
			

			lb.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
			lb.setTextFill(Color.WHITE);
			lb.setTranslateX(getX(Buildings.get(i).x)+20);
			lb.setTranslateY(getY(Buildings.get(i).y));

			root.getChildren().add(b);
			root.getChildren().add(lb);
		}

	}

	Group group=new Group();
	
	private void drawPathOnMap(Building[] Building) {
		for (int i = 0; i < Building.length - 1; i++) {
			Line line = new Line(getX(Building[i].x)+7.5 ,getY(Building[i].y)+7.5 ,
					getX(Building[i+1].x)+7.5 ,getY(Building[i+1].y)+7.5 );
			line.setStroke(Color.DARKRED);
			line.setStrokeWidth(2);
			group.getChildren().add(line);
		}
		
	}
	
	private float getX(float xBuilding) {
		float div=mapWidth/1200;
		return ((3.3334f*xBuilding)-45)*div+mapWidth/2;
		
	}
	private float getY(float yBuilding) {
		float div=mapHeight/715;
		return ((-3.97222f*yBuilding)-22.5f)*div+mapHeight/2;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Buildings = Dijkstra.readFile();
		launch(args);
	}
}