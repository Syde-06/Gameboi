package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class renderer extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.renderer");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.renderer.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvboard = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvnext = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlboard = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlnext = null;
public int _cell_size = 0;
public int _preview_cell_size = 0;
public int _boardoffsetx = 0;
public int _boardoffsety = 0;
public anywheresoftware.b4a.objects.collections.Map _colormap = null;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public int  _calculateghosty(b4a.example.tetromino _piece,b4a.example.grid _grid) throws Exception{
int _testy = 0;
b4a.example.collisionmanager _collisionmgr = null;
 //BA.debugLineNum = 164;BA.debugLine="Private Sub CalculateGhostY(piece As Tetromino, gr";
 //BA.debugLineNum = 165;BA.debugLine="Dim testY As Int = piece.GetY";
_testy = _piece._gety /*int*/ ();
 //BA.debugLineNum = 166;BA.debugLine="Dim collisionMgr As CollisionManager";
_collisionmgr = new b4a.example.collisionmanager();
 //BA.debugLineNum = 167;BA.debugLine="collisionMgr.Initialize(grid)";
_collisionmgr._initialize /*String*/ (ba,_grid);
 //BA.debugLineNum = 169;BA.debugLine="Do While collisionMgr.IsValidPosition(piece.GetX,";
while (_collisionmgr._isvalidposition /*boolean*/ (_piece._getx /*int*/ (),(int) (_testy+1),_piece._getshape /*int[][]*/ ())) {
 //BA.debugLineNum = 170;BA.debugLine="testY = testY + 1";
_testy = (int) (_testy+1);
 }
;
 //BA.debugLineNum = 173;BA.debugLine="Return testY";
if (true) return _testy;
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return 0;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cvBoard As Canvas";
_cvboard = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private cvNext As Canvas";
_cvnext = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private pnlBoard As Panel";
_pnlboard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private pnlNext As Panel";
_pnlnext = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private Const CELL_SIZE As Int = 20dip";
_cell_size = __c.DipToCurrent((int) (20));
 //BA.debugLineNum = 12;BA.debugLine="Private Const PREVIEW_CELL_SIZE As Int = 10dip";
_preview_cell_size = __c.DipToCurrent((int) (10));
 //BA.debugLineNum = 14;BA.debugLine="Private boardOffsetX As Int = 20dip";
_boardoffsetx = __c.DipToCurrent((int) (20));
 //BA.debugLineNum = 15;BA.debugLine="Private boardOffsetY As Int = 10%y";
_boardoffsety = __c.PerYToCurrent((float) (10),ba);
 //BA.debugLineNum = 17;BA.debugLine="Private colorMap As Map";
_colormap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public String  _drawactivepiece(b4a.example.gamemanager _gamemgr) throws Exception{
b4a.example.tetromino _piece = null;
int[][] _shape = null;
int _piecex = 0;
float _piecey = 0f;
int _colorcode = 0;
 //BA.debugLineNum = 134;BA.debugLine="Private Sub DrawActivePiece(gameMgr As GameManager";
 //BA.debugLineNum = 135;BA.debugLine="Try";
try { //BA.debugLineNum = 136;BA.debugLine="Dim piece As Tetromino = gameMgr.GetActivePiece";
_piece = _gamemgr._getactivepiece /*b4a.example.tetromino*/ ();
 //BA.debugLineNum = 137;BA.debugLine="Dim shape(,) As Int = piece.GetShape";
_shape = _piece._getshape /*int[][]*/ ();
 //BA.debugLineNum = 138;BA.debugLine="If shape(0, 0) = -1 Then Return";
if (_shape[(int) (0)][(int) (0)]==-1) { 
if (true) return "";};
 //BA.debugLineNum = 140;BA.debugLine="Dim pieceX As Int = piece.GetX";
_piecex = _piece._getx /*int*/ ();
 //BA.debugLineNum = 142;BA.debugLine="Dim pieceY As Float = gameMgr.GetPieceAnimY";
_piecey = _gamemgr._getpieceanimy /*float*/ ();
 //BA.debugLineNum = 143;BA.debugLine="Dim colorCode As Int = piece.GetColorCode";
_colorcode = _piece._getcolorcode /*int*/ ();
 //BA.debugLineNum = 145;BA.debugLine="DrawPieceShapeSmooth(shape, pieceX, pieceY, colo";
_drawpieceshapesmooth(_shape,_piecex,_piecey,_colorcode,(float) (1.0));
 } 
       catch (Exception e10) {
			ba.setLastException(e10); };
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public String  _drawblock(anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas,int _x,int _y,int _size,int _colorcode,float _alpha) throws Exception{
int _blockcolor = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
int _highlightcolor = 0;
 //BA.debugLineNum = 227;BA.debugLine="Private Sub DrawBlock(canvas As Canvas, x As Int,";
 //BA.debugLineNum = 228;BA.debugLine="Dim blockColor As Int = GetColorWithAlpha(colorCo";
_blockcolor = _getcolorwithalpha(_colorcode,_alpha);
 //BA.debugLineNum = 231;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 232;BA.debugLine="rect.Initialize(x + 2, y + 2, x + size - 2, y + s";
_rect.Initialize((int) (_x+2),(int) (_y+2),(int) (_x+_size-2),(int) (_y+_size-2));
 //BA.debugLineNum = 233;BA.debugLine="canvas.DrawRect(rect, blockColor, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_blockcolor,__c.True,(float) (0));
 //BA.debugLineNum = 236;BA.debugLine="If alpha > 0.5 Then";
if (_alpha>0.5) { 
 //BA.debugLineNum = 237;BA.debugLine="Dim highlightColor As Int = Colors.ARGB(150 * al";
_highlightcolor = __c.Colors.ARGB((int) (150*_alpha),(int) (255),(int) (255),(int) (255));
 //BA.debugLineNum = 238;BA.debugLine="canvas.DrawLine(x + 2, y + 2, x + size - 2, y +";
_canvas.DrawLine((float) (_x+2),(float) (_y+2),(float) (_x+_size-2),(float) (_y+2),_highlightcolor,(float) (2));
 //BA.debugLineNum = 239;BA.debugLine="canvas.DrawLine(x + 2, y + 2, x + 2, y + size -";
_canvas.DrawLine((float) (_x+2),(float) (_y+2),(float) (_x+2),(float) (_y+_size-2),_highlightcolor,(float) (2));
 };
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public String  _drawblockonboard(int _gridx,int _gridy,int _colorcode,float _alpha) throws Exception{
int _x = 0;
int _y = 0;
 //BA.debugLineNum = 215;BA.debugLine="Private Sub DrawBlockOnBoard(gridX As Int, gridY A";
 //BA.debugLineNum = 216;BA.debugLine="Dim x As Int = boardOffsetX + gridX * CELL_SIZE";
_x = (int) (_boardoffsetx+_gridx*_cell_size);
 //BA.debugLineNum = 217;BA.debugLine="Dim y As Int = boardOffsetY + gridY * CELL_SIZE";
_y = (int) (_boardoffsety+_gridy*_cell_size);
 //BA.debugLineNum = 218;BA.debugLine="DrawBlock(cvBoard, x, y, CELL_SIZE, colorCode, al";
_drawblock(_cvboard,_x,_y,_cell_size,_colorcode,_alpha);
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public String  _drawblockonboardsmooth(int _gridx,float _gridy,int _colorcode,float _alpha) throws Exception{
int _x = 0;
int _y = 0;
 //BA.debugLineNum = 221;BA.debugLine="Private Sub DrawBlockOnBoardSmooth(gridX As Int, g";
 //BA.debugLineNum = 222;BA.debugLine="Dim x As Int = boardOffsetX + gridX * CELL_SIZE";
_x = (int) (_boardoffsetx+_gridx*_cell_size);
 //BA.debugLineNum = 223;BA.debugLine="Dim y As Int = boardOffsetY + (gridY * CELL_SIZE)";
_y = (int) (_boardoffsety+(_gridy*_cell_size));
 //BA.debugLineNum = 224;BA.debugLine="DrawBlock(cvBoard, x, y, CELL_SIZE, colorCode, al";
_drawblock(_cvboard,_x,_y,_cell_size,_colorcode,_alpha);
 //BA.debugLineNum = 225;BA.debugLine="End Sub";
return "";
}
public String  _drawcombodisplay(int _combocount,float _multiplier) throws Exception{
int _textx = 0;
int _texty = 0;
int _pulsesize = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _bgrect = null;
int _bordercolor = 0;
int _i = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _barrect = null;
 //BA.debugLineNum = 263;BA.debugLine="Private Sub DrawComboDisplay(comboCount As Int, mu";
 //BA.debugLineNum = 265;BA.debugLine="Dim textX As Int = boardOffsetX + (10 * CELL_SIZE";
_textx = (int) (_boardoffsetx+(10*_cell_size)-80);
 //BA.debugLineNum = 266;BA.debugLine="Dim textY As Int = boardOffsetY + 20";
_texty = (int) (_boardoffsety+20);
 //BA.debugLineNum = 269;BA.debugLine="Dim pulseSize As Int = 5 * (comboCount Mod 3)";
_pulsesize = (int) (5*(_combocount%3));
 //BA.debugLineNum = 270;BA.debugLine="Dim bgRect As Rect";
_bgrect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 271;BA.debugLine="bgRect.Initialize(textX - 10 - pulseSize, textY -";
_bgrect.Initialize((int) (_textx-10-_pulsesize),(int) (_texty-5-_pulsesize),(int) (_textx+100+_pulsesize),(int) (_texty+45+_pulsesize));
 //BA.debugLineNum = 275;BA.debugLine="cvBoard.DrawRect(bgRect, Colors.ARGB(180, 50, 50,";
_cvboard.DrawRect((android.graphics.Rect)(_bgrect.getObject()),__c.Colors.ARGB((int) (180),(int) (50),(int) (50),(int) (50)),__c.True,(float) (0));
 //BA.debugLineNum = 278;BA.debugLine="Dim borderColor As Int";
_bordercolor = 0;
 //BA.debugLineNum = 279;BA.debugLine="If comboCount >= 4 Then";
if (_combocount>=4) { 
 //BA.debugLineNum = 280;BA.debugLine="borderColor = Colors.ARGB(255, 255, 0, 0) ' Red";
_bordercolor = __c.Colors.ARGB((int) (255),(int) (255),(int) (0),(int) (0));
 }else if(_combocount>=3) { 
 //BA.debugLineNum = 282;BA.debugLine="borderColor = Colors.ARGB(255, 255, 165, 0) ' Or";
_bordercolor = __c.Colors.ARGB((int) (255),(int) (255),(int) (165),(int) (0));
 }else {
 //BA.debugLineNum = 284;BA.debugLine="borderColor = Colors.ARGB(255, 255, 200, 0) ' Ye";
_bordercolor = __c.Colors.ARGB((int) (255),(int) (255),(int) (200),(int) (0));
 };
 //BA.debugLineNum = 286;BA.debugLine="cvBoard.DrawRect(bgRect, borderColor, False, 3)";
_cvboard.DrawRect((android.graphics.Rect)(_bgrect.getObject()),_bordercolor,__c.False,(float) (3));
 //BA.debugLineNum = 289;BA.debugLine="For i = 0 To Min(comboCount - 1, 5)";
{
final int step16 = 1;
final int limit16 = (int) (__c.Min(_combocount-1,5));
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 290;BA.debugLine="Dim barRect As Rect";
_barrect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 291;BA.debugLine="barRect.Initialize(textX + 5 + (i * 15), textY +";
_barrect.Initialize((int) (_textx+5+(_i*15)),(int) (_texty+35),(int) (_textx+15+(_i*15)),(int) (_texty+40));
 //BA.debugLineNum = 293;BA.debugLine="cvBoard.DrawRect(barRect, borderColor, True, 0)";
_cvboard.DrawRect((android.graphics.Rect)(_barrect.getObject()),_bordercolor,__c.True,(float) (0));
 }
};
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public String  _drawgameboard(b4a.example.grid _grid,b4a.example.gamemanager _gamemgr) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Public Sub DrawGameBoard(grid As Grid, gameMgr As";
 //BA.debugLineNum = 45;BA.debugLine="cvBoard.DrawColor(Colors.RGB(40,40,40))";
_cvboard.DrawColor(__c.Colors.RGB((int) (40),(int) (40),(int) (40)));
 //BA.debugLineNum = 47;BA.debugLine="DrawGrid(grid)";
_drawgrid(_grid);
 //BA.debugLineNum = 48;BA.debugLine="DrawLockedBlocks(grid, gameMgr)";
_drawlockedblocks(_grid,_gamemgr);
 //BA.debugLineNum = 49;BA.debugLine="DrawGhostPiece(gameMgr.GetActivePiece, grid)";
_drawghostpiece(_gamemgr._getactivepiece /*b4a.example.tetromino*/ (),_grid);
 //BA.debugLineNum = 50;BA.debugLine="DrawActivePiece(gameMgr)";
_drawactivepiece(_gamemgr);
 //BA.debugLineNum = 53;BA.debugLine="If gameMgr.GetComboCount > 1 Then";
if (_gamemgr._getcombocount /*int*/ ()>1) { 
 //BA.debugLineNum = 54;BA.debugLine="DrawComboDisplay(gameMgr.GetComboCount, gameMgr.";
_drawcombodisplay(_gamemgr._getcombocount /*int*/ (),_gamemgr._getcombomultiplier /*float*/ ());
 };
 //BA.debugLineNum = 57;BA.debugLine="pnlBoard.Invalidate";
_pnlboard.Invalidate();
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public String  _drawghostpiece(b4a.example.tetromino _piece,b4a.example.grid _grid) throws Exception{
int[][] _shape = null;
int _piecex = 0;
int _ghosty = 0;
int _colorcode = 0;
 //BA.debugLineNum = 150;BA.debugLine="Private Sub DrawGhostPiece(piece As Tetromino, gri";
 //BA.debugLineNum = 151;BA.debugLine="Try";
try { //BA.debugLineNum = 152;BA.debugLine="Dim shape(,) As Int = piece.GetShape";
_shape = _piece._getshape /*int[][]*/ ();
 //BA.debugLineNum = 153;BA.debugLine="If shape(0, 0) = -1 Then Return";
if (_shape[(int) (0)][(int) (0)]==-1) { 
if (true) return "";};
 //BA.debugLineNum = 155;BA.debugLine="Dim pieceX As Int = piece.GetX";
_piecex = _piece._getx /*int*/ ();
 //BA.debugLineNum = 156;BA.debugLine="Dim ghostY As Int = CalculateGhostY(piece, grid)";
_ghosty = _calculateghosty(_piece,_grid);
 //BA.debugLineNum = 157;BA.debugLine="Dim colorCode As Int = piece.GetColorCode";
_colorcode = _piece._getcolorcode /*int*/ ();
 //BA.debugLineNum = 159;BA.debugLine="DrawPieceShape(shape, pieceX, ghostY, colorCode,";
_drawpieceshape(_shape,_piecex,_ghosty,_colorcode,(float) (0.25));
 } 
       catch (Exception e9) {
			ba.setLastException(e9); };
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return "";
}
public String  _drawgrid(b4a.example.grid _grid) throws Exception{
int _gridcolor = 0;
int _row = 0;
int _col = 0;
int _x = 0;
int _y = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 94;BA.debugLine="Private Sub DrawGrid(grid As Grid)";
 //BA.debugLineNum = 95;BA.debugLine="Dim gridColor As Int = Colors.RGB(255, 255, 255)";
_gridcolor = __c.Colors.RGB((int) (255),(int) (255),(int) (255));
 //BA.debugLineNum = 97;BA.debugLine="For row = 0 To grid.GetHeight - 1";
{
final int step2 = 1;
final int limit2 = (int) (_grid._getheight /*int*/ ()-1);
_row = (int) (0) ;
for (;_row <= limit2 ;_row = _row + step2 ) {
 //BA.debugLineNum = 98;BA.debugLine="For col = 0 To grid.GetWidth - 1";
{
final int step3 = 1;
final int limit3 = (int) (_grid._getwidth /*int*/ ()-1);
_col = (int) (0) ;
for (;_col <= limit3 ;_col = _col + step3 ) {
 //BA.debugLineNum = 99;BA.debugLine="Dim x As Int = boardOffsetX + col * CELL_SIZE";
_x = (int) (_boardoffsetx+_col*_cell_size);
 //BA.debugLineNum = 100;BA.debugLine="Dim y As Int = boardOffsetY + row * CELL_SIZE";
_y = (int) (_boardoffsety+_row*_cell_size);
 //BA.debugLineNum = 102;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 103;BA.debugLine="rect.Initialize(x, y, x + CELL_SIZE, y + CELL_S";
_rect.Initialize(_x,_y,(int) (_x+_cell_size),(int) (_y+_cell_size));
 //BA.debugLineNum = 104;BA.debugLine="cvBoard.DrawRect(rect, gridColor, False, 1)";
_cvboard.DrawRect((android.graphics.Rect)(_rect.getObject()),_gridcolor,__c.False,(float) (1));
 }
};
 }
};
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public String  _drawlockedblocks(b4a.example.grid _grid,b4a.example.gamemanager _gamemgr) throws Exception{
int[][] _cells = null;
int _row = 0;
int _col = 0;
float _alpha = 0f;
anywheresoftware.b4a.objects.collections.List _clearinglines = null;
int _liney = 0;
float _progress = 0f;
 //BA.debugLineNum = 109;BA.debugLine="Private Sub DrawLockedBlocks(grid As Grid, gameMgr";
 //BA.debugLineNum = 110;BA.debugLine="Dim cells(,) As Int = grid.GetCells";
_cells = _grid._getcells /*int[][]*/ ();
 //BA.debugLineNum = 112;BA.debugLine="For row = 0 To grid.GetHeight - 1";
{
final int step2 = 1;
final int limit2 = (int) (_grid._getheight /*int*/ ()-1);
_row = (int) (0) ;
for (;_row <= limit2 ;_row = _row + step2 ) {
 //BA.debugLineNum = 113;BA.debugLine="For col = 0 To grid.GetWidth - 1";
{
final int step3 = 1;
final int limit3 = (int) (_grid._getwidth /*int*/ ()-1);
_col = (int) (0) ;
for (;_col <= limit3 ;_col = _col + step3 ) {
 //BA.debugLineNum = 114;BA.debugLine="If cells(col, row) > 0 Then";
if (_cells[_col][_row]>0) { 
 //BA.debugLineNum = 115;BA.debugLine="Dim alpha As Float = 1.0";
_alpha = (float) (1.0);
 //BA.debugLineNum = 118;BA.debugLine="If gameMgr.IsClearingLines Then";
if (_gamemgr._isclearinglines /*boolean*/ ()) { 
 //BA.debugLineNum = 119;BA.debugLine="Dim clearingLines As List = gameMgr.GetCleari";
_clearinglines = new anywheresoftware.b4a.objects.collections.List();
_clearinglines = _gamemgr._getclearinglines /*anywheresoftware.b4a.objects.collections.List*/ ();
 //BA.debugLineNum = 120;BA.debugLine="For Each lineY As Int In clearingLines";
{
final anywheresoftware.b4a.BA.IterableList group8 = _clearinglines;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_liney = (int)(BA.ObjectToNumber(group8.Get(index8)));
 //BA.debugLineNum = 121;BA.debugLine="If row = lineY Then";
if (_row==_liney) { 
 //BA.debugLineNum = 122;BA.debugLine="Dim progress As Float = gameMgr.GetClearAni";
_progress = _gamemgr._getclearanimprogress /*float*/ ();
 //BA.debugLineNum = 123;BA.debugLine="alpha = 1.0 - (Sin(progress * 3.14159) * 0.";
_alpha = (float) (1.0-(__c.Sin(_progress*3.14159)*0.5));
 };
 }
};
 };
 //BA.debugLineNum = 128;BA.debugLine="DrawBlockOnBoard(col, row, cells(col, row), al";
_drawblockonboard(_col,_row,_cells[_col][_row],_alpha);
 };
 }
};
 }
};
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public String  _drawnextpiece(b4a.example.tetromino _piece) throws Exception{
int[][] _shape = null;
int _colorcode = 0;
int _offsetx = 0;
int _offsety = 0;
int _row = 0;
int _col = 0;
int _x = 0;
int _y = 0;
 //BA.debugLineNum = 60;BA.debugLine="Public Sub DrawNextPiece(piece As Tetromino)";
 //BA.debugLineNum = 61;BA.debugLine="cvNext.DrawColor(Colors.RGB(40, 40, 40))";
_cvnext.DrawColor(__c.Colors.RGB((int) (40),(int) (40),(int) (40)));
 //BA.debugLineNum = 63;BA.debugLine="Dim shape(,) As Int = piece.GetShape";
_shape = _piece._getshape /*int[][]*/ ();
 //BA.debugLineNum = 64;BA.debugLine="Dim colorCode As Int = piece.GetColorCode";
_colorcode = _piece._getcolorcode /*int*/ ();
 //BA.debugLineNum = 66;BA.debugLine="Try";
try { //BA.debugLineNum = 67;BA.debugLine="If shape(0, 0) = -1 Then Return";
if (_shape[(int) (0)][(int) (0)]==-1) { 
if (true) return "";};
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 69;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 72;BA.debugLine="Dim offsetX As Int = (pnlNext.Width - 4 * PREVIEW";
_offsetx = (int) ((_pnlnext.getWidth()-4*_preview_cell_size)/(double)2);
 //BA.debugLineNum = 73;BA.debugLine="Dim offsetY As Int = (pnlNext.Height - 4 * PREVIE";
_offsety = (int) ((_pnlnext.getHeight()-4*_preview_cell_size)/(double)2);
 //BA.debugLineNum = 75;BA.debugLine="For row = 0 To 3";
{
final int step11 = 1;
final int limit11 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit11 ;_row = _row + step11 ) {
 //BA.debugLineNum = 76;BA.debugLine="For col = 0 To 3";
{
final int step12 = 1;
final int limit12 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit12 ;_col = _col + step12 ) {
 //BA.debugLineNum = 77;BA.debugLine="Try";
try { //BA.debugLineNum = 78;BA.debugLine="If shape(col, row) = 1 Then";
if (_shape[_col][_row]==1) { 
 //BA.debugLineNum = 79;BA.debugLine="Dim x As Int = offsetX + col * PREVIEW_CELL_S";
_x = (int) (_offsetx+_col*_preview_cell_size);
 //BA.debugLineNum = 80;BA.debugLine="Dim y As Int = offsetY + row * PREVIEW_CELL_S";
_y = (int) (_offsety+_row*_preview_cell_size);
 //BA.debugLineNum = 81;BA.debugLine="DrawBlock(cvNext, x, y, PREVIEW_CELL_SIZE, co";
_drawblock(_cvnext,_x,_y,_preview_cell_size,_colorcode,(float) (1.0));
 };
 } 
       catch (Exception e20) {
			ba.setLastException(e20); };
 }
};
 }
};
 //BA.debugLineNum = 88;BA.debugLine="pnlNext.Invalidate";
_pnlnext.Invalidate();
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public String  _drawpieceshape(int[][] _shape,int _posx,int _posy,int _colorcode,float _alpha) throws Exception{
int _row = 0;
int _col = 0;
int _gridx = 0;
int _gridy = 0;
 //BA.debugLineNum = 179;BA.debugLine="Private Sub DrawPieceShape(shape(,) As Int, posX A";
 //BA.debugLineNum = 180;BA.debugLine="For row = 0 To 3";
{
final int step1 = 1;
final int limit1 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit1 ;_row = _row + step1 ) {
 //BA.debugLineNum = 181;BA.debugLine="For col = 0 To 3";
{
final int step2 = 1;
final int limit2 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit2 ;_col = _col + step2 ) {
 //BA.debugLineNum = 182;BA.debugLine="Try";
try { //BA.debugLineNum = 183;BA.debugLine="If shape(col, row) = 1 Then";
if (_shape[_col][_row]==1) { 
 //BA.debugLineNum = 184;BA.debugLine="Dim gridX As Int = posX + col";
_gridx = (int) (_posx+_col);
 //BA.debugLineNum = 185;BA.debugLine="Dim gridY As Int = posY + row";
_gridy = (int) (_posy+_row);
 //BA.debugLineNum = 187;BA.debugLine="If gridY >= 0 Then";
if (_gridy>=0) { 
 //BA.debugLineNum = 188;BA.debugLine="DrawBlockOnBoard(gridX, gridY, colorCode, al";
_drawblockonboard(_gridx,_gridy,_colorcode,_alpha);
 };
 };
 } 
       catch (Exception e12) {
			ba.setLastException(e12); };
 }
};
 }
};
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public String  _drawpieceshapesmooth(int[][] _shape,int _posx,float _posy,int _colorcode,float _alpha) throws Exception{
int _row = 0;
int _col = 0;
int _gridx = 0;
float _gridy = 0f;
 //BA.debugLineNum = 197;BA.debugLine="Private Sub DrawPieceShapeSmooth(shape(,) As Int,";
 //BA.debugLineNum = 198;BA.debugLine="For row = 0 To 3";
{
final int step1 = 1;
final int limit1 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit1 ;_row = _row + step1 ) {
 //BA.debugLineNum = 199;BA.debugLine="For col = 0 To 3";
{
final int step2 = 1;
final int limit2 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit2 ;_col = _col + step2 ) {
 //BA.debugLineNum = 200;BA.debugLine="Try";
try { //BA.debugLineNum = 201;BA.debugLine="If shape(col, row) = 1 Then";
if (_shape[_col][_row]==1) { 
 //BA.debugLineNum = 202;BA.debugLine="Dim gridX As Int = posX + col";
_gridx = (int) (_posx+_col);
 //BA.debugLineNum = 203;BA.debugLine="Dim gridY As Float = posY + row";
_gridy = (float) (_posy+_row);
 //BA.debugLineNum = 205;BA.debugLine="If gridY >= 0 Then";
if (_gridy>=0) { 
 //BA.debugLineNum = 206;BA.debugLine="DrawBlockOnBoardSmooth(gridX, gridY, colorCo";
_drawblockonboardsmooth(_gridx,_gridy,_colorcode,_alpha);
 };
 };
 } 
       catch (Exception e12) {
			ba.setLastException(e12); };
 }
};
 }
};
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public int  _getcolorwithalpha(int _colorcode,float _alpha) throws Exception{
int _basecolor = 0;
int _a = 0;
int _r = 0;
int _g = 0;
int _b = 0;
 //BA.debugLineNum = 243;BA.debugLine="Private Sub GetColorWithAlpha(colorCode As Int, al";
 //BA.debugLineNum = 244;BA.debugLine="Dim baseColor As Int";
_basecolor = 0;
 //BA.debugLineNum = 246;BA.debugLine="If colorMap.ContainsKey(colorCode) Then";
if (_colormap.ContainsKey((Object)(_colorcode))) { 
 //BA.debugLineNum = 247;BA.debugLine="baseColor = colorMap.Get(colorCode)";
_basecolor = (int)(BA.ObjectToNumber(_colormap.Get((Object)(_colorcode))));
 }else {
 //BA.debugLineNum = 249;BA.debugLine="baseColor = Colors.Gray";
_basecolor = __c.Colors.Gray;
 };
 //BA.debugLineNum = 252;BA.debugLine="Dim a As Int = 255 * alpha";
_a = (int) (255*_alpha);
 //BA.debugLineNum = 253;BA.debugLine="Dim r As Int = Bit.And(Bit.UnsignedShiftRight(bas";
_r = __c.Bit.And(__c.Bit.UnsignedShiftRight(_basecolor,(int) (16)),((int)0xff));
 //BA.debugLineNum = 254;BA.debugLine="Dim g As Int = Bit.And(Bit.UnsignedShiftRight(bas";
_g = __c.Bit.And(__c.Bit.UnsignedShiftRight(_basecolor,(int) (8)),((int)0xff));
 //BA.debugLineNum = 255;BA.debugLine="Dim b As Int = Bit.And(baseColor, 0xFF)";
_b = __c.Bit.And(_basecolor,((int)0xff));
 //BA.debugLineNum = 257;BA.debugLine="Return Colors.ARGB(a, r, g, b)";
if (true) return __c.Colors.ARGB(_a,_r,_g,_b);
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _gameboardpanel,anywheresoftware.b4a.objects.PanelWrapper _nextpiecepanel) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 20;BA.debugLine="Public Sub Initialize(gameBoardPanel As Panel, nex";
 //BA.debugLineNum = 21;BA.debugLine="pnlBoard = gameBoardPanel";
_pnlboard = _gameboardpanel;
 //BA.debugLineNum = 22;BA.debugLine="pnlNext = nextPiecePanel";
_pnlnext = _nextpiecepanel;
 //BA.debugLineNum = 24;BA.debugLine="cvBoard.Initialize(pnlBoard)";
_cvboard.Initialize((android.view.View)(_pnlboard.getObject()));
 //BA.debugLineNum = 25;BA.debugLine="cvNext.Initialize(pnlNext)";
_cvnext.Initialize((android.view.View)(_pnlnext.getObject()));
 //BA.debugLineNum = 27;BA.debugLine="InitializeColorMap";
_initializecolormap();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public String  _initializecolormap() throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Private Sub InitializeColorMap";
 //BA.debugLineNum = 31;BA.debugLine="colorMap.Initialize";
_colormap.Initialize();
 //BA.debugLineNum = 32;BA.debugLine="colorMap.Put(1, Colors.RGB(0, 240, 240))    ' Cya";
_colormap.Put((Object)(1),(Object)(__c.Colors.RGB((int) (0),(int) (240),(int) (240))));
 //BA.debugLineNum = 33;BA.debugLine="colorMap.Put(2, Colors.RGB(240, 240, 0))    ' Yel";
_colormap.Put((Object)(2),(Object)(__c.Colors.RGB((int) (240),(int) (240),(int) (0))));
 //BA.debugLineNum = 34;BA.debugLine="colorMap.Put(3, Colors.RGB(160, 0, 240))    ' Pur";
_colormap.Put((Object)(3),(Object)(__c.Colors.RGB((int) (160),(int) (0),(int) (240))));
 //BA.debugLineNum = 35;BA.debugLine="colorMap.Put(4, Colors.RGB(0, 240, 0))      ' Gre";
_colormap.Put((Object)(4),(Object)(__c.Colors.RGB((int) (0),(int) (240),(int) (0))));
 //BA.debugLineNum = 36;BA.debugLine="colorMap.Put(5, Colors.RGB(240, 0, 0))      ' Red";
_colormap.Put((Object)(5),(Object)(__c.Colors.RGB((int) (240),(int) (0),(int) (0))));
 //BA.debugLineNum = 37;BA.debugLine="colorMap.Put(6, Colors.RGB(0, 0, 240))      ' Blu";
_colormap.Put((Object)(6),(Object)(__c.Colors.RGB((int) (0),(int) (0),(int) (240))));
 //BA.debugLineNum = 38;BA.debugLine="colorMap.Put(7, Colors.RGB(240, 160, 0))    ' Ora";
_colormap.Put((Object)(7),(Object)(__c.Colors.RGB((int) (240),(int) (160),(int) (0))));
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
