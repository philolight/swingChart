package main.component.chart.theme;

import java.awt.Color;
import java.awt.Font;

import main.component.common.VerticalPosition;

public class LabelChartTheme {
	public VerticalPosition positionType = VerticalPosition.TOP;
	public Color fontColor = Color.BLACK;
	public Font font = new Font("돋움", Font.PLAIN, 9);
	public String valueStringFormat = "%.2f";
}
