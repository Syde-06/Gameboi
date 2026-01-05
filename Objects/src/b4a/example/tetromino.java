package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class tetromino extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.tetromino");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.tetromino.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int _xpos = 0;
public int _ypos = 0;
public int _piecetype = 0;
public int _rotationstate = 0;
public int[][] _shape = null;
public int _colorcode = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private xPos As Int";
_xpos = 0;
 //BA.debugLineNum = 7;BA.debugLine="Private yPos As Int";
_ypos = 0;
 //BA.debugLineNum = 8;BA.debugLine="Private pieceType As Int  ' 1-7 (I, O, T, S, Z, J";
_piecetype = 0;
 //BA.debugLineNum = 9;BA.debugLine="Private rotationState As Int  ' 0-3";
_rotationstate = 0;
 //BA.debugLineNum = 10;BA.debugLine="Private shape(,) As Int";
_shape = new int[(int) (0)][];
{
int d0 = _shape.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_shape[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 11;BA.debugLine="Private colorCode As Int";
_colorcode = 0;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public int  _getcolorcode() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Public Sub GetColorCode As Int";
 //BA.debugLineNum = 60;BA.debugLine="Return colorCode";
if (true) return _colorcode;
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return 0;
}
public int[][]  _getrotatedshape() throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Public Sub GetRotatedShape As Int(,)";
 //BA.debugLineNum = 73;BA.debugLine="Return RotateMatrix90(shape)";
if (true) return _rotatematrix90(_shape);
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return null;
}
public int[][]  _getshape() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Public Sub GetShape As Int(,)";
 //BA.debugLineNum = 56;BA.debugLine="Return shape";
if (true) return _shape;
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return null;
}
public int  _gettypes() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Public Sub GetTypes As Int";
 //BA.debugLineNum = 64;BA.debugLine="Return pieceType";
if (true) return _piecetype;
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return 0;
}
public int  _getx() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Public Sub GetX As Int";
 //BA.debugLineNum = 45;BA.debugLine="Return xPos";
if (true) return _xpos;
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return 0;
}
public int  _gety() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Public Sub GetY As Int";
 //BA.debugLineNum = 49;BA.debugLine="Return yPos";
if (true) return _ypos;
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
int[][] _emptyshape = null;
 //BA.debugLineNum = 14;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 16;BA.debugLine="pieceType = 0";
_piecetype = (int) (0);
 //BA.debugLineNum = 17;BA.debugLine="xPos = 0";
_xpos = (int) (0);
 //BA.debugLineNum = 18;BA.debugLine="yPos = 0";
_ypos = (int) (0);
 //BA.debugLineNum = 19;BA.debugLine="rotationState = 0";
_rotationstate = (int) (0);
 //BA.debugLineNum = 20;BA.debugLine="colorCode = 0";
_colorcode = (int) (0);
 //BA.debugLineNum = 22;BA.debugLine="Dim emptyShape(1, 1) As Int";
_emptyshape = new int[(int) (1)][];
{
int d0 = _emptyshape.length;
int d1 = (int) (1);
for (int i0 = 0;i0 < d0;i0++) {
_emptyshape[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 23;BA.debugLine="emptyShape(0, 0) = -1";
_emptyshape[(int) (0)][(int) (0)] = (int) (-1);
 //BA.debugLineNum = 24;BA.debugLine="shape = emptyShape";
_shape = _emptyshape;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public String  _initialize2(int _ptype,int[][] _initialshape) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Public Sub Initialize2(pType As Int, initialShape(";
 //BA.debugLineNum = 28;BA.debugLine="pieceType = pType";
_piecetype = _ptype;
 //BA.debugLineNum = 29;BA.debugLine="colorCode = pType";
_colorcode = _ptype;
 //BA.debugLineNum = 30;BA.debugLine="shape = initialShape";
_shape = _initialshape;
 //BA.debugLineNum = 31;BA.debugLine="rotationState = 0";
_rotationstate = (int) (0);
 //BA.debugLineNum = 32;BA.debugLine="xPos = 0";
_xpos = (int) (0);
 //BA.debugLineNum = 33;BA.debugLine="yPos = 0";
_ypos = (int) (0);
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public String  _rotate() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Public Sub Rotate";
 //BA.debugLineNum = 68;BA.debugLine="rotationState = (rotationState + 1) Mod 4";
_rotationstate = (int) ((_rotationstate+1)%4);
 //BA.debugLineNum = 69;BA.debugLine="shape = RotateMatrix90(shape)";
_shape = _rotatematrix90(_shape);
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public int[][]  _rotatematrix90(int[][] _matrix) throws Exception{
int[][] _rotated = null;
int _row = 0;
int _col = 0;
 //BA.debugLineNum = 76;BA.debugLine="Private Sub RotateMatrix90(matrix(,) As Int) As In";
 //BA.debugLineNum = 77;BA.debugLine="Dim rotated(4, 4) As Int";
_rotated = new int[(int) (4)][];
{
int d0 = _rotated.length;
int d1 = (int) (4);
for (int i0 = 0;i0 < d0;i0++) {
_rotated[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 80;BA.debugLine="For row = 0 To 3";
{
final int step2 = 1;
final int limit2 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit2 ;_row = _row + step2 ) {
 //BA.debugLineNum = 81;BA.debugLine="For col = 0 To 3";
{
final int step3 = 1;
final int limit3 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit3 ;_col = _col + step3 ) {
 //BA.debugLineNum = 82;BA.debugLine="rotated(row, col) = 0";
_rotated[_row][_col] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 87;BA.debugLine="For row = 0 To 3";
{
final int step7 = 1;
final int limit7 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit7 ;_row = _row + step7 ) {
 //BA.debugLineNum = 88;BA.debugLine="For col = 0 To 3";
{
final int step8 = 1;
final int limit8 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit8 ;_col = _col + step8 ) {
 //BA.debugLineNum = 89;BA.debugLine="Try";
try { //BA.debugLineNum = 90;BA.debugLine="rotated(row, col) = matrix(3 - col";
_rotated[_row][_col] = _matrix[(int) (3-_col)][_row];
 } 
       catch (Exception e12) {
			ba.setLastException(e12); };
 }
};
 }
};
 //BA.debugLineNum = 97;BA.debugLine="Return rotated";
if (true) return _rotated;
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return null;
}
public String  _setposition(int _x,int _y) throws Exception{
 //BA.debugLineNum = 39;BA.debugLine="Public Sub SetPosition(x As Int, y As Int)";
 //BA.debugLineNum = 40;BA.debugLine="xPos = x";
_xpos = _x;
 //BA.debugLineNum = 41;BA.debugLine="yPos = y";
_ypos = _y;
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
