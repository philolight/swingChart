package main.component.interaction;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.component.chart.theme.themes.BlackTheme;
import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.IMark;
import main.component.interaction.panel.InteractionPanel;

public class InteractionMainFrame extends JFrame{
	
	InteractionPanel panel = new InteractionPanel();
	
	public InteractionMainFrame(){
		add(panel);
		panel.setTheme(new BlackTheme());
		
		initMainFrame();
		
		for(int i = 0 ; i < 24; i++){
			IPiece ess = getEss(100 + i * 45, 300, 40, 40, i);
			IPiece transformer = getTransformer(100 + i * 45, 340, 40, 40);
			
			GroupPiece group = new GroupPiece();
			group.add(ess);
			group.add(transformer);
			
			panel.add(group);
		}
		
		LinePiece mainLine = new LinePiece();
		mainLine.setStartPoint(50, 380);
		mainLine.setEndPoint(1000, 380);
		
		panel.add(mainLine);
		addIpc();

		addPipelines();
		
		addBendedLinePieceTest();
		
		setVisible(true);
	}
	
	public void initMainFrame(){
		setSize(1024, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void setSize(int width, int height) {				
		panel.setSize(width, height);
		super.setSize(width, height);
	}
	
	private IPiece getEss(int x, int y, int width, int height, int i){
		ImagePiece ess;
		Image image;
		TextPiece text1;
		TextPiece text2;
		
		try {
			image = ImageIO.read(new File("./image/ESS.png"));
		} catch (IOException e) {
			image = null;
		}
		
		ess = new ImagePiece();
		ess.setArea(new Rectangle(0, 0, 40, 40));
		ess.onDragged(0, 0, x, y, null);
		ess.setImage(image);
		
		text1 = new TextPiece();
		text1.setText("ESS");
		text1.setArea(new Rectangle(0, 0, width, height/2));
		text1.onDragged(0, 0, x, y, null);
		text1.setColor(Color.WHITE);
		
		text2 = new TextPiece();
		text2.setText("#" + i);
		text2.setArea(new Rectangle(0, 0, width, height/2));
		text2.onDragged(0, 0, x, y+20, null);
		text2.setColor(Color.WHITE);
		
		GroupPiece group = new GroupPiece();
		group.add(ess);
		group.add(text1);
		group.add(text2);
		
		return group;
	}
	
	private IPiece getTransformer(int x, int y, int width, int height){
		TransformerPiece piece = new TransformerPiece();
		piece.setArea(new Rectangle(0, 0, 40, 40));
		piece.onDragged(0, 0, x, y, null);
		return piece;
	}
	
	private void addIpc(){
		Image image;
		
		ImagePiece ipc;
		try {
			image = ImageIO.read(new File("./image/IPC.png"));
		} catch (IOException e) {
			image = null;
		}
		
		ipc = new ImagePiece();
		ipc.setArea(new Rectangle(0, 0, 600, 200));
		ipc.onDragged(0, 0, 200, 50, null);
		ipc.setImage(image);
		ipc.setSelectable(false);
		panel.add(ipc);
	}
	
	private void addPipelines(){
		PlanePiece start = getPipeline(200, 150);
		PlanePiece end = getPipeline(325, 125);
		LinePiece line = new LinePiece();
		Connection connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
		
		PlanePiece temp = end;
		
		end = getPipeline(325, 175);
		line = new LinePiece();
		connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
			
		start = end;
		end = getPipeline(450, 150);
		line = new LinePiece();
		connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
		
		start = temp;
		line = new LinePiece();
		connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
		
		start = end;
		end = getPipeline(575, 150);
		line = new LinePiece();
		connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
		
		start = end;
		end = getPipeline(700, 150);
		line = new LinePiece();
		connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
	}
	
	private ImagePiece getPipeline(int x, int y){
		Image image;
		
		ImagePiece piece;
		try {
			image = ImageIO.read(new File("./image/pipeline.png"));
		} catch (IOException e) {
			image = null;
		}
		
		piece = new ImagePiece();
		piece.setArea(new Rectangle(0, 0, 100, 25));
		piece.onDragged(0, 0, x, y, null);
		piece.setImage(image);
		panel.add(piece);
		return piece;
	}
	
	private void addBendedLinePieceTest(){
		PlanePiece start = getPipeline(20, 20);
		PlanePiece end = getPipeline(125, 125);
		BendedLinePiece line = new BendedLinePiece();
		Connection connection = new Connection();
		connection.setConnection(line, start, line.getMark(Directions.LINE_START), start.getMark(Directions.RIGHT));
		line.setConnection(connection);
		start.setConnection(connection);
		connection = new Connection();
		connection.setConnection(line, end, line.getMark(Directions.LINE_END), end.getMark(Directions.LEFT));
		line.setConnection(connection);
		end.setConnection(connection);
		panel.add(line);
	}
	
	public static void main(String[] args) {
		InteractionMainFrame frame = new InteractionMainFrame();
	}
}
