package main.component.interaction.panel.drawer;

import java.util.HashSet;
import java.util.Set;

import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.interaction.GroupPiece;
import main.component.interaction.IPiece;
import main.component.interaction.PieceCollection;

public class PieceDrawer extends InteractionDrawer{
	PieceCollection pieces = new PieceCollection();
	Set<IPiece> selected = new HashSet<IPiece>();
	
	@Override
	public void draw() {
		pieces.draw(g);
		selected.forEach(each -> each.drawSelectedMark(g, -1, -1));
	}

	@Override
	public void setTheme(Theme theme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSettingControl(SettingControl settingControl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateData() {
		// TODO Auto-generated method stub
		
	}
	
	public void setPieceCollection(PieceCollection pieces) {
		this.pieces = pieces;
	}

	public void selectedPieceGroup(Set<IPiece> selected) {
		this.selected = selected;
	}

}
