package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class grid extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.grid");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.grid.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int[][] _cells = null;
public int _width = 0;
public int _height = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cells(,) As Int  ' 0 = empty, 1-7 = color";
_cells = new int[(int) (0)][];
{
int d0 = _cells.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_cells[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 7;BA.debugLine="Private width As Int";
_width = 0;
 //BA.debugLineNum = 8;BA.debugLine="Private height As Int";
_height = 0;
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public String  _clear() throws Exception{
int _x = 0;
int _y = 0;
 //BA.debugLineNum = 24;BA.debugLine="Public Sub Clear";
 //BA.debugLineNum = 25;BA.debugLine="For x = 0 To width - 1";
{
final int step1 = 1;
final int limit1 = (int) (_width-1);
_x = (int) (0) ;
for (;_x <= limit1 ;_x = _x + step1 ) {
 //BA.debugLineNum = 26;BA.debugLine="For y = 0 To height - 1";
{
final int step2 = 1;
final int limit2 = (int) (_height-1);
_y = (int) (0) ;
for (;_y <= limit2 ;_y = _y + step2 ) {
 //BA.debugLineNum = 27;BA.debugLine="cells(x, y) = 0";
_cells[_x][_y] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public String  _clearlines(anywheresoftware.b4a.objects.collections.List _lineindices) throws Exception{
int _liney = 0;
 //BA.debugLineNum = 113;BA.debugLine="Public Sub ClearLines(lineIndices As List)";
 //BA.debugLineNum = 114;BA.debugLine="lineIndices.Sort(False)  ' Sort bottom to top";
_lineindices.Sort(__c.False);
 //BA.debugLineNum = 116;BA.debugLine="For Each lineY As Int In lineIndices";
{
final anywheresoftware.b4a.BA.IterableList group2 = _lineindices;
final int groupLen2 = group2.getSize()
;int index2 = 0;
;
for (; index2 < groupLen2;index2++){
_liney = (int)(BA.ObjectToNumber(group2.Get(index2)));
 //BA.debugLineNum = 117;BA.debugLine="RemoveLine(lineY)";
_removeline(_liney);
 }
};
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _findcompletedlines() throws Exception{
anywheresoftware.b4a.objects.collections.List _completedlines = null;
int _y = 0;
 //BA.debugLineNum = 91;BA.debugLine="Public Sub FindCompletedLines As List";
 //BA.debugLineNum = 92;BA.debugLine="Dim completedLines As List";
_completedlines = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 93;BA.debugLine="completedLines.Initialize";
_completedlines.Initialize();
 //BA.debugLineNum = 95;BA.debugLine="For y = 0 To height - 1";
{
final int step3 = 1;
final int limit3 = (int) (_height-1);
_y = (int) (0) ;
for (;_y <= limit3 ;_y = _y + step3 ) {
 //BA.debugLineNum = 96;BA.debugLine="If IsLineComplete(y) Then";
if (_islinecomplete(_y)) { 
 //BA.debugLineNum = 97;BA.debugLine="completedLines.Add(y)";
_completedlines.Add((Object)(_y));
 };
 }
};
 //BA.debugLineNum = 101;BA.debugLine="Return completedLines";
if (true) return _completedlines;
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return null;
}
public int  _getcell(int _x,int _y) throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Public Sub GetCell(x As Int, y As Int) As Int";
 //BA.debugLineNum = 39;BA.debugLine="If IsInBounds(x, y) Then";
if (_isinbounds(_x,_y)) { 
 //BA.debugLineNum = 40;BA.debugLine="Return cells(x, y)";
if (true) return _cells[_x][_y];
 };
 //BA.debugLineNum = 42;BA.debugLine="Return 0";
if (true) return (int) (0);
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return 0;
}
public int[][]  _getcells() throws Exception{
 //BA.debugLineNum = 45;BA.debugLine="Public Sub GetCells As Int(,)";
 //BA.debugLineNum = 46;BA.debugLine="Return cells";
if (true) return _cells;
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return null;
}
public int  _getheight() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Public Sub GetHeight As Int";
 //BA.debugLineNum = 58;BA.debugLine="Return height";
if (true) return _height;
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return 0;
}
public int  _getwidth() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Public Sub GetWidth As Int";
 //BA.debugLineNum = 54;BA.debugLine="Return width";
if (true) return _width;
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,int _gridwidth,int _gridheight) throws Exception{
innerInitialize(_ba);
int[][] _tempcells = null;
 //BA.debugLineNum = 11;BA.debugLine="Public Sub Initialize(gridWidth As Int, gridHeight";
 //BA.debugLineNum = 12;BA.debugLine="width = gridWidth";
_width = _gridwidth;
 //BA.debugLineNum = 13;BA.debugLine="height = gridHeight";
_height = _gridheight;
 //BA.debugLineNum = 15;BA.debugLine="Dim tempCells(width, height) As Int";
_tempcells = new int[_width][];
{
int d0 = _tempcells.length;
int d1 = _height;
for (int i0 = 0;i0 < d0;i0++) {
_tempcells[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 16;BA.debugLine="cells = tempCells";
_cells = _tempcells;
 //BA.debugLineNum = 18;BA.debugLine="Clear";
_clear();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public boolean  _isinbounds(int _x,int _y) throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Public Sub IsInBounds(x As Int, y As Int) As Boole";
 //BA.debugLineNum = 50;BA.debugLine="Return x >= 0 And x < width And y >= 0 And y < he";
if (true) return _x>=0 && _x<_width && _y>=0 && _y<_height;
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return false;
}
public boolean  _islinecomplete(int _y) throws Exception{
int _x = 0;
 //BA.debugLineNum = 104;BA.debugLine="Private Sub IsLineComplete(y As Int) As Boolean";
 //BA.debugLineNum = 105;BA.debugLine="For x = 0 To width - 1";
{
final int step1 = 1;
final int limit1 = (int) (_width-1);
_x = (int) (0) ;
for (;_x <= limit1 ;_x = _x + step1 ) {
 //BA.debugLineNum = 106;BA.debugLine="If cells(x, y) = 0 Then";
if (_cells[_x][_y]==0) { 
 //BA.debugLineNum = 107;BA.debugLine="Return False";
if (true) return __c.False;
 };
 }
};
 //BA.debugLineNum = 110;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return false;
}
public String  _lockpiece(b4a.example.tetromino _piece) throws Exception{
int[][] _shape = null;
int _piecex = 0;
int _piecey = 0;
int _colorcode = 0;
int _row = 0;
int _col = 0;
int _gridx = 0;
int _gridy = 0;
 //BA.debugLineNum = 64;BA.debugLine="Public Sub LockPiece(piece As Tetromino)";
 //BA.debugLineNum = 65;BA.debugLine="Dim shape(,) As Int = piece.GetShape";
_shape = _piece._getshape /*int[][]*/ ();
 //BA.debugLineNum = 66;BA.debugLine="Dim pieceX As Int = piece.GetX";
_piecex = _piece._getx /*int*/ ();
 //BA.debugLineNum = 67;BA.debugLine="Dim pieceY As Int = piece.GetY";
_piecey = _piece._gety /*int*/ ();
 //BA.debugLineNum = 68;BA.debugLine="Dim colorCode As Int = piece.GetColorCode";
_colorcode = _piece._getcolorcode /*int*/ ();
 //BA.debugLineNum = 70;BA.debugLine="For row = 0 To 3";
{
final int step5 = 1;
final int limit5 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit5 ;_row = _row + step5 ) {
 //BA.debugLineNum = 71;BA.debugLine="For col = 0 To 3";
{
final int step6 = 1;
final int limit6 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit6 ;_col = _col + step6 ) {
 //BA.debugLineNum = 72;BA.debugLine="Try";
try { //BA.debugLineNum = 73;BA.debugLine="If shape(col, row) = 1 Then";
if (_shape[_col][_row]==1) { 
 //BA.debugLineNum = 74;BA.debugLine="Dim gridX As Int = pieceX + col";
_gridx = (int) (_piecex+_col);
 //BA.debugLineNum = 75;BA.debugLine="Dim gridY As Int = pieceY + row";
_gridy = (int) (_piecey+_row);
 //BA.debugLineNum = 77;BA.debugLine="If IsInBounds(gridX, gridY) Then";
if (_isinbounds(_gridx,_gridy)) { 
 //BA.debugLineNum = 78;BA.debugLine="cells(gridX, gridY) = colorCode";
_cells[_gridx][_gridy] = _colorcode;
 };
 };
 } 
       catch (Exception e16) {
			ba.setLastException(e16); };
 }
};
 }
};
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public String  _removeline(int _liney) throws Exception{
int _y = 0;
int _x = 0;
 //BA.debugLineNum = 121;BA.debugLine="Private Sub RemoveLine(lineY As Int)";
 //BA.debugLineNum = 123;BA.debugLine="For y = lineY To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_y = _liney ;
for (;_y >= limit1 ;_y = _y + step1 ) {
 //BA.debugLineNum = 124;BA.debugLine="For x = 0 To width - 1";
{
final int step2 = 1;
final int limit2 = (int) (_width-1);
_x = (int) (0) ;
for (;_x <= limit2 ;_x = _x + step2 ) {
 //BA.debugLineNum = 125;BA.debugLine="cells(x, y) = cells(x, y - 1)";
_cells[_x][_y] = _cells[_x][(int) (_y-1)];
 }
};
 }
};
 //BA.debugLineNum = 130;BA.debugLine="For x = 0 To width - 1";
{
final int step6 = 1;
final int limit6 = (int) (_width-1);
_x = (int) (0) ;
for (;_x <= limit6 ;_x = _x + step6 ) {
 //BA.debugLineNum = 131;BA.debugLine="cells(x, 0) = 0";
_cells[_x][(int) (0)] = (int) (0);
 }
};
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public String  _setcell(int _x,int _y,int _value) throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Public Sub SetCell(x As Int, y As Int, value As In";
 //BA.debugLineNum = 33;BA.debugLine="If IsInBounds(x, y) Then";
if (_isinbounds(_x,_y)) { 
 //BA.debugLineNum = 34;BA.debugLine="cells(x, y) = value";
_cells[_x][_y] = _value;
 };
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
